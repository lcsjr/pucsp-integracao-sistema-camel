package resource;

import java.util.Map;
import java.util.Set;

import br.com.study.model.Student;
import br.com.study.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/student")
public class StudentResource {

	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/add/{id}/{name}")
	public Map<String, Student> add(@PathVariable("id") final String id, @PathVariable("name") final String name ) {
		
		studentRepository.save(new Student(id, name));
		
		return studentRepository.findAll();
	}
	
	@GetMapping("/all")
	public Map<String, Student> findAll(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/listar")
	public Set<String> listUsers(){
		return studentRepository.ListUsers();
	}
	
	@GetMapping("/{id}")
	public Student findId(@PathVariable("id") String id){
		return studentRepository.findId(id);
	}
	
}
