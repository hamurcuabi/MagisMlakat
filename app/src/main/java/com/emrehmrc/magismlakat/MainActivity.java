package com.emrehmrc.magismlakat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.emrehmrc.magismlakat.adapter.ProductRecyclerAdapter;
import com.emrehmrc.magismlakat.api.ApiClient;
import com.emrehmrc.magismlakat.api.JsonApi;
import com.emrehmrc.magismlakat.model.ProductModel;
import com.emrehmrc.magismlakat.model.Products;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private JsonApi jsonApi;
    private RecyclerView rcv_products;
    private ProductRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProductModel productModel;
    private List<Products> mainList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv_products = findViewById(R.id.rcycler_product);
        layoutManager = new LinearLayoutManager(this);
        rcv_products.setLayoutManager(layoutManager);
        //

        jsonApi = ApiClient.getClient().create(JsonApi.class);

        Call<ProductModel> call = jsonApi.getProducts();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                productModel = response.body();
                adapter = new ProductRecyclerAdapter(getApplicationContext(), productModel);
                rcv_products.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
