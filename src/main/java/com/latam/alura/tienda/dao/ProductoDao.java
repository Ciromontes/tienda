package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Producto;

public class ProductoDao {
	
	private EntityManager em;
	
	
	public ProductoDao(EntityManager em) {
		this.em = em;
	}

	public void guardar(Producto producto) {
		this.em.persist(producto);
	}
	
	public Producto consultaPorId(Long id) {
		return em.find(Producto.class, id);
	}
	
	public List<Producto> consultarTodos(){
		String jqpl= "SELECT P FROM Producto AS P";
		return em.createQuery(jqpl,Producto.class).getResultList();
	}
	
	public List<Producto> consultaPorNombre(String nombre){
		String jpql ="SELECT P FROM Producto AS P WHERE P.nombre=:nombre";
		return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
	}
	
	public List<Producto> consultaPorNombreDeCategoria(String nombre){
		String jpql= "SELECT P FROM Producto AS P WHERE P.categoria.nombre=:nombre";
		return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
	}
	
	public BigDecimal consultaPrecioPorNombreDeProducto(String nombre){
		
		return em.createNamedQuery("Producto.consultaDePrecio",BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
	}

}
