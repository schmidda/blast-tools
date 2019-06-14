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

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test cases for BlastSummary
 * @author schmidda
 */
public class BlastSummaryTest {
    
    public BlastSummaryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Setting up BlastSummaryTest");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Tearing down BlastSummaryTest");
    }

    /**
     * Test of readConfig method, of class BlastSummary.
     */
    @Test
    public void testReadConfig() throws Exception {
        System.out.println("readConfig");
        BlastSummary instance = new BlastSummary("N");
        try {
            instance.readConfig();
        }
        catch ( Exception e ) {
            fail("failed to read config:"+e.getMessage());
        }
    }

    /**
     * Test of read method, of class BlastSummary.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File srcN = new File("examples/blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt");
        File srcP = new File("examples/blastp_PB64-S175_21-22nt_K9-21_cap3_vs_NR_out.virus_viroids.bls.sorted.FINAL.txt");
        BlastSummary nInstance = new BlastSummary("N");
        BlastSummary pInstance = new BlastSummary("P");
        try {
            // the read is replete with tests
            nInstance.readConfig();
            nInstance.read(srcN);
            pInstance.readConfig();
            pInstance.read(srcP);
        }
        catch ( Exception e ) {
            fail("Read method failed:"+e.getMessage());
        }        
    }

    /**
     * Test of write method, of class BlastSummary.
     */
    @Test
    public void testWrite() throws Exception {
        System.out.println("write");
        File srcN = new File("examples/blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt");
        File srcP = new File("examples/blastp_PB64-S175_21-22nt_K9-21_cap3_vs_NR_out.virus_viroids.bls.sorted.FINAL.txt");
        BlastSummary nInstance = new BlastSummary("N");
        BlastSummary pInstance = new BlastSummary("P");
        try {
            nInstance.readConfig();
            nInstance.read(srcN);
            pInstance.readConfig();
            pInstance.read(srcP);
            nInstance.write("examples/"+srcN.getName());
            pInstance.write("examples/"+srcP.getName());
        }
        catch ( Exception e ) {
            fail("write method failed: "+e.getMessage());
        }
    }
}
