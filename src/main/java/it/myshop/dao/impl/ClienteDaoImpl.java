package it.myshop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;

import it.myshop.dao.ClienteDao;
import it.myshop.model.Categoria;
import it.myshop.model.Cliente;

public class ClienteDaoImpl implements ClienteDao{

	//@PersistenceContext(type = PersistenceContextType.EXTENDED)// usare extended se si vuole eliminare le transazioni che servono per quando si fa DELETE INSERT o UPDATE
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	@Transactional //serve perchè le operazioni di insert,delete e update avvengono sotto transazione
	public void add(Cliente cliente) {
		
		manager.persist(cliente);
	}

	@Override
	public Cliente getClienteById(int id) {
		return manager.find(Cliente.class, id);
		 
	}

	@Override
	@Transactional
	public void update(Cliente cliente) {
		
		manager.merge(cliente);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		Cliente c = getClienteById(id);
		
		manager.remove(c);
		
	}

	@Override
	public List<Cliente> getClienti() {
		
		
		Query sql = manager.createNamedQuery("SELECT * FROM cliente");
		
		List<Cliente> clienti = sql.getResultList();
		
		for(Cliente cliente : clienti) {
			
			cliente.getNome();
			
		}
		
		return null;
	}	
	
}
