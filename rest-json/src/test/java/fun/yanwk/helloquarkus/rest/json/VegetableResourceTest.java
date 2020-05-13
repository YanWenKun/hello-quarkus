package fun.yanwk.helloquarkus.rest.json;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class VegetableResourceTest {

    @Test
    public void testList() {
        given()
            .when().get("/vegetables")
            .then()
            .statusCode(200)
            .body("$.size()", is(2),
                "name", containsInAnyOrder("萝卜", "黄瓜"),
                "description", containsInAnyOrder("又名大根", "又名小根"));
    }
}
