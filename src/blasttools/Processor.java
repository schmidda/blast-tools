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

import blast.BlastSummary;
import java.io.File;
import java.io.InputStream;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Reusable tools for various processors
 * @author schmidda
 */
public abstract class Processor {
    protected static File src;
    protected JSONObject config;
    protected abstract void readConfig() throws Exception;
    protected abstract void read(File src ) throws Exception;
    protected abstract void write( String srcName ) throws Exception;
    /**
     * Factory method to build processors anonymously
     * @param name name of the processor
     * @return the built processor
     */
    static public Processor getProcessor( String name ) throws Exception {
        if ( name.equals("blastn") )
            return new BlastSummary("N");
        else if ( name.equals("blastp") )
            return new BlastSummary("P");
        else
            throw new Exception("Unknown processor "+name);
    }
    protected boolean isInt( String value ) {
        try {
            if ( value.length() > 0 ) {
                Integer.parseInt(value);
                return true;
            }
            else
                return false;
        }
        catch ( Exception e ) {
            return false;
        }
    }
    /**
     * Is the string given a suitable double number
     * @param value the value of the double
     * @return 
     */
    protected boolean isDouble( String value ) {
        try {
            if ( value.length() > 0 ) {
                Double.parseDouble(value);
                return true;
            }
            else
                return false;
        }
        catch ( Exception e ) {
            return false;
        }
    }
    protected String shortFileName( String fileName ) {
        if ( fileName.lastIndexOf(".")!= -1 )
            return fileName.substring(0,fileName.lastIndexOf(".") );
        else
            return fileName;
    }
    /**
     * 
     * @param key the master key of the processor's config
     * @param required an array of keys required in the sub-section of config
     * @return a JSONObject containing the checked config
     */
    protected JSONObject loadConfig( String key, String path, String[] required )
    {
        InputStream is = BlastSummary.class.getResourceAsStream(path);
        try {
            int len = is.available();
            byte[] data = new byte[len];
            is.read(data);
            String jStr = new String(data);
            JSONObject jObj = (JSONObject)JSONValue.parse(jStr);
            if ( jObj.containsKey(key) ){
                JSONObject config = (JSONObject)(JSONObject)jObj.get(key);
                for ( int i=0;i<required.length;i++ )
                    if ( !config.containsKey(required[i]) )
                        return null;
                return config;
            }
            else
                return null;
        }
        catch ( Exception e ) {
            return null;
        }
    }
    /**
     * Get the int value of a config setting
     * @param key the config key
     * @param jObj the config object
     * @return the integer value
     */
    protected int intVal( String key, JSONObject jObj ) throws Exception {
        try {
            return ((Number)jObj.get(key)).intValue();
        }
        catch ( Exception e )
        {
            throw new Exception("Invalid key "+key);
        }
    }
}
