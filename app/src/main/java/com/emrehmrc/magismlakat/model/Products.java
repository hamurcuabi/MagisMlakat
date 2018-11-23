package com.emrehmrc.magismlakat.model;

import java.util.List;

public class Products
{
    private String picture;

    private String isActive;

    private String title;

    private String _id;

    private List<Prices> prices;

    private String available;

    public String getPicture ()
    {
        return picture;
    }

    public void setPicture (String picture)
    {
        this.picture = picture;
    }

    public String getIsActive ()
    {
        return isActive;
    }

    public void setIsActive (String isActive)
    {
        this.isActive = isActive;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public List<Prices> getPrices ()
    {
        return prices;
    }

    public void setPrices (List<Prices> prices)
    {
        this.prices = prices;
    }

    public String getAvailable ()
    {
        return available;
    }

    public void setAvailable (String available)
    {
        this.available = available;
    }

    @Override
    public String toString()
    {
        return "Products [picture = "+picture+", isActive = "+isActive+", title = "+title+", _id = "+_id+", prices = "+prices+", available = "+available+"]";
    }
}

