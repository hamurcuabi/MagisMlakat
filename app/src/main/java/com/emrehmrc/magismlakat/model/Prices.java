package com.emrehmrc.magismlakat.model;

public class Prices
{
    private String unit;

    private String price;

    private String currency;

    public String getUnit ()
    {
        return unit;
    }

    public void setUnit (String unit)
    {
        this.unit = unit;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "Prices [unit = "+unit+", price = "+price+", currency = "+currency+"]";
    }
}