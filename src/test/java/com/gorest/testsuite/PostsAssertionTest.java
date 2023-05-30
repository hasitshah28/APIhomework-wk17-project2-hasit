package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
        RestAssured.basePath = "public/v2";
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
    }
    //    1. Verify the if the total record is 25
    @Test
    public void verifyTotal(){
        response.body("size()",equalTo(25));
    }

    // 2. Verify the if the title of id = 39295 is equal to "Sapiente aestivus valde aro vacuus saepe."
    @Test
    public void verifyTitle(){
        response.body("findAll{it.id== 39686}.title",hasItem("Ustilo quasi aveho quos caveo charisma."));
    }
    //  3. Check the single user_id in the Array list (2272684)
    @Test
    public void verifyUserId(){
        response.body("[1].user_id",equalTo(2302180));
    }
    //4. Check the multiple ids in the ArrayList (39292,39297,39304)
    @Test
    public void verifyMultipleId(){
        response.body("id",hasItems(39686,39653,39294));
    }
//5. Verify the body of userid = 2272666 is equal  "Contabesco tamen solio. Vivo tamdiu arbustum. Casus viridis vox. Sub suscipio adsum. Est careo minus. Coniecto terra congregatio. Versus doloremque volubilis. Dedecor corporis victus. Thorax tremo aequus. Vero corroboro dolorem. Solus sursum advenio. Sunt abutor cunabula. At conor est. Pax officiis succedo. Concido sumo atque. Carcer sui delectatio. Deduco agnitio volo. Appello arbustum qui."

    @Test
    public void verifyBody(){
        response.body("findAll{it.user_id==2302180}.body",hasItem("Aro eius cursim. Sol cum addo. Amplexus quis argentum. Virga aeger considero. Earum bellicus unde. Careo arca vos. Aestas adeptio sophismata. In sumptus apud. Alter succedo vero. Fugit carmen optio. Vomer et asperiores. Catena copia subnecto. " +
                "Thermae aut certus. Utpote aliquid volup. " +
                "Vero solum cunctatio. Qui vilitas cimentarius. Subito synagoga truculenter."));
    }
}
