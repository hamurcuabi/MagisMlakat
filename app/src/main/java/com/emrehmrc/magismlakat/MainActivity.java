package com.emrehmrc.magismlakat;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.emrehmrc.magismlakat.adapter.ProductRecyclerAdapter;
import com.emrehmrc.magismlakat.api.ApiClient;
import com.emrehmrc.magismlakat.api.JsonApi;
import com.emrehmrc.magismlakat.fragment.ProductReadyToBuyFragment;
import com.emrehmrc.magismlakat.helper.SingletonProduct;
import com.emrehmrc.magismlakat.model.ProductModel;
import com.emrehmrc.magismlakat.model.Products;

import java.util.ArrayList;
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
    private boolean isEnable = true;
    private ProductModel responseModel;
    private int counter = 3;
    private NestedScrollView nestedScrollView;
    private Button btn_buy;
    private EditText edt_amoun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcv_products = findViewById(R.id.rcycler_product);
        nestedScrollView = findViewById(R.id.nested);
        btn_buy = findViewById(R.id.btn_buy);
        edt_amoun = findViewById(R.id.edt_amount);

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
                List<Products> temp = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    temp.add(responseModel.getProducts().get(i));
                }

                productModel.setProducts(temp);
                adapter = new ProductRecyclerAdapter(getApplicationContext(), productModel);
                rcv_products.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        nestedScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {

            @Override
            public void onScrollChanged() {
                View view = nestedScrollView.getChildAt(nestedScrollView.getChildCount() - 1);

                int diff = (view.getBottom() - (nestedScrollView.getHeight() + nestedScrollView
                        .getScrollY()));

                if (diff == 0 && isEnable) {
                    counter++;
                    isEnable = false;
                    DataFetch();


                }
            }
        });

        Toast.makeText(this, "Fetching Data started", Toast.LENGTH_LONG).show();
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edt_amoun.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Amount Can not be empty!", Toast
                            .LENGTH_LONG).show();
                } else if (SingletonProduct.getInstance().getBuy_type() == 0) {

                    Toast.makeText(getApplicationContext(), "Please Select an item!", Toast
                            .LENGTH_LONG).show();
                } else {
                    SingletonProduct.getInstance().setAmount(Integer.parseInt(edt_amoun.getText()
                            .toString()));
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    fragmentTransaction.replace(R.id.relative, ProductReadyToBuyFragment.newInstance());
                    fragmentTransaction.commit();


                }
            }
        });

    }

    private void DataFetch() {
        Toast.makeText(this, "Fetching Data!", Toast.LENGTH_LONG).show();
        int count = productModel.getProducts().size();
        Log.d(TAG, "productModel: " + count);
        Log.d(TAG, "responseModel: " + responseModel.getProducts().size());
        for (int i = count; i <= count + 5; i++) {
            if (responseModel.getProducts().size() >= i)
                productModel.getProducts().add(i, responseModel.getProducts().get(i));
            Log.d(TAG, "DataFetch: " + responseModel.getProducts().get(i).toString());
            adapter.notifyItemInserted(i);


        }


        isEnable = true;


    }


}
