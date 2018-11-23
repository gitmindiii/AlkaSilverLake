package com.alkasilverlake.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alkasilverlake.R;
import com.alkasilverlake.activity.CartActivity;
import com.alkasilverlake.activity.TabActivity;
import com.alkasilverlake.adapter.ExpandableListAdapter;
import com.alkasilverlake.connection.RetrofitClient;
import com.alkasilverlake.model.Header;
import com.alkasilverlake.model.ProductChild;
import com.alkasilverlake.responce.ProductListResponce;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class HomeFragment extends Fragment {


    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    /*List<String> listDataHeader;
    HashMap<String, List<Home_content_list>> listDataChild;*/


    List<Header> listDataHeader = new ArrayList<Header>();
    public static HashMap<String, List<ProductChild>> listDataChild = new HashMap<String, List<ProductChild>>();

    List<ProductListResponce.DataBean> productListResponces;


    int index;
    private ProgressDialog pDialog;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

        productListResponces = new ArrayList<>();
        // preparing list data
        prepareListData();
        RegisterUser();
        final TextView textView = ((TabActivity) Objects.requireNonNull(getActivity())).tvcountid;
        textView.setText("0");

        final ImageView ivcartbadge = ((TabActivity) Objects.requireNonNull(getActivity())).ivcart;


        ivcartbadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                // intent.putExtra("list",listDataChild);
                startActivity(intent);
            }
        });


        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild, textView, new ExpandableListAdapter.cartupdate() {
            @Override
            public void updateproductcount() {


                int i = Integer.parseInt(textView.getText().toString());
                i = i + 1;
                textView.setText("" + i);


            }
        });

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return view;
    }

    private void prepareListData() {
      /*  listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Home_content_list>>();


        // Adding child data
        listDataHeader.add("ALKALINE WATER");
        listDataHeader.add("PURIFIED WATER");

        // Adding child data
        List<Home_content_list> top250 = new ArrayList<Home_content_list>();


        top250.add(new Home_content_list());
        top250.add(new Home_content_list());
        top250.add(new Home_content_list());


        List<Home_content_list> nowShowing = new ArrayList<Home_content_list>();
        nowShowing.add(new Home_content_list());
        nowShowing.add(new Home_content_list());
        nowShowing.add(new Home_content_list());


        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);*/
    }

    public void RegisterUser() {

        //progress dialog
        pDialog = new ProgressDialog(getActivity());
        pDialog.getWindow().setBackgroundDrawable(new
                ColorDrawable(android.graphics.Color.TRANSPARENT));
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(true);
        pDialog.show();
        pDialog.setContentView(R.layout.my_progress);

        SharedPreferences preferences = getActivity().getSharedPreferences("logdata", Context.MODE_PRIVATE);

        String s = preferences.getString("authToken", "");


        Call<ProductListResponce> call = RetrofitClient.getInstance().getApi()
                .header(s);

        call.enqueue(new Callback<ProductListResponce>() {
            @Override
            public void onResponse(Call<ProductListResponce> call, retrofit2.Response<ProductListResponce> response) {

                try {
                    hideDialog();

                    if (response.code() == 200) {

                        if (response.body().getStatus().equals("success")) {
                            ProductListResponce productListResponce = response.body();

                            List<ProductListResponce.DataBean> dataBeans = productListResponce.getData();

                            for (ProductListResponce.DataBean dataBean : dataBeans) {

                                Header header = new Header(dataBean.getWaterId(), dataBean.getWater_name());
                                listDataHeader.add(header);

                                List<ProductListResponce.DataBean.ProductsBean> products = dataBean.getProducts();

                                List<ProductChild> productChildren = new ArrayList<>();
                                for (ProductListResponce.DataBean.ProductsBean productsBean : products) {

                                    ProductChild productChild = new ProductChild(productsBean.getProductId(),
                                            productsBean.getProduct_name(), productsBean.getProduct_price(),
                                            productsBean.getProduct_image(), productsBean.getWaterId(), productsBean.getWater_name(), productsBean.getBottleId(),
                                            productsBean.getBottle_type(), productsBean.getQuantity());

                                    productChildren.add(productChild);

                                }

                                listDataChild.put(header.getWaterId(), productChildren);
                                listAdapter.notifyDataSetChanged();
                            }

                        }


                        Log.e("re", response.body().getData().toString());

                    } else if (response.code() == 400) {

                        String result = response.errorBody().string();

                        Log.d("response400", result);

                        JSONObject jsonObject = new JSONObject(result);

                        String statusCode = jsonObject.optString("status");


                        String msg = jsonObject.optString("message");

                        if (statusCode.equals("fail")) {
                            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                        }

                    } else if (response.code() == 401) {

                        try {

                            Log.d("ResponseInvalid", response.errorBody().string());


                        } catch (Exception e1) {

                            e1.printStackTrace();

                        }

                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }


            @Override

            public void onFailure(Call<ProductListResponce> call, Throwable t) {

                hideDialog();

                Toast.makeText(getActivity(), "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        listAdapter.notifyDataSetChanged();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}


/**
 * A simple {@link Fragment} subclass.
 */
//public class HomeFragment extends ListFragment {
//
//
//    private List<Object> dataset = getContent();
//    private HomeListAdapter mAdapter;
//    public HomeFragment() {
//
//        // Required empty public constructor
//    }
//
////    ListView listView;
////    public static final String[] titles = new String[] { "ALKALINE WATER",
////            "PURIFIED WATER" };
////    ArrayList<Header> rowItems;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        // Inflate the layout for this fragment
//        LinearLayout wrapper = new LinearLayout(getActivity());
////        listView = (ListView) wrapper.findViewById(R.id.list);
//        wrapper.setBackgroundColor(getResources().getColor(R.color.colorLightGray));
//        inflater.inflate(R.layout.fragment_home, wrapper, true);
//        return wrapper;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//
//
//        setListAdapter(new HomeListAdapter(getActivity(), dataset));
//    }
//
//    private List<Object> getContent() {
//        List<Object> list = new ArrayList<>(60);
//        Home_content_list content = null;
//        Header header = null;
//
//
//        for(int j=0;j<3;j++){
//
//            header = new Header();
//            if(j==0)
//
//            header.setTitle("ALKALINE WATER");
//
//            else if(j==1)
//            {
//                header.setTitle("PURIFIED WATER");
//            }
//            else
//                {
//                    header.setTitle("DEFAULT");
//                }
//                    list.add(header);
//
//            for (int i = 0; i < 4; i++)
//            {
//
//                content = new Home_content_list();
//                list.add(content);
//            }
//
//        }
//
//        return list;
//
//    }
//
//}









                     /*  String stresult = response.body().toString();
                        Log.d("response", stresult);

                        JSONObject jsonObject = new JSONObject(stresult);


                        String statusCode = jsonObject.optString("status");

                        String msg = jsonObject.optString("message");*/

//                        if (response.body().equals("success")) {
//
//                            Gson gson = new Gson();
//                            ProductListResponce responce = gson.fromJson(response.body().toString(), ProductListResponce.class);
//                            //(response.body(),ProductListResponce.class);
//
//                            Log.e("onResponse: ", responce.getData().get(1).getWater_name());
//                           /* JSONArray jsonArray = jsonObject.getJSONArray("data");
//                            JSONObject jsonObject1 = jsonArray.getJSONObject(0);
//                            String water_name = jsonObject1.getString("water_name");
//                            JSONArray jsonArray1 = jsonObject1.getJSONArray("products");
//                            JSONObject jsonObject2 = jsonArray1.getJSONObject(0);
//                            String product_name = jsonObject2.getString("product_name");
//                            String product_price = jsonObject2.getString("product_price");*/
//
//
///*
//                                ProductListResponce responce = new ProductListResponce();
//
//                                responce.setData();*/
//