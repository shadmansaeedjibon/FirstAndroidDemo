import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Drag_Drop_Test {

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

    @Test
    public void drag_drop() throws InterruptedException {
        AndroidElement views = driver.findElementByAccessibilityId("Views");
        actions = new AndroidTouchAction(driver);
        actions.tap(ElementOption.element(views)).perform();

        AndroidElement drag_drop = driver.findElementByAccessibilityId("Drag and Drop");
        actions.tap(ElementOption.element(drag_drop)).perform();
        AndroidElement drag = driver.findElement(By.id("drag_dot_1"));
        AndroidElement drop = driver.findElement(By.id("drag_dot_3"));

        actions.longPress(ElementOption.element(drag))
                .waitAction().moveTo(ElementOption.element(drop))
                .release()
                .perform();

        Thread.sleep(3000);

    }

    @AfterTest
    public void tearDown() {
        if (null != driver) {
            driver.quit();

        }
    }
}