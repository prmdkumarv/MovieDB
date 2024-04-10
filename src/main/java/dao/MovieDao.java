package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Movie;

public class MovieDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public void saveMovie(Movie movie) {
		transaction.begin();
		manager.persist(movie);
		transaction.commit();
	}
	
	public List<Movie>  fetchMovies(){
		
		return manager.createQuery("select x from Movie x").getResultList();
		
		//below is for using SQL lang 
		//return manager.createNativeQuery("select * from Movie",Movie.class).getResultList();
	}
}
