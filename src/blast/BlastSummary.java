/* This file is part of BlastTools.
 *
 *  BlastTools is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  BlastTools is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with BlastTools.  If not, see <http://www.gnu.org/licenses/>.
 *  (c) 2019 Queensland Institute of Technology
 */
package blast;

import blasttools.Sacc;
import blasttools.Processor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author schmidda
 */
public class BlastSummary extends Processor {
    String type;// N = nucleotide, P=protein
    String configMaster;
    static String[] ncols ={"sacc", "sstart", "send","stitle","pident","sstrand","slen"};
    static String[] pcols ={"sacc", "sstart", "send","stitle","pident","slen"};
    static String configPath = "/blasttools/config.json";
    static String outputHeading = "sacc\tnaccs\tlength\tslen\tcov\tav-pident\tstitle\tqseqids\n";
    /** map to store results */
    HashMap<String,Sacc> map;
    /**
     * Create a new BlastN processor
     */
    public BlastSummary(String type) {
        this.type = type;
        if ( type.equals("N") )
            configMaster = "blastn";
        else
            configMaster = "blastp";
        map = new HashMap<>();
    }
    /**
     * Read the config and customise it for us
     */
    public void readConfig() throws Exception {
        String[] cols = (type.equals("N"))?ncols:pcols;
        config = loadConfig(configMaster,configPath,cols);
        if ( config == null ) 
            throw new Exception("invalid config");
    }
    /**
     * Read in the source file and build a map of its contents
     * @param src the source file, existing
     * @throws Exception 
     */
    protected void read( File src ) throws Exception {
        BufferedReader r = new BufferedReader(new FileReader(src));
        String line = r.readLine();
        int sacc = intVal("sacc",config);
        int sstart = intVal("sstart",config);
        int send = intVal("send",config);
        int slen = intVal("slen",config);
        int pident = intVal("pident",config);
        int sstrand = (type.equals("N"))?intVal("sstrand",config):0;
        int stitle = intVal("stitle",config);
        int qseqid = intVal("qseqid",config);
        while (line != null) {
            String[] cols = line.split("\t");
            if ( cols.length > 11 
                    && cols[sacc].length()>0 
                    && isInt(cols[sstart])
                    && isInt(cols[send])
                    && isInt(cols[slen])
                    && isDouble(cols[pident])
                    && cols[qseqid].length()>0
                    && (!type.equals("N")||cols[sstrand].length()>0)) {
                int sStart = Integer.parseInt(cols[sstart]);
                int sEnd = Integer.parseInt(cols[send]);
                int sLen = Integer.parseInt(cols[slen]);
                if ( type.equals("N") && cols[sstrand].equals("minus") ) {
                    int temp = sEnd;
                    sEnd = sStart;
                    sStart = temp;
                }   
                String key = cols[sacc].trim();
                String qSeqId = cols[qseqid].trim();
                String sTitle = cols[stitle].trim();
                double pIdent = Double.parseDouble(cols[pident]);
                if ( map.containsKey(key) ) {
                    Sacc mySacc = map.get(key);
                    mySacc.addRange(sStart, sEnd, qSeqId, pIdent);
                }
                else {
                    Sacc mySacc = new Sacc(sTitle,sLen);
                    mySacc.addRange(sStart,sEnd,qSeqId,pIdent);
                    map.put(key, mySacc);
                }
            }
            line = r.readLine();
        }
    }
    /**
     * Write out the input data in its new format
     * @param srcName the original src file name
     * @throws Exception 
     */
    protected void write( String srcName ) throws Exception {
        Set<String> keys = map.keySet();
        Iterator<String> iter = keys.iterator();
        String newFileName = "summary_"+shortFileName(srcName)+".txt";
        File dest = new File(newFileName);
        if ( dest.exists() && !dest.delete() )
            throw new Exception("Failed to create "+newFileName);
        dest.createNewFile();
        FileOutputStream fos = new FileOutputStream(dest);
        fos.write(outputHeading.getBytes());
        while ( iter.hasNext() ) {
            String key = iter.next();
            fos.write(key.getBytes());
            fos.write('\t');
            Sacc sacc = map.get(key);
            fos.write(sacc.toString().getBytes());
            fos.write('\n');
        }
    }
}
