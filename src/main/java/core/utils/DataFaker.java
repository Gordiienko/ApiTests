package core.utils;

import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataFaker {

    private static Faker faker = new Faker();

    public static String getPetPhotoUrl() {
        return faker.internet().url();
    }

    public static String getPetName() {
        return faker.dog().name();
    }

    public static int getPetId() {
        return faker.number().numberBetween(0, 99);
    }

    public static String getUserFirstName() {
        return faker.name().firstName();
    }

    public static String getUserLastName() {
        return faker.name().lastName();
    }

    public static String getUserEmail() {
        return faker.internet().emailAddress();
    }

    public static int getUserStatus() {
        return faker.number().numberBetween(1,99);

    }

    public static String getUserPhone() {
        return faker.phoneNumber().phoneNumber();

    }

    public static String getUserName() {
        return faker.name().username();

    }

    public static int getUserId() {
        return faker.number().numberBetween(1,99);

    }


    public static String getUserPassword() {
        return faker.internet().password();

    }

}
