package com.singulariti.deepshare.protocol.httpsendmessages;

import android.content.Context;

import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.protocol.ServerHttpRespMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httprespmessages.CloseRespMessage;
import com.singulariti.deepshare.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class CloseMessage extends ServerHttpSendJsonMessage {
    public final static String URL_PATH = "dsactions/";
    private JSONObject receiverInfo = new JSONObject();

    public CloseMessage(Context context) {
        super(context);
    }

    @Override
    public JSONObject getJSONObject(Configuration config) throws JSONException {
        JSONObject closePost = new JSONObject();
        try {
            this.receiverInfo.put("unique_id", Configuration.getInstance().getUniqueID());
        } catch (JSONException ex) {
        }
        closePost.put("receiver_info", Util.escapeJSONStrings(receiverInfo));
        closePost.put("action", "app/close");

        //
        JSONObject lapse = new JSONObject();
        lapse.put("inappdata", Util.inappDataTime);
        lapse.put("genurl", Util.generateURLTime);
        lapse.put("attribute", Util.attributeTime);

        closePost.put("kvs", Util.escapeJSONStrings(lapse));

        return closePost;
    }

    @Override
    public ServerHttpRespMessage buildResponse() {
        return new CloseRespMessage(this);
    }

    @Override
    public String getUrlPath() {
        return URL_PATH + Configuration.getInstance().getAppKey();
    }

}
