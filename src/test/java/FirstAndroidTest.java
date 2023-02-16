import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class FirstAndroidTest {

    AppiumDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("platformVersion", "10.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void click_App_Button() throws InterruptedException {
        By appLocator = By.xpath("//android.widget.TextView[@content-desc='App']");
        System.out.println(driver.findElement(appLocator).getText());
        driver.findElement(appLocator).click();
        Thread.sleep(5000);



    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
