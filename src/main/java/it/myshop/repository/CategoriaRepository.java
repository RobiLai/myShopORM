package it.myshop.repository;

import org.springframework.data.repository.CrudRepository;

import it.myshop.model.Categoria;
import java.util.List;


public interface CategoriaRepository extends CrudRepository<Categoria, Integer>{

	public Categoria findByNome(String nome);
	
	public List<Categoria> findByNomeLike(String like);
	
}
