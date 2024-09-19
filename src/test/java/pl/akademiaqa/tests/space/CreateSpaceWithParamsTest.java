package pl.akademiaqa.tests.space;

import org.apache.http.HttpStatus;
import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.requests.space.CreateSpaceRequest;
import pl.akademiaqa.requests.space.DeleteSpaceRequest;

import java.util.stream.Stream;

class CreateSpaceWithParamsTest {

    @ParameterizedTest(name = "Create space with space name {0}")
    @DisplayName("Create space with valid space name")
    @MethodSource("createSpaceData")
    void createSpaceTest(String spaceName) {

        JSONObject space = new JSONObject();
        space.put("name", spaceName);

        final var createResponse = CreateSpaceRequest.createSpace(space);
        Assertions.assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
        Assertions.assertThat(createResponse.jsonPath().getString("name")).isEqualTo(spaceName);
        final var spaceId = createResponse.jsonPath().getString("id");

        final var deleteResponse = DeleteSpaceRequest.deleteSpace(spaceId);
        Assertions.assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
    }

    private static Stream<Arguments> createSpaceData() {
        return Stream.of(
                Arguments.of("TEST SPACE"),
                Arguments.of("123"),
                Arguments.of("*")
        );
    }

}
