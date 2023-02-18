import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SendSmsTest {

    public AndroidDriver<AndroidElement> driver;

    @BeforeTest

    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("appPackage", "com.android.messaging");
        caps.setCapability("appActivity", ".ui.conversationlist.ConversationListActivity");
        //caps.setCapability("app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void send_sms() throws InterruptedException {
        driver.sendSMS("01739544499","Hello from Shadman");

        Thread.sleep(5000);
    }
    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();

        }
    }
}
