package tests;

import setting.BrowserSetting;
import data.TestData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.MainPage;
 import static org.junit.Assert.assertEquals;

 @RunWith(Parameterized.class)
public class FAQTests extends BrowserSetting {
     private final int questionIndex;
     private final String expectedAnswer;

     public FAQTests(int questionIndex, String expectedAnswer) {
         this.questionIndex = questionIndex;
         this.expectedAnswer = expectedAnswer;
     }

     @Parameterized.Parameters(name = "Вопрос #{0}")
     public static Object[][] getFAQData() {
         return TestData.FAQ_DATA;
     }
     @Test
     public void checkFaqAnswerIsCorrect() {
         MainPage mainPage = new MainPage(driver);
         mainPage.open();

         mainPage.clickFaqQuestion(questionIndex);
         String actualAnswer = mainPage.getFaqAnswerText(questionIndex);

         assertEquals(
                 "Текст ответа на вопрос #" + questionIndex + " не совпадает с ожидаемым",
                 expectedAnswer,
                 actualAnswer
         );
     }
}
