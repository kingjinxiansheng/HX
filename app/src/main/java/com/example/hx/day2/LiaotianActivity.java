package com.example.hx.day2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.hx.R;
import com.example.hx.base.BaseActivity;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiaotianActivity extends BaseActivity {


    @BindView(R.id.frame)
    FrameLayout frame;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String a = intent.getStringExtra("A");
        EaseChatFragment chatFragment = new EaseChatFragment();
        //传入参数
        Bundle args = new Bundle();
        // args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
        args.putString(EaseConstant.EXTRA_USER_ID, a);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, chatFragment).commit();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_liaotian;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
