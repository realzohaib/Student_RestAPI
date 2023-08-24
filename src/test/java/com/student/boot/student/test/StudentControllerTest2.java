package com.student.boot.student.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.boot.student.Controller.StudentController;
import com.student.boot.student.Serice.ServiceLayer;
import com.student.boot.student.data.StudentInfo;

@WebMvcTest(value = StudentController.class)
class StudentControllerTest2 {
	@MockBean
	private ServiceLayer service;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testgetAll() throws Exception {
		List<StudentInfo> list = new ArrayList<>();
		list.add(new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023"));

		when(service.getStudent()).thenReturn(list);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/student");

		ResultActions perform = mockMvc.perform(builder);
		MvcResult result = perform.andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();

		String contentAsString = response.getContentAsString();
		List<StudentInfo> readValue = new ObjectMapper().readValue(contentAsString,
				new com.fasterxml.jackson.core.type.TypeReference<List<StudentInfo>>() {
				});
		assertEquals(200, status);
		assertNotNull(readValue);
		assertEquals(list, readValue);

	}

	@Test
	void testCreate() throws Exception {
		StudentInfo expectedStudentInfo = new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023");
		when(service.insert(expectedStudentInfo)).thenReturn(expectedStudentInfo);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(expectedStudentInfo);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/student")
				.contentType(MediaType.APPLICATION_JSON).content(json);

		ResultActions perform = mockMvc.perform(builder);
		MvcResult result = perform.andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();
		assertEquals(200, status);

		StudentInfo actualStudentInfo = mapper.readValue(json, StudentInfo.class);

		assertNotNull(actualStudentInfo);
		assertEquals(expectedStudentInfo, actualStudentInfo);
		// verify(service, times(1)).insert(expectedStudentInfo);

	}

	@Test
	void testgetbyid() throws Exception {
		int studentid = 1;
		StudentInfo expectedStudentInfo = new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023");

		when(service.getbyid(studentid)).thenReturn(expectedStudentInfo);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/student/" + studentid);

		ResultActions perform = mockMvc.perform(builder);
		MvcResult result = perform.andReturn();
		MockHttpServletResponse response = result.getResponse();
		int status = response.getStatus();

		String responseJson = response.getContentAsString();
		StudentInfo actualStudentInfo = new ObjectMapper().readValue(responseJson, StudentInfo.class);

		assertEquals(200, status);
		assertNotNull(actualStudentInfo);
		/*
		 * assertEquals(expectedStudentInfo.getStudentid(),
		 * actualStudentInfo.getStudentid());
		 * assertEquals(expectedStudentInfo.getName(), actualStudentInfo.getName());
		 * assertEquals(expectedStudentInfo.getAddress(),
		 * actualStudentInfo.getAddress());
		 * assertEquals(expectedStudentInfo.getMobileno(),
		 * actualStudentInfo.getMobileno()); assertEquals(expectedStudentInfo.getDate(),
		 * actualStudentInfo.getDate());
		 */
		assertEquals(expectedStudentInfo, actualStudentInfo);

	}

	@Test
	 void testupdate() throws Exception {
		int studentid = 1;
		StudentInfo expectedStudentInfo = new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023");
		Mockito.doNothing().when(service).update(expectedStudentInfo, studentid);

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(expectedStudentInfo);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/student/" + studentid)
				.contentType(MediaType.APPLICATION_JSON).content(json);

		int status = mockMvc.perform(builder).andReturn().getResponse().getStatus();
		assertEquals(200, status);
		verify(service).update(expectedStudentInfo, studentid);
	}

	@Test
	void testdelete() throws Exception {
		int studentid = 1;
		StudentInfo expectedStudentInfo = new StudentInfo(1, "zohaib", "ghar", "000000000", "01/03/2023");
		Mockito.doNothing().when(service).deletestudent(studentid);

		MockHttpServletRequestBuilder delete = MockMvcRequestBuilders.delete("/student/" + studentid);
		int status = mockMvc.perform(delete).andReturn().getResponse().getStatus();
		assertEquals(200, status);
		verify(service, times(1)).deletestudent(studentid);
	}

}
