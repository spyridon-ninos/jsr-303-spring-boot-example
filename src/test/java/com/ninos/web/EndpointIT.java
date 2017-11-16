package com.ninos.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninos.App;
import com.ninos.web.model.Request;
import com.ninos.web.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class EndpointIT {

	@Inject
	private WebApplicationContext wax;
	private static ObjectMapper mapper = new ObjectMapper();
	private MockMvc mockMvc;

	@Before
	public void beforeMethod() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wax).build();
	}

	@Test
	public void happyPath() throws Exception {
		String response = mockMvc.perform(post("/")
	              .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	              .content(buildRequest("Spyros", "Spyridon"))
	              .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse()
			.getContentAsString();

		Response actualResponse = mapper.readValue(response, Response.class);

		assertEquals(0, actualResponse.getErrorList().size());
	}
	@Test
	public void invalidName() throws Exception {
		String response = mockMvc.perform(post("/")
		        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
		        .content(buildRequest("spyros", "Spyros"))
		        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
		        .andExpect(status().isBadRequest())
			.andReturn()
			.getResponse()
			.getContentAsString();

		Response actualResponse = mapper.readValue(response, Response.class);

		assertEquals("name", actualResponse.getErrorList().get(0));
	}

	@Test
	public void invalidNameAndType() throws Exception {
		String response = mockMvc.perform(post("/")
                  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                  .content(buildRequest("spyros", "spyros"))
                  .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
             .andExpect(status().isBadRequest())
             .andReturn()
             .getResponse()
             .getContentAsString();

		Response deserialisedResponse = mapper.readValue(response, Response.class);
		List<String> actualResponse = deserialisedResponse.getErrorList();
		actualResponse.sort(String.CASE_INSENSITIVE_ORDER);

		List<String> expectedResponse = Arrays.asList("name", "type");
		expectedResponse.sort(String.CASE_INSENSITIVE_ORDER);

		assertEquals(expectedResponse, actualResponse);
	}

	private static String buildRequest(String name, String type) throws JsonProcessingException {
		Request request = new Request();
		request.setId(1L);
		request.setName(name);
		request.setType(type);

		return mapper.writeValueAsString(request);
	}
}
