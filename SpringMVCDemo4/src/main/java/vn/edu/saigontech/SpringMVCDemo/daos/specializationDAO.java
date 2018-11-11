package vn.edu.saigontech.SpringMVCDemo.daos;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import vn.edu.saigontech.SpringMVCDemo.models.Specialization;

@Transactional
public class specializationDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private studentDAO studentDAO;

	public List<Specialization> getAllSpecialization() {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from Specialization");

		List<Specialization> specArr = query.getResultList();

		return specArr;
	}

	public Specialization getSpecializationByID(int id) {
		Session session = sessionFactory.getCurrentSession();

		Query query = session.createQuery("from Specialization s where s.id = :id").setParameter("id", id);

		Specialization spec = (Specialization) query.list().get(0);

		return spec;
	}

	public Specialization addSpecialization(Specialization spec) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.persist(spec);

			return spec;
		} catch (Exception e) {
			return null;
		}
	}

	public String deleteSpecialization(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();

			Specialization targetSpec = getSpecializationByID(id);

			session.delete(targetSpec);

			return "Delete successfully";
		} catch (Exception e) {
			return "Delete unsuccessfully";
		}
	}

	public Specialization updateSpecialization(Specialization targetSpec) {
		try {
			Session session = sessionFactory.getCurrentSession();

			session.merge(targetSpec);
			
			return targetSpec;
		} catch (Exception e) {
			return null;
		}
	}
}
