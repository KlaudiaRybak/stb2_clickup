package pl.akademiaqa.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter                                                 // getter z lomboka
@ToString                                               // metoda toString z lomboka
@JsonIgnoreProperties(ignoreUnknown = true)             //jackson ignoruje w deserializacji niewskazane pola
public class CreateTaskResponseDto {

    // obiekt klasy CreateTaskResponseDto będzie składał się z czterech pól i dwóch mniejszych obiektów
    private String id;
    private String name;
    @JsonProperty("text_content")                                   // nazewnictwo oryginalne w zwracanym jsonie
    private String textContent;
    private String description;
    private CreateTaskStatusResponseDto status;
    private CreateTaskCreatorResponseDto creator;


    // kod zbędny, jeżeli stosujemy bibliotekę lombok
//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getTextContent() {
//        return textContent;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public CreateTaskStatusResponseDto getStatus() {
//        return status;
//    }
//
//    public CreateTaskCreatorResponseDto getCreator() {
//        return creator;
//    }
//
//    @Override
//    public String toString() {
//        return "CreateTaskResponseDto{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", textContent='" + textContent + '\'' +
//                ", description='" + description + '\'' +
//                ", status=" + status +
//                ", creator=" + creator +
//                '}';
//    }
}
