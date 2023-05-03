/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Daniel Noriega
 */
public class ConnectionFactory {
    private DataSource datasoruce;

    public ConnectionFactory(){
        String[] dataConnection = {
            "jdbc:postgresql://localhost/tienda_alura?useTimeZone=true&serverTimeZone=UTC",
            "noriega",
            "server2023$"
        };
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl(dataConnection[0]);
        pooledDataSource.setUser(dataConnection[1]);
        pooledDataSource.setPassword(dataConnection[2]);
        pooledDataSource.setMaxPoolSize(10); // numero MAx de conexiones
        this.datasoruce = pooledDataSource;
    }
    
    public Connection recuperarConexion() throws SQLException{
        return this.datasoruce.getConnection();
    }
}
