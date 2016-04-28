import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.ExtractableResponse;
import com.jayway.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertTrue;

public class ProfileTestIT{


    @BeforeClass
    public void setupClass(){
        RestAssured.urlEncodingEnabled = true;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void givenValidProfile_onCreate_profileCreatedWithValidLocationHeader(){
        final String location = createProfileAndGetLocation();
        given().get(location).then().statusCode(200).and().body("id", notNullValue());
    }

    @Test
    public void givenValidProfile_onDelete_profileCanNoLongerBeRetrieved(){
        final String location = createProfileAndGetLocation();
        given().delete(location).then().statusCode(200);
        given().get(location).then().statusCode(404);
    }



    @Test
    public void givenValidProfile_onUpdate_profileCanBeRetrievedAndContainsNewValues(){
        final String location = createProfileAndGetLocation();
        final String newProfile = PROFILE.replace("@gmail.com", "@yahoo.com");
        given().contentType(ContentType.JSON).body(newProfile).when().put(location).then().statusCode(204);
        given().get(location).then().statusCode(200).extract().asString().contains("@yahoo.com");
    }

    @Test
    public void givenInvalidLocation_onUpdate_404IsReturned(){
        given().contentType(ContentType.JSON).body(PROFILE).when().put("profiles/999999").then().statusCode(404);
    }

    @Test
    public void givenMultipleValidProfiles_getToRoot_returnsListOfProfiles(){
        final int numProfiles = 3;
        for (int i = 0; i < numProfiles; i++){
            createProfileAndGetLocation();
        }
        final ExtractableResponse<Response> getRootResponse = when().get("profiles").then().statusCode(200).extract();

        for (int i = 1; i <= numProfiles; i++){
            assertTrue(getRootResponse.body().asString().contains(Integer.toString(i)));
        }
    }


    private String createProfileAndGetLocation() {
        final ExtractableResponse<Response> createResponse = given().contentType(ContentType.JSON).body(PROFILE).when().post("profiles").then().statusCode(201).extract();
        return createResponse.header("location").replace("http://localhost:8080/","");
    }

    private static final String PROFILE = "{\"firstName\":\"Jason\",\"lastName\":\"Leezer\",\"userName\":\"jleezer\",\"emailAddress\":\"jleezer@gmail.com\"}";
}
