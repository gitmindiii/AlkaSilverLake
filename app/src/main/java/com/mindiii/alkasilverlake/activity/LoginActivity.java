package com.mindiii.alkasilverlake.activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mindiii.alkasilverlake.R;
import com.mindiii.alkasilverlake.connection.RetrofitClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends AppCompatActivity {


    EditText logemail, logpassword;
    Button btnsign, btnlog;
    private ProgressDialog pDialog;
    SharedPreferences preferences;
    CheckBox rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Shared preference
        preferences = getSharedPreferences("data", MODE_PRIVATE);

        // remove State bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Login Casting

        logemail = (EditText) findViewById(R.id.log_email);
        logpassword = (EditText) findViewById(R.id.log_pass);
        btnsign = (Button) findViewById(R.id.log_sign);
        btnlog = (Button) findViewById(R.id.log_btn);
        rem = (CheckBox) findViewById(R.id.checkBox2);


        //Remember me code

        if (preferences.getString("tag", "").equals("0")) {
            String ema = preferences.getString("em", "");
            String paa = preferences.getString("pa", "");
            rem.setChecked(true);
            logemail.setText(ema);
            logpassword.setText(paa);

        } else {
            rem.setSelected(false);
        }


        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LoginUser();

            }
        });
        btnsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        // onShareClick();

    }

    public void LoginUser() {

        String lemail = logemail.getText().toString().trim();
        String lpass = logpassword.getText().toString().trim();
        if (rem.isChecked()) {
            SharedPreferences.Editor e = preferences.edit();
            e.putString("tag", "0");
            e.putString("em", lemail);
            e.putString("pa", lpass);
            e.commit();

        } else {

            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.commit();
        }


        if (lemail.length() == 0) {
            Toast.makeText(LoginActivity.this, "Email cannot be Blank", Toast.LENGTH_SHORT).show();

        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(lemail).matches()) {

            Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
        } else if (lpass.length() == 0) {
            Toast.makeText(LoginActivity.this, "Password cannot be Blank", Toast.LENGTH_SHORT).show();
        } else {

            //progress dialog

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.getWindow().setBackgroundDrawable(new
                    ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.setContentView(R.layout.my_progress);


            Call<ResponseBody> call = RetrofitClient.getInstance().getApi()
                    .login(lemail, lpass);

            call.enqueue(new Callback<ResponseBody>() {


                @Override

                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                    try {

                        hideDialog();

                        if (response.code() == 200) {

                            String stresult = response.body().string();

                            Log.i("eail4254", "" + stresult);


                            JSONObject jsonObject = new JSONObject(stresult);

                            String statusCode = jsonObject.optString("status");


                            String msg = jsonObject.optString("message");

                            if (statusCode.equals("success")) {

                                JSONObject jsonObject1 = jsonObject.getJSONObject("userDetail");
                                String name = jsonObject1.getString("fullName");
                                String email = jsonObject1.getString("email");
                                String authToken = jsonObject1.getString("authToken");

                                SharedPreferences preferences = getSharedPreferences("logdata", MODE_PRIVATE);

                                SharedPreferences.Editor c = preferences.edit();
                                c.putString("authToken", authToken);
                                c.commit();

                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, TabActivity.class);

                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                            }

                        } else if (response.code() == 400) {

                            String result = response.errorBody().string();

                            Log.d("response400", result);

                            JSONObject jsonObject = new JSONObject(result);

                            String statusCode = jsonObject.optString("status");


                            String msg = jsonObject.optString("message");

                            if (statusCode.equals("fail")) {
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

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

                public void onFailure(Call<ResponseBody> call, Throwable t) {

                    hideDialog();

                    Toast.makeText(LoginActivity.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


                }
            });

        }
    }


    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    public void onShareClick() {
        Resources resources = getResources();

        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        // Native email client doesn't currently support HTML, but it doesn't hurt to try in case they fix it
        emailIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("sDASDASDASD"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "XYZ5465");
        emailIntent.setType("message/rfc822");

        PackageManager pm = getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        Intent openInChooser = Intent.createChooser(emailIntent, "ACKJHASJ7896");

        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            // Extract the label, append it, and repackage it in a LabeledIntent
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if (packageName.contains("android.email")) {
                emailIntent.setPackage(packageName);
            } else if (packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms") || packageName.contains("android.gm")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");
                if (packageName.contains("twitter")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "sDASDASDASD");
                } else if (packageName.contains("facebook")) {
                    // Warning: Facebook IGNORES our text. They say "These fields are intended for users to express themselves. Pre-filling these fields erodes the authenticity of the user voice."
                    // One workaround is to use the Facebook SDK to post, but that doesn't allow the user to choose how they want to share. We can also make a custom landing page, and the link
                    // will show the <meta content ="..."> text from that page with our link in Facebook.
                    intent.putExtra(Intent.EXTRA_TEXT, "sDASDASDASD");
                } else if (packageName.contains("mms")) {
                    intent.putExtra(Intent.EXTRA_TEXT, "sDASDASDASD");
                } else if (packageName.contains("android.gm")) { // If Gmail shows up twice, try removing this else-if clause and the reference to "android.gm" above
                    intent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("sDASDASDASD"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "sDASDASDASD");
                    intent.setType("message/rfc822");
                }

                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        // convert intentList to array
        LabeledIntent[] extraIntents = intentList.toArray(new LabeledIntent[intentList.size()]);

        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);
    }


}


