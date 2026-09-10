package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.UserEndPoints;
import com.api.endpoints.UserEndpointsProperties;
import com.api.payloads.User;
import com.api.utilitise.DataProviders;

import io.restassured.response.Response;

public class DataDrivenTest {
	
	@Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void createUser(String userId, String username, String fName, String lName, String email, String pwd, String phone) {
		User userPayload = new User();
		
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(username);
		userPayload.setFirstName(fName);
		userPayload.setLastName(lName);
		userPayload.setEmail(email);
		userPayload.setPassword(pwd);
		userPayload.setPhone(phone);
		
		Response response = UserEndpointsProperties.createUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
	public void deleteUser(String userName)
	{
		Response response = UserEndpointsProperties.deleteUser(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
	}
}
