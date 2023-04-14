package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager em;
	
	
	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	public void guardar(Cliente Cliente) {
		this.em.persist(Cliente);
	}
	
	public Cliente consultaPorId(Long id) {
		return em.find(Cliente.class, id);
	}
	
	public List<Cliente> consultarTodos(){
		String jqpl= "SELECT P FROM Cliente AS P";
		return em.createQuery(jqpl,Cliente.class).getResultList();
	}
	
	public List<Cliente> consultaPorNombre(String nombre){
		String jpql ="SELECT P FROM Cliente AS P WHERE P.nombre=:nombre";
		return em.createQuery(jpql, Cliente.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Cliente> consultaPorNombreDeCategoria(String nombre){
		String jpql= "SELECT P FROM Cliente AS P WHERE P.categoria.nombre=:nombre";
		return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultaPrecioPorNombreDeCliente(String nombre){
		String jpql= "SELECT P.precio FROM Cliente AS P WHERE P.nombre=:nombre";
		return em.createQuery(jpql,BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}

}
