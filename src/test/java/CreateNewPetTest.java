import core.model.PetModel;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import static core.model.Endpoints.PHOTOS;

public class CreateNewPetTest extends BaseTest {

    @Test
    public void checkPetsDateRequest() {
        PetModel petModel = PetModel.builder()
                .name("Rex")
                .category("Dogs")
                .tags()
                .photoUrls()
                .status("available")
                .build();
        /*ValidatableResponse response =*/
        RestAssured
                .given()
                .body(petModel)
                .when()
                .post(PHOTOS)
                .then()
                .statusCode(200);
       /* PetModel model = response.extract().as(PetModel.class);
        Integer petId = model.getId();

        Assertions.assertThat(petId).isNotEqualTo(0);*/
    }
}
