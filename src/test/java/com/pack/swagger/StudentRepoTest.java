package com.pack.swagger;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentRepoTest {
	@Autowired
	StudentRepo studentRepo;
	
	
	  //this method is used to tests weather Student Object saves in the database or not
	
	@Test
	void testSaveStudent() {
		
		Student s=new Student(41,"komal","cse",9981234l);
		Student saveStudent=studentRepo.save(s);
		Optional<Student> opt= studentRepo.findById(saveStudent.getId());
		Student s1=(Student)opt.get();
		assertEquals(s.getId(),s1.getId());
	}
	/**
	 * this method is used to check weather we are getting all the users or not
	 */
	@Test
//	@Disabled
	void testGetAllStudents() {
		List<Student> allStudentsFromDb = studentRepo.findAll();
		List<Student> list=new ArrayList<>();
		for(Student s:allStudentsFromDb) {
			list.add(s);
		}
		assertEquals(allStudentsFromDb.size(),list.size());
	}
	/**
	 * this method is used to check weather the update statement works or not
	 */
	@Test
	void testUpdateStudent() {
		Optional<Student> opt= studentRepo.findById(10);
		Student getFromDb =(Student)opt.get();
		getFromDb.setName("komalnadhghv");
		System.out.println(getFromDb.getName());
		getFromDb.setPhone_No(9951246595l);
		studentRepo.save(getFromDb);
		System.out.println("saved");
		assertThat(getFromDb.getName()).isEqualTo("komalnadhghv");
		
	}
	/**
	 * this method is used to test weather we can find userName or not
	 */
	@Test

	void testFindByName() {
		Student saveIntoDb=new Student(3,"mech","king",12345l);
		studentRepo.save(saveIntoDb);
		Student getFromDb=studentRepo.findByName(saveIntoDb.getName());
		assertEquals(getFromDb.getName(),saveIntoDb.getName());
		
		
	}
	@Test
	void testDeleteById() {
		List<Student> slist=studentRepo.findAll();
		Student saveIntoDb=new Student(13,"mech","king",12345l);
		studentRepo.save(saveIntoDb);
		studentRepo.deleteById(saveIntoDb.getId());
		List<Student> slist1=studentRepo.findAll();
		assertEquals(slist.size(),slist1.size());
	}
	@Test
	void testGetStudentWithId() {
		List<StudentIdName> list=studentRepo.findNameId();
		List<Student> list1=studentRepo.findAll();
		assertEquals(list.size(),list1.size());
	}
	@Test
	void testGetStudentCountWithName() {
		List<CountForDept> list=studentRepo.findCountByDept();
		List<CountForDept> list1=new ArrayList<>();
		for(CountForDept c:list) {
			list1.add(c);
		}
		assertEquals(list.size(),list1.size());
	}
		
}
