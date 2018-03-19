package webdriver;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.monte.media.Format;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.DOMConfiguration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


import static org.monte.media.AudioFormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class WebDriverDemo {

    public static void main(String[] args) throws IOException, AWTException {

        Logger logger = LogManager.getLogger(WebDriverDemo.class.getName());

        /**
         * логирование будет происходить не в консоль, а в файл, это прописано в файле log4j.xml
         */
        DOMConfigurator.configure("src/main/resources/log4j.xml");
        logger.info("#################");
        logger.info("TEST has started");

        /**
         * объект нужный для {@link ScreenRecorder}
         */
        GraphicsConfiguration gconfig = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        /**
         * создаём объект, который будет записывать видео
         */
        ScreenRecorder recorder = new ScreenRecorder(gconfig,
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                        ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, (int)24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, (int) (15 * 60)),
                new Format(MediaTypeKey, MediaType.VIDEO,
                        EncodingKey,"black", FrameRateKey, Rational.valueOf(30)), null);

        /**
         * начинаем записывать видео
         */
        recorder.start();

        /**
         * прописываем путь до "нативного" драйвера
         */
        System.setProperty("webdriver.chrome.driver", "D:\\Dev\\Selenium\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        /**
         * Чтобы синхронизировать работу нашего приложения и web-приложения(например, подождать, пока полностью загрузиться html-страница)
         * можно использовать:
         * 1) Thread.sleep()
         * 2) Явное ожидание(ждёт выполнения какого-то условия перед тем как продолжить работу):
         * WebElement DynamicElement =
         *   (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("DynamicElement")));
         *   - ждём, пока элемент подгрузиться на html-страницу
         * 3) Неявное ожидание(используется в случаях когда web-драйвеп не может найти сразу нужный элемент и ждёт указанное время,
         * если в первый раз не удалось найти, ищёт второй раз, если во второй раз наййти не удалось - выбрасывает исключение). Пример:
         * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         * 3) Свободное ожидание(в объекте свободного ожидания указывается max время ожидания условия, а также частоту, с которой нужно проверять наличие состояния объекта). Пример:
         * Wait wait =
         *      new FluentWait(driver).withTimeout(60, SECONDS).pollingEvery(10, SECONDS).ignoring(NoSuchElementException.class);
         * WebElement dynamicElement = wait.until(new Function<webdriver,webElement>() {
         *  public WebElement apply(WebDriver driver) {
         *      return driver.findElement(By.id("dynamicelement"));
         *  }
         *}); - ждём 60 секунд, пока элемент не станет доступным на html-странице и проверяем это каждые 10 секунд
         * manage() даёт доступ к опциям драйвера
         * в данном случае, указываем время ожидания, если найти нужный элемент сразу не удалось, если
         * после в течении этого времени элемент не найден, драйвер выбрасывает исключение {@link NoSuchElementException}
         */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        /**
         * на полный экран(рамку окна браузера видно)
         */
        driver.manage().window().maximize();

        /**
         * на полный экран(рамку окна браузера не видно)
         */
//        driver.manage().window().fullscreen();

        /**
         * переход на нужный сайт
         */
        driver.get("http://www.calculator.net/");
        logger.info("Open site");
        /**
         * переход на нужный сайт
         */
//        driver.navigate().to("http://www.calculator.net/");

        /**
         * находим нужный элемент и кликаем по нему
         */
        driver.findElement(By.xpath(".//*[@id='hl3']/li[3]/a")).click();
        logger.info("Click percent calculator");

        /**
         * sendKeys() устанавливает значение нужного поля(элемента)
         * findElement() возвращает первый найденный элемент и выбрасывает исключение, если элемент не найден
         * в методе findElements(), если элемент не найден, возвращает пустой список
         */
        driver.findElement(By.id("cpar1")).sendKeys("10");
        logger.info("Set text value1");

        driver.findElement(By.id("cpar2")).sendKeys("50");
        logger.info("Set text value2");

        /**
         * берём значение текстового поля формы, которое храниться в атрибуте value
         */
        System.out.println("Value of left field(text box): " +
                driver
                        .findElement(By.id("cpar1"))
                        .getAttribute("value"));

        System.out.println("Value of right field(text box): " +
                driver
                        .findElement(By.id("cpar2"))
                        .getAttribute("value"));


        driver.findElement(By.xpath(".//*[@id='content']/table[1]/tbody/tr[2]/td/input[2]")).click();


        /**
         * берём значение нужного поля(элемента)
         */
        String result = driver.findElement(By.xpath(".//*[@id='content']/p[2]/font/b")).getText();
        logger.info("Get result");

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("D:\\Projects\\Projects for github\\JavaProjects\\SeleniumTutorial\\media\\screen.jpg"));
        System.out.println("Result is - " + result);

        /**
         * закрывает текущую вкладку, если она последняя, то закрывает окно браузера
         */
        driver.close();

        /**
         * заканчиваем записывать видео(файл с видео будет сохранён в папке C:\Users\User\Videos)
         */
        recorder.stop();
        logger.info("###################");

    }

}
