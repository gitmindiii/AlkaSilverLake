package com.alkasilverlake.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alkasilverlake.R;
import com.alkasilverlake.activity.CartActivity;
import com.alkasilverlake.model.ProductChild;

import java.util.List;

/**
 * Created by vaibhav dave on 30-12-2017.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context c;
    public List<ProductChild> cartlist;


    public CartAdapter(Context c, List<ProductChild> cartlist) {
        this.c = c;
        this.cartlist = cartlist;
    }

    //VIEW ITEM
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cartlist, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        //CURRENT SPACECRAFT
        final ProductChild s = cartlist.get(position);

        holder.name.setText(s.getProduct_name());
        holder.price.setText(s.getProduct_price());
        holder.count.setText(String.valueOf(s.getCount()));


        //final int[] product_quantity = {1};
        holder.ivadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductChild productChild = cartlist.get(position);
                int test_count = productChild.getCount();

                test_count = test_count + 1;
                if (test_count != 0) {
                    productChild.setCount(test_count);
                    notifyDataSetChanged();
                    ((CartActivity) c).updateCarddata(productChild.getWaterId(),productChild.getProductId(), test_count);
                }
            }
        });
        holder.ivsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductChild productChild = cartlist.get(position);
                int test_count = productChild.getCount();
                test_count = test_count - 1;
                if (test_count > 0) {
                    productChild.setCount(test_count);
                    notifyDataSetChanged();
                    ((CartActivity) c).updateCarddata(productChild.getWaterId(),productChild.getProductId(), test_count);

                }
            }
        });

    }


    //TOTAL SPACECRAFTS
    @Override
    public int getItemCount() {
        return cartlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, count, price, qun;
        ImageView img, ivadd, ivsub;


        public MyViewHolder(final View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.cart_water_name);
            count = (TextView) itemView.findViewById(R.id.cart_tv_Bottle_quntituy);
            price = (TextView) itemView.findViewById(R.id.cart_water_price);
            ivadd = (ImageView) itemView.findViewById(R.id.cart_im_inc_add);
            ivsub = (ImageView) itemView.findViewById(R.id.cart_im_inc_sub);

        }
    }

}
