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
public class PruebaPoolConexiones {

    public static void main(String[] args) throws SQLException {
        ConnectionFactory conexionesPool = new ConnectionFactory();
        for (int i = 0; i < 20; i++) {
            Connection conexion = conexionesPool.recuperarConexion();
            System.out.println("Conexion " + i + " abierta.");
        }
    }
}
