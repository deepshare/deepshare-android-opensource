package com.singulariti.deepshare.protocol.httpsendmessages;

import android.content.Context;
import android.text.TextUtils;

import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.ErrorString;
import com.singulariti.deepshare.listeners.DSFailListener;
import com.singulariti.deepshare.protocol.ServerHttpRespMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendMessage;
import com.singulariti.deepshare.protocol.httprespmessages.ClearUsageRespMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class ClearUsageMessage extends ServerHttpSendJsonMessage {
    public final static String URL_PATH = "dsusages/";
    private DSFailListener listener;

    public ClearUsageMessage(Context context, DSFailListener callback) {
        super(context);
        this.listener = listener;
    }

    @Override
    public JSONObject getJSONObject(Configuration config) throws JSONException {
        JSONObject linkPost = new JSONObject();

        //linkPost.put("session_id", config.getUniqueID());

        return linkPost;
    }

    public DSFailListener getListener() {
        return listener;
    }

    @Override
    public ServerHttpRespMessage buildResponse() {
        return new ClearUsageRespMessage(this);
    }

    @Override
    public String getMethod() {
        return ServerHttpSendMessage.DELETE;
    }

    @Override
    public String getUrlPath() {
        return URL_PATH + Configuration.getInstance().getAppKey() + "/" + Configuration.getInstance().getUniqueID();
    }

}
