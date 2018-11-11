package vn.edu.saigontech.SpringMVCDemo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import vn.edu.saigontech.SpringMVCDemo.daos.specializationDAO;
import vn.edu.saigontech.SpringMVCDemo.daos.studentDAO;
import vn.edu.saigontech.SpringMVCDemo.models.Student;
import vn.edu.saigontech.SpringMVCDemo.utils.fileUploadUtils;

@RestController
@RequestMapping("rest")
public class studentRESTController {
	@Autowired
	private studentDAO studentDAO;
	@Autowired
	private specializationDAO specializationDAO;

	@RequestMapping(value = "/StudentREST", method = RequestMethod.GET)
	public List<Student> getAllStudent() {
		System.out.println(studentDAO.getAllStudent().size());
		return studentDAO.getAllStudent();
	}

	@RequestMapping(value = "/StudentREST/{id}", method = RequestMethod.GET)
	public Student getAllStudent(@PathVariable int id) {
		return studentDAO.getStudentByID(id);
	}

	@RequestMapping(value = "/StudentREST", method = RequestMethod.POST)
	public Student addStu(HttpServletRequest req, 
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, 
			@RequestParam("email") String email,
			@RequestParam("isMale") boolean isMale, 
			@RequestParam("image") MultipartFile imageFile,
			@RequestParam("specialization") int specID) throws IOException {

		Student targetStu = new Student();
		targetStu.setLastName(lastName);
		targetStu.setFirstName(firstName);
		targetStu.setEmail(email);
		targetStu.setMale(isMale);
		targetStu
				.setImage(fileUploadUtils.saveUploadedFile(imageFile, req.getServletContext().getRealPath("/images/")));
		targetStu.setSpecialization(specializationDAO.getSpecializationByID(specID));

		return studentDAO.addStudent(targetStu);
	}

	@RequestMapping(value = "/StudentREST/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable int id, HttpServletRequest req) {

		fileUploadUtils.deleteUploadFile(studentDAO.getStudentByID(id).getImage(),
				req.getServletContext().getRealPath("/images/"));

		return studentDAO.deleteStudent(id);
	}

//	@RequestMapping(value = "/StudentREST", method = RequestMethod.PUT)
//	public Student updateStu(@RequestBody Student targetStu) {
//
//		return studentDAO.updateStudent(targetStu);
//	}

	@RequestMapping(value = "/StudentREST/{targetStudentID}", method = RequestMethod.POST)
	public Student updateStu(HttpServletRequest req, @PathVariable("targetStudentID") int id,
			@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("isMale") boolean isMale,
			@RequestParam("image") MultipartFile imageFile, @RequestParam("specialization") int specID)
			throws IOException {

		Student targetStu = studentDAO.getStudentByID(id);

		fileUploadUtils.deleteUploadFile(targetStu.getImage(), req.getServletContext().getRealPath("/images/"));

		targetStu.setLastName(lastName);
		targetStu.setFirstName(firstName);
		targetStu.setEmail(email);
		targetStu.setMale(isMale);
		targetStu
				.setImage(fileUploadUtils.saveUploadedFile(imageFile, req.getServletContext().getRealPath("/images/")));
		targetStu.setSpecialization(specializationDAO.getSpecializationByID(specID));

		return studentDAO.updateStudent(targetStu);
	}

}
