package com.greenmarket.base;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup() {
        // Configura la URL base de la API
        RestAssured.baseURI = "https://690e0883bd0fefc30a033951.mockapi.io/api/v1";
    }
}
