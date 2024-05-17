import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.example.DataGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.util.Locale;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestsWithGeneratedDataTestV1 {
        private Faker faker;

        @Test
        void shouldPreventSendRequestMultipleTimes(){
                faker = new Faker(new Locale("ru"));
                var daysToAddForFirstMeeting = 4;
                var daysToAddForSecondMeeting = 7;
                String name = faker.name().firstName();
                String lastName = faker.name().lastName();
                String LastAndFirst = lastName + " " + name;
                String phone = faker.phoneNumber().phoneNumber();
                String city = faker.address().city();


                String firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting,"dd.MM.yyyy");
                String secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting,"dd.MM.yyyy");
                open("http://localhost:9999");
                $("[data-test-id=city] input").setValue(city);
                $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
                $("[data-test-id=date] input").setValue(firstMeetingDate);
                $("[data-test-id=name] input").setValue(LastAndFirst);
                $("[data-test-id=phone] input").setValue(phone);
                $("[data-test-id=agreement]").click();
                $(".button").click();
                $("[data-test-id=success-notification]").shouldBe(visible);
                $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate));

                $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
                $("[data-test-id=date] input").setValue(secondMeetingDate);
                $(".button").click();
                $("[data-test-id=replan-notification]").shouldBe(visible);
                $(withText("У вас уже запланирована")).shouldHave(Condition.exactText("У вас уже запланирована встреча на другую дату. Перепланировать?"));
                $(".button__content").click();
                $("[data-test-id=success-notification]").shouldBe(visible);
                $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
        }

}
