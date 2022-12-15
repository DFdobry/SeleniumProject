package seleniumtests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestClass {

    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    @BeforeEach
    public void before() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver.get("http://training.appline.ru/user/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        driver.findElement(By.xpath("//input[@name='_username']")).sendKeys("Taraskina Valeriya");
        driver.findElement(By.xpath("//input[@name='_password']")).sendKeys("testing");
        driver.findElement(By.xpath("//button[@id='_submit']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//h1[text()='Панель быстрого запуска']"))));
        WebElement expenses = driver.findElement(By.xpath
                ("//ul[contains(@class, 'main-menu')]/li/a/span[text()='Расходы']"));
        expenses.click();
        wait.until(ExpectedConditions.visibilityOf(expenses.findElement(By.xpath(
                "./ancestor::li//ul[@class='dropdown-menu menu_level_1']"))));
        driver.findElement(By.xpath("//span[text()='Командировки']")).click();
        driver.findElement(By.xpath
                ("//div[@class='pull-left btn-group icons-holder']/a[text()='Создать командировку']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Создать командировку']"))));
        driver.findElement(By.xpath("//select[@name='crm_business_trip[businessUnit]']")).click();
        driver.findElement(By.xpath("//option[text()='Отдел внутренней разработки']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement
                (By.xpath("//span[text()='Отдел внутренней разработки']"))));
        driver.findElement(By.xpath("//a[text()='Открыть список']")).click();
        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();
        driver.findElement(By.xpath("//div[text()='(Хром) Призрачная Организация Охотников']")).click();
        driver.findElement(By.xpath("//label[text()='Заказ билетов']/..//input")).click();
        // заполнение полей
        WebElement date_departure = driver.findElement(By.xpath("//input[@data-name='field__departure-city']"));
        date_departure.clear();
        date_departure.sendKeys("Россия, Курск");
        driver.findElement((By.xpath("//input[@data-name='field__arrival-city']"))).sendKeys("Россия, Москва");
        driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_departure')]")).sendKeys("20.01.2023");
        WebElement date_return = driver.findElement(By.xpath("//input[contains(@id, 'date_selector_crm_business_trip_return')]"));
        date_return.sendKeys("30.01.2023");
        date_return.sendKeys(Keys.ESCAPE);
        //подтверждение
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();
        WebElement validation_error = driver.findElement(By.xpath("//div[@class='controls validation-error']"));
        wait.until(ExpectedConditions.visibilityOf(validation_error));
    }
    @AfterEach
    public void after() {
        driver.quit();
    }

}
