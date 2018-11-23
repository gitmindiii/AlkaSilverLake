package com.alkasilverlake.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alkasilverlake.R;
import com.alkasilverlake.adapter.CartAdapter;
import com.alkasilverlake.fragment.HomeFragment;
import com.alkasilverlake.model.ProductChild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import com.alkasilverlake.adapter.CartAdapter;

public class CartActivity extends AppCompatActivity {

    public CartAdapter cartAdapter;
    public RecyclerView recyclerView;
    ImageView img_back;
    public List<ProductChild> value = new ArrayList<>();
    HashMap<String, List<ProductChild>> myValue = HomeFragment.listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerView = (RecyclerView) findViewById(R.id.cart_recycler_view);
        img_back = findViewById(R.id.img_back);


        for (HashMap.Entry<String, List<ProductChild>> stringListHashMap : myValue.entrySet()) {

            List<ProductChild> test_value = stringListHashMap.getValue();

            if (test_value.size() > 0) {


                for (int i = 0; i < test_value.size(); i++) {
                    if (test_value.get(i).isClicked()) {
                        value.add(test_value.get(i));

                    }

                }

            }


        }


        cartAdapter = new CartAdapter(CartActivity.this, value);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cartAdapter);




        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

                ////kk
                finish();
            }
        });

    }


    public void updateCarddata(String water_id, String product_id, int count) {
        List<ProductChild> listchild = myValue.get(water_id);
        for (ProductChild productChild : listchild) {
            if (productChild.getWaterId().equals(water_id) && productChild.getProductId().equals(product_id))
                productChild.setCount(count);
        }


    }




}

