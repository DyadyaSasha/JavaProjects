package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class RadioButtonInteraction {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.navigate().to("https://www.calculator.net/mortgage-payoff-calculator.html");


        /**
         * кликаем на радиокнопку
         */
        driver.findElement(By.id("cpayoff1")).click();

        /**
         * проверяем выбрана ли данная радиокнопка
         */
        System.out.println("Radiobutton1 is selected: " + driver.findElement(By.id("cpayoff1")).isSelected());

        /**
         * доступна ли вообще радиокнопка
         */
        System.out.println("Radiobutton1 is displayed: " + driver.findElement(By.id("cpayoff1")).isDisplayed());

        /**
         * размещена ли радиокнопка на html-странице
         */
        System.out.println("Radiobutton1 is  enabled: " + driver.findElement(By.id("cpayoff1")).isEnabled());

        driver.findElement(By.id("cpayoff2")).click();

        System.out.println("Radiobutton1 is selected: " + driver.findElement(By.id("cpayoff1")).isSelected());

        System.out.println("Radiobutton1 is displayed: " + driver.findElement(By.id("cpayoff1")).isDisplayed());

        System.out.println("Radiobutton1 is  enabled: " + driver.findElement(By.id("cpayoff1")).isEnabled());


        driver.close();

    }

}
