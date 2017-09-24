package com.example.hx.day1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hx.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText register_name;
    private EditText register_password;
    private Button register_zc;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        register_name = (EditText) findViewById(R.id.register_name);
        register_password = (EditText) findViewById(R.id.register_password);
        register_zc = (Button) findViewById(R.id.register_zc);

        register_zc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_zc:
                submit();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            EMClient.getInstance().createAccount(name, password);
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        name = register_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "输入账号", Toast.LENGTH_SHORT).show();
            return;
        }

        password = register_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
