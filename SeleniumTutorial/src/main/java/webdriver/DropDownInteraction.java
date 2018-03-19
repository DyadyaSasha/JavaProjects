package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class DropDownInteraction {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://www.calculator.net/interest-calculator.html");

        /**
         * объект, отвечающий за выплывающий список
         */
        Select dropdown = new Select(driver.findElement(By.id("ccompound")));

        /**
         * выбираем элемент выплывающего списка по тексту, который мы видим на html-странице
         */
        dropdown.selectByVisibleText("semimonthly");
        /**
         * смотрим содержимое(отображаемый клиенту текст) выбранного элемента выплывающего списка
         */
        System.out.println(dropdown.getFirstSelectedOption().getText());

        System.out.println("Dropdown is selected " + driver.findElement(By.id("ccompound")).isSelected());
        System.out.println("Dropdown is enabled " + driver.findElement(By.id("ccompound")).isEnabled());
        System.out.println("Dropdown is displayed " + driver.findElement(By.id("ccompound")).isDisplayed());

        /**
         * выбираем элемент выплывающего списка по его индексу следования(начиная с 0)
         */
        dropdown.selectByIndex(5);
        System.out.println(dropdown.getFirstSelectedOption().getText());

        /**
         * выбираем элемент выплывающего списка по его значению атрибута value
         */
        dropdown.selectByValue("quarterly");
        System.out.println(dropdown.getFirstSelectedOption().getText());

        driver.close();



    }

}
