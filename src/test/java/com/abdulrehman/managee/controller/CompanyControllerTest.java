package com.abdulrehman.managee.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.abdulrehman.managee.model.CompanyType;
import com.abdulrehman.managee.payload.request.CompanyRequest;
import com.abdulrehman.managee.payload.response.CompanyResponse;
import com.abdulrehman.managee.payload.response.DeleteResponse;
import com.abdulrehman.managee.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Khan Abdulrehman
 * @CreatedAt Jan 7, 2020
 */
@WithMockUser
@WebMvcTest(CompanyController.class)
class CompanyControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	CompanyService companyService;

	CompanyResponse companyResponse;
	CompanyRequest companyRequest;

	private final static Long ID = 1l;
	private final static String DISPLAYNAME = "Safa Water";
	private final static String EMAIL = "safawater@gmail.com";

	@BeforeEach
	void setUp() {
		// Init MockMvc Object
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		companyResponse = new CompanyResponse(ID, DISPLAYNAME, CompanyType.MANUFACTURER, EMAIL, false, "9856320147",
				false, "5b6ac6d9-a7ed-4cfe-b231-3692adb86877");
		companyRequest = new CompanyRequest(DISPLAYNAME, "Manufacturer", EMAIL, "9856320147");
	}

	@Test
	@DisplayName("Test find by display name successfully")
	void testFindByDisplayName() throws Exception {

		when(companyService.findByDisplayName(DISPLAYNAME)).thenReturn(companyResponse);

		this.mockMvc.perform(get("/company/displayname/" + DISPLAYNAME)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Test find by email successfully")
	void testFindByEmail() throws Exception {

		when(companyService.findByEmail(EMAIL)).thenReturn(companyResponse);

		this.mockMvc.perform(get("/company/email/" + EMAIL)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Test dinf by id successfully")
	void testFindById() throws Exception {
		when(companyService.findById(ID)).thenReturn(companyResponse);

		this.mockMvc.perform(get("/company/id/" + ID)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	@DisplayName("Test create company successfully")
	void testCreateCompanySuccessful() throws Exception {

		when(companyService.createCompany(ArgumentMatchers.any(CompanyRequest.class))).thenReturn(companyResponse);

		// response is retrieved as MvcResult
		MvcResult mvcResult = mockMvc
				.perform(post("/company/create").contentType(MediaType.APPLICATION_JSON).content(json(companyRequest)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.displayName", is(DISPLAYNAME))).andReturn();

		// json response body is converted/mapped to the Java Object
		String jsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(jsonResponse);
		CompanyResponse result = new ObjectMapper().readValue(jsonResponse, CompanyResponse.class);

		assertNotNull(result);
		assertNotNull(result.getId());
	}

	@Test
	@DisplayName("Test update company successfully")
	void testUpdateCompany() throws Exception {

		when(companyService.updateCompany(ArgumentMatchers.any(Long.class), ArgumentMatchers.any(CompanyRequest.class)))
				.thenReturn(companyResponse);

		// response is retrieved as MvcResult
		MvcResult mvcResult = mockMvc
				.perform(post("/company/update/" + ID).contentType(MediaType.APPLICATION_JSON)
						.content(json(companyRequest)))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.displayName", is(DISPLAYNAME))).andReturn();

		// json response body is converted/mapped to the Java Object
		String jsonResponse = mvcResult.getResponse().getContentAsString();
		System.out.println(jsonResponse);
		CompanyResponse result = new ObjectMapper().readValue(jsonResponse, CompanyResponse.class);

		assertNotNull(result);
		assertNotNull(result.getId());
	}

	@Test
	@DisplayName("Test delete company successfully")
	void testDeleteCompany() throws Exception {

		DeleteResponse deleteResponse = new DeleteResponse("Record deleted succeddfully.");

		when(companyService.deleteCompany(ID)).thenReturn(deleteResponse);

		this.mockMvc.perform(delete("/company/delete/" + ID)).andDo(print()).andExpect(status().isOk());
	}

	private String json(Object obj) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(companyRequest);
	}

}
