package com.latam.alura.tienda.prueba;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Categoria;
import com.latam.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Categoria celulares = new Categoria("CELULARES");  
		//Producto celular= new Producto("Samsung", "telefono usado", new BigDecimal("1000"),celulares);
		
		
		EntityManager em = JPAUtils.getEntityManager();
		
		//ProductoDao productoDao = new ProductoDao(em);
		//CategoriaDao categoriaDao = new CategoriaDao(em);
		
		em.getTransaction().begin();
		
		em.persist(celulares);
		
		celulares.setNombre("LIBROS");
		
		em.flush();
		em.clear();
		//categoriaDao.guardar(celulares);
		//productoDao.guardar(celular);
	
		//em.getTransaction().commit();
		//em.close();
		celulares = em.merge(celulares);		
		celulares.setNombre("SOFTWARES");
		
		em.flush();
		em.clear();
		celulares = em.merge(celulares);		

		em.remove(celulares);
		em.flush();
							
	}

}
