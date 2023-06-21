package MyUtilities; 

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

	public class ReadConfig {
		Properties pro; 
		
	
		public ReadConfig() { 
			File src = new File("./Configurations/config.properties");
			try {
				
				FileInputStream fis = new FileInputStream(src);
				pro = new Properties(); 
				pro.load(fis);
				
				}
				catch (Exception e) { 
					System.out.println("Exception is : " + e.getMessage());					
					
					}
				}		

				public String getfirstname() { 
					String fn = pro.getProperty("firstname"); 
					return fn; 
				}	
				
				public String getlastname() { 
					String sn = pro.getProperty("lastname"); 
					return sn; 
				}	
				
				public String getpincode() { 
					String pn = pro.getProperty("pincode"); 
					return pn; 
				}								 
			}
		
					
