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
public class RangeTest {
    static Range r1,r2,r3,r4;
    public RangeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        r1 = new Range(100, 200, "blah blah", 98.0d);
        r2 = new Range(150, 249, "blah blah", 92.2d);
        r3 = new Range(10, 80, "blah blah", 92.6d);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getOverlap method, of class Range.
     */
    @Test
    public void testGetOverlap() {
        System.out.println("getOverlap");
        int result = r2.getOverlap(r3);
        assertEquals(0, result);
        result = r1.getOverlap(r2);
        assertEquals(51, result);
    }

    /**
     * Test of getLength method, of class Range.
     */
    @Test
    public void testGetLength() {
        System.out.println("getLength");
        int expResult = 101;
        int result = r1.getLength();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRelPIdent method, of class Range.
     */
    @Test
    public void testGetRelPIdent() {
        System.out.println("getRelPIdent");
        double result = r1.getRelPIdent();
        assertEquals(9898.0d, result, 0.01d);
    }
    
}
