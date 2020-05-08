package fun.yanwk.hello.helloquarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/**
 * 使用 QuarkusTest runner，会指示 JUnit 先启动程序，再执行测试
 */
@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .when()
                .get("/hello")
            .then()
                .statusCode(200) // HTTP OK
                .body(is("你好，世界！"));
    }

    // REST Assured 挺好使
    @Test
    public void testGreetingEndpoint() {
        String uuid = UUID.randomUUID().toString();
        given()
                .pathParam("name", uuid)
            .when()
                .get("/hello/greeting/{name}")
            .then()
                .statusCode(200)
                .body(is("Hello, " + uuid + "!"));
    }

}
