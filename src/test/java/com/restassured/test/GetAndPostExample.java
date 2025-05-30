package com.restassured.test;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static io.restassured.RestAssured.then;

public class GetAndPostExample {

@Test
public void testGet() {
 RestAssured.baseURI = "https://reqres.in/api";
 RequestSpecification httpRequest = RestAssured.given();

 Response res = httpRequest.queryParam("page", "2").get("/users");
 ResponseBody body = res.body();

 String rbody = body.asString();
 System.out.println(rbody);


}

 @Test
 public void testGetMile() {
  RestAssured.baseURI = "https://reqres.in/api";

  when().
          get("/users?page=2").
          then().
          statusCode(200).
          body("data.size()", is(6)).
          body("data.first_name", hasItems("George", "Rachel"));
 }

 @Test
public void testPost() {

 JSONObject request = new JSONObject();

 request.put("name", "Ernesto Perez");
request.put("job", "QA Automation");

 RestAssured.baseURI = "https://reqres.in/api";

 given().
header("Content-Type", "application/json").
 contentType(ContentType.JSON).
 body(request.toJSONString()).
 when().
post("/users").
then().
 statusCode(201).
 log().all();
}
}