package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BasePage {

    public static final String URL = "https://qa-scooter.praktikum-services.ru/";

    // Список элементов главной страницы

    // Верхняя кнопка "Заказать"
    // Локатор: //div[contains(@class, 'Header_Nav')]//button[text()='Заказать']
    @FindBy(xpath = "//div[contains(@class, 'Header_Nav')]//button[text()='Заказать']")
    private WebElement topOrderButton;

    // Нижняя кнопка "Заказать"
    // Локатор: //div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']
    @FindBy(xpath = "//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']")
    private WebElement bottomOrderButton;


    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get(URL);
    }

    public void clickTopOrderButton() {
        waitForElementClickable(topOrderButton);
        topOrderButton.click();
    }

    public void clickBottomOrderButton() {
        scrollToElement(bottomOrderButton);
        waitForElementClickable(bottomOrderButton);
        bottomOrderButton.click();
    }

    // Вопросы о важном

    public void clickFaqQuestion(int index) {
        WebElement question = driver.findElement(By.id("accordion__heading-" + index));
        scrollToElement(question);
        waitForElementClickable(question);
        question.click();
    }

    public String getFaqAnswerText(int index) {
        WebElement answer = driver.findElement(By.xpath("//div[@id='accordion__panel-" + index + "']"));
        waitForElementVisible(answer);
        return answer.getText();
    }
}