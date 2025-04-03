package com.techmapi.auto.tests;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;
import com.techmapi.auto.controller.ReqResAPIController;
import com.techmapi.auto.datamodel.CreateUserResponse;
import com.techmapi.auto.datamodel.UserData;

import io.restassured.response.Response;

public class ReqResTests {

//	@Test
	public void createUser() {
		UserData user = new UserData();
		 Faker faker = new Faker();
        // Generate fake data        
		user.setName(faker.name().fullName());
		user.setJob(faker.job().title());
		ReqResAPIController controller = new ReqResAPIController();
		CreateUserResponse createUserRes = controller.createUser(user);
		System.out.println("Generated Users id is :: " + createUserRes.getId());
		System.out.println(createUserRes.toString());
	}
	
//	@Test
	public void getUser() {		
		ReqResAPIController controller = new ReqResAPIController();
		Response userRes = controller.getUser("2");
		System.out.println("User retrieved is  :: " +userRes.asPrettyString() );
	}
	
	
//	@Test
	public void getUserList() {		
		ReqResAPIController controller = new ReqResAPIController();
		Response users = controller.getUsersOnPage("2");
		System.out.println("Users retrieved is  :: " + users.asPrettyString() );
	}
	
	
//	@Test
	public void updateUser() {		
		ReqResAPIController controller = new ReqResAPIController();
		Response updUserRes = controller.updateUser("2", "./src/test/resources/updateUser.json");
		System.out.println("Response Received  :: " + updUserRes.asPrettyString() );
	}
	
	@Test
	public void deleteUser() {		
		ReqResAPIController controller = new ReqResAPIController();
		Response deleteUserRes = controller.deleteUser("2");
		System.out.println("Response Received  :: " + deleteUserRes.asPrettyString() );
	}
}
