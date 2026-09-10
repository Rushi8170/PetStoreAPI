package com.api.tests;

import static io.restassured.RestAssured.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndpointsProperties;
import com.api.payloads.User;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserTestCases2 {
	Faker faker;
	User userPayload;
	Logger logger;

	@BeforeClass
	public void setUp() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		logger = LogManager.getLogger(this.getClass());
	}

	@Test(priority = 1)
	public void createUserTest() {
		Response response = UserEndpointsProperties.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority = 2)
	public void retriveUserTest() {
		Response response = UserEndpointsProperties.readUser(this.userPayload.getUsername());
		response.then().log().all();
	}

	@Test(priority = 3)
	public void updateByUsername() {

		logger.info("***Updating user***");

		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		Response response = UserEndpointsProperties.updateUser(this.userPayload.getUsername(), userPayload);
		response.then().log().all().statusCode(200);

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***User Updated***");

		Response responseAfterUpdate = UserEndpointsProperties.readUser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().all();
	}
	
	@Test(priority = 4)
	public void deleteUserTest() {
		Response response = UserEndpointsProperties.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
	}
}
