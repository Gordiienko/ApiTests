package core.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public  class PetModel {
    public Integer id;
    public String category;
    public String name;
    public ArrayList<String> photoUrls;
    public ArrayList<Object> tags;
    public String status;

    @Getter
    public static class Category {
        public int id;
        public String name;

    }

    @Getter
    public static class Tag {
        public int id;
        public String name;
    }

}
