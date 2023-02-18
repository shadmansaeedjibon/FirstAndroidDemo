import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SwipeTest {
    public AndroidDriver<AndroidElement> driver;
    public AndroidTouchAction actions;

    @BeforeTest

    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("automationName", "uiautomator2");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("deviceName", "Android Emulator");
        //caps.setCapability("platformVersion", "12.0");
        //caps.setCapability("deviceName", "POCO X4 Pro 5G");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");
        // driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void swipe_test(){

        AndroidElement views = driver.findElementByAccessibilityId("Views");
        //tap
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();

        AndroidElement gallery = driver.findElementByAccessibilityId("Gallery");
        actions.tap(ElementOption.element(gallery)).perform();

        AndroidElement photo = driver.findElementByAccessibilityId("1. Photos");
        actions.tap(ElementOption.element(photo)).perform();

        AndroidElement pic1 = driver.findElements(By.className("android.widget.ImageView")).get(0);
        actions.press(ElementOption.element(pic1))
                .waitAction()
                .moveTo(PointOption.point(-20,210))
                .release()
                .perform();


    }
    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();

        }
    }

}



