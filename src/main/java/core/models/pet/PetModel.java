package core.models.pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public  class PetModel {

    public Long id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public List<Tag> tags;
    public String status;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Tag {
        public int id;
        public String name;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Category {
        public int id;
        public String name;
    }

}
