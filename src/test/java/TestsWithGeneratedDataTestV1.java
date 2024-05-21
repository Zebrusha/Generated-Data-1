import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestsWithGeneratedDataTestV1 {
                @BeforeEach
                void setup() {
                        open("http://localhost:9999");
                }

                @Test
                void shouldSuccessfulPlanAndReplanMeeting() {
                    var daysToAddForFirstMeeting = 4;
                    var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
                    var daysToAddForSecondMeeting = 7;
                    var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
                    open("http://localhost:9999");
                    $("[data-test-id=city] input").setValue(DataGenerator.generateCity());
                    $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
                    $("[data-test-id=date] input").setValue(firstMeetingDate);
                    $("[data-test-id=name] input").setValue(DataGenerator.generateName());
                    $("[data-test-id=phone] input").setValue(DataGenerator.generatePhone());
                    $("[data-test-id=agreement]").click();
                    $(".button").click();
                    $("[data-test-id=success-notification]").shouldBe(visible);
                    $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + firstMeetingDate));

                    $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
                    $("[data-test-id=date] input").setValue(secondMeetingDate);
                    $(".button").click();
                    $("[data-test-id=replan-notification]").shouldBe(visible);
                    $(withText("У вас уже запланирована")).shouldHave(Condition.exactText("У вас уже запланирована встреча на другую дату. Перепланировать?\n" +
                            "\n" +
                            "Перепланировать"));
                    $(".button__content").click();
                    $("[data-test-id=success-notification]").shouldBe(visible);
                    $(".notification__content").shouldHave(Condition.exactText("Встреча успешно запланирована на " + secondMeetingDate));
                }
}


