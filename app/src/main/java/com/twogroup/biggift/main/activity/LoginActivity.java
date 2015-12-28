package com.twogroup.biggift.main.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.utils.MySQLiteOpenHelper;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button id_login;
    private TextView youce;
    private ImageView fanhui;
    private EditText numphone, password;
    private boolean islogin;
    private String username;
    private String passwords;
    private SQLiteDatabase db;
    private SharedPreferences.Editor editor;
    private SharedPreferences mySharedPreferences;
    private String getnumphone;
    private String getpassword;

    @Override
    protected void onResume() {
        super.onResume();
        numphone = (EditText) findViewById(R.id.id_numphone);
        password = (EditText) findViewById(R.id.id_pasword);
        id_login = (Button) findViewById(R.id.id_login);
        getnumphone = numphone.getText().toString();
        getpassword = password.getText().toString();
        queryData();
        numphone.setText(username);
        password.setText(passwords);
        if (!TextUtils.isEmpty(username) && getnumphone.equals(username) && !TextUtils.isEmpty(passwords) && getpassword.equals(passwords)) {
            Intent intent = new Intent(LoginActivity.this, MyinformationActivity.class);
            editor.putBoolean("islogin", true).apply();
            db.close();
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        mySharedPreferences = getSharedPreferences("flag",
                MODE_PRIVATE);
        editor = mySharedPreferences.edit();
        final boolean isok=mySharedPreferences.getBoolean("flag", islogin);
        id_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isok){
                queryData();
                    numphone.setText(username);
                    password.setText(passwords);
                getnumphone = numphone.getText().toString();
                getpassword = password.getText().toString();
                Log.w("--1-username", username + "getnumphone" + getnumphone + "passwords" + passwords + "getlpassword" + getpassword);
                if (!TextUtils.isEmpty(username) && getnumphone.equals(username) && !TextUtils.isEmpty(passwords) && getpassword.equals(passwords)) {
                    Intent intent = new Intent(LoginActivity.this, MyinformationActivity.class);
                    editor.putBoolean("islogin", true).apply();
                    db.close();
                    startActivity(intent);
                    finish();
                }
            }else {
                    queryData();
                    getnumphone = numphone.getText().toString();
                    getpassword = password.getText().toString();
                    Log.w("--1-username", username + "getnumphone" + getnumphone + "passwords" + passwords + "getlpassword" + getpassword);
                    if (!TextUtils.isEmpty(username) && getnumphone.equals(username) && !TextUtils.isEmpty(passwords) && getpassword.equals(passwords)) {
                        Intent intent = new Intent(LoginActivity.this, MyinformationActivity.class);
                        editor.putBoolean("islogin", true).apply();
                        db.close();
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });


    }


    private void initView() {
        id_login = (Button) findViewById(R.id.id_login);
        youce = (TextView) findViewById(R.id.tv1);
        fanhui = (ImageView) findViewById(R.id.img1);
        numphone = (EditText) findViewById(R.id.id_numphone);
        password = (EditText) findViewById(R.id.id_pasword);
        youce.setOnClickListener(this);
        fanhui.setOnClickListener(this);

    }

    public void queryData() {
        MySQLiteOpenHelper mySQLite = new MySQLiteOpenHelper(this, "userInfo.db", null, 1);
        db = mySQLite.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT username,passwords from users", null);
        if (c != null) {
            while (c.moveToNext()) {
                username = c.getString(c.getColumnIndex("username"));
                passwords = c.getString(c.getColumnIndex("passwords"));
                Log.i("--2-", "  name:" + username + "  salary:" + passwords);
            }
        } else {
            Log.i("--2-", "nothing");
        }
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.img1:

                finish();
                break;
            case R.id.tv1:
                intent.setClass(LoginActivity.this, RegisteredActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}

