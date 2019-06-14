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
import java.io.File;

/**
 * Tests for BlastTools
 * @author schmidda
 */
public class BlastToolsTest {
    
    public BlastToolsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readArgs method, of class BlastTools.
     */
    @Test
    public void testReadArgs() throws Exception {
        System.out.println("readArgs");
        String[] args1 = {"-t","blastn","examples/blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt"};
        String[] args2 = {"-t","blastp","examples/blastp_PB64-S175_21-22nt_K9-21_cap3_vs_NR_out.virus_viroids.bls.sorted.FINAL.txt"};
        boolean result = BlastTools.readArgs(args1);
        assertEquals(true, result);
        assert(BlastTools.src!=null &&BlastTools.src.exists());
        assert(BlastTools.processor != null);
        result = BlastTools.readArgs(args2);
        assertEquals(true, result);
        assert(BlastTools.src!=null &&BlastTools.src.exists());
        assert(BlastTools.processor != null);
    }

    /**
     * Test of main method, of class BlastTools.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args1 = {"-t","blastn","examples/blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt"};
        String[] args2 = {"-t","blastp","examples/blastp_PB64-S175_21-22nt_K9-21_cap3_vs_NR_out.virus_viroids.bls.sorted.FINAL.txt"};
        BlastTools.main(args1);
        File f1 = new File("examples/summary_blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt");
        assert(f1.exists());
        BlastTools.main(args2);
        File f2 = new File("examples/summary_blastp_PB64-S175_21-22nt_K9-21_cap3_vs_NR_out.virus_viroids.bls.sorted.FINAL.txt");
        assert(f2.exists());
    }
}
