package com.alkasilverlake.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.app.Fragment;
import android.widget.TextView;


import com.alkasilverlake.fragment.HomeFragment;
import com.alkasilverlake.fragment.NotificationFragment;
import com.alkasilverlake.fragment.Order;
import com.alkasilverlake.fragment.Profile;
import com.alkasilverlake.fragment.Setting;
import com.alkasilverlake.R;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {

   private LinearLayout tab_li;
   ImageView user,not,set,home,order;
    Fragment fragment=null;
    Toolbar toolbar;
  public   TextView tv_head;
  public  TextView tvcountid;
  public  ImageView ivcart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);


        //Toolbar
        ivcart=findViewById(R.id.im_cartbadge);

        tv_head = findViewById(R.id.tv_head);
        tvcountid = findViewById(R.id.count_cartbadge);

//        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        // Setting toolbar as the ActionBar with setSupportActionBar() call
//        setSupportActionBar(toolbar);

        tab_li=findViewById(R.id.tab_li);
        user=findViewById(R.id.user);
        not=findViewById(R.id.not);
        set=findViewById(R.id.setting);
        home=findViewById(R.id.home);
        order=findViewById(R.id.order);

        user.setOnClickListener(this);
        not.setOnClickListener(this);
        set.setOnClickListener(this);
        home.setOnClickListener(this);
        order.setOnClickListener(this);



        tv_head.setText("HOME");
        //Fragment home Bottom Tab layout
        fragment = new HomeFragment();
        displaySelectedFragment(fragment);

        user.setImageResource(R.drawable.ic_user);
        not.setImageResource(R.drawable.ic_notification);
        set.setImageResource(R.drawable.ic_settings);
        home.setImageResource(R.drawable.ic_big_bottle_of_water);
        order.setImageResource(R.drawable.ic_wedding_planning);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.user:
                tv_head.setText("PROFILE");
                fragment = new Profile();
                displaySelectedFragment(fragment);
                updateTabView( R.id.user);
                break;
            case R.id.not:
                tv_head.setText("NOTIFICATION");
                fragment = new NotificationFragment();
                displaySelectedFragment(fragment);
                updateTabView( R.id.not);
                break;
            case R.id.setting:
                tv_head.setText("SETTING");
                fragment = new Setting();
                displaySelectedFragment(fragment);
                updateTabView( R.id.setting);
                break;
            case R.id.home:
                tv_head.setText("HOME");
                fragment = new HomeFragment();
                displaySelectedFragment(fragment);
                updateTabView( R.id.home);

                break;
            case R.id.order:
                tv_head.setText("ORDER");
                fragment = new Order();
                displaySelectedFragment(fragment);
                updateTabView( R.id.order);
                break;
            default:
                tv_head.setText("HOME");
                fragment = new HomeFragment();
                displaySelectedFragment(fragment);
                break;
        }
    }

    private void updateTabView(int flag){
        switch (flag){
            case R.id.user:
                user.setImageResource(R.drawable.ic_active_user);
                not.setImageResource(R.drawable.ic_notification);
                set.setImageResource(R.drawable.ic_settings);
                home.setImageResource(R.drawable.ic_big_bottle_of_water);
                order.setImageResource(R.drawable.ic_wedding_planning);
                break;

            case R.id.not:
                user.setImageResource(R.drawable.ic_user);
                not.setImageResource(R.drawable.ic_active_notification);
                set.setImageResource(R.drawable.ic_settings);
                home.setImageResource(R.drawable.ic_big_bottle_of_water);
                order.setImageResource(R.drawable.ic_wedding_planning);
                break;
            case R.id.setting:
                user.setImageResource(R.drawable.ic_user);
                not.setImageResource(R.drawable.ic_notification);
                set.setImageResource(R.drawable.ic_active_settings);
                home.setImageResource(R.drawable.ic_big_bottle_of_water);
                order.setImageResource(R.drawable.ic_wedding_planning);
                break;

            case R.id.home:
                user.setImageResource(R.drawable.ic_user);
                not.setImageResource(R.drawable.ic_notification);
                set.setImageResource(R.drawable.ic_settings);
                home.setImageResource(R.drawable.ic_active_big_bottle_of_water);
                order.setImageResource(R.drawable.ic_wedding_planning);
                break;

            case R.id.order:
                user.setImageResource(R.drawable.ic_user);
                not.setImageResource(R.drawable.ic_notification);
                set.setImageResource(R.drawable.ic_settings);
                home.setImageResource(R.drawable.ic_big_bottle_of_water);
                order.setImageResource(R.drawable.ic_active_wedding_planning);
                break;
        }
    }
    private void displaySelectedFragment(Fragment fragment) {
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

}
