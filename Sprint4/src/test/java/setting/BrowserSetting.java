package setting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserSetting {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        if ("firefox".equalsIgnoreCase(browser)) {
            setupFirefox();
        } else {
            setupChrome();
        }

    }

    private void setupChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        System.out.println("Запущен Google Chrome");
        System.out.println("В Google Chrome есть баг с оформлением заказа");
    }

    private void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox");
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        System.out.println("Запущен Mozilla Firefox");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}





