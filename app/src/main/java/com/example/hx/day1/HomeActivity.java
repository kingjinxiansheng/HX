package com.example.hx.day1;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hx.App;
import com.example.hx.R;
import com.example.hx.base.ActivityCollector;
import com.example.hx.base.BaseActivity;
import com.example.hx.base.BaseFragment;
import com.example.hx.base.FragmentMager;
import com.example.hx.fragment.HuihuaFragment;
import com.example.hx.fragment.ShezhiFragment;
import com.example.hx.fragment.TOngxunluFragment;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_tv)
    TextView homeTv;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.unread_msg_number)
    TextView unreadMsgNumber;
    @BindView(R.id.btn_container_conversation)
    RelativeLayout btnContainerConversation;
    @BindView(R.id.unread_address_number)
    TextView unreadAddressNumber;
    @BindView(R.id.btn_container_address_list)
    RelativeLayout btnContainerAddressList;
    @BindView(R.id.btn_container_setting)
    RelativeLayout btnContainerSetting;
    @BindView(R.id.main_bottom)
    LinearLayout mainBottom;
    @BindView(R.id.home_fragmelayout)
    FrameLayout homeFragmelayout;
    @BindView(R.id.activity_home)
    RelativeLayout activityHome;
    @BindView(R.id.home_huihua)
    AppCompatButton homeHuihua;
    @BindView(R.id.home_tongxunlu)
    AppCompatButton homeTongxunlu;
    @BindView(R.id.home_shezhi)
    AppCompatButton homeShezhi;
    @BindView(R.id.home_add)
    ImageView homeAdd;
    private FragmentManager fragmentManager;
    private long mExitTime;

    @Override
    protected void initData() {
        fragmentManager = App.mBaseActivity.getSupportFragmentManager();
        FragmentMager.getInstance().start(R.id.home_fragmelayout, HuihuaFragment.class, false).build();
    }

    @Override
    protected void initView() {
//        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(username);
//        conversation.getUnreadMsgCount();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    /**
     * 自定义回退栈管理；
     * 获取栈顶的fragment的名字，判断名字是否和主页的名字是否一样，
     * 如果一样就退出应用，如果不是就回退上一个fragment；
     * <p>
     * onBackPressed()与onKeyDown
     */
    @Override
    public void onBackPressed() {
        String simpleName = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HuihuaFragment".equals(simpleName) ||
                "ShezhiFragment".equals(simpleName) ||
                "TOngxunluFragment".equals(simpleName)
                ) {
            finish();
        } else {
            if (fragmentManager.getBackStackEntryCount() > 1) {
                fragmentManager.popBackStackImmediate();//
                String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
                App.lastfragment = (BaseFragment) fragmentManager.findFragmentByTag(name);
            }
        }
    }

    /**
     * 双击退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String name = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();
        if ("HuihuaFragment".equals(name) ||
                "ShezhiFragment".equals(name) ||
                "TOngxunluFragment".equals(name)
                ) {
            if (keyCode == KeyEvent.KEYCODE_BACK) {//back键被按下了
                if ((System.currentTimeMillis() - mExitTime) > 2000) {//第二次点击判断是否在两秒内完成，是的话Finish掉（退出）
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    mExitTime = System.currentTimeMillis();
                } else {
                    ActivityCollector.getInstance().exit(App.mBaseActivity);
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.home_huihua, R.id.home_tongxunlu, R.id.home_shezhi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_huihua:
                homeTv.setText("会话");
                homeAdd.setVisibility(View.GONE);
                FragmentMager.getInstance().start(R.id.home_fragmelayout, HuihuaFragment.class, false).build();
                break;
            case R.id.home_tongxunlu:
                homeTv.setText("通讯录");
                homeAdd.setVisibility(View.VISIBLE);
                FragmentMager.getInstance().start(R.id.home_fragmelayout, TOngxunluFragment.class, false).build();
                break;
            case R.id.home_shezhi:
                homeTv.setText("设置");
                homeAdd.setVisibility(View.GONE);
                FragmentMager.getInstance().start(R.id.home_fragmelayout, ShezhiFragment.class, false).build();
                break;
        }
    }
}
