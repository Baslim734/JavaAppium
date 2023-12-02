package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Platform {

    private static Platform instance;

    private Platform() {
    }

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            APPIUM_URL = "http://127.0.0.1:4723",
            PLATFORM_MOBILE_WEB = "mobile_web";

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("Cannot detect type of platform. Now platform is:" + this.getPlatformVar());
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "AndroidTestDevice");
        capabilities.setCapability("appium:platformVersion", "8.1.0");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/nikitabirukov/Desktop/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 13");
        capabilities.setCapability("appium:platformVersion", "15.5");
        capabilities.setCapability(MobileCapabilityType.LANGUAGE, "EN");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/nikitabirukov/Desktop/JavaAppiumAutomationFromGit/JavaAppiumAutomation/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMWChromeOptions(){
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String,Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10157) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        chromeOptions.addArguments("window-size=340,640");

        return chromeOptions;
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }
}
