package br.com.study.model;

import java.util.Map;
import java.util.Set;

public interface StudentRepository {

	void save(Student student);

	Map<String, Student> findAll();

	Student findId(String id);
	
	Set<String> ListUsers() ;
}
