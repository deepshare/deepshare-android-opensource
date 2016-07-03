package com.singulariti.deepshare.protocol.httpsendmessages;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;

import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.ErrorString;
import com.singulariti.deepshare.listeners.NewUsageFromMeListener;
import com.singulariti.deepshare.protocol.ServerHttpRespMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendMessage;
import com.singulariti.deepshare.protocol.httprespmessages.NewUsageRespMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class NewUsageMessage extends ServerHttpSendJsonMessage {
    public final static String URL_PATH = "dsusages/";
    private NewUsageFromMeListener listener;

    public NewUsageMessage(Context context, NewUsageFromMeListener callback) {
        super(context);
        this.listener = callback;
    }

    @Override
    public JSONObject getJSONObject(Configuration config) throws JSONException {

        JSONObject linkPost = new JSONObject();

        //linkPost.put("session_id", config.getUniqueID());

        return linkPost;
    }

    public NewUsageFromMeListener getListener() {
        return listener;
    }

    @Override
    public ServerHttpRespMessage buildResponse() {
        return new NewUsageRespMessage(this);
    }

    @Override
    public String getMethod() {
        return ServerHttpSendMessage.GET;
    }

    @Override
    public String getUrlPath() {
        return URL_PATH + Configuration.getInstance().getAppKey() + "/" + Configuration.getInstance().getUniqueID();
    }

}
