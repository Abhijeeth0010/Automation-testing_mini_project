package testing_pro;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

public class ecommerce {
    static WebDriver driver;
    

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test //1
    public void searchAndAddToCart() throws InterruptedException {
        driver.get("https://www.ajio.com/");
        WebElement searchBox = driver.findElement(By.name("searchVal"));
        searchBox.sendKeys("shoes");
        searchBox.submit();

        String mainPage = driver.getWindowHandle();
//        System.out.println("Main page=" + mainPage);

        driver.findElement(By.xpath("//*[@id=\"0\"]/a/div/div[1]/div[1]/img")).click();
 

        Set<String> allPages = driver.getWindowHandles();
        for (String page : allPages) {
            if (!page.equals(mainPage)) {
                driver.switchTo().window(page);
                break;
            }
        }
        System.out.println(driver.getCurrentUrl());
        List<WebElement> products = driver.findElements(By.className("prod-list"));
//        System.out.println(products.size());
//        for (WebElement product : products) {
//            System.out.println(product.getText());
//        }
        driver.findElement(By.xpath("//span[normalize-space()='6']")).click(); //Select Shoe size
        driver.findElement(By.xpath("//div[contains(@class,'btn-gold')]")).click(); //Add to cart
        
        System.out.println("ADDED TO CART!");
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@aria-label='PROCEED TO BAG']")).click();
    }

    @Test(dependsOnMethods = "searchAndAddToCart") //2
    public void Title_Test() {
        String expected_Title = "Your Shopping Bag | AJIO";
        String actual_Title = driver.getTitle();
        System.out.println("Title: " + actual_Title);
        Assert.assertEquals(actual_Title, expected_Title, "Title does not match!");
    }
    
    @Test(dependsOnMethods = "Title_Test") //3
    public void logo_test() {
        boolean flag = driver.findElement(By.xpath("//img[@alt='Ajio logo']")).isDisplayed(); //Logo-checked
        Assert.assertTrue(flag, "Logo Verified");
        System.out.println("Logo Verified");
    }

    
    @Test(dependsOnMethods = "logo_test") //4
    @Parameters({"Ph_no"})
    public void shipping(String phno) throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"dCartWrapper\"]/div[2]/div[2]/div[2]/div[2]/button")).click(); //Proceed to Shipping
        driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div/form/div[2]/div[1]/label/input")).click(); //Clicking on input text for phone number 
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div/form/div[2]/div[1]/label/input")).sendKeys(phno); //Filling the phone number in the field.
        Thread.sleep(1000);            
        System.out.println("Phone number added  for OTP");
    }

    @Test(dependsOnMethods = "shipping") //5
    @Parameters({"Name", "Email"})
    public void sign_up_otp(String name, String email) throws InterruptedException {
    	 driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div/form/div[2]/div[2]/input")).click(); //Continue for signing in.
    	 Thread.sleep(2000);
    	 driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div[3]/label[1]")).click(); //Selecting Gender(Male).
    	 Thread.sleep(1000);
    	 driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div[5]/input")).sendKeys(name); //Filling Name.
    	 Thread.sleep(1000);
    	 driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div[7]/input")).sendKeys(email); //Filling Email.
    	 Thread.sleep(1000);
    	 driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div[10]/label/span")).click(); //Check box Agreeing T&C.
    	 Thread.sleep(1000);
    	 WebElement Sent_otp = driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div[12]/input"));
    	 Assert.assertTrue(Sent_otp.isDisplayed(), "Send OTP is not displayed");
    	 Assert.assertTrue(Sent_otp.isEnabled(), "Send OTP is not displayed");
    	 
    	 Sent_otp.click();  //Clicking send OTP button
    	 System.out.println("OTP sent sucessfully!");
    	 Thread.sleep(2000);
    }
    
    @Test(dependsOnMethods = "sign_up_otp") //5
    @Parameters({"OTP"})
    public void Otp(String otp) throws InterruptedException {
//    	driver.findElement(By.xpath("//*[@id=\\\"login-modal\\\"]/div/div/div[2]/div[2]/form/div/div[2]/div[1]/input")).click(); //Click for entering text.
//    	Thread.sleep(1000);
    	driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div/div[2]/div[1]/input")).sendKeys(otp); //Filling an OTP.
    	Thread.sleep(1000);	
    	driver.findElement(By.xpath("//*[@id=\"login-modal\"]/div/div/div[2]/div[2]/form/div/div[3]/input")).click(); //Submit button
    	Thread.sleep(1000);
    }
    
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}