package br.com.study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.study.model.Student;
import br.com.study.repository.StudentRepository;

@Component("myBean")
public class MyBean {

    @Autowired
    StudentRepository studentRepository;

    public String saySomething(String body) {
    	
    	Student student = new Student("Eng2015001", "John Doe");
    			studentRepository.save(student);
    			
    	System.out.println(student.toString());
        
    	return student.toString();
    }

}