package com.alkasilverlake.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alkasilverlake.R;
import com.alkasilverlake.model.Header;
import com.alkasilverlake.model.Home_content_list;


public class HomeListAdapter extends ArrayAdapter<Object>
{

    private LayoutInflater layoutInflater;
    LinearLayout li1,li2;
    ImageView ivadd,ivsub;
    TextView tvquantity;



    public HomeListAdapter(Context context, List<Object> objects)
    {
        super(context, 0, objects);
        layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        //header
        if(getItem(position) instanceof Header)
        {
            convertView = layoutInflater.inflate(R.layout.listview_header, null);
//            if (convertView == null || convertView.findViewById(R.id.textViewHeader)==null)
//            {
//
//            }
            TextView textView = convertView.findViewById(R.id.textViewHeader);
            Header header = (Header) getItem(position);
            textView.setText(header.getWater_name());
        }
        else //ViewHolder Pattern for content
        {
            convertView = layoutInflater.inflate(R.layout.home_content_list, null);

                  li1 = convertView.findViewById(R.id.inc_li);
                  li2 = convertView.findViewById(R.id.inc_li1);
                  tvquantity = convertView.findViewById(R.id.tv_Bottle_quntituy);
                  ivadd = convertView.findViewById(R.id.im_inc_add);
                  ivsub = convertView.findViewById(R.id.im_inc_sub);

            final int[] product_quantity = {1};
            tvquantity.setText(String.valueOf(product_quantity[0]));

            ivadd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    product_quantity[0]++;
                    if (product_quantity[0] > 1000) {
                        product_quantity[0] = 1000;
                    }

                    tvquantity.setText(String.valueOf(product_quantity[0]));
                }
            });
            ivsub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    product_quantity[0]--;
                    if (product_quantity[0] < 1) {
                        product_quantity[0] = 1;
                    }
                    tvquantity.setText(String.valueOf(product_quantity[0]));
                }
            });


            final Home_content_list home_content_list= (Home_content_list) getItem(position);
                  if(home_content_list.isClicked){

                      li1.setVisibility(View.GONE);
                      li2.setVisibility(View.VISIBLE);


                  } else{
                      li2.setVisibility(View.GONE);
                      li1.setVisibility(View.VISIBLE);
                  }

                li1.setOnClickListener(new View.OnClickListener() {
                      @Override
                      public void onClick(View v) {
                          Home_content_list home_content_list= (Home_content_list) getItem(position);
                          home_content_list.isClicked=true;
                          notifyDataSetChanged();

                      }
                  });

        }
        return convertView;
    }

}

