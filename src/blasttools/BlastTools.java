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
package blasttools;
import java.io.File;
/**
 * Entry point for general blast tools: read input and transform to output
 * @author schmidda
 */
public class BlastTools {
    public static Processor processor;
    public static File src;
    /**
     * Read commandline arguments. Set src file and processor type
     * @param args the args passed into BlastTools
     * @return true if they were OK
     * @throws Exception 
     */
    static boolean readArgs( String[] args) throws Exception
    {
        boolean sane = true;
        for ( int i=0;i<args.length;i++ ){
            if ( args[i].length()>1 && args[i].startsWith("-")) {
                char opt = args[i].charAt(1);
                switch ( opt ) {
                    case 't':   // type of processor
                        processor = Processor.getProcessor(args[i+1]);
                        break;
                    default:
                        sane = false;
                        break;
                }
            }
            if ( i == args.length-1 ) {
                src = new File(args[i]);
                if ( !src.exists() )
                    throw new Exception(args[i]+" not found");
            }
            if ( processor == null )
                sane = false;
        }
        return src != null && sane;
    }
    /**
     * Main Entry point
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            if ( readArgs(args) ) {
                processor.readConfig();
                processor.read(src);
                processor.write(src.getName());
            }
            else
                System.out.println("Usage: java BlastTools -t (blastn|blastp) <input-file>\n"
                +"    blastn: compute blastn (nucleotide) summary\n"
                +"    blastp: compute blastp (protein) summary");
        }
        catch ( Exception e )
        {
            e.printStackTrace(System.out);
        }
    }
}
