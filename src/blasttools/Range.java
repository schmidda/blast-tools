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

/**
 * Represent a matched range in a sequence
 * @author schmidda
 */
public class Range {
    int start,end;
    String qSeqId;
    double pIdent;
    /**
     * Create a new Range.
     * @param start the start offset in the sequence
     * @param end the end-offset
     * @param qSeqId the sequence id in which this range was found
     * @param pIdent the percent of identity within the match
     */
    public Range( int start, int end, String qSeqId, double pIdent )
    {
        this.start = start;
        this.end = end;
        this.qSeqId = qSeqId;
        this.pIdent = pIdent;
    }
    /**
     * Get the number of characters of overlap between two ranges
     * @param other the other range
     * @return the number of characters overlapping
     */
    public int getOverlap( Range other )
    {
        if ( start > other.end || other.start > end )
            return 0;
        else if ( start < other.start && end >= other.start )
            return (end-other.start)+1;
        else if ( other.start < start && other.end >= start )
            return (other.end-start)+1;
        else if ( start >= other.start && end <= other.end )
            return (end-start)+1;
        else if ( other.start >= start && other.end <= end )
            return (other.end-other.start)+1;
        else return 0;
    }
    /**
     * Get the length of this range
     * @return its absolute length in characters
     */
    public int getLength()
    {
        if ( end-start < 0 )
            return 0;
        return (end-start)+1;
    }
    /**
     * Compute the percent identity normalised over its length
     * @return a percent value
     */
    public double getRelPIdent()
    {
        return ((end-start)+1)*pIdent;
    }
}
