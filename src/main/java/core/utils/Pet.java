package core.utils;

import lombok.Getter;

@Getter
public class Pet extends DataFaker {
    static String petName;
    static int randomPetId;
    static String petUrl;

    public Pet() {
        this.petName = getPetName();
        this.randomPetId = getPetId();
        this.petUrl = getPetPhotoUrl();

    }

}
