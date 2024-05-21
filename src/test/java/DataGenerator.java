import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;
import lombok.Value;

public class DataGenerator {
    private DataGenerator() {
    }
    private static final Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity() {
        String[] cities = {"Москва", "Санкт-Петербург", "Екатеринбург", "Казань", "Нижний Новгород", "Самара", "Омск", "Челябинск", "Ростов-на-Дону", "Уфа"};
        Random random = new Random();
        int index = random.nextInt(cities.length);
        String city = cities[index];
        return city;
    }

    public static String generateName() {
        String firstNamename = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = lastName + " " + firstNamename;
        return name;
    }

    public static String generatePhone() {
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}