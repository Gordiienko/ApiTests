import core.model.Category;
import core.model.PetModel;
import core.model.Tag;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;

import static core.model.Endpoints.PET;

public class CreateNewPetTest extends BaseTest {

    @Test
    public void checkPetsDateRequest() {
        List<Tag> listTags = new ArrayList<>();
        listTags.add(new Tag(31,"Small dog"));
        listTags.add(new Tag(30,"Cute"));
        listTags.add(new Tag(20,"Silent"));
        List<String> listUrl = new ArrayList<>();
        listUrl.add("https://unsplash.com/photos/v3-zcCWMjgM");
        listUrl.add("https://unsplash.com/photos/T-0EW-SEbsE");
        listUrl.add("https://unsplash.com/photos/BJaqPaH6AGQ");

        PetModel petModel = PetModel.builder()
                .name("Rex")
                .category(new Category(10,"Dogs"))
                .tags(listTags)
                .photoUrls(listUrl)
                .status("available")
                .build();
        PetModel pet = RestAssured
                .given()
                .body(petModel)
                .when()
                .post(PET)
                .then()
                .statusCode(200).extract().as(PetModel.class);



        Assertions.assertThat(pet.getId()).isNotEqualTo(0);
        petModel.setId(pet.getId());
        Assertions.assertThat(pet).isEqualTo(petModel);
    }
}
