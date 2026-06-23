package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrderPage extends BasePage {

    public OrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // Список элементов  (форма 1: "Для кого самокат")


    // Поле "Имя"
    // Локатор: //input[@placeholder='* Имя']
    @FindBy(xpath = "//input[@placeholder='* Имя']")
    private WebElement nameInput;

    // Поле "Фамилия"
    // Локатор: //input[@placeholder='* Фамилия']
    @FindBy(xpath = "//input[@placeholder='* Фамилия']")
    private WebElement surnameInput;

    // Поле "Адрес"
    // Локатор: //input[@placeholder='* Адрес: куда привезти заказ']
    @FindBy(xpath = "//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement addressInput;

    // Поле "Станция метро"
    // Локатор: //input[@placeholder='* Станция метро']
    @FindBy(xpath = "//input[@placeholder='* Станция метро']")
    private WebElement metroInput;

    // Поле "Телефон"
    // Локатор: //input[@placeholder='* Телефон: на него позвонит курьер']
    @FindBy(xpath = "//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement phoneInput;

    // Кнопка "Далее"
    // Локатор: //button[text()='Далее']
    @FindBy(xpath = "//button[text()='Далее']")
    private WebElement nextButton;


    // Список элементов (форма 2: "Про аренду")


    // Поле "Дата доставки"
    // Локатор: //input[@placeholder='* Когда привезти самокат']
    @FindBy(xpath = "//input[@placeholder='* Когда привезти самокат']")
    private WebElement dateInput;

    // Выпадающий список "Срок аренды"
    // Локатор: //div[contains(@class, 'Dropdown-control')]
    @FindBy(xpath = "//div[contains(@class, 'Dropdown-control')]")
    private WebElement rentalPeriodDropdown;

    // Чекбокс "Чёрный жемчуг"
    // Локатор: //label[text()='Чёрный жемчуг']
    @FindBy(xpath = "//label[text()='Чёрный жемчуг']")
    private WebElement blackColorLabel;

    // Чекбокс "Серая безысходность"
    // Локатор: //label[text()='Серая безысходность']
    @FindBy(xpath = "//label[text()='Серая безысходность']")
    private WebElement greyColorLabel;

    // Поле "Комментарий для курьера"
    // Локатор: //input[@placeholder='Комментарий для курьера']
    @FindBy(xpath = "//input[@placeholder='Комментарий для курьера']")
    private WebElement commentInput;

    // Кнопка "Заказать" (на второй форме)
    // Локатор: //div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']
    @FindBy(xpath = "//div[contains(@class, 'Order_Buttons')]//button[text()='Заказать']")
    private WebElement submitOrderButton;

    // Список элементов модального окна

    // Кнопка "Да" в модальном окне
    // Локатор: //button[text()='Да']
    @FindBy(xpath = "//button[text()='Да']")
    private WebElement confirmYesButton;

    // Сообщение "Заказ оформлен"
    // Локатор: //div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]
    @FindBy(xpath = "//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]")
    private WebElement orderSuccessHeader;

    // Методы
    public void fillFirstForm(String name, String surname, String address, String metro, String phone) {
        waitForElementVisible(nameInput);
        nameInput.sendKeys(name);
        surnameInput.sendKeys(surname);
        addressInput.sendKeys(address);

        metroInput.click();

        metroInput.sendKeys(metro);

        String metroXpath = "//div[contains(@class, 'Order_List')]//div[contains(text(), '" + metro + "')]";
        WebElement metroOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(metroXpath)));
        metroOption.click();

        phoneInput.sendKeys(phone);
        clickWithWait(nextButton);
    }

    public void fillSecondForm(String date, String rentalPeriod, String color, String comment) {
        waitForElementVisible(dateInput);
        dateInput.clear();
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);

        clickWithWait(rentalPeriodDropdown);
        String periodXpath = "//div[contains(@class, 'Dropdown-menu')]//div[text()='" + rentalPeriod + "']";
        WebElement periodOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(periodXpath)));
        periodOption.click();

        if ("black".equals(color)) {
            scrollToElement(blackColorLabel);
            clickWithWait(blackColorLabel);
        } else if ("grey".equals(color)) {
            scrollToElement(greyColorLabel);
            clickWithWait(greyColorLabel);
        }

        commentInput.sendKeys(comment);
        clickWithWait(submitOrderButton);
    }

    public void confirmOrder() {
        waitForElementClickable(confirmYesButton);
        confirmYesButton.click();
    }

    public boolean isOrderSuccessDisplayed() {
        try {
            waitForElementVisible(orderSuccessHeader);
            return orderSuccessHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void createOrder(String name, String surname, String address, String metro,

                            String phone, String date, String rentalPeriod, String color, String comment) {
        fillFirstForm(name, surname, address, metro, phone);
        fillSecondForm(date, rentalPeriod, color, comment);
        confirmOrder();
    }
}