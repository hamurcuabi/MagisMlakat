package com.emrehmrc.magismlakat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.emrehmrc.magismlakat.api.ApiClient;
import com.emrehmrc.magismlakat.api.JsonApi;
import com.emrehmrc.magismlakat.model.ProductModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    JsonApi jsonApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonApi = ApiClient.getClient().create(JsonApi.class);

        Call<ProductModel> call = jsonApi.getProducts();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                ProductModel productModel = response.body();

            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
