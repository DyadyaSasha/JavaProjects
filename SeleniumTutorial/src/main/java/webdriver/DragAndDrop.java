package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class DragAndDrop {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.manage().window().maximize();

        driver.get("http://keenthemes.com/preview/metronic/theme/admin_1/ui_tree.html");

        /**
         * находим элемент, который нужно перетащить
         */
        WebElement from = driver.findElement(By.xpath(".//*[@id='j3_7_anchor']"));

        /**
         * находим элемент, в который нужно вставить элемент from
         */
        WebElement to = driver.findElement(By.xpath(".//*[@id='j3_1_anchor']"));

        /**
         * класс, имитирующий действия клиента
         */
        Actions builder = new Actions(driver);

        /**
         * создаём действие перетаскивания элемента
         */
        Action dragAndDrop = builder.clickAndHold(from).moveToElement(to).release(to).build();

        /**
         * осуществляем перетаскивание элемента
         */
        dragAndDrop.perform();

        Thread.sleep(5000);

        driver.close();

    }

}
