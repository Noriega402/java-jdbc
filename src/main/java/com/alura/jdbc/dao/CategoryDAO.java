/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.dao;

import com.alura.jdbc.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Noriega
 */
public class CategoryDAO {
    final private Connection con;
    
    public CategoryDAO(Connection con){
        this.con = con;
    }

    public List<Category> listar() {
        List<Category> resultado = new ArrayList<>();
        String query = "SELECT  *FROM categories";
        try {
            PreparedStatement stm = con.prepareStatement(query);
            
            try(stm){
                final ResultSet resultSet = stm.executeQuery();
                
                try(resultSet){
                    while(resultSet.next()){
                        var categories = new Category(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        );
                        
                        resultado.add(categories);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        return resultado;
    }
}