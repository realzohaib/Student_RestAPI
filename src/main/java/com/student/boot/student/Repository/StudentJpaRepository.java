package com.student.boot.student.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.student.boot.student.data.StudentInfo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StudentJpaRepository {

	@PersistenceContext
	public EntityManager entityManager;

	public StudentInfo insert(StudentInfo studentInfo) {
		return entityManager.merge(studentInfo);		
	}

	public List<StudentInfo> getStudent() {
		Query query = entityManager.createQuery("SELECT s FROM StudentInfo s", StudentInfo.class);
		List<StudentInfo> list = query.getResultList();
		return list;
	}

	public StudentInfo getbyid(int studentid) {
		return entityManager.find(StudentInfo.class, studentid);
		
	}

	public void deletestudent(int studentid) {
		StudentInfo student = entityManager.find(StudentInfo.class, studentid);
		entityManager.remove(student);
	}
	
	public void update(StudentInfo studentInfo, int studentid) {
		studentInfo.setStudentid(studentid);
		entityManager.merge(studentInfo);
	}
	
}
