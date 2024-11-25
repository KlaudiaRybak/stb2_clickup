package pl.akademiaqa.tests.e2e;

import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.akademiaqa.dto.task.requests.CreateTaskRequestDto;
import pl.akademiaqa.requests.list.CreateListRequest;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;
import pl.akademiaqa.requests.task.CreateTaskRequest;
import pl.akademiaqa.requests.task.UpdateTaskRequest;

class UpdateTaskE2ETest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTaskE2ETest.class);          // logowanie na konsolę
    private static String spaceName = "SPACE E2E JAVA";
    private static String listName = "ZADANIA";
    private static String taskName = "Przetestować ClickUp";
    private String spaceId;
    private String listId;
    private String taskId;

    @Test
    void updateTaskE2ETest() {
        spaceId = createSpaceStep();
        LOGGER.info("Space created with id: {}", spaceId);

        listId = createListStep();
        LOGGER.info("List created with id: {}", listId);

        taskId = createTaskStep();
        LOGGER.info("Task created with id: {}", taskId);

        updateTaskStep();
        closeTaskStep();
        deleteSpaceStep();
    }

    private String createSpaceStep() {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var response = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(spaceName);

        return jsonData.getString("id");
    }

    private String createListStep() {

        JSONObject list = new JSONObject();
        list.put("name", listName);

        final var response = CreateListRequest.createList(list, spaceId);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo(listName);

        return jsonData.getString("id");
    }

    private String createTaskStep() {
        // SERIALIZACJA + DESERIALIZACJA

//        JSONObject task = new JSONObject();
//        task.put("name", taskName);
//        task.put("description", "Ciekawe jak to działa");
//        task.put("status", "to do");
//        task.put("priority", JSONObject.NULL);
//        task.put("parent", JSONObject.NULL);
//        task.put("assignees", JSONObject.NULL);
//        task.put("archived", false);

        // zamiast przekazywać do requestu JSONObject - tworzymy zwykły obiekt w Javie POJO (Plain Old Java Object)
        // obiekty przetrzymujemy w request dto - dto - data transfer object - obiekty transferujące dane do testów
        // czyli SERIALIZACJA - zamiana POJO na Jsona - utworzenie obiektu POJO i przekazanie go do requestu i jego zamiana na jsona
        CreateTaskRequestDto taskDto = new CreateTaskRequestDto();
        taskDto.setName(taskName);                                              // ustawianie pól za pomocą utworzonych seterów
        taskDto.setDescription("Ciekawe jak to działa");
        taskDto.setStatus("to do");

        final var response = CreateTaskRequest.createTask(taskDto, listId);
        LOGGER.info("CREATE TASK RESPONSE OBJECT: {}", response);

        Assertions.assertThat(response.getName()).isEqualTo(taskName);
        Assertions.assertThat(response.getDescription()).isEqualTo("Ciekawe jak to działa");

//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
//        JsonPath jsonData = response.jsonPath();
//        Assertions.assertThat(jsonData.getString("name")).isEqualTo(taskName);
//        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Ciekawe jak to działa");
//        return jsonData.getString("id");

        return response.getId();
    }

    private void updateTaskStep() {

//        UpdateTaskRequestDto taskDto = new UpdateTaskRequestDto();
//        taskDto.setName("Zmieniam nazwę zadania");
//        taskDto.setDescription("Zmieniam opis zadania");

        // put z pojo - gdy nie wszystkie pola są ustawione, to pozostałe pola ustawiane są domyślnie ustawiane na null, boolean jako fałsz
        // jeżeli nie chcemy zmieniać wszystkich pól, to i tak należy utworzyć cały obiekt i wysłać wszystkie pola
        // dlatego lepiej nie wysyłać pojo, ale zwykły jsonObject (w tym przypadku put działa jak patch)

        JSONObject updateTask = new JSONObject();
        updateTask.put("name", "Zmieniam nazwę zadania");
        updateTask.put("description", "Zmieniam opis zadania");

        final var response = UpdateTaskRequest.updateTask(updateTask, taskId);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("name")).isEqualTo("Zmieniam nazwę zadania");
        Assertions.assertThat(jsonData.getString("description")).isEqualTo("Zmieniam opis zadania");
    }

    private void closeTaskStep() {

        JSONObject closeTask = new JSONObject();
        closeTask.put("status", "complete");

        final var response = UpdateTaskRequest.updateTask(closeTask, taskId);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);

        JsonPath jsonData = response.jsonPath();
        Assertions.assertThat(jsonData.getString("status.status")).isEqualTo("complete");
    }

    private void deleteSpaceStep() {

        final var response = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
