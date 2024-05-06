package com.qa.opencart.falctory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager op;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		op = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		String Browser = prop.getProperty("browser").trim();
		System.out.println("browser name is :" + Browser);

		if (Browser.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(op.getChromeOptions());
			tlDriver.set(new ChromeDriver(op.getChromeOptions()));
		} else if (Browser.equalsIgnoreCase("firefox")) {
			// driver = new FirefoxDriver(op.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(op.getFirefoxOptions()));
		} else if (Browser.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
		} else {
			System.out.println("please pass the right browser name :" + Browser);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());

		return driver;
	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		prop = new Properties();
		FileInputStream ip = null;

		String env = System.getProperty("env");

		if (env == null) {
			try {
				ip = new FileInputStream("./src/tests/resources/confiq/confiq.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment: " + env);

			try {
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/tests/resources/confiq/qa.confiq.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/tests/resources/confiq/stage.confiq.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/tests/resources/confiq/dev.confiq.properties");
					break;

				default:
					System.out.println(".....Please pass the right environment....." + env);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

}
