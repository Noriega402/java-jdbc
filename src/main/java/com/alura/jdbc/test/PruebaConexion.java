/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.test;

import com.alura.jdbc.factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Daniel Noriega
 */
public class PruebaConexion {
    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().recuperarConexion(); //agregar conexion a la DB
        System.out.println("Cerrando conexion");
        con.close();
    }
}
