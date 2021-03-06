import core.models.ErrorModel;
import core.models.pet.DeletePetModel;
import core.models.pet.PetModel;
import core.models.pet.UpdatePetModel;
import core.utils.Pet;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static core.Endpoints.*;

public class CreateNewPetTest extends BaseTest {
    SoftAssertions softAssertions = new SoftAssertions();
    static Long petId;


    @Test(priority = 1)
    public void checkPetsDateRequest() {
        List<PetModel.Tag> listTags = new ArrayList<>();
        listTags.add(new PetModel.Tag(31, "Small dog"));
        listTags.add(new PetModel.Tag(30, "Cute"));
        listTags.add(new PetModel.Tag(20, "Silent"));
        List<String> listUrl = new ArrayList<>();
        listUrl.add("https://unsplash.com/photos/v3-zcCWMjgM");
        listUrl.add("https://unsplash.com/photos/T-0EW-SEbsE");
        listUrl.add("https://unsplash.com/photos/BJaqPaH6AGQ");

        PetModel petModel = PetModel.builder()
                .name("Rex")
                .category(new PetModel.Category(10, "Dogs"))
                .tags(listTags)
                .photoUrls(listUrl)
                .status("available")
                .build();
        PetModel petResponse = RestAssured
                .given()
                .body(petModel)
                .when()
                .post(PET)
                .then()
                .statusCode(200).extract().as(PetModel.class);
        petId = petResponse.getId();
        softAssertions.assertThat(petId).isNotEqualTo(0);
        softAssertions.assertThat(petResponse)
                .as("All date in response must be the same as in request")
                .isEqualToIgnoringGivenFields(petModel, "id");
        softAssertions.assertAll();
    }

    @Test(dependsOnMethods = "checkPetsDateRequest")
    public void getPetByPetIdTest() {
        ValidatableResponse petResponse = RestAssured
                .given()
                .pathParam("id", petId)
                .when()
                .get(PET_BY_ID)
                .then()
                .statusCode(200);

        PetModel petModel = petResponse.extract().as(PetModel.class);
        softAssertions.assertThat(petModel.getName()).as("Pet name in response must be: 'Rex'")
                .isEqualTo("Rex");
        softAssertions.assertThat(petModel.getStatus()).as("Pet status must be: 'available'")
                .isEqualTo("available");
        softAssertions.assertAll();
    }

    @Test(dependsOnMethods = "checkPetsDateRequest")
    public void updatePetDate() {
        ValidatableResponse petResponse = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .pathParam("id", petId)
                .formParam("name", "Sky")
                .formParam("status", "sold")
                .when()
                .post(PET_BY_ID)
                .then()
                .statusCode(200);

        UpdatePetModel newPetModel = petResponse.extract().as(UpdatePetModel.class);
        softAssertions.assertThat(newPetModel.getMessage())
                .as("Value of message field in response must be equals to pet id")
                .isEqualTo(petId);
        PetModel pet = RestAssured
                .given()
                .pathParam("id", petId)
                .when()
                .get(PET_BY_ID)
                .then()
                .statusCode(200).extract().as(PetModel.class);


        softAssertions.assertThat(pet.getName()).as("New name of dog must be: 'Sky'")
                .isEqualTo("Sky");
        softAssertions.assertThat(pet.getStatus()).as("New status must be: 'sold'")
                .isEqualTo("sold");
        softAssertions.assertAll();
    }

    @Test(dependsOnMethods = "checkPetsDateRequest")
    public void deletePetTest() {

        ValidatableResponse petResponse = RestAssured
                .given()
                .pathParam("id", petId)
                .when()
                .delete(PET_BY_ID)
                .then()
                .statusCode(200);

        DeletePetModel deleteResponse = petResponse.extract().as(DeletePetModel.class);

        softAssertions.assertThat(deleteResponse.getMessage()).as("das")
                .isEqualTo(petId.toString());

        ValidatableResponse newPetResponse = RestAssured
                .given()
                .pathParam("id", petId)
                .when()
                .get(PET_BY_ID)
                .then()
                .statusCode(404);
        ErrorModel deletePetModel = newPetResponse.extract().as(ErrorModel.class);

        softAssertions.assertThat(deletePetModel.getCode()).as("asd")
                .isEqualTo(1);
        softAssertions.assertThat(deletePetModel.getType()).as("dasd")
                .isEqualTo("error");
        softAssertions.assertThat(deletePetModel.getMessage()).as("das")
                .isEqualTo("Pet not found");
        softAssertions.assertAll();
    }

    @Test(priority = 2)
    public void createPetWithRandomDate() {

        List<PetModel.Tag> newListTags = new ArrayList<>();
        newListTags.add(new PetModel.Tag(Pet.getPetId(), Pet.getPetName()));
        List<String> newListUrl = new ArrayList<>();
        newListUrl.add(Pet.getPetPhotoUrl());
        PetModel newPet = PetModel.builder()
                .name(Pet.getPetName())
                .category(new PetModel.Category(Pet.getPetId(), Pet.getPetName()))
                .tags(newListTags)
                .photoUrls(newListUrl)
                .status("sold")
                .build();
        PetModel petResponse = RestAssured
                .given()
                .body(newPet)
                .when()
                .post(PET)
                .then()
                .statusCode(200)
                .extract().as(PetModel.class);

        PetModel actualPetResponse = RestAssured
                .given()
                .basePath("/pet/findByStatus")
                .queryParam("status", "sold")
                .when()
                .get()
                .then()
                .statusCode(200).extract().as(PetModel.class);

        /*Assertions.assertThat(petResponse).as("dsa").isEqualTo(actualPetResponse);*/

    }

}
