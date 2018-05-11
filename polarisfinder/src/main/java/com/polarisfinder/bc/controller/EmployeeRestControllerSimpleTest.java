package com.polarisfinder.bc.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@WebAppConfiguration
//@AutoConfigureMockMvc
public class EmployeeRestControllerSimpleTest {

	//@Autowired
	private MockMvc mvc;

	//@MockBean
	private EmployeeRestController employeeRestController;

	//@Test 
	public void testEmployee(){
		assertThat(employeeRestController).isNotNull();
	}
	/*
	@Test
	public void getemployees() throws Exception {

	    //given(employeeRestController.getemployees()).willReturn();
		this.mvc.perform(get("/employee/getemployees")).andDo(print()).andExpect(status().isOk())
        .andExpect(content().string(containsString("Hello World")));;
	}

	
	@Test
	public void getArrivals() throws Exception {
		Arrival arrival = new Arrival();
		arrival.setCity("Yerevan");

		List<Arrival> allArrivals = singletonList(arrival);

		given(arrivalController.getAllArrivals()).willReturn(allArrivals);

		mvc.perform(
				get(VERSION + ARRIVAL + "all").with(user("blaze").password("Q1w2e3r4")).contentType(APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].city", is(arrival.getCity())));
	}

	@Test
	public void getArrivalsById() throws Exception {
		Arrival arrival = new Arrival();
		arrival.setCity("Yerevan");

		given(arrivalController.getArrivalById(arrival.getId())).willReturn(arrival);

		mvc.perform(get(VERSION + ARRIVAL + arrival.getId()).with(user("blaze").password("Q1w2e3r4"))
				.contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("city", is(arrival.getCity())));
	}
	*/
}
