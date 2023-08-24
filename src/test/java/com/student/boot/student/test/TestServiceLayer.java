package com.student.boot.student.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.student.boot.student.Repository.StudentJpaRepository;
import com.student.boot.student.Serice.ServiceLayer;
import com.student.boot.student.data.StudentInfo;

//@SpringBootTest
@WebMvcTest(value = ServiceLayer.class)
class TestServiceLayer {

	@Autowired
	private ServiceLayer layer;

	@MockBean
	private StudentJpaRepository studentrepo;

	@Test
	void testinsert() {
		StudentInfo s = new StudentInfo(1, "zohaib", "idgah", "0000000000", "28/02/2023");
		when(studentrepo.insert(s)).thenReturn(s);
		assertEquals(s, layer.insert(s));
	}

	@Test
	void getbyid() {
		int id = 1;
		StudentInfo s = new StudentInfo(1, "zohaib", "idgah", "0000000000", "28/02/2023");
		when(studentrepo.getbyid(id)).thenReturn(s);
		assertEquals(s, layer.getbyid(id));
	}

	@Test
	void deletestudent() {
		int id = 1;
		StudentInfo s = new StudentInfo(1, "zohaib", "idgah", "0000000000", "28/02/2023");
		layer.deletestudent(id);
		verify(studentrepo, times(1)).deletestudent(id);

	}

	@Test
	void updatetest() {
		int id = 1;
		StudentInfo s = new StudentInfo(1, "zohaib", "idgah", "0000000000", "28/02/2023");
		layer.update(s, id);
		verify(studentrepo, times(1)).update(s, id);
	}

	@Test
	void testgetstudent() {
		StudentInfo s = new StudentInfo(1, "zohaib", "idgah", "0000000000", "28/02/2023");

		List<StudentInfo> list = new ArrayList<>();
		list.add(s);
		when(studentrepo.getStudent()).thenReturn(list);
		assertEquals(1, layer.getStudent().size());
	}

}
