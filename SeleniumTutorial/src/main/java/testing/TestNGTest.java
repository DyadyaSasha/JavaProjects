package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;

public class TestNGTest {

    static {
        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");
    }

    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void beforeTest(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://www.calculator.net");
    }

    @Test
    public void test1(){
        driver.findElement(By.xpath(".//*[@id='hl3']/li[3]/a")).click();
        driver.findElement(By.xpath(".//*[@id='cpar1']")).sendKeys("10");
        driver.findElement(By.xpath(".//*[@id='cpar2']")).sendKeys("50");
        driver.findElement(By.xpath(".//*[@id='content']/table[1]/tbody/tr[2]/td/input[2]")).click();

        assertEquals(Integer.parseInt(driver.findElement(By.xpath(".//*[@id='content']/p[2]/font/b")).getText()),
                5);
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
