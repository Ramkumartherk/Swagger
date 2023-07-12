package com.pack.swagger.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.swagger.Dto.StudentDto;
import com.pack.swagger.Repository.StudentRepo;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	StudentRepo studentRepo;
	
	public StudentDto convertEntityToDto(Student student) {
		StudentDto studentDto=new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setDept(student.getDept());
		studentDto.setPhoneNo(student.getPhone_No());
		return studentDto;
	}
	public Student convertDtoToEntity(StudentDto studentDto) {
		Student student=new Student();
		student.setId(studentDto.getId());
		student.setName(studentDto.getName());
		student.setDept(studentDto.getDept());
		student.setPhone_No(studentDto.getPhoneNo());
		return student;
	}
	
	@Override
	public StudentDto InsertMovie(StudentDto s) {
		return convertEntityToDto(studentRepo.save(convertDtoToEntity(s)));
		
	}
	@Override
	public List<StudentDto> getAllStudents() {
		
		return studentRepo.findAll()
						  .stream().map(this::convertEntityToDto)
						  .collect(Collectors.toList());
						  
	}
	@Override
	public StudentDto findByStudentId(Integer id) {
		Optional<Student> s=studentRepo.findById(id);
		Student std=null;
		if(s.isPresent()) {
		std= s.get() ;
		}
		return convertEntityToDto(std);
		
		
	}
	@Override
	public void deleteStudent(Integer id) {
		 studentRepo.deleteById(id);
	}
	@Override
	public StudentDto findByStudentName(String name) {
		
		return convertEntityToDto(studentRepo.findByName(name));
	}
	@Override
	public StudentDto updateStudent(StudentDto student) {
		Student s=studentRepo.save(convertDtoToEntity(student));
		return convertEntityToDto(s);
	}
	@Override
	public StudentDto updateStudentById(Integer id, String dept) {
		Optional<Student> s=studentRepo.findById(id);
		Student std=null;
		if(s.isPresent()) {
			std= s.get() ;
			std.setDept(dept);
			studentRepo.save(std);
			}
		
		return convertEntityToDto(std);
	}
	@Override
	public List<CountForDept> getStudentCount() {
		
		return studentRepo.findCountByDept();
	}
	@Override
	public List<StudentIdName> getStudentWithId() {
		
		return studentRepo.findNameId();
	}

}
