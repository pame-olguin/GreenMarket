package com.greenmarket.tests;

import com.greenmarket.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.json.simple.JSONObject;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest extends BaseTest {

    private static String userId;

    //Test GET
    @Test
    @Order(1)
    public void testGetUsers() {
        Response response = RestAssured
                .given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        System.out.println("GET /users → Status: " + response.getStatusCode());

        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.getHeader("Content-Type"), containsString("application/json"));
        assertThat(response.asString(), not(emptyOrNullString()));
        assertThat(response.jsonPath().getList("id"), is(not(empty())));
    }

    //Test POST
    @Test
    @Order(2)
    public void testCreateUser() {
        JSONObject request = new JSONObject();
        request.put("username", "maria");
        request.put("email", "maria@example.com");
        request.put("password", "123456");
        request.put("role", "user");

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .post("/users")
                .then()
                .extract()
                .response();

        System.out.println("POST /users → Status: " + response.getStatusCode());

        assertThat(response.getStatusCode(), equalTo(201));
        assertThat(response.jsonPath().getString("id"), notNullValue());

        // Guarda el ID para usarlo en PUT y DELETE
        userId = response.jsonPath().getString("id");
        System.out.println("Nuevo userId: " + userId);
    }

    // Test PUT
    @Test
    @Order(3)
    public void testUpdateUser() {
        JSONObject request = new JSONObject();
        request.put("username", "maria_updated");

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(request.toJSONString())
                .when()
                .put("/users/" + userId)
                .then()
                .extract()
                .response();

        System.out.println("PUT /users/" + userId + " → Status: " + response.getStatusCode());

        assertThat(response.getStatusCode(), equalTo(200));
        assertThat(response.jsonPath().getString("username"), equalTo("maria_updated"));
    }

    // Test DELETE
    @Test
    @Order(4)
    public void testDeleteUser() {
        Response response = RestAssured
                .given()
                .when()
                .delete("/users/" + userId)
                .then()
                .extract()
                .response();

        System.out.println("DELETE /users/" + userId + " → Status: " + response.getStatusCode());

        assertThat(response.getStatusCode(), anyOf(equalTo(200), equalTo(204)));
    }
}
