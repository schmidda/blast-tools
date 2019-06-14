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
import org.json.simple.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import blast.BlastSummary;

/**
 *
 * @author schmidda
 */
public class ProcessorTest {
    
    public ProcessorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getProcessor method, of class Processor.
     */
    @Test
    public void testGetProcessor() throws Exception {
        System.out.println("getProcessor");
        Processor result = Processor.getProcessor("blastn");
        assertEquals(true, result instanceof BlastSummary);
        result = Processor.getProcessor("blastp");
        assertEquals(true, result instanceof BlastSummary);
    }

    /**
     * Test of isInt method, of class Processor.
     */
    @Test
    public void testIsInt() {
        System.out.println("isInt");
        Processor instance = new ProcessorImpl();
        boolean result = instance.isInt("1234");
        assertEquals(true, result);
        result = instance.isInt("123.456");
        assertEquals(false, result);
        result = instance.isInt("dog");
        assertEquals(false, result);
    }

    /**
     * Test of isDouble method, of class Processor.
     */
    @Test
    public void testIsDouble() {
        System.out.println("isDouble");
        Processor instance = new ProcessorImpl();
        boolean result = instance.isDouble("1234");
        assertEquals(true, result);
        result = instance.isDouble("123.456");
        assertEquals(true, result);
        result = instance.isDouble("dog");
        assertEquals(false, result);
    }

    /**
     * Test of shortFileName method, of class Processor.
     */
    @Test
    public void testShortFileName() {
        System.out.println("shortFileName");
        String fileName = "blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL.txt";
        Processor instance = new ProcessorImpl();
        String expResult = "blastn_Top5Hits_PB64-S175_23-24nt_K9-21_cap3_vs_NT_out.virus_viroids.mod.FINAL";
        String result = instance.shortFileName(fileName);
        assertEquals(expResult, result);
    }

    /**
     * Test of loadConfig method, of class Processor.
     */
    @Test
    public void testLoadConfig() {
        System.out.println("loadConfig");
        String key = "";
        String path = "";
        String[] required = null;
        Processor instance = new ProcessorImpl();
        JSONObject expResult = null;
        JSONObject result = instance.loadConfig(key, path, required);
        assertEquals(expResult, result);
    }

    /**
     * Test of intVal method, of class Processor.
     */
    @Test
    public void testIntVal() throws Exception {
        System.out.println("intVal");
        String key = "sstart";
        JSONObject jObj = new JSONObject();
        jObj.put("sstart", 12);
        Processor instance = new ProcessorImpl();
        int expResult = 12;
        int result = instance.intVal(key, jObj);
        assertEquals(expResult, result);
    }

    public class ProcessorImpl extends Processor {

        public void readConfig() throws Exception {
        }

        public void read(File src) throws Exception {
        }

        public void write(String srcName) throws Exception {
        }
    }
    
}
