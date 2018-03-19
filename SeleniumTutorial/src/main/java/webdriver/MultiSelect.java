package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MultiSelect {

    public static void main(String[] args) throws InterruptedException{

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("https://demos.devexpress.com/aspxeditorsdemos/ListEditors/MultiSelect.aspx");

        /**
         * кликаем на выпадающий список
         */
        driver.findElement(By.id("ControlOptionsTopHolder_lbSelectionMode_I")).click();

        /**
         * кликаем на нужную опцию из выпадающего списка для множественного выбора
         */
        driver.findElement(By.id("ControlOptionsTopHolder_lbSelectionMode_DDD_L_LBI1T0")).click();
        Thread.sleep(5000);

        Actions builder = new Actions(driver);
        /**
         * находим таблицу с элементами, которые нужно выбрать
         */
        WebElement select = driver.findElement(By.id("ContentHolder_lbFeatures_LBT"));

        /**
         * находим внитри таблицы все строки, среди которых мы будем выбирать нужные
         */
        List<WebElement> options = select.findElements(By.tagName("td"));

        /**
         * выводим кол-во элементов в списке
         */
        System.out.println(options.size());

        /**
         * выбираем нужные элементы множественного выбора и строим объект действия
         */
        Action multipleSelect = builder.keyDown(Keys.CONTROL).click(options.get(2)).click(options.get(4)).click(options.get(6)).build();

        /**
         * совершаем действие
         */
        multipleSelect.perform();

        Thread.sleep(5000);

        driver.close();

    }

}
