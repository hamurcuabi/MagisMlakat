package com.emrehmrc.magismlakat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrehmrc.magismlakat.R;
import com.emrehmrc.magismlakat.model.Prices;
import com.emrehmrc.magismlakat.model.ProductModel;
import com.emrehmrc.magismlakat.model.Products;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter
        .MyViewHolder> {

    private Context mContext;
    private ProductModel model;
    private List<Products> productsList;

    public ProductRecyclerAdapter(Context mContext, ProductModel model) {
        this.mContext = mContext;
        this.model = model;
        this.productsList = Arrays.asList(model.getProducts());

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_product_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        Products current = productsList.get(i);
        Picasso.get()
                .load(current.getPicture())
                .resize(64, 64)
                .centerCrop()
                .into(myViewHolder.img_product);
        myViewHolder.txt_title.setText(current.getTitle());
        myViewHolder.txt_avaible.setText(current.getAvailable());
        //Prices
        List<Prices> current_prices = Arrays.asList(current.getPrices());
        if (current_prices.size() == 3) {
            String prices_string_format_1 = "1 " + current_prices.get(0)
                    .getUnit() + "->" + current_prices.get(0).getPrice() + current_prices.get(0)
                    .getCurrency();
            String prices_string_format_2 = "1 " + current_prices.get(1)
                    .getUnit() + "->" + current_prices.get(1).getPrice() + current_prices.get(1)
                    .getCurrency();
            String prices_string_format_3 = "1 " + current_prices.get(2)
                    .getUnit() + "->" + current_prices.get(2).getPrice() + current_prices.get(2)
                    .getCurrency();
            myViewHolder.txt_price_one.setText(prices_string_format_1);
            myViewHolder.txt_price_two.setText(prices_string_format_2);
            myViewHolder.txt_price_three.setText(prices_string_format_3);


        } else if (current_prices.size() == 2) {
            String prices_string_format_1 = "1 " + current_prices.get(0)
                    .getUnit() + "->" + current_prices.get(0).getPrice() + current_prices.get(0)
                    .getCurrency();
            String prices_string_format_2 = "1 " + current_prices.get(1)
                    .getUnit() + "->" + current_prices.get(1).getPrice() + current_prices.get(1)
                    .getCurrency();

            myViewHolder.txt_price_one.setText(prices_string_format_1);
            myViewHolder.txt_price_two.setText(prices_string_format_2);
            myViewHolder.txt_price_three.setVisibility(View.GONE);
            myViewHolder.cb_price_three.setVisibility(View.GONE);
        } else if (current_prices.size() == 1) {
            String prices_string_format_1 = "1 " + current_prices.get(0)
                    .getUnit() + "->" + current_prices.get(0).getPrice() + current_prices.get(0)
                    .getCurrency();

            myViewHolder.txt_price_one.setText(prices_string_format_1);
            myViewHolder.txt_price_two.setVisibility(View.GONE);
            myViewHolder.cb_price_two.setVisibility(View.GONE);
            myViewHolder.txt_price_three.setVisibility(View.GONE);
            myViewHolder.cb_price_three.setVisibility(View.GONE);
        } else {
            myViewHolder.txt_price_one.setVisibility(View.GONE);
            myViewHolder.cb_price_one.setVisibility(View.GONE);
            myViewHolder.txt_price_two.setVisibility(View.GONE);
            myViewHolder.cb_price_two.setVisibility(View.GONE);
            myViewHolder.txt_price_three.setVisibility(View.GONE);
            myViewHolder.cb_price_three.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return model.getProducts().length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_product;
        public TextView txt_title, txt_avaible, txt_price_one, txt_price_two, txt_price_three;
        public CheckBox cb_price_one, cb_price_two, cb_price_three;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product = itemView.findViewById(R.id.img_product);
            txt_title = itemView.findViewById(R.id.txt_title);
            txt_avaible = itemView.findViewById(R.id.txt_avaible);
            txt_price_one = itemView.findViewById(R.id.txt_prices_one);
            txt_price_two = itemView.findViewById(R.id.txt_price_two);
            txt_price_three = itemView.findViewById(R.id.txt_price_three);
            cb_price_one = itemView.findViewById(R.id.cb_price_one);
            cb_price_two = itemView.findViewById(R.id.cb_price_two);
            cb_price_three = itemView.findViewById(R.id.cb_price_three);
        }
    }
}
