package br.com.study.model;

import java.util.Map;
import java.util.Set;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository{

	private static final String STUDENT = "STUDENT";

	private RedisTemplate<String, Student> redisTemplate;
	
	private HashOperations<String, String, Student> hashOperations;

	public StudentRepositoryImpl(RedisTemplate<String, Student> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = this.redisTemplate.opsForHash();
	}

	@Override
	public void save(Student student) {
		hashOperations.put(STUDENT, student.getId(), student);
	}

	@Override
	public Map<String, Student> findAll() {
		return hashOperations.entries(STUDENT);
	}

	@Override
	public Student findId(String id) {
		return hashOperations.get(STUDENT, id);
	}

	@Override
	public Set<String> ListUsers() {
		return hashOperations.keys(STUDENT);
	}

}
