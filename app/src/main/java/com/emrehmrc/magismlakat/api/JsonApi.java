package com.emrehmrc.magismlakat.api;

import com.emrehmrc.magismlakat.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonApi {

    @GET("products")
    Call<ProductModel> getProducts();
}
