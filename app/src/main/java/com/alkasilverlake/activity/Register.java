package com.alkasilverlake.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alkasilverlake.R;
import com.alkasilverlake.connection.RetrofitClient;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;


public class Register extends AppCompatActivity  {


    private static final String TAG = Register.class.getSimpleName();
    EditText username, useremail, userpass;
    Button usersign,userlog;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);



        //Registration casting

        username = (EditText) findViewById(R.id.reg_name);
        useremail = (EditText) findViewById(R.id.reg_email);
        userpass = (EditText) findViewById(R.id.reg_pass);
        usersign = (Button) findViewById(R.id.reg_btn);
        userlog = (Button) findViewById(R.id.reg_login);

        userlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        usersign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RegisterUser();

            }
        });

    }

    public void RegisterUser() {
        String uname = username.getText().toString().trim();
        String uemail = useremail.getText().toString().trim();
        String upass = userpass.getText().toString().trim();


        if (uname.length() == 0) {
            Toast.makeText(Register.this, "Name cannot be Blank", Toast.LENGTH_SHORT).show();

        } else if (uemail.length() == 0) {

            Toast.makeText(Register.this, "Email cannot be Blank", Toast.LENGTH_SHORT).show();


        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {

            Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_SHORT).show();


        } else if (upass.length() == 0) {
            Toast.makeText(Register.this, "Password cannot be Blank", Toast.LENGTH_SHORT).show();


        } else {
            //progress dialog
            pDialog = new ProgressDialog(Register.this);
            pDialog.getWindow().setBackgroundDrawable(new
                    ColorDrawable(android.graphics.Color.TRANSPARENT));
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
            pDialog.setContentView(R.layout.my_progress);

            MediaType text = MediaType.parse("text/plain");
            RequestBody name1 = RequestBody.create(text, uname);
            RequestBody email1 = RequestBody.create(text, uemail);
            RequestBody password1 = RequestBody.create(text, upass);

            Call<ResponseBody> call = RetrofitClient.getInstance().getApi()
                    .createuser(name1, email1,password1);

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                    try {

                        hideDialog();

                        if (response.code() == 200) {

                            String stresult = response.body().string();
                            Log.d("response", stresult);

                            JSONObject jsonObject = new JSONObject(stresult);

                            String statusCode = jsonObject.optString("status");


                            String msg = jsonObject.optString("message");

                            if (statusCode.equals("success")) {


                                Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, LoginActivity.class);

                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();

                            }


                        } else if (response.code() == 400) {

                            String result = response.errorBody().string();

                            Log.d("response400", result);

                            JSONObject jsonObject = new JSONObject(result);

                            String statusCode = jsonObject.optString("status");


                            String msg = jsonObject.optString("message");

                            if (statusCode.equals("fail")) {
                                Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();

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

                    Toast.makeText(Register.this, "Please Check Internet Connection", Toast.LENGTH_SHORT).show();


                }
            });

        }



    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}