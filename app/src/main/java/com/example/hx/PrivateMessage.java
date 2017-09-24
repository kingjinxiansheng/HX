package com.example.hx;

import com.hyphenate.chat.EMMessage;

/**
 * Created by 王晓亮 on 2017/9/11.
 */

public class PrivateMessage {
    public EMMessage message;
    public String uHead;

    public static PrivateMessage crateMessage(EMMessage message,String uHead){
        PrivateMessage privateMessage = new PrivateMessage();
        privateMessage.message = message;
        privateMessage.uHead = uHead;
        return privateMessage;
    }
}
