package browserTesting;


    import io.restassured.response.Response;
    import org.apache.commons.io.FileUtils;
    import org.testng.annotations.Test;

    import static io.restassured.RestAssured.given;
    import static io.restassured.RestAssured.when;
    import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
    import static org.junit.Assert.assertThat;


public class FirstAPiTest {

    private String jsonSchema = "{\n" +
            "\t\"definitions\": {},\n" +
            "\t\"$schema\": \"http://json-schema.org/draft-07/schema#\", \n" +
            "\t\"$id\": \"https://example.com/object1619539667.json\", \n" +
            "\t\"title\": \"Root\", \n" +
            "\t\"type\": \"object\",\n" +
            "\t\"required\": [\n" +
            "\t\t\"data\",\n" +
            "\t\t\"support\"\n" +
            "\t],\n" +
            "\t\"properties\": {\n" +
            "\t\t\"data\": {\n" +
            "\t\t\t\"$id\": \"#root/data\", \n" +
            "\t\t\t\"title\": \"Data\", \n" +
            "\t\t\t\"type\": \"object\",\n" +
            "\t\t\t\"required\": [\n" +
            "\t\t\t\t\"id\",\n" +
            "\t\t\t\t\"email\",\n" +
            "\t\t\t\t\"first_name\",\n" +
            "\t\t\t\t\"last_name\",\n" +
            "\t\t\t\t\"avatar\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"id\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/data/id\", \n" +
            "\t\t\t\t\t\"title\": \"Id\", \n" +
            "\t\t\t\t\t\"type\": \"integer\",\n" +
            "\t\t\t\t\t\"default\": 0\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"email\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/data/email\", \n" +
            "\t\t\t\t\t\"title\": \"Email\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"first_name\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/data/first_name\", \n" +
            "\t\t\t\t\t\"title\": \"First_name\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"last_name\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/data/last_name\", \n" +
            "\t\t\t\t\t\"title\": \"Last_name\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"avatar\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/data/avatar\", \n" +
            "\t\t\t\t\t\"title\": \"Avatar\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            ",\n" +
            "\t\t\"support\": {\n" +
            "\t\t\t\"$id\": \"#root/support\", \n" +
            "\t\t\t\"title\": \"Support\", \n" +
            "\t\t\t\"type\": \"object\",\n" +
            "\t\t\t\"required\": [\n" +
            "\t\t\t\t\"url\",\n" +
            "\t\t\t\t\"text\"\n" +
            "\t\t\t],\n" +
            "\t\t\t\"properties\": {\n" +
            "\t\t\t\t\"url\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/support/url\", \n" +
            "\t\t\t\t\t\"title\": \"Url\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t\"text\": {\n" +
            "\t\t\t\t\t\"$id\": \"#root/support/text\", \n" +
            "\t\t\t\t\t\"title\": \"Text\", \n" +
            "\t\t\t\t\t\"type\": \"string\",\n" +
            "\t\t\t\t\t\"default\": \"\",\n" +
            "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\n" +
            "\t}\n" +
            "}\n";
        private String jsonSchemaSingle = "{\n" +
                "\t\"definitions\": {},\n" +
                "\t\"$schema\": \"http://json-schema.org/draft-07/schema#\", \n" +
                "\t\"$id\": \"https://example.com/object1619540007.json\", \n" +
                "\t\"title\": \"Root\", \n" +
                "\t\"type\": \"object\",\n" +
                "\t\"required\": [\n" +
                "\t\t\"data\",\n" +
                "\t\t\"support\"\n" +
                "\t],\n" +
                "\t\"properties\": {\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"$id\": \"#root/data\", \n" +
                "\t\t\t\"title\": \"Data\", \n" +
                "\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\"required\": [\n" +
                "\t\t\t\t\"id\",\n" +
                "\t\t\t\t\"name\",\n" +
                "\t\t\t\t\"year\",\n" +
                "\t\t\t\t\"color\",\n" +
                "\t\t\t\t\"pantone_value\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"id\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/data/id\", \n" +
                "\t\t\t\t\t\"title\": \"Id\", \n" +
                "\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t2\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"name\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/data/name\", \n" +
                "\t\t\t\t\t\"title\": \"Name\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t\"fuchsia rose\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"year\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/data/year\", \n" +
                "\t\t\t\t\t\"title\": \"Year\", \n" +
                "\t\t\t\t\t\"type\": \"integer\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t2001\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"default\": 0\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"color\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/data/color\", \n" +
                "\t\t\t\t\t\"title\": \"Color\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t\"#C74375\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"pantone_value\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/data/pantone_value\", \n" +
                "\t\t\t\t\t\"title\": \"Pantone_value\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t\"17-2031\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                ",\n" +
                "\t\t\"support\": {\n" +
                "\t\t\t\"$id\": \"#root/support\", \n" +
                "\t\t\t\"title\": \"Support\", \n" +
                "\t\t\t\"type\": \"object\",\n" +
                "\t\t\t\"required\": [\n" +
                "\t\t\t\t\"url\",\n" +
                "\t\t\t\t\"text\"\n" +
                "\t\t\t],\n" +
                "\t\t\t\"properties\": {\n" +
                "\t\t\t\t\"url\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/support/url\", \n" +
                "\t\t\t\t\t\"title\": \"Url\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t\"https://reqres.in/#support-heading\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"text\": {\n" +
                "\t\t\t\t\t\"$id\": \"#root/support/text\", \n" +
                "\t\t\t\t\t\"title\": \"Text\", \n" +
                "\t\t\t\t\t\"type\": \"string\",\n" +
                "\t\t\t\t\t\"default\": \"\",\n" +
                "\t\t\t\t\t\"examples\": [\n" +
                "\t\t\t\t\t\t\"To keep ReqRes free, contributions towards server costs are appreciated!\"\n" +
                "\t\t\t\t\t],\n" +
                "\t\t\t\t\t\"pattern\": \"^.*$\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t}\n" +
                "\t\t}\n" +
                "\n" +
                "\t}\n" +
                "}\n";
        public String jsonSchemaUnknown = "{}";
        public String jsonSchemaPost = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"cityslicka\"\n" +
                "}";
    @Test
    public void getSingleUserTest(){
      Response response = when().get("https://reqres.in/api/users/2");
      response.then().statusCode(200).assertThat().body(matchesJsonSchema(jsonSchema));

    }
    @Test
    public void getSingleResourseTest(){
        Response response = when().get("https://reqres.in/api/unknown/2");
        response.then().statusCode(200).assertThat().body(matchesJsonSchema(jsonSchemaSingle));

    }
    @Test
    public void getUnknownTest() {
        Response response = when().get("https://reqres.in/api/unknown/23");
        response.then().statusCode(404).assertThat().body(matchesJsonSchema(jsonSchemaUnknown));
    }

    @Test
    public void postTest () {
        Response response = given().body(matchesJsonSchema(jsonSchemaPost))
        .when().post("https://reqres.in/api/login");
        response.then().statusCode(200);
    }
}
