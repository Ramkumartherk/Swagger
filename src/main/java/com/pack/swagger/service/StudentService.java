package com.pack.swagger.service;

import java.util.List;

import com.pack.swagger.Dto.StudentDto;
import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;

public interface StudentService {
	public Student convertDtoToEntity(StudentDto studentDto);
	public StudentDto convertEntityToDto(Student student);
	public StudentDto InsertMovie(StudentDto student);
	public List<StudentDto> getAllStudents();
	public StudentDto findByStudentId(Integer id);
	public void deleteStudent(Integer id);
	public StudentDto findByStudentName(String name);
	public StudentDto updateStudent(StudentDto s);
	public StudentDto updateStudentById(Integer id,String dept);
	public List<CountForDept> getStudentCount();
	public List<StudentIdName> getStudentWithId();
}
