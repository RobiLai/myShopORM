package it.myshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.myshop.dao.ClienteDao;
import it.myshop.model.Cliente;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {
	
	@Autowired
	private ClienteDao clienteService;
	
	@ResponseBody
	@GetMapping("/add")
	public String add() {
		Cliente c = new Cliente();
		c.setNome("Gianni");
		clienteService.add(c);
		
		return "welcome";
	}
	
	@ResponseBody
	@GetMapping("/getById")
	public String getById() {
		
		Cliente c = clienteService.getClienteById(3);
		
		return "welcome" + c.getNome() + " " + c.getCognome();
	}
	
	@ResponseBody
	@GetMapping("/update")
	public String update() {
		Cliente c = clienteService.getClienteById(3);
		
		c.setNome("Umberto");
		clienteService.update(c);
		
		return null;
	}
	
	@ResponseBody
	@GetMapping("/delete")
	public String delete() {
	
		clienteService.delete(3);
		
		return null;
	}
	
	
}
