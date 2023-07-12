package com.pack.swagger;

import static org.junit.Assert.assertEquals;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.swagger.Dto.StudentDto;
import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;
import com.pack.swagger.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentServiceTest {
	@Autowired
	StudentService studentService;
	@Autowired
	StudentRepo studentRepo;
	
	
	@Test
	void testDeleteStudent() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		studentService.deleteStudent(saveIntoDb.getId());
		List<Student> getAllStudentsFromDb=studentRepo.findAll();
		assertEquals(6,getAllStudentsFromDb.size());
		
		
	}
	@Test
	void testFindByStudentName() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		StudentDto getFromDb=studentService.findByStudentName(saveIntoDb.getName());
		assertEquals(getFromDb.getName(),saveIntoDb.getName());
	}
	@Test
	void testFindByStudentId() {
		Student saveIntoDb=new Student(1,"eee","mahi",8686l);
		studentRepo.save(saveIntoDb);
		StudentDto getFromDb=studentService.findByStudentId(saveIntoDb.getId());
		assertEquals(getFromDb.getName(),saveIntoDb.getName());
	}
	@Test
	void testSaveStudent() {
		Student s=new Student(41,"komal","cse",9981234l);
		StudentDto std=studentService.convertEntityToDto(s);
		StudentDto saveStudent=studentService.InsertMovie(std);
		Optional<Student> opt= studentRepo.findById(saveStudent.getId());
		Student s1=(Student)opt.get();
		assertEquals(s.getId(),s1.getId());
	}
	@Test
	void testUpdateDept() {
		StudentDto student=studentService.updateStudentById(41, "king");
		 assertEquals("king",student.getDept());
	}
	@Test
	void testgetAllStudents() {
		List<StudentDto> list=studentService.getAllStudents();
		List<StudentDto> std=new ArrayList<>();
		for(StudentDto s:list) {
			std.add(s);
		}
		assertEquals(list.size(),std.size());
	}
	@Test
	void testUpdateStudent() {
		Optional<Student> s=studentRepo.findById(41);
		StudentDto std=studentService.convertEntityToDto(s.get());
		std.setDept("king");
		std.setName("komalnadh");
		StudentDto std1=studentService.updateStudent(std);
		assertEquals("komalnadh",std1.getName());
	}
	@Test
	void testStudentDeptCount() {
		List<CountForDept> list=studentService.getStudentCount();
		List<CountForDept> list1=new ArrayList<>();
		for(CountForDept c:list) {
			list1.add(c);
		}
		assertEquals(list.size(),list1.size());
	}
	@Test
	void testStudentIdAndName() {
		List<StudentIdName> list=studentService.getStudentWithId();
		List<StudentDto> list1=studentService.getAllStudents();
		assertEquals(list.size(),list1.size());
	}
	@Test
	void testConvertEntityToDto() {
		Student s=new Student(41,"komal","cse",9981234l);
		StudentDto sd=studentService.convertEntityToDto(s);
		assertEquals(sd.getId(),s.getId());
	}
	@Test
	void testConvertDtoToEntity() {
		StudentDto s=new StudentDto(41,"komal","cse",9981234l);
		Student sd=studentService.convertDtoToEntity(s);
		assertEquals(sd.getId(),s.getId());
	}
}



