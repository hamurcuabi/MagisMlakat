package com.emrehmrc.magismlakat.helper;

import com.emrehmrc.magismlakat.model.Products;

public class SingletonProduct {
    private static SingletonProduct singleton = new SingletonProduct();

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public int getBuy_type() {
        return buy_type;
    }

    public void setBuy_type(int buy_type) {
        this.buy_type = buy_type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private Products products;
    private int buy_type;
    private int amount;
    private SingletonProduct() {
    }

    public static SingletonProduct getInstance() {
        return singleton;
    }


}
