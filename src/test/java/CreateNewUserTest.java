
import core.models.user.UpdateUserModel;
import core.models.user.UserModel;
import core.utils.User;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.Map;

import static core.Endpoints.*;

public class CreateNewUserTest extends BaseTest {
    SoftAssertions softAssertions = new SoftAssertions();
    static int userId;
    static String userName;
    static String valueMessage;
    static String userPassword;

    @Test(priority = 1)
    public void checkUserMessageOfBody() {
        UserModel userModel = UserModel.builder()
                .firstName(User.getUserFirstName())
                .lastName(User.getUserLastName())
                .username(User.getUserName())
                .email(User.getUserEmail())
                .password(User.getUserPassword())
                .id(User.getUserId())
                .phone(User.getUserPhone())
                .userStatus(User.getUserStatus())
                .build();
        ValidatableResponse userResponse = RestAssured
                .given()
                .body(userModel)
                .when()
                .post(USER)
                .then()
                .statusCode(200);
        UpdateUserModel newUserModel = userResponse.extract().as(UpdateUserModel.class);
        userPassword = userModel.getPassword();
        userId = userModel.getId();
        userName = userModel.getUsername();
        softAssertions.assertThat(newUserModel.getMessage()).isNotEqualTo(0);
    }

    @Test(dependsOnMethods = "checkUserMessageOfBody")
    public void getUserByUserName() {
        ValidatableResponse userResponse =
                RestAssured
                        .given()
                        .pathParam("username", userName)
                        .when()
                        .get(USER_NAME)
                        .then()
                        .statusCode(200);
        UpdateUserModel userModel = userResponse.extract().as(UpdateUserModel.class);
        /*softAssertions.assertThat(userModel.toString()).as("das").isEqualTo();*/

        /*valueMessage = newUserModel.getMessage();
        softAssertions.assertThat(valueMessage).as("asd").isEqualTo(userId);*/
    }
    @Test(dependsOnMethods = "checkUserMessageOfBody")
    public void getUserWitLogin(){
        ValidatableResponse userResponse =
                RestAssured
                        .given()
                        .basePath("/user/login")
                        .queryParams("login",userName,userPassword)
                        .when()
                        .get(LOGIN)
                        .then()
                        .statusCode(200);

    }
}
