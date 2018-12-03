package vn.edu.saigontech.SpringMVCDemo.daos.tests;

import com.google.gson.Gson;

import vn.edu.saigontech.SpringMVCDemo.configurations.ApplicationContextConfig;
import vn.edu.saigontech.SpringMVCDemo.daos.studentDAO;
import vn.edu.saigontech.SpringMVCDemo.models.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContextConfig.class })
public class TestStudentDAO {
	@Autowired
	private studentDAO studentDAO;

	@Test
	public void testAddStudent() {
		boolean res = false;
		List<Student> stuArr = studentDAO.getAllStudent();
		
		//add a new student to database and get their ID
		int addedStudentID = studentDAO
				.addStudent(new Student("A", "Nguyen Van", "anguyen@gmail.com", true, "picture.png")).getId();
		
		//get this student by it's ID
		Student addedStudent = studentDAO.getStudentByID(addedStudentID);
		
		//if the student above not null, so the daos is OK
		if (addedStudent != null)
			res = true;

		studentDAO.deleteStudent(addedStudentID);
		assertTrue(res);
	}

	@Test
	public void testDeleteStudent() {
		//get number of student before testing
		int beforeNumStudent = studentDAO.getAllStudent().size();
		
		//add a new student and get it's ID
		int addedStudentID = studentDAO
				.addStudent(new Student("A", "Nguyen Van", "anguyen@gmail.com", true, "picture.png")).getId();
		
		//delete that student
		studentDAO.deleteStudent(addedStudentID);
		
		//get number of student after deleting
		int currentNumStudent = studentDAO.getAllStudent().size();
		//if the current of student equal number of student before, so this daos is OK
		assertTrue(currentNumStudent == beforeNumStudent);

	}

	@Test
	public void testUpdateStudent() {
		String firstName = "A";
		String lastName = "Nguyen Van";
		String email = "anguyen@gmail.com";
		boolean isMale = true;
		String imageName = "picture.img";
		Student testStudent = new Student(firstName, lastName, email, isMale, imageName);
		
		//add a new student to database and get it's ID
		int addedStudentID = studentDAO.addStudent(testStudent).getId();

		firstName = "B";
		lastName = "Nguyen Thi";
		email = "bnguyen@gmail.com";
		isMale = false;
		imageName = "picture.jpg";
		
		//edit information of this student in class and update this student in database via Hibernate
		testStudent = new Student(addedStudentID, firstName, lastName, email, isMale, imageName);
		int updatedStudentID = studentDAO.updateStudent(testStudent).getId();

		boolean res = true;
		testStudent = studentDAO.getStudentByID(updatedStudentID);
		
		//if those changed field different from this one before, so this daos is OK
		if (addedStudentID != updatedStudentID)
			res = false;
		if (!firstName.equals(testStudent.getFirstName()))
			res = false;
		if (!lastName.equals(testStudent.getLastName()))
			res = false;
		if (!email.equals(testStudent.getEmail()))
			res = false;
		if (isMale != testStudent.isMale())
			res = false;
		if (!imageName.equals(testStudent.getImage()))
			res = false;

		studentDAO.deleteStudent(updatedStudentID);

		assertTrue(res);

	}

	@Test
	public void testGetAllStudent() {
		boolean res = false;
		//get number of student before testing
		int oldStudentNumber = studentDAO.getAllStudent().size();
		
		
		String firstName = "A";
		String lastName = "Nguyen Van";
		String email = "anguyen@gmail.com";
		boolean isMale = true;
		String imageName = "picture.img";
		Student testStudent = new Student(firstName, lastName, email, isMale, imageName);
		//add a new student to the database using Hibernate
		int addedStudentID = studentDAO.addStudent(testStudent).getId();

		//get number of student after adding
		int newStudentNumber = studentDAO.getAllStudent().size();
		
		//if number of student before equal after minus 1, so this daos is OK.
		if (oldStudentNumber == (newStudentNumber - 1))
			res = true;

		studentDAO.deleteStudent(addedStudentID);

		assertTrue(res);

	}

	@Test
	public void testGetStudentByID() {
		String firstName = "A";
		String lastName = "Nguyen Van";
		String email = "anguyen@gmail.com";
		boolean isMale = true;
		String imageName = "picture.img";
		Student testStudent = new Student(firstName, lastName, email, isMale, imageName);
		//add a new student to the database and get it ID
		int addedStudentID = studentDAO.addStudent(testStudent).getId();
		
		//get this student again by it ID.
		testStudent = studentDAO.getStudentByID(addedStudentID);

		boolean res = true;
		
		//compare with those value, which is initialize before adding. 
		//If those field is matched with the new student object, so this dao is  OK.
		if (!firstName.equals(testStudent.getFirstName()))
			res = false;
		if (!lastName.equals(testStudent.getLastName()))
			res = false;
		if (!email.equals(testStudent.getEmail()))
			res = false;
		if (isMale != testStudent.isMale())
			res = false;
		if (!imageName.equals(testStudent.getImage()))
			res = false;

		studentDAO.deleteStudent(addedStudentID);

		assertTrue(res);

	}

}