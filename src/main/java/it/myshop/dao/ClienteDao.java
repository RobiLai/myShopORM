package it.myshop.dao;

import java.util.List;

import it.myshop.model.Cliente;

public interface ClienteDao{

	public void add(Cliente cliente);
	
	public Cliente getClienteById(int id);
	
	public List<Cliente> getClienti();
	
	public void update(Cliente cliente);
	
	public void delete(int id);
	
}
