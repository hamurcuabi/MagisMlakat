package com.emrehmrc.magismlakat.model;


import java.util.List;

public class ProductModel
{
    private List<Products> products;

    public List<Products> getProducts ()
    {
        return products;
    }

    public void setProducts (List<Products> products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ProductModel [products = "+products+"]";
    }
}