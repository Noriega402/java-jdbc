/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.model.Product;
import com.alura.jdbc.dao.ProductDAO;
import java.util.List;

/**
 *
 * @author Daniel Noriega
 */
public class ProductController {

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        ProductDAO productDAO = new ProductDAO(new ConnectionFactory().recuperarConexion());
        return productDAO.modificar(nombre, descripcion, cantidad, id);
    }

    public int eliminar(Integer id) {
        ProductDAO productDAO = new ProductDAO(new ConnectionFactory().recuperarConexion());
        return productDAO.eliminar(id);
    }

    public List<Product> listar(){
        ProductDAO productDAO = new ProductDAO(new ConnectionFactory().recuperarConexion());
        return productDAO.listar();
    }

    public void guardar(Product producto, Integer categoriaId){
       ProductDAO productDAO = new ProductDAO(new ConnectionFactory().recuperarConexion());
       producto.setCategoryId(categoriaId);
       productDAO.guardar(producto);
    }
}
