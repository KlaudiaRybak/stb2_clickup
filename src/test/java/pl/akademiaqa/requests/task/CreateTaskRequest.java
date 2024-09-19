package pl.akademiaqa.requests.task;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import pl.akademiaqa.dto.requests.CreateTaskRequestDto;
import pl.akademiaqa.dto.response.CreateTaskResponseDto;
import pl.akademiaqa.properties.ClickUpProperties;
import pl.akademiaqa.requests.BaseRequest;
import pl.akademiaqa.url.ClickUpUrl;

import static io.restassured.RestAssured.given;

public class CreateTaskRequest {

    // nadpisywanie metod - czyli dwie metody, ale o różnych przekazywanych parrametrach
    public static Response createTask(JSONObject task, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(task.toString())
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .log().ifError()                                                 // logowanie błędów
                .extract()
                .response();
    }

    public static CreateTaskResponseDto createTask(CreateTaskRequestDto taskDto, String listId) {
        return given()
                .spec(BaseRequest.requestSpecWithLogs())
                .body(taskDto)                                                   // .toString() już niepotrzebne
                .when()
                .post(ClickUpUrl.getTasksUrl(listId))
                .then()
                .statusCode(HttpStatus.SC_OK)                                    // należy dodać sprawdzenie status code w .then (po deserialzacji będzie brak dostępu w asercji)
                .log().ifError()                                                 // logowanie błędów
                .extract()
                .response()                                                      // deserializacja, zamiana zwracanego jsona na obiekt z określonymi polami
                .as(CreateTaskResponseDto.class);                                // obiekt klasy CreateTaskResponseDto
    }
}
