package com.example.hx.day1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hx.MyThread;
import com.example.hx.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_password;
    private Button bt_zc;
    private Button bt_dl;
    private String name;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        bt_zc = (Button) findViewById(R.id.bt_zc);
        bt_dl = (Button) findViewById(R.id.bt_dl);

        bt_zc.setOnClickListener(this);
        bt_dl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_zc:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.bt_dl:
                submit();
                EMClient.getInstance().login(name, password, new EMCallBack() {//回调
                    @Override
                    public void onSuccess() {
                        EMClient.getInstance().groupManager().loadAllGroups();
                        EMClient.getInstance().chatManager().loadAllConversations();
                        MyThread.getUIThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "成功登录到服务器", Toast.LENGTH_SHORT).show();
                            }
                        });
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                        finish();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }

                    @Override
                    public void onError(int code, String message) {
                        MyThread.getUIThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, "账号或密码错误！无法登录到服务器", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                break;
        }
    }

    private void submit() {
        // validate
        name = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "账号", Toast.LENGTH_SHORT).show();
            return;
        }

        password = et_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "密码", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something


    }
}
