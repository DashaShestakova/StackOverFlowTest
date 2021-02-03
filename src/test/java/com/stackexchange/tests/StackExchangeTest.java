package com.stackexchange.tests;

import com.stackexchange.api.AnswersApi;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class StackExchangeTest {

    public static final long MAX_TIMEOUT = 10000L;
    private AnswersApi.AnswersApiBuilder answersApiBuilder;

    @Before
    public void setUp() {
        answersApiBuilder = AnswersApi
                .builder()
                .filter("default")
                .sort("activity")
                .order("desc")
                .pageSize(10)
                .page(1)
                .site("stackoverflow");
        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();
    }

    @Test
    public void testStatusCodeIsSuccess(){
        RestAssured.given()
                .get(answersApiBuilder.build().buildEndpoint())
                .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public void testOwnerNumbersFromApiIsLessOrEqualsTen(){
        RestAssured.given()
                .get(answersApiBuilder.build().buildEndpoint())
                .then()
                .statusCode(200)
                .and()
                .body("items.size()",lessThan(11));
    }

    @Test
    public void testEachElementOfArrayContainsOwnerObject(){
        RestAssured.given()
                .get(answersApiBuilder.build().buildEndpoint())
                .then()
                .statusCode(200)
                .and()
                .body("items", everyItem(hasKey("owner")));
    }
    @Test
    public void testEachOwnerContainsDisplayNameAndUserID(){
        RestAssured.given()
                .get(answersApiBuilder.build().buildEndpoint())
                .then()
                .statusCode(200)
                .and()
                .body("items.owner", everyItem(hasKey("display_name")))
                .body("items.owner", everyItem(hasKey("user_id")));
    }

}
