/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alura.jdbc.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Noriega
 */
public class Category {
    private Integer id;
    private String name;
    private List<Product> products;
    
    public Category(){
        
    }
    
    public Category(int id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void agregar(Product product) {
        if(this.products == null){
            this.products = new ArrayList<Product>();
        }
        
        this.products.add(product);
    }
    
    public List<Product> getProductos(){
        return this.products;
    }
    
    @Override
    public String toString(){
        return this.name.toString();
    }
}
