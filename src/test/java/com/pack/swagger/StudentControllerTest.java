package com.pack.swagger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.ArgumentMatchers;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MvcResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.swagger.Dto.StudentDto;
import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;
import com.pack.swagger.service.StudentServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest
class StudentControllerTest {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	StudentServiceImpl studentImpl;
	@MockBean
	StudentRepo studentRepo;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	void testinsertStudent() throws Exception {
		StudentDto sd=new StudentDto(10, "luffy", "pirate", 8678554l);
//		Mockito.when(studentRepo.save(s)).thenReturn(s);
		Mockito.when(studentImpl.InsertMovie(ArgumentMatchers.any())).thenReturn(sd);
		String studentJson = mapper.writeValueAsString(sd);// convert student to string
		MvcResult result = mockMvc
				.perform(post("/student/insertStudent").contentType(MediaType.APPLICATION_JSON)
						.characterEncoding("utf-8").content(studentJson).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		String res = result.getResponse().getContentAsString(); // convert response to string
		StudentDto std = new ObjectMapper().readValue(res, StudentDto.class);
		assertEquals((Integer) 10, std.getId());
	}

	@Test
	void testgetAllStudents() throws Exception {
		List<StudentDto> slist = new ArrayList<>();
		slist.add(new StudentDto(1, "komal", "cse", 8968587l));
		slist.add(new StudentDto(2, "luffy", "pirate", 8675l));
		slist.add(new StudentDto(3, "hygc", "gcf", 87567l));
		// Mockito.when(studentRepo.findAll()).thenReturn(slist);
		Mockito.when(studentImpl.getAllStudents()).thenReturn(slist);
		MvcResult result = mockMvc.perform(get("/student/studentsList")).andReturn();
		String res = result.getResponse().getContentAsString();
		List<StudentDto> s = Arrays.asList(mapper.readValue(res, StudentDto[].class));
		assertEquals(slist.size(), s.size());
	}

	@Test
	void testgetStudentById() throws Exception {
		StudentDto sd=new StudentDto(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.findByStudentId(10)).thenReturn(sd);
		MvcResult result = mockMvc.perform(get("/student/findbyid/10")).andReturn();
		String res = result.getResponse().getContentAsString();
		StudentDto std = new ObjectMapper().readValue(res, StudentDto.class);
		assertEquals("luffy",std.getName());
	}
	//dd

	@Test
	void testdeleteStudentById() throws Exception {
		Integer id=1;
		Student s=new Student(id,"komal","cse",8775l);
		when(studentRepo.findById(id)).thenReturn(Optional.of(s));
		mockMvc.perform(delete("/student/delete/{id}",1))
		       .andExpect(status().isOk());
		verify(studentImpl,times(1)).deleteStudent(id);
				

	}

	@Test
	void testfindByName() throws Exception {
		Student s = new Student(10, "luffy", "pirate", 8678554l);
		StudentDto sd=new StudentDto(10, "luffy", "pirate", 8678554l);
		System.out.println(sd);
		Mockito.when(studentImpl.findByStudentName("luffy")).thenReturn(sd);
		MvcResult result = mockMvc.perform(get("/student/findbyname/luffy")).andReturn();
		String res = result.getResponse().getContentAsString();
		StudentDto std = new ObjectMapper().readValue(res, StudentDto.class);
		assertEquals(s.getId(), std.getId());
	}
	@Test
	void testUpdatDept() throws Exception {
		StudentDto s=new StudentDto(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.InsertMovie(s)).thenReturn(s);
		 Mockito.when(studentImpl.findByStudentId(Mockito.anyInt())).thenReturn(s);//		MvcResult result = mockMvc.perform(put("/student/studentId/10/dept/mech")).andReturn();
//		String res = result.getResponse().getContentAsString();
//		Student std = new ObjectMapper().readValue(res, Student.class);
////		s.setDept("mech");
////		studentRepo.save(s);
//		assertEquals(std.getDept(),"mech");
		 Mockito.when(studentImpl.updateStudentById(10, "mech")).thenReturn(s);
		 String expectedJson = mapper.writeValueAsString(s);
         MvcResult result = mockMvc
 				.perform(put("/student/studentId/10/dept/mech").contentType(MediaType.APPLICATION_JSON)
 						.characterEncoding("utf-8").content(expectedJson).accept(MediaType.APPLICATION_JSON))
 				.andReturn();

         String res = result.getResponse().getContentAsString();
 		StudentDto student = new ObjectMapper().readValue(res, StudentDto.class);
//         assertThat(outputInJson).isEqualTo(expectedJson);
 		assertEquals(s.getDept(),student.getDept());
		
	}
	@Test
	void testUpdateStudent() throws Exception {
		StudentDto sd=new StudentDto(10, "luffy", "pirate", 8678554l);
		Mockito.when(studentImpl.InsertMovie(sd)).thenReturn(sd);
		Mockito.when(studentImpl.findByStudentId(ArgumentMatchers.any())).thenReturn(sd);
		Mockito.when(studentImpl.updateStudent(sd)).thenReturn(sd);
		String expectedJson = mapper.writeValueAsString(sd);
		System.out.println(expectedJson);
		MvcResult result =  mockMvc
 				.perform(post("/student/update/10").contentType(MediaType.APPLICATION_JSON)
 						.characterEncoding("utf-8").content(expectedJson).accept(MediaType.APPLICATION_JSON))
 				.andReturn();
		String res = result.getResponse().getContentAsString();
		StudentDto std = new ObjectMapper().readValue(res, StudentDto.class);
		assertEquals(sd.getDept(),std.getDept());
	}
	@Test
	void testStudentCountWithDept() throws Exception {
		List<Student> slist = new ArrayList<>();
		slist.add(new Student(1, "komal", "cse", 8968587l));
		slist.add(new Student(2, "luffy", "pirate", 8675l));
		slist.add(new Student(3, "hygc", "pirate", 87567l));
		List<CountForDept> list=new ArrayList<>();
		list.add(new CountForDept(1,"cse"));
		list.add(new CountForDept(2,"pirate"));
		Mockito.when(studentRepo.saveAll(slist)).thenReturn(slist);
		Mockito.when(studentImpl.getStudentCount()).thenReturn(list);
		MvcResult result=mockMvc.perform(get("/student/studentCount")).andReturn();
		String res = result.getResponse().getContentAsString();
		List<CountForDept> s = Arrays.asList(mapper.readValue(res, CountForDept[].class));
		assertEquals(list.get(0).getCount(), s.get(0).getCount());
		
	}
	@Test
	void testStudentIdWithName() throws Exception {
		List<Student> slist = new ArrayList<>();
		slist.add(new Student(1, "komal", "cse", 8968587l));
		slist.add(new Student(2, "luffy", "pirate", 8675l));
		slist.add(new Student(3, "hygc", "pirate", 87567l));
		List<StudentIdName> list=new ArrayList<>();
		list.add(new StudentIdName(1,"komal"));
		list.add(new StudentIdName(1,"luffy"));
		list.add(new StudentIdName(1,"hygc"));
		Mockito.when(studentRepo.saveAll(slist)).thenReturn(slist);
		Mockito.when(studentImpl.getStudentWithId()).thenReturn(list);
		MvcResult result=mockMvc.perform(get("/student/studentAndId")).andReturn();
		String res = result.getResponse().getContentAsString();
		List<StudentIdName> s = Arrays.asList(mapper.readValue(res, StudentIdName[].class));
		assertEquals(list.get(0).getId(),s.get(0).getId());
	}

}
