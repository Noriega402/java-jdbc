package com.alura.jdbc.controller;

import com.alura.jdbc.dao.CategoryDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.model.Category;
import java.util.List;

/**
 *
 * @author Daniel Noriega
 */
public class CategoryController {

    public List<Category> listar() {
        CategoryDAO categoryDAO = new CategoryDAO(new ConnectionFactory().recuperarConexion());
        return categoryDAO.listar();
    }

    public List<Category> cargaReporte() {
        CategoryDAO categoryDAO = new CategoryDAO(new ConnectionFactory().recuperarConexion());
        return categoryDAO.listarConProductos();
    }
}
