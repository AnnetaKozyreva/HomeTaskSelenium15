import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;





public class Colours {

    private static final Color RGB_COLOUR_TOP_SALE = Color.fromString("rgb(255, 169, 0)");
    private static final Color RGB_COLOUR_ONLY_ROZETKA = Color.fromString("rgb(216, 55, 152)");
    private static final Color HEX_COLOUR_DISCOUNT = Color.fromString("#f84147");

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("fat-menu")));
        WebElement katalog = driver.findElement(By.id("fat-menu"));
        katalog.click();
        try {
            List<WebElement> compMenu = driver.findElements(By.xpath("//li[1]//div[1]/div[2]/ul[2]/li/ul/li[@class = 'ng-star-inserted']"));
            System.out.println(compMenu.size());
            compMenu.get(1).click();
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error! List compMenu is empty");
        }
        try {
            WebElement topSale = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_popularity ng-star-inserted']")));
            System.out.println(topSale.getText());
            Color topSaleColour = Color
                    .fromString(driver.findElement(By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_popularity ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(topSaleColour.equals(RGB_COLOUR_TOP_SALE));

            WebElement onlyRozetka = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated (By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_exclusive ng-star-inserted']")));
            System.out.println(onlyRozetka.getText());
            Color onlyRozetkaColour = Color
                    .fromString(driver.findElement(By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_exclusive ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(onlyRozetkaColour.equals(RGB_COLOUR_ONLY_ROZETKA));

            WebElement discount = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_action ng-star-inserted']")));
            System.out.println(discount.getText());
            Color discountColour = Color
                    .fromString(driver.findElement(By.xpath("//span[@class= 'goods-tile__label promo-label promo-label_type_action ng-star-inserted']"))
                            .getCssValue("background-color"));
            System.out.println(discountColour.equals(HEX_COLOUR_DISCOUNT));
        } finally {
            driver.quit();

        }

    }
}
