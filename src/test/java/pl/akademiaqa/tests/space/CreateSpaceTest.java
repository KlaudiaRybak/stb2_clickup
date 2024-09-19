package pl.akademiaqa.tests.space;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

class CreateSpaceTest {

    private static final String SPACE_NAME = "MY SPACE FROM JAVA";
    @Test
    void createSpaceTest() {

        JSONObject space = new JSONObject();
        space.put("name", SPACE_NAME);

        final var createResponse = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(createResponse.jsonPath().getString("name")).isEqualTo(SPACE_NAME);
        final var spaceId = createResponse.jsonPath().getString("id");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

}
