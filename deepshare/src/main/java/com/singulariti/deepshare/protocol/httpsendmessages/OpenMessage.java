package com.singulariti.deepshare.protocol.httpsendmessages;

import android.content.Context;
import android.text.TextUtils;

import com.singulariti.deepshare.BuildConfig;
import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.listeners.DSInappDataListener;
import com.singulariti.deepshare.protocol.ServerHttpRespMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httprespmessages.OpenRespMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenMessage extends ServerHttpSendJsonMessage {
    public final static String URL_PATH = "inappdata/";
    private DSInappDataListener listener;

    public OpenMessage(Context context, DSInappDataListener listener) {
        super(context);
        this.listener = listener;
    }

    public DSInappDataListener getListener() {
        return listener;
    }

    @Override
    public JSONObject getJSONObject(Configuration config) throws JSONException {
        JSONObject openPost = new JSONObject();

        openPost.put("is_newuser", false);

        String text = config.getClickId();
        config.setClickId("");
        if (!TextUtils.isEmpty(text)) {
            openPost.put("click_id", text);;
        }

        text = config.getWorkerId();
        config.setWorkerId("");
        if (!TextUtils.isEmpty(text)) {
            openPost.put("worker_id", text);;
        }

        text = config.getUniqueID();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("unique_id", text);
        }
        int versionCode = config.getAppVersionCode();
        openPost.put("app_version_code", versionCode);


        text = config.getAppVersion();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("app_version_name", text);
        }

        text = "android" + BuildConfig.VERSION_NAME;
        if (!TextUtils.isEmpty(text)) {
            openPost.put("sdk_info", text);
        }

        text = config.getCarrier();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("carrier_name", text);
        }

        openPost.put("is_wifi_connected", config.isWifiConnected());

        openPost.put("is_emulator", !config.hasRealHardwareId());

        text = config.getPhoneBrand();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("brand", text);
        }
        text = config.getPhoneModel();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("model", text);
        }

        text = config.getOS();
        if (!TextUtils.isEmpty(text)) {
            openPost.put("os", text);
        }

        openPost.put("os_version", "" + config.getOSRelease());

        return openPost;
    }

    @Override
    public ServerHttpRespMessage buildResponse() {
        return new OpenRespMessage(this);
    }

    @Override
    public String getUrlPath() {
        return URL_PATH + Configuration.getInstance().getAppKey();
    }
}
