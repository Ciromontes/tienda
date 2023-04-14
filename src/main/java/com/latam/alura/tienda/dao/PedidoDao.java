package com.latam.alura.tienda.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import com.latam.alura.tienda.modelo.Pedido;
import com.latam.alura.tienda.vo.RelatorioDeVenta;

public class PedidoDao {
	
	private EntityManager em;
	
	
	public PedidoDao(EntityManager em) {
		this.em = em;
	}

	public void guardar(Pedido Pedido) {
		this.em.persist(Pedido);
	}
	
	public Pedido consultaPorId(Long id) {
		return em.find(Pedido.class, id);
	}
	
	public List<Pedido> consultarTodos(){
		String jqpl= "SELECT P FROM Pedido AS P";
		return em.createQuery(jqpl,Pedido.class).getResultList();
	}
	
	public BigDecimal ValorTotalVendido() {
		String jpql ="SELECT SUM(p.valorTotal) From Pedido p";
		return em.createQuery(jpql,BigDecimal.class).getSingleResult();
	}
	
	public Double ValorPromedioVendido() {
		String jpql ="SELECT AVG(p.valorTotal) From Pedido p";
		return em.createQuery(jpql,Double.class).getSingleResult();
		
	}
	
	public List<Object[]> relatorioDeVentas(){
	    String jpql= "SELECT producto.nombre, "
	                + "SUM(item.cantidad), "
	                + "MAX(pedido.fecha) "
	                + "FROM Pedido pedido "
	                + "JOIN pedido.items item "
	                + "JOIN item.producto producto "
	                + "GROUP BY producto.nombre "
	                + "ORDER BY SUM(item.cantidad) DESC";
	    return em.createQuery(jpql,Object[].class).getResultList();
	}
	
	public List<RelatorioDeVenta> relatorioDeVentasVO(){
	    String jpql= "SELECT new com.latam.alura.tienda.vo.RelatorioDeVenta(producto.nombre, "
	                + "SUM(item.cantidad), "
	                + "MAX(pedido.fecha))"
	                + "FROM Pedido pedido "
	                + "JOIN pedido.items item "
	                + "JOIN item.producto producto "
	                + "GROUP BY producto.nombre "
	                + "ORDER BY SUM(item.cantidad) DESC";
	    return em.createQuery(jpql,RelatorioDeVenta.class).getResultList();
	}

	
	
	
}
