/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.dao;

import com.alura.jdbc.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Noriega
 */
public class ProductDAO {

    final private Connection con;

    public ProductDAO(Connection con) {
        this.con = con;
    }

    public void guardar(Product producto) {
        String query = "INSERT INTO products(name,description, quantity, category_id) VALUES(?,?,?,?)";
        try (con) {
            PreparedStatement stm = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            // RETURN_GENERATED_KEYS -> retorna el ultimo ID del producto insertado en la DB
            try (stm) {
                ejecutarRegistro(producto, stm);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void ejecutarRegistro(Product producto, PreparedStatement stm) {
        try {
            stm.setString(1, producto.getName());
            stm.setString(2, producto.getDescription());
            stm.setInt(3, producto.getQuantity());
            stm.setInt(4, producto.getCategoryId());
            stm.execute();

            final ResultSet resultSet = stm.getGeneratedKeys(); // tomar el nuevo ID del producto ingresado
            try (resultSet) {
                while (resultSet.next()) {
                    producto.setId(resultSet.getInt(1));
                    System.out.println(
                            String.format("Fue insertado el producto con ID %s", producto)
                    );
                    resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> listar() {
        List<Product> resultado = new ArrayList<>();
        String query = "SELECT *FROM products";
        try (con) {
            final PreparedStatement stm = con.prepareStatement(query);
            try (stm) {
                stm.execute();
                final ResultSet resultSet = stm.getResultSet();

                while (resultSet.next()) {
                    Product row = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getInt("quantity")
                    );
                    resultado.add(row);
                }
            }
            return resultado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int eliminar(Integer id) {
        try (con) {
            String query = "DELETE FROM products WHERE id = ?";
            final PreparedStatement stm = con.prepareStatement(query);
            try (stm) {
                stm.setInt(1, id);
                stm.execute();
                int deletes = stm.getUpdateCount(); // devuelve el numero de filas que fueron modificadas
                return deletes;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int modificar(String nombre, String descripcion, Integer cantidad, Integer id) {
        try (con) {
            String query = "UPDATE products SET name = ?,"
                    + "description = ?,"
                    + "quantity = ?"
                    + "WHERE id = ?";
            final PreparedStatement stm = con.prepareStatement(query);
            try (stm) {
                stm.setString(1, nombre);
                stm.setString(2, descripcion);
                stm.setInt(3, cantidad);
                stm.setInt(4, id);
                stm.execute();
                int updates = stm.getUpdateCount();

                return updates;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
