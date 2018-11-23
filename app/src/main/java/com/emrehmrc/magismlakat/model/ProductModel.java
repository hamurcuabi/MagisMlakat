package com.emrehmrc.magismlakat.model;


public class ProductModel
{
    private Products[] products;

    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ProductModel [products = "+products+"]";
    }
}