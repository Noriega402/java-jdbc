/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.alura.jdbc;

import javax.swing.JFrame;
import com.alura.jdbc.view.StockControlFrame;

/**
 *
 * @author Daniel Noriega
 */
public class Principal {

    public static void main(String[] args) {
        StockControlFrame produtoCategoriaFrame = new StockControlFrame();
        produtoCategoriaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
