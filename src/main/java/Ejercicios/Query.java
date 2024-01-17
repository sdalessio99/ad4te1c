package Ejercicios;

import java.util.List;

import Entidades.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Query {
	public static void main(String[] args) {

		// sustituir Session por entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		
		try {
			//comenzar transaccion
			entityManager.getTransaction().begin();
			
			String hql = "SELECT CONCAT(student.first_name, ' ', student.last_name), university.name " +
		             "FROM student" +
		             "JOIN s.university_id university";

			TypedQuery<Student[]> query = entityManager.createQuery(hql, Student[].class);

			List<Student[]> results = query.getResultList();
	
			for (Object[] result : results) {
			    String nombreEstudiante = (String) result[0];
			    String nombreUni = (String) result[1];
	
			    System.out.println("Nombre completo: " + nombreEstudiante + ", Universidad: " + nombreUni);
			}
		}
		finally {
			factory.close();
		}
		
		
	}
}
