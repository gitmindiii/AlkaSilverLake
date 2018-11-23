package com.alkasilverlake.adapter;

/**
 * Created by mindiii on 16/11/18.
 */

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alkasilverlake.R;
import com.alkasilverlake.model.Header;
import com.alkasilverlake.model.ProductChild;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Header> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<ProductChild>> _listDataChild;
    cartupdate cartupdate;

    TextView tvcount;
    Boolean isClicked = false;
    int test_count;

    public interface cartupdate {

        public void updateproductcount();


    }

    private int product_quantity = 0;


    public ExpandableListAdapter(Context context, List<Header> listDataHeader, HashMap<String, List<ProductChild>> listChildData, TextView tvcount, cartupdate cartupdate) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.tvcount = tvcount;
        this.cartupdate = cartupdate;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getWaterId())
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final ProductChild productChild = (ProductChild) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.home_content_list, null);
        }


        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.water_name);
        final LinearLayout li_view1 = (LinearLayout) convertView.findViewById(R.id.inc_li);
        final LinearLayout li_view2 = (LinearLayout) convertView.findViewById(R.id.inc_li1);
        final TextView tvquantity = convertView.findViewById(R.id.tv_Bottle_quntituy);
        ImageView ivadd = convertView.findViewById(R.id.im_inc_add);
        ImageView ivsub = convertView.findViewById(R.id.im_inc_sub);
        TextView water_name = convertView.findViewById(R.id.water_name);
//        final TextView tvcount = convertView.findViewById(R.id.count_cartbadge);
        TextView water_price = convertView.findViewById(R.id.water_price);


        water_name.setText(productChild.getProduct_name());
        water_price.setText(productChild.getProduct_price());

        int count = productChild.getCount();
        tvquantity.setText(String.valueOf(count));


        //final int[] product_quantity = {1};
        ivadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductChild productChild = (ProductChild) getChild(groupPosition, childPosition);
                int test_count = productChild.getCount();

                test_count = test_count + 1;
                if (test_count != 0) {
                    productChild.setCount(test_count);
                    notifyDataSetChanged();
                }
            }
        });
        ivsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductChild productChild = (ProductChild) getChild(groupPosition, childPosition);
                int test_count = productChild.getCount();
                test_count = test_count - 1;
                if (test_count > 0) {
                    productChild.setCount(test_count);
                    notifyDataSetChanged();

                }
            }
        });

        //notifyDataSetChanged();


        if (productChild.isClicked()) {

            li_view1.setVisibility(View.GONE);
            li_view2.setVisibility(View.VISIBLE);

        } else {
            li_view2.setVisibility(View.GONE);
            li_view1.setVisibility(View.VISIBLE);
        }


        li_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                productChild.setClicked(true);

                notifyDataSetChanged();
                cartupdate.updateproductcount();


                //  if(childPosition == index) {


              /*  }
                else{
                    li_view1.setVisibility(View.VISIBLE);
                    li_view2.setVisibility(View.GONE);
                }*/


            }
        });

        //  txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition).getWaterId())
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {


        Header header = (Header) getGroup(groupPosition);
        String headerTitle = header.getWater_name();
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.listview_header, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.textViewHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);
        ExpandableListView mExpandableListView = (ExpandableListView) parent;
        mExpandableListView.expandGroup(groupPosition);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}