package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class UserAssertionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
       response.body("size",equalTo(20));
    }

    // 2. Verify the if the name of id = 5487 is equal to "Shubha Varma”
    @Test
    public void test002() {
        response.body("find{it.id==2272570}.name", equalTo("Shubha Varma"));
    }

    // 3. Check the single ‘Name’ in the Array list (Chandi Verma CPA)
    @Test
    public void test003() {
        response.body("[3].name", equalTo("Chandi Verma CPA"));

    }

    //4. Check the multiple ‘Names’ in the ArrayList ("Kanti Naik", "Amrita Jha", "Jitender Mishra")

    @Test
    public void test004() {
        response.body("name", hasItems("Kanti Naik", "Amrita Jha", "Jitender Mishra"));
    }

    //5. Verify the emai of userid = 5471 is equal “varma_shubha@west.example”
    @Test
    public void test005() {
        response.body("[1].email", equalTo("varma_shubha@west.example"));
    }

    //6. Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test006() {
        response.body("findAll{it.name=='Chandi Verma CPA'}.status", hasItem("active"));
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007() {
        response.body("findAll{it.name= 'Kanti Naik'}.gender", hasItem("male"));

    }
}
