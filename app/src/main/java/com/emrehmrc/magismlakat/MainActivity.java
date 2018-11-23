package com.emrehmrc.magismlakat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.Toast;

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
    private LinearLayoutManager layoutManager;
    private ProductModel productModel;
    private List<Products> mainList;
    private boolean isScrolling = false;
    private int currentItems, totalItems, scrollOutItems;
    private ProductModel responseModel;
    private int counter=2;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        rcv_products = findViewById(R.id.rcycler_product);
        layoutManager = new LinearLayoutManager(this);
        rcv_products.setLayoutManager(layoutManager);
        //

        jsonApi = ApiClient.getClient().create(JsonApi.class);

        Call<ProductModel> call = jsonApi.getProducts();
        call.enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {

                productModel = new ProductModel();
                responseModel = response.body();
                productModel.setProducts(responseModel.getProducts().subList(0, 10));
                adapter = new ProductRecyclerAdapter(getApplicationContext(), productModel);
                rcv_products.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


        rcv_products.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = layoutManager.getChildCount();
                totalItems = layoutManager.getItemCount();
                scrollOutItems = layoutManager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems + scrollOutItems == totalItems)) {

                    //data fetch
                    isScrolling = false;
                    counter++;
                    DataFetch();
                }
            }
        });
    }

    private void DataFetch() {
        Log.d(TAG, "DataFetch: ");
        Toast.makeText(getApplicationContext(),"Data Fetched",Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.VISIBLE);
        if((counter*5)<=responseModel.getProducts().size()){
            productModel.setProducts(responseModel.getProducts().subList(0, 5*counter));
        }
        else {
            productModel.setProducts(responseModel.getProducts());
        }
        adapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);

    }


}
