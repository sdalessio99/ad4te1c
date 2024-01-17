package Ejercicios;

import Entidades.Course;
import Entidades.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Crear {

	/**
	 * 1. ManyToMany bidireccional entre entidades Student y Course
	 * Crea un nuevo curso y añade un alumno al curso 
	 */
	public static void main(String[] args) {

		// sustituir Session por entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		

		
		try {			
			// crea un objeto Student y Course
			System.out.println("Creando un nuevo curso y añadiendo un alumno...");
			
			Student student = entityManager.find(Student.class, 13);
			Course course = createCourse();
						
			student.getCourses().add(course);
			course.getStudents().add(student);//asociación bidireccional para mantener la coherencia en ambos lados
			
											
			// comienza la transacción
			entityManager.getTransaction().begin();
			
			// guarda el objeto Student y el curso
			System.out.println("Guardando el curso...");
						
			entityManager.persist(course);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();	
			
			// Inicia una nueva transacción y recupera el curso de la base de datos para verificar los estudiantes asociados.
			// Esta parte está comentada temporalmente para evitar operaciones adicionales de base de datos durante la demostración.
			// Si necesitas verificar que la relación ManyToMany se ha establecido correctamente, puedes descomentar estas líneas.
			// session.beginTransaction();
			// Course dbCourse= (Course) session.get(Course.class, course.getId());
			// System.out.println(dbCourse.getStudents().iterator().next().getLastName());
			
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepción
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			entityManager.close();
		}
	}
	private static Course createCourse() {
		Course tempCourse = new Course();
				
		tempCourse.setName("Bases de datos");
		tempCourse.setCredits(6);
		return tempCourse;		
	}
}
