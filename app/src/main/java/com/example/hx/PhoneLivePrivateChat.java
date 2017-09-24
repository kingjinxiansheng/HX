package com.example.hx;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王晓亮 on 2017/9/11.
 */

public class PhoneLivePrivateChat {
    public static EMMessage sendChatMessage(String content,String id){
        ///创建一条文本消息,content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
        EMMessage message = EMMessage.createTxtSendMessage(content,String.valueOf(id));

        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
        return message;
    }

    //获取会话历史消息
//    public static List<PrivateMessage> getUnreadRecord(UserBean mUser, String id){
//        List<PrivateMessage> mChats = new ArrayList<>();
//
//
//        //获取历史会话消息
//        EMConversation conversation = EMClient.getInstance().chatManager().getConversation(String.valueOf(id));
//        //指定会话消息未读数清零
//        if(conversation != null){
//            conversation.markAllMessagesAsRead();
//        }
//
//        //获取此会话的所有消息
//        try {
            //循环创建
//            for(EMMessage emMessage : conversation.getAllMessages()){
//                if(StringUtils.toInt(emMessage.getFrom(),0) == id){
//                    mChats.add(PrivateMessage.crateMessage(emMessage,mUser.getAvatar()));
//                }else{
//                    mChats.add(PrivateMessage.crateMessage(emMessage,mToUser.getAvatar()));
//                }
//            }
//        }catch(Exception e){
//            //无历史消息纪录
//        }
//        return mChats;
//
//
//    }
    //获取未读消息总数

    public static int getUnreadMsgsCount(){
        return EMClient.getInstance().chatManager().getUnreadMsgsCount();
    }
}
