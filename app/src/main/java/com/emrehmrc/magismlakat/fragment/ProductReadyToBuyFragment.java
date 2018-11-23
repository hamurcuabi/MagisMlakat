package com.emrehmrc.magismlakat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.emrehmrc.magismlakat.R;
import com.emrehmrc.magismlakat.helper.SingletonProduct;
import com.emrehmrc.magismlakat.model.Products;
import com.squareup.picasso.Picasso;

public class ProductReadyToBuyFragment extends Fragment {

    private static final String TAG = "ProductReadyToBuyFragme";
    private TextView txt_title, txt_amount, txt_price;
    private ImageView img_product;
    private ImageButton close;

    public static ProductReadyToBuyFragment newInstance() {

        Bundle args = new Bundle();
        ProductReadyToBuyFragment fragment = new ProductReadyToBuyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.product_ready_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txt_amount = view.findViewById(R.id.txt_amount);
        txt_title = view.findViewById(R.id.txt_title);
        txt_price = view.findViewById(R.id.txt_price);
        img_product = view.findViewById(R.id.img_product);
        close = view.findViewById(R.id.btn_close);
        SetProduct();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CloseFragment();
            }
        });
    }

    private void CloseFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private void SetProduct() {

        Products products = SingletonProduct.getInstance().getProducts();
        int buy_type = SingletonProduct.getInstance().getBuy_type();
        int amount = SingletonProduct.getInstance().getAmount();
        int price;
        String amount_type;
        String curreny;

        switch (buy_type) {
            case 1:
                price = Integer.parseInt(products.getPrices().get(0).getPrice()) * amount;
                amount_type = products.getPrices().get(0).getUnit();
                curreny = products.getPrices().get(0).getCurrency();
                break;
            case 2:
                price = Integer.parseInt(products.getPrices().get(1).getPrice()) * amount;
                amount_type = products.getPrices().get(1).getUnit();
                curreny = products.getPrices().get(1).getCurrency();
                break;
            case 3:
                price = Integer.parseInt(products.getPrices().get(2).getPrice()) * amount;
                amount_type = products.getPrices().get(2).getUnit();
                curreny = products.getPrices().get(2).getCurrency();
                break;
            default:
                amount_type = "";
                price = 0;
                curreny = "";
                break;


        }

        Picasso.get()
                .load(products.getPicture())
                .resize(128, 128)
                .centerCrop()
                .into(img_product);
        txt_amount.setText("" + amount +" "+ amount_type);
        txt_title.setText(products.getTitle());
        txt_price.setText("" + price + " " + curreny);

    }
}
