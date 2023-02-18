
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ScrollAndroidDown {


    public AndroidDriver<AndroidElement> driver;
    public AndroidTouchAction actions;


    @BeforeTest

    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        //caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Android Emulator");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        //driver= new AndroidDriver (new URL("http://localhost:4723/wd/hub"),caps);
        // driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //custom method for touch action
    private void scrollDown() {
        Dimension dimension = driver.manage().window().getSize();
        int scrollStart = (int) (dimension.getHeight() * 0.8);
        int scrollEnd = (int) (dimension.getHeight() * 0.1);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, scrollStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(0, scrollEnd)).
                release().
                perform();

    }

    @Test
    public void scroll_test() throws InterruptedException {
        AndroidElement views = (AndroidElement) driver.findElementByAccessibilityId("Views");
        //tap
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();
        Thread.sleep(2000);
        //scroll down
        scrollDown();
        AndroidElement lists = driver.findElementByAccessibilityId("Lists");
        actions.tap(ElementOption.element(lists)).perform();
        Thread.sleep(3000);

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}


