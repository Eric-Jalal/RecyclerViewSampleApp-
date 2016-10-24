package com.synolexoil.synolexom;

import co.ronash.pushe.Pushe;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backtory.androidsdk.Storage;
import com.backtory.androidsdk.internal.Backtory;
import com.backtory.androidsdk.internal.BacktoryCallBack;
import com.backtory.androidsdk.internal.BacktoryCloudCode;
import com.backtory.androidsdk.internal.Config;
import com.backtory.androidsdk.model.BacktoryResponse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    EditText etName;
    EditText etPhone;
    EditText etMail;
    EditText etState;
    EditText etCity;
    EditText etAddress;
    EditText etAutoServiceName;
    EditText etMerchandName;

    /*Action Bar menu items initialization*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    /*Menu item selection controller*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_productsList:

                Intent intent = new Intent(MainActivity.this, productsList.class);
                startActivity(intent);

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Appbar styling*/
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        /*Pushe lib init*/
        Pushe.initialize(this, true);

        /*Calligraphy Configuration method*/
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/IRANSansWeb_Light.ttf")
                .setFontAttrId(R.attr.fontPath).build());

        /*Direction decorator*/
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        /*Title*/
        /*getSupportActionBar().setTitle("ثبت خرید");*/




        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etMail = (EditText) findViewById(R.id.etMail);
        etState = (EditText) findViewById(R.id.etState);
        etCity = (EditText) findViewById(R.id.etCity);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etAutoServiceName = (EditText) findViewById(R.id.etAutoServiceName);
        etMerchandName = (EditText) findViewById(R.id.etMerchandName);

        Backtory.init(this, Config.newBuilder().
                // Setting shared preferences as default storage for backtory
                        storage(new Storage.SharedPreferencesStorage(this)).
                // Enabling User Services
                        initAuth("initKey", "initKey").
                // Enabling Cloud Code
                        initCloudCode("initKey").
                // Finilizing sdk
                        build());
        Button btnSend = (Button) findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserData userData = new UserData(
                        etName.getText().toString(),
                        etState.getText().toString(),
                        etPhone.getText().toString(),
                        etMail.getText().toString(),
                        etCity.getText().toString(),
                        etAddress.getText().toString(),
                        etAutoServiceName.getText().toString(),
                        etMerchandName.getText().toString()
                );
                InsertData(userData);
            }
        });

    }

    public void InsertData(UserData userData) {
        BacktoryCloudCode.runInBackground("insertData", userData, Void.class, new BacktoryCallBack<Void>() {
            @Override
            public void onResponse(BacktoryResponse<Void> backtoryResponse) {
                if (backtoryResponse.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "ارسال با موفقیت انجام شد.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "error code: " + backtoryResponse.code(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    // BACKTORY
    public class UserData {
        public String etName;
        public String etState;
        public String etPhone;
        public String etMail;
        public String etCity;
        public String etAddress;
        public String etAutoServiceName;
        public String etMerchandName;

        public UserData(String etName, String etState, String etPhone, String etMail, String etCity, String etAddress, String etAutoServiceName, String etMerchandName) {
            this.etName = etName;
            this.etState = etState;
            this.etPhone = etPhone;
            this.etMail = etMail;
            this.etCity = etCity;
            this.etAddress = etAddress;
            this.etAutoServiceName = etAutoServiceName;
            this.etMerchandName = etMerchandName;
        }

    }


}