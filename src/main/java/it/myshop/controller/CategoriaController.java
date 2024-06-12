package it.myshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.model.Categoria;
import it.myshop.repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository catRepo;
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		
		Categoria c = new Categoria();
		c.setNome("cuffie");
		c.setDescrizione("ci sono nuove cuffie");
		
		catRepo.save(c);
		
		return "test Inserimento";
		
	}
	
	@ResponseBody
	@GetMapping("/get")
	public String getCategoria() {
		
		//qui si crea un oggetto iterable che prende una classe al suo interno. poi facciamo sull'ggetto un foreach che è direttamente consumanìbile dall'oggetto 
		//e questo foreach vuole come parametri l'ggetto che sta iterando e poi un azione come una landa che stampa il ciclo
		
		Iterable<Categoria> categorie = catRepo.findAll();
		
		categorie.forEach((Categoria c) -> {
			System.out.println(c.getNome());
			System.out.println(c.getDescrizione());
		});
		
		return "get categoria";
		
	}
	
	@ResponseBody
	@GetMapping("/getById")
	public String getById() {
		
	
		 Optional<Categoria> categoria = catRepo.findById(2);
		 
		 System.out.println(categoria.get().getNome());
		
		return categoria.get().getNome();
		
	}
	
	@ResponseBody
	@GetMapping("/getByNome")
	public String getByNome() {
		
	
		Categoria c = catRepo.findByNome("TV");
		 
		
		return c.getNome();
		
	}
	
	@ResponseBody
	@GetMapping("/getByNomeLike")
	public String getByNomelike() {
		
	
		List<Categoria> c = catRepo.findByNomeLike("%TV%");
		
		System.out.println(c.size());
		
		return "";
		
	}
	
}
