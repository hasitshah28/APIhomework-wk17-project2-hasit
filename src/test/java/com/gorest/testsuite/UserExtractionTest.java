package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";

        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allId = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total Ids are : "  + allId );
        System.out.println("------------------End of Test---------------------------");
    }
    // 2. Extract the all Names
    @Test
    public void test002() {
        List<Integer> allNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total names are : " + allNames  );
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the name of 5th object
    @Test
    public void test003() {
        String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of 5th object is : " + name );
        System.out.println("------------------End of Test---------------------------");

    }
    // 4.Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String>nameObject = response.extract().path("findAll{it.status=='inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all object whose status is inactive : " + nameObject);
        System.out.println("------------------End of Test---------------------------");

    }
    // 5.Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<String> genderName = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender of all object whose status is active : " + genderName );
        System.out.println("------------------End of Test---------------------------");

    }
    // 6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<String> gender = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of the object whose gender is female" + gender );
        System.out.println("------------------End of Test---------------------------");

    }
    // 7. Get all the emails of the object where status = inactive
    @Test
    public void test007() {
        List<?> emailObject = response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the emails of the object where status = inactive" + emailObject );
        System.out.println("------------------End of Test---------------------------");

    }
    //  8. Get the ids of the object where gender = male
    @Test
    public void test008() {
        List<?> idsObject = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Theids of the object where gender = male" + idsObject );
        System.out.println("------------------End of Test---------------------------");

    }
    // 9. Get all the status
    @Test
    public void test009() {
        List<String> allStatus = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All status : " + allStatus  );
        System.out.println("------------------End of Test---------------------------");
    }
    // 10. Get email of the object where name = Baala Chopra
    @Test
    public void test010() {
        String email = response.extract().path("[4].email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email of object name : " + email );
        System.out.println("------------------End of Test---------------------------");
    }
    //  11. Get gender of id = 5471
    @Test
    public void test011() {
        String gender = response.extract().path("[2].gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender of ID : " + gender );
        System.out.println("------------------End of Test---------------------------");
    }
}
