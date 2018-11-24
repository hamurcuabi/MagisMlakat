package com.emrehmrc.magismlakat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrehmrc.magismlakat.R;
import com.emrehmrc.magismlakat.helper.SingletonProduct;
import com.emrehmrc.magismlakat.model.Prices;
import com.emrehmrc.magismlakat.model.ProductModel;
import com.emrehmrc.magismlakat.model.Products;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter
        .MyViewHolder> {
    private static final String TAG = "ProductRecyclerAdapter";

    private Context mContext;
    private ProductModel model;
    private int selectedPosition = -1;// no selection by default


    public ProductRecyclerAdapter(Context mContext, ProductModel model) {
        this.mContext = mContext;
//        for (int i = 0; i < model.getProducts().size(); i++) {
//            if (!Boolean.parseBoolean(model.getProducts().get(i).getIsActive())) {
//                model.getProducts().remove(i);
//            }
//        }
        this.model = model;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_product_item, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {

        final Products current = model.getProducts().get(i);
        Picasso.get()
                .load(current.getPicture())
                .resize(64, 64)
                .centerCrop()
                .into(myViewHolder.img_product);
        myViewHolder.txt_title.setText(current.getTitle());
        myViewHolder.txt_avaible.setText(current.getAvailable());
        //Prices
        List<Prices> current_prices = current.getPrices();
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
            Log.d(TAG, "Visibilty");
        } else if (current_prices.size() == 1) {
            String prices_string_format_1 = "1 " + current_prices.get(0)
                    .getUnit() + "->" + current_prices.get(0).getPrice() + current_prices.get(0)
                    .getCurrency();

            myViewHolder.txt_price_one.setText(prices_string_format_1);
            myViewHolder.txt_price_two.setVisibility(View.GONE);
            myViewHolder.cb_price_two.setVisibility(View.GONE);
            myViewHolder.txt_price_three.setVisibility(View.GONE);
            myViewHolder.cb_price_three.setVisibility(View.GONE);
            Log.d(TAG, "Visibilty whyyy");
        } else {
            myViewHolder.txt_price_one.setVisibility(View.GONE);
            myViewHolder.cb_price_one.setVisibility(View.GONE);
            myViewHolder.txt_price_two.setVisibility(View.GONE);
            myViewHolder.cb_price_two.setVisibility(View.GONE);
            myViewHolder.txt_price_three.setVisibility(View.GONE);
            myViewHolder.cb_price_three.setVisibility(View.GONE);
        }
        //cb_1
        myViewHolder.cb_price_one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    myViewHolder.cb_price_two.setChecked(false);
                    myViewHolder.cb_price_three.setChecked(false);
                    SingletonProduct.getInstance().setBuy_type(1);
                    SingletonProduct.getInstance().setProducts(current);
                    Log.d(TAG, "selectedPosition: "+selectedPosition);
                    if(selectedPosition!=myViewHolder.getAdapterPosition()&&selectedPosition!=-1){
                        notifyItemChanged(selectedPosition);
                    }
                    selectedPosition = myViewHolder.getAdapterPosition();
                    Log.d(TAG, "myViewHolder.getAdapterPosition(): "+myViewHolder.getAdapterPosition());




                } else {
                    SingletonProduct.getInstance().setBuy_type(0);
                    SingletonProduct.getInstance().setProducts(null);

                }


            }
        });
        //cb_2
        myViewHolder.cb_price_two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    myViewHolder.cb_price_one.setChecked(false);
                    myViewHolder.cb_price_three.setChecked(false);
                    SingletonProduct.getInstance().setBuy_type(2);
                    SingletonProduct.getInstance().setProducts(current);
                    Log.d(TAG, "selectedPosition: "+selectedPosition);
                    if(selectedPosition!=myViewHolder.getAdapterPosition()&&selectedPosition!=-1){
                        notifyItemChanged(selectedPosition);
                    }
                    selectedPosition = myViewHolder.getAdapterPosition();
                    Log.d(TAG, "myViewHolder.getAdapterPosition(): "+myViewHolder.getAdapterPosition());



                } else {
                    SingletonProduct.getInstance().setBuy_type(0);
                    SingletonProduct.getInstance().setProducts(null);



                }
            }
        });
        //cb_3
        myViewHolder.cb_price_three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    myViewHolder.cb_price_one.setChecked(false);
                    myViewHolder.cb_price_two.setChecked(false);
                    SingletonProduct.getInstance().setBuy_type(3);
                    SingletonProduct.getInstance().setProducts(current);
                    Log.d(TAG, "selectedPosition: "+selectedPosition);
                    if(selectedPosition!=myViewHolder.getAdapterPosition()&&selectedPosition!=-1){
                        notifyItemChanged(selectedPosition,null);
                    }
                    selectedPosition = myViewHolder.getAdapterPosition();
                    Log.d(TAG, "myViewHolder.getAdapterPosition(): "+myViewHolder.getAdapterPosition());




                } else {
                    SingletonProduct.getInstance().setBuy_type(0);
                    SingletonProduct.getInstance().setProducts(null);


                }
            }
        });

        Log.d(TAG, "onBindViewHolder: "+ i);

    }

    @Override
    public int getItemCount() {
        return model.getProducts().size();
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
