package tests;

import setting.BrowserSetting;
import data.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
import pages.OrderPage;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTests extends BrowserSetting {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;

    public OrderTests(String name, String surname, String address, String metro,
                      String phone, String date, String rentalPeriod,
                      String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters(name = "Заказ: {0} {1}")
    public static Object[][] getOrderData() {
        return TestData.ORDER_DATA;
    }

    @Test
    public void createOrderFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.createOrder(name, surname, address, metro, phone, date, rentalPeriod, color, comment);

        boolean success = orderPage.isOrderSuccessDisplayed();
        String browser = System.getProperty("browser", "chrome");

        if ("chrome".equalsIgnoreCase(browser) && !success) {
            System.out.println(" Найден баг в Chrome: заказ не создается через верхнюю кнопку");
        }

        assertTrue("Заказ не был успешно создан через верхнюю кнопку!", success);
    }

    @Test
    public void createOrderFromBottomButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickBottomOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.createOrder(name, surname, address, metro, phone, date, rentalPeriod, color, comment);

        boolean success = orderPage.isOrderSuccessDisplayed();
        String browser = System.getProperty("browser", "chrome");

        if ("chrome".equalsIgnoreCase(browser) && !success) {
            System.out.println(" Найден баг в Chrome: заказ не создается через нижнюю кнопку");
        }

        assertTrue("Заказ не был успешно создан через нижнюю кнопку!", success);
    }
}
