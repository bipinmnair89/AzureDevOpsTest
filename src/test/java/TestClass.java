import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import javax.management.monitor.CounterMonitor;
import java.util.List;

public class TestClass extends BaseConfiguration {

    public TestClass()
    {
        super();
    }

    @BeforeSuite
    public void initialSetup()
    {
        initializationMethod();
    }

    @Test(priority=0)
    public void verify_LoginPage_Title()
    {
        CommonMethods.assertWaitForElementToBeClickable(driver, By.xpath("//input[@name='email']"), 50);
        CommonMethods.assertPageTitle(driver, "Cogmento CRM");
    }

    @Test(priority=1)
    public void verify_Application_Login() {
        CommonMethods.assertWaitForElementToBeClickable(driver, By.xpath("//input[@name='email']"), 50);
        CommonMethods.clearAndEnterText(driver, By.xpath("//input[@name='email']"), prop.getProperty("username"));
        CommonMethods.assertWaitForElementToBeClickable(driver, By.xpath("//input[@name='password']"), 50);
        CommonMethods.clearAndEnterText(driver, By.xpath("//input[@name='password']"), prop.getProperty("password"));
        CommonMethods.clickWebElement(driver, By.xpath("//div[text()='Login']"), 50);
        CommonMethods.assertWebElementText(driver, By.xpath("//div[@id='main-nav']/descendant::span[text()='Home']"), "Home");
    }

    @Test(priority=2)
    public void verify_NavigationMenu_Links()
    {
        List<WebElement> listItem=driver.findElements(By.xpath("//div[@id='main-nav']/descendant::span"));
        for(WebElement element : listItem)
        {
            String elementTxt=element.getText();
            System.out.println(elementTxt);
            CommonMethods.assertWebElementText(driver, By.xpath("//div[@id='main-nav']/descendant::span[text()='"+elementTxt+"']"), elementTxt);
        }
    }


    @AfterSuite
    public void tearDown()
    {
        driver.close();
    }


}