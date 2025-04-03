package com.techmapi.auto.controller;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.techmapi.auto.datamodel.CreateUserResponse;
import com.techmapi.auto.datamodel.UserData;
import com.techmapi.auto.utils.Properties;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.File;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

public class ReqResAPIController {
	
    private RequestSpecification requestSpecification;
    private ResponseSpecification responseSpecification;

	
	public ReqResAPIController() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("");
        requestSpecBuilder.setContentType(ContentType.JSON);
        requestSpecBuilder.log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectContentType(ContentType.JSON);
        responseSpecBuilder.expectStatusCode(Matchers.both(Matchers.greaterThanOrEqualTo(200)).and(Matchers.lessThan(300)));
        responseSpecBuilder.log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    /**
     * Add a new User
     */
    public Response createUser(String userDataFilePath) {
        return given(requestSpecification)
                .body(new File(userDataFilePath))
                .when()
                .post(Properties.CREATE_USER_END_POINT)
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }
    
   public CreateUserResponse createUser(UserData user) {

    	return given(requestSpecification)
    			.body(user)
    			.when()
    			.post(Properties.CREATE_USER_END_POINT)
    			.then()
    			.spec(responseSpecification)
    			.extract()
    			.as(CreateUserResponse.class);
    }

    public Response getUser(String userId) {
    	return given(requestSpecification)
    			.pathParam("userId", userId)
                .get(Properties.GET_USER_END_POINT)                
                .then()
                .spec(responseSpecification)
                .extract()
                .response();
    }
    
    /*public UserData getUserAsUserDataObject(String userId) {
    	return given(requestSpecification)
    			.pathParam("userId", userId)
                .get(Properties.GET_USER_END_POINT)                
                .then()
                .spec(responseSpecification)
                .extract()
                .as(UserData.class);
    }*/
    
    public Response getUsersOnPage(String pageNo) {
    	System.out.println("Get Users on Page Endpoint is :: " + Properties.GET_USERS_ON_PAGE_END_POINT);
    	return given(requestSpecification)
    			.queryParam("pageNo", pageNo)
    			.get(Properties.GET_USERS_ON_PAGE_END_POINT)                
    			.then()
    			.spec(responseSpecification)
    			.extract()
    			.response();
    }
    
    public Response updateUser(String userId, String filePath) {
    	System.out.println("Update Users on Page Endpoint is :: " + Properties.UPDATE_USER_END_POINT);
    	return given(requestSpecification)
    			.pathParam("userId", userId)
    			.body(new File(filePath))
    			.put(Properties.UPDATE_USER_END_POINT)                
    			.then()
    			.spec(responseSpecification)
    			.extract()
    			.response();
    }
    
    public Response deleteUser(String userId) {
    	System.out.println("Delete Users on Page Endpoint is :: " + Properties.DELETE_USER_END_POINT);
    	return given(requestSpecification)
    			.pathParam("userId", userId)
    			.delete(Properties.DELETE_USER_END_POINT)                
    			.then()
    			.statusCode(204)
    			.extract()
    			.response();
    }
      
}
