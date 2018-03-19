package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class CheckBoxInteraction {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://www.calculator.net/mortgage-calculator.html");

        /**
         * кликаем по флажку, изначально флажок выставлен, т.е. в данном случае снимаем флажок
         */
        driver.findElement(By.id("caddoptional")).click();

        /**
         * удостоверяемся, что флажок снят
         */
        System.out.println("CheckBox is selected: " + driver.findElement(By.id("caddoptional")).isSelected());

        /**
         * доступно ли вообще поле(кнопка) выставления флажка
         */
        System.out.println("CheckBox is enabled: " + driver.findElement(By.id("caddoptional")).isEnabled());

        /**
         * размещено ли поле(кнопка) выставления флажка на html-странице
         */
        System.out.println("CheckBox is displayed: " + driver.findElement(By.id("caddoptional")).isDisplayed());

        /**
         * выставляем флажок
         */
        driver.findElement(By.id("caddoptional")).click();

        /**
         * удостоверяемся, что флажок снова выставлен
         */
        System.out.println("CheckBox is selected: " + driver.findElement(By.id("caddoptional")).isSelected());

        System.out.println("CheckBox is enabled: " + driver.findElement(By.id("caddoptional")).isEnabled());

        System.out.println("CheckBox is displayed: " + driver.findElement(By.id("caddoptional")).isDisplayed());


        driver.close();

    }
}
