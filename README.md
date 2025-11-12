# 🌿 GreenMarket API Test Suite

Este proyecto contiene un **conjunto de pruebas automatizadas** para la API de **GreenMarket**, desarrolladas con **Java 17**, **JUnit 5** y **REST Assured**.  
Permite validar los endpoints REST de forma eficiente y generar reportes automáticos en formato HTML.

---

## 🧩 Tecnologías utilizadas

- ☕ **Java 17**
- 🧪 **JUnit 5** – Framework de pruebas
- 🌐 **REST Assured** – Pruebas de APIs REST
- 🔍 **Hamcrest** – Matchers para validaciones legibles
- 🧱 **JSON Simple** – Construcción de objetos JSON para peticiones `POST` y `PUT`
- 🧾 **Maven Surefire** – Ejecución de pruebas y generación de reportes HTML

---

## ⚙️ Configuración del proyecto

### 📁 Estructura de carpetas

GreenMarket/

├── pom.xml

├── README.md

└── src/

    └── test/java/com/greenmarket/
    
        ├── base/
        
        │   └── BaseTest.java
        
        └── tests/
        
            └── UserTest.java

## 🚀 Ejecución de las pruebas

### ▶️ Desde la terminal

Asegúrate de estar en la raíz del proyecto (donde está el `pom.xml`) y ejecuta:

bash
mvn clean test

---

## Visualizar el reporte HTML

El reporte se genera automáticamente después de ejecutar las pruebas.
Puedes abrirlo en tu navegador:

target/site/surefire-report.html

---

## Descripción de las pruebas (UserTest.java)

| Método     | Descripción                 | Endpoint      | Aserciones clave                                      |
| ---------- | --------------------------- | ------------- | ----------------------------------------------------- |
| **GET**    | Obtiene todos los usuarios  | `/users`      | Status 200, header `application/json`, lista no vacía |
| **POST**   | Crea un nuevo usuario       | `/users`      | Status 201, campo `id` no nulo                        |
| **PUT**    | Actualiza el usuario creado | `/users/{id}` | Status 200, `username` actualizado                    |
| **DELETE** | Elimina el usuario creado   | `/users/{id}` | Status 200 o 204                                      |

---

Notas técnicas

El archivo BaseTest.java configura la URL base de la API:
RestAssured.baseURI = "https://690e0883bd0fefc30a033951.mockapi.io/api/v1";

-El orden de ejecución está controlado con @TestMethodOrder(OrderAnnotation.class).
-Hamcrest se utiliza para todas las validaciones (assertThat, equalTo, notNullValue, etc.).
-Se puede integrar con Jenkins o GitHub Actions para CI/CD.

Autor

Pamela Olguín Solar
📍 Iquique, Chile
📧 pame.ertek@gmail.com
