package pl.akademiaqa.dto.task.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.ToString;

@Getter                                                 // getter z lomboka
@ToString                                               // metoda toString z lomboka
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTaskStatusResponseDto {
    private String status;
    private String type;


    // kod zbędny, jeżeli stosujemy bibliotekę lombok
//    public String getStatus() {
//        return status;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    @Override
//    public String toString() {
//        return "CreateTaskStatusResponseDto{" +
//                "status='" + status + '\'' +
//                ", type='" + type + '\'' +
//                '}';
//    }
}
