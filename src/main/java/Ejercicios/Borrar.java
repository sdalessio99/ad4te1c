package Ejercicios;



import Entidades.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Borrar {

	/**
	 * 4. ManyToMany bidireccional entre entidades Student y Course
	 * Borra una Student pero no el curso
	 */
	public static void main(String[] args) {

		// sustituir Session por entityManager
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ud4");
		EntityManager entityManager = factory.createEntityManager();
		
		
		try {			
			// Borra un objeto Student
			System.out.println("Borrando un objeto Student ");
			
			int student_id = 13;
			
			Student tempStudent= entityManager.find(Student.class, student_id);
			// comienza la transacción
			entityManager.getTransaction().begin();
			
			// borra el objecto Student pero sin CascadeType.REMOVE no elimina el curso
			entityManager.remove(tempStudent);
			
			// hace commit de la transaccion
			entityManager.getTransaction().commit();
					
			System.out.println("Hecho!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci n
			System.out.println("Realizando Rollback");
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			entityManager.close();
			entityManager.close();
		}
	}
	
}