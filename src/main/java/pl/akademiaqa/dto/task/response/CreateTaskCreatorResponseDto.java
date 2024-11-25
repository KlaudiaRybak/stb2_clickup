package pl.akademiaqa.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter                                                 // getter z lomboka
@ToString                                               // metoda toString z lomboka
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskCreatorResponseDto {
    @JsonProperty("username")
    private String userName;
    private String email;

    // kod zbędny, jeżeli stosujemy bibliotekę lombok
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public String toString() {
//        return "CreateTaskCreatorResponseDto{" +
//                "userName='" + userName + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
}
