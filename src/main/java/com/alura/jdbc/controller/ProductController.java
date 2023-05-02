/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();

        String query = "UPDATE products SET name = ?,"
                + "description = ?,"
                + "quantity = ?"
                + "WHERE id = ?";

        PreparedStatement stm = con.prepareStatement(query);
        stm.setString(1, nombre);
        stm.setString(2, descripcion);
        stm.setInt(3, cantidad);
        stm.setInt(4, id);
        stm.execute();
        int updates = stm.getUpdateCount();
        con.close();

        return updates;
    }

    public int eliminar(Integer id) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion();
        String query = "DELETE FROM products WHERE id = ?";

        PreparedStatement stm = con.prepareStatement(query);
        stm.setInt(1, id);
        stm.execute();
        int deletes = stm.getUpdateCount(); // devuelve el numero de filas que fueron modificadas
        con.close();

        return deletes;
    }

    public List<Map<String, String>> listar() throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion(); //agregar conexion a la DB
        String query = "SELECT *FROM products";

        PreparedStatement stm = con.prepareStatement(query);
        stm.execute();

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
        Connection con = new ConnectionFactory().recuperarConexion();
        con.setAutoCommit(false); // nosotros controlamos la trasaccion de las consultas

        String name = producto.get("name");
        String description = producto.get("description");
        Integer quantity = Integer.valueOf(producto.get("quantity"));
        Integer quantityMax = 100;

        String query = "INSERT INTO products(name,description, quantity) VALUES(?,?,?)";
        PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        // RETURN_GENERATED_KEYS -> retorna el ultimo ID del producto insertado en la DB

        try {
            do {
                int cantidadParaGuardar = Math.min(quantity, quantityMax);
                ejecutarRegistro(name, description, cantidadParaGuardar, stm);
                quantity -= quantityMax;
            } while (quantity > 0);
            con.commit(); // realizar la transaccion correctamente
        } catch (Exception e) {
            con.rollback();
            System.out.println("Ocurrio un error en la transaccion, haciendo un rollback");
        }
        
        stm.close();
        con.close();
    }

    public void ejecutarRegistro(String name, String description, Integer quantity, PreparedStatement stm) throws SQLException {
        stm.setString(1, name);
        stm.setString(2, description);
        stm.setInt(3, quantity);
        stm.execute();

        ResultSet resultSet = stm.getGeneratedKeys(); // tomar el nuevo ID del producto ingresado

        while (resultSet.next()) {
            System.out.println(
                    String.format("Fue insertado el producto con ID %d", resultSet.getInt(1))
            );
            resultSet.getInt(1);
        }
    }

}
