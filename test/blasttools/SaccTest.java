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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author schmidda
 */
public class SaccTest {
    static Sacc s1;
    public SaccTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        s1 = new Sacc("banana",1000);
        String qSeqId = "apple";
        double pIdent = 90.0;
        s1.addRange(100, 200, qSeqId, pIdent);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addRange method, of class Sacc.
     */
    @Test
    public void testAddRange() {
        System.out.println("addRange");
        String qSeqId = "orange";
        double pIdent = 95.0;
        Sacc instance = s1;
        int oldLen = s1.numRanges();
        instance.addRange(400, 500, qSeqId, pIdent);
        int newLen = s1.numRanges();
        assertEquals(oldLen,newLen-1);
    }

    /**
     * Test of toString method, of class Sacc.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Sacc instance = s1;
        String expResult = "1\t101\t1000\t0.101\t90\tbanana\tapple";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
