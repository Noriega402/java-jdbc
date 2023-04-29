/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel Noriega
 */
public class ConnectionFactory {
    public Connection recuperarConexion() throws SQLException{
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost/tienda_alura?useTimeZone=true&serverTimeZone=UTC",
                "noriega",
                "server2023$");
        
        return con;
    }
}
