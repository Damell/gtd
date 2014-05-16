/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package GTD.PL.PLView;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads config.properties file with info about database to be connected to. Properties are
 * - dbhostname Hostname
 * - dbport Port
 * - dbsid SID
 * 
 * @author damell
 * @version 1.0
 */
public class Config {
	
	private Properties prop;
	private InputStream input = null;
	
	Config() {
		prop = new Properties();
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
		} catch (IOException io) {
			GTDGUI.getGTDGUI().showError(io.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}	
		}
	}

	public String getHostname () {
		return prop.getProperty("dbhostname");
	}

	public String getPort () {
		return prop.getProperty("dbport");
	}

	public String getSID () {
		return prop.getProperty("dbsid");
	}
	
}
