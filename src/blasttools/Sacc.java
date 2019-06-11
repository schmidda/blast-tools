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
import java.util.ArrayList;
import java.text.DecimalFormat;
/**
 * Represent an accession number
 * @author schmidda
 */
public class Sacc {
    ArrayList<Range> ranges;
    String title;
    int sLen;
    public Sacc( String title, int sLen )
    {
        this.title = title;
        this.sLen = sLen;
    }
    public void addRange( int start, int end, String qSeqId, double pIdent )
    {
        if ( ranges == null ) {
            ranges = new ArrayList<Range>();
        }
        for ( int i=0;i<ranges.size();i++ ) {
            Range r = ranges.get(i);
            if ( r.start == start && r.end == end && r.qSeqId.equals(qSeqId) )
                return;
        }
        ranges.add( new Range(start, end, qSeqId, pIdent ));
    }
    /**
     * Not really needed: Do an insertion sort based on start range
     */
    void sortRanges() {
        for(int i = 1; i < ranges.size(); i++){
            Range r = ranges.get(i);
            int j = i - 1;
            while(j >= 0 && ranges.get(j).start > r.start){
              ranges.set(j + 1, ranges.get(j));
              j = j - 1;
            }
            ranges.set(j + 1, r);
        }
    }
    String sumRanges() {
        int length = 0;
        StringBuilder sb = new StringBuilder();
        sortRanges();
        for ( int i=0;i<ranges.size();i++ ) {
            Range r = ranges.get(i);
            int overlap = 0;
            for ( int j=i-1;j>=0;j-- ) {
                overlap += r.getOverlap(ranges.get(j));
            }
            length += r.getLength()-overlap;
        }
        sb.append(ranges.size());
        sb.append("\t");
        sb.append(length);
        sb.append("\t");
        // add sLen
        sb.append(sLen);
        sb.append("\t");
        // add coverage = length/sLen
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        double coverage = (double)length/(double)sLen;
        sb.append(df.format(coverage));
        sb.append("\t");
        // compute average pident
        int totalLength = 0;
        double total = 0.0;
        for ( int i=0;i<ranges.size();i++ ) {
            Range r = ranges.get(i);
            totalLength += r.getLength();
            total += r.getRelPIdent();
        }
        sb.append(df.format(total/totalLength));
        sb.append("\t");
        sb.append(title);
        sb.append("\t");
        for ( int i=0;i<ranges.size();i++ ) {
            Range r = ranges.get(i);
            if ( i!=0 )
                sb.append(",");
            sb.append(r.qSeqId);
        }
        return sb.toString();        
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        if ( ranges != null ) {
            sb.append(sumRanges());
        }
        else
        {
            sb.append("\t");
        }
        return sb.toString();
    }
}
