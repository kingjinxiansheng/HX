package com.example.hx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.hx.R;
import com.example.hx.base.BaseFragment;
import com.example.hx.day2.LiaotianActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.easeui.widget.EaseConversationList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 王晓亮 on 2017/9/8.
 */

public class HuihuaFragment extends BaseFragment {
    @BindView(R.id.list)
    EaseConversationList list;
    Unbinder unbinder;
    private List<EMConversation> listt = new ArrayList<>();
    private List<String> listname = new ArrayList<>();

    //消息监听
    EMMessageListener msgListener = new EMMessageListener() {

        @Override
        public void onMessageReceived(List<EMMessage> messages) {
            //收到消息
            list.refresh();
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> messages) {
            //收到透传消息
        }

        @Override
        public void onMessageRead(List<EMMessage> messages) {
            //收到已读回执
        }

        @Override
        public void onMessageDelivered(List<EMMessage> message) {
            //收到已送达回执
        }

        @Override
        public void onMessageChanged(EMMessage message, Object change) {
            //消息状态变动
            list.refresh();
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.huihuafragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        EMClient.getInstance().chatManager().addMessageListener(msgListener);
        Map<String, EMConversation> conversations = EMClient.getInstance().chatManager().getAllConversations();
        Set<String> strings = conversations.keySet();
        for (String key : strings) {
            listt.add(conversations.get(key));
            listname.add(key);
        }
        list.init(listt);
//刷新列表
        list.refresh();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LiaotianActivity.class);
                intent.putExtra("A", listname.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        list.refresh();
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
