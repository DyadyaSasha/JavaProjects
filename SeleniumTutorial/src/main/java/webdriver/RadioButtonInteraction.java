package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TextBoxInteraction {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.navigate().to("http://www.calculator.net/percent-calculator.html");

    }

}
