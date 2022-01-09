import net.bytebuddy.agent.builder.AgentBuilder;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class ScreenShots {
    public static void getScreenShot (WebDriver driver){
        TakesScreenshot scr = ((TakesScreenshot) driver);
        byte[] screenshot = scr.getScreenshotAs(OutputType.BYTES);
        String fileName = "screenshot.png";
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            fileOutputStream.write(screenshot);
        } catch (IOException e) {
            e.printStackTrace();}
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://hard.rozetka.com.ua/mouses/c80172/");
    try{
        WebElement options = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@class = 'select-css ng-untouched ng-pristine ng-valid ng-star-inserted']")));
        System.out.println(options.getText());

        WebElement selectElement = driver.findElement(By.xpath("//select[@class = 'select-css ng-untouched ng-pristine ng-valid ng-star-inserted']"));
        Select selectObject = new Select(selectElement);
        List<WebElement> allAvailableOptions = selectObject.getOptions();
        boolean doesThisAllowMultipleSelections = selectObject.isMultiple();
        selectObject.selectByIndex(5);
        for (WebElement f : allAvailableOptions){
            System.out.println("Selected options " + f.getText());
        }
        System.out.println("Does this allow Multiple Selections? - " + doesThisAllowMultipleSelections);

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='goods-tile__heading ng-star-inserted']")));
        WebElement prodItem = driver.findElement(By.xpath("//a[@class='goods-tile__heading ng-star-inserted']"));
        Actions actionProvider = new Actions(driver);
        actionProvider.contextClick(prodItem).build().perform();
        prodItem.click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='buy-button__label ng-star-inserted']")));
        WebElement buyButton = driver.findElement(By.xpath("//span[@class='buy-button__label ng-star-inserted']"));
        buyButton.click();

        getScreenShot(driver);

    } finally {
    driver.quit();

        }
    }
}
