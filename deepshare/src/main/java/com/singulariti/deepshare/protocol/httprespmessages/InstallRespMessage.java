package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httpsendmessages.InstallMessage;
import com.singulariti.deepshare.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InstallRespMessage extends ServerHttpRespJsonMessage {
    private JSONObject data;

    public InstallRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }


    @Override
    public void getPayload(JSONObject obj) throws JSONException {
        if (obj.has("inapp_data")) {
            data = Util.getJSONObject(obj.getString("inapp_data"));
        }

        if (obj.has("channels")) {
            JSONArray channels = obj.getJSONArray("channels");
            if (channels != null) {
                Configuration.getInstance().setInstallChannels(String.valueOf(channels));
            }
        }
    }

    @Override
    public void processResponse() {
        if (isOk()) {
            Configuration.getInstance().setClickId("");

            if (((InstallMessage) getRequest()).getListener() != null) {
                long currentTime = System.currentTimeMillis();
                Util.inappDataTime.put(currentTime - Util.startTicks);
                ((InstallMessage) getRequest()).getListener().onInappDataReturned(getParams());
            }

        } else if (((InstallMessage) getRequest()).getListener() != null) {
            ((InstallMessage) getRequest()).getListener().onFailed(getError());
        }
    }

    public JSONObject getParams() {
        return data;
    }

}
