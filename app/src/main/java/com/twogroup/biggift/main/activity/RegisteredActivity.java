package com.twogroup.biggift.main.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twogroup.biggift.main.R;
import com.twogroup.biggift.main.utils.MySQLiteOpenHelper;

public class RegisteredActivity extends BaseActivity implements View.OnClickListener {
    private ImageView fanhui;
    private TextView queding;
    private EditText sj, mm, mm2;
    private String username;
    private String passwords;
    private String password2;
    private String mima;
    private int m1m;
    private int length;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_registered);

        initView();

    }



    private void initView() {
        fanhui = (ImageView) findViewById(R.id.fanhui);
        queding = (TextView) findViewById(R.id.bt);
        sj = (EditText) findViewById(R.id.register_username);
        mm = (EditText) findViewById(R.id.register_passwd);
        mm2 = (EditText) findViewById(R.id.register_passwd2);
        fanhui.setOnClickListener(this);

        sj.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                length = s.length();
                sj.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if ((!hasFocus) && (length != 1)) {
                            Toast.makeText(getBaseContext(), "手机号码必须为11位", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mima = s.toString();
                m1m = s.length();
                mm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (((m1m > 0 && m1m < 6) || m1m > 12) && !hasFocus) {
                            Toast.makeText(getBaseContext(), "密码必须为6-12位", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mm2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == m1m) {
                    if (!s.toString().equals(mm.getText().toString())) {
                        Toast.makeText(getBaseContext(), "两次密码必须相同", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        queding.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.fanhui:
               finish();
                break;
            case R.id.bt:
                intent.setClass(RegisteredActivity.this, LoginActivity.class);
                username = sj.getText().toString();
                passwords = mm.getText().toString();
                password2 = mm2.getText().toString();
                if ((mm.getText().toString()).equals(mm2.getText().toString()) && (sj.length() == 1)) {

                    insertData();


                        startActivity(intent);
                        finish();

                } else {
                    Toast.makeText(getBaseContext(), "请输入正确的账号密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
    public void insertData(){
        MySQLiteOpenHelper mySQLite = new MySQLiteOpenHelper(this, "userInfo.db", null, 1);
        db = mySQLite.getWritableDatabase();
		/*String sql = "INSERT INTO person(name,salary) values (?,?)";

		db.execSQL(sql, new Object[]{name,salary});*/
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("passwords", passwords);
        Log.i("--2-", "username="+username+"passwords"+passwords);
        long id = db.insert("users", null, values);
        Log.i("--2-", "id="+id);

    }
}
