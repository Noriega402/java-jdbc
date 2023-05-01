/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Daniel Noriega
 */
public class ProductController {

    public void modificar(String nombre, String descripcion, Integer id) {
        // TODO
    }

    public void eliminar(Integer id) {
        // TODO
    }

    public List<Map<String, String>> listar() throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion(); //agregar conexion a la DB
        Statement stm = con.createStatement();
        stm.execute("SELECT *FROM products");

        ResultSet resultSet = stm.getResultSet();

        List<Map<String, String>> resultado = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, String> row = new HashMap<>();
            row.put("id", String.valueOf(resultSet.getInt("id")));
            row.put("name", resultSet.getString("name"));
            row.put("description", resultSet.getString("description"));
            row.put("quantity", String.valueOf(resultSet.getInt("quantity")));

            resultado.add(row);
        }

        con.close();
        return resultado;
    }

    public void guardar(Map<String, String> producto) throws SQLException {
        // TODO
        Connection con = new ConnectionFactory().recuperarConexion();

        Statement stm = con.createStatement();

        stm.execute("INSERT INTO products(name,description, quantity)"
                + "VALUES('" + producto.get("name") + "' ,"
                + "'" + producto.get("description") + "' ,"
                + producto.get("quantity") + ")",
                Statement.RETURN_GENERATED_KEYS // para recuperar el valor del ID creado
        );
        
        ResultSet resultSet = stm.getGeneratedKeys();
        
        while(resultSet.next()){
            System.out.println(
                    String.format("Fue insertado el producto con ID %d", resultSet.getInt(1))
            );
            resultSet.getInt(1);
        }
    }

}
