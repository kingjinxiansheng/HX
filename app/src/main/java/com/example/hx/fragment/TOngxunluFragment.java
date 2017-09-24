package com.example.hx.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hx.MyThread;
import com.example.hx.R;
import com.example.hx.base.BaseFragment;
import com.example.hx.day2.LiaotianActivity;
import com.hyphenate.EMValueCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMContactManager;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.ui.EaseChatFragment;
import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.hyphenate.easeui.widget.EaseContactList;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 王晓亮 on 2017/9/8.
 */

public class TOngxunluFragment extends BaseFragment implements EMValueCallBack<List<String>> {


    @BindView(R.id.contact_list)
    EaseContactList contactList;
    Unbinder unbinder;
    private List<EaseUser> listease = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.tongxunlufragment;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        contactList.init(listease);
        ListView listView = contactList.getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), LiaotianActivity.class);
                intent.putExtra("A", listease.get(i).getUsername());
                startActivity(intent);
            }
        });
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

    @Override
    public void onSuccess(List<String> strings) {
        for (String name : strings) {
            listease.add(new EaseUser(name));
        }
        contactList.refresh();
    }

    @Override
    public void onError(int i, String s) {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        EMClient.getInstance().contactManager().aysncGetAllContactsFromServer(this);
        super.onCreate(savedInstanceState);
    }
}
