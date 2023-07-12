package com.pack.swagger.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pack.swagger.model.CountForDept;
import com.pack.swagger.model.Student;
import com.pack.swagger.model.StudentIdName;
@Repository
public interface StudentRepo extends JpaRepository<Student,Integer>{
	 public Student findByName(String name);
	 
	 @Query("select new com.pack.swagger.model.StudentIdName(s.id,s.name) from Student s")
	 List<StudentIdName> findNameId();
	 
	 @Query("select new com.pack.swagger.model.CountForDept(count(s.Dept),s.Dept) from Student s group by s.Dept")
	 List<CountForDept> findCountByDept();
}
