package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeSuite;

public class BaseSuite {

	FileInputStream fis;
	Properties prop;
	
	@BeforeSuite
	public void loadPropertiesFile() throws IOException
	{
		fis=new FileInputStream(System.getProperty("user.dir")+"//configure.properties");
		
		prop=new Properties();
		prop.load(fis);
	}
}
