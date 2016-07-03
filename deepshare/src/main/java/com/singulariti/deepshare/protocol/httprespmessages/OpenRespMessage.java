package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.Configuration;
import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httpsendmessages.OpenMessage;
import com.singulariti.deepshare.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class OpenRespMessage extends ServerHttpRespJsonMessage {
    private JSONObject data;

    public OpenRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }


    @Override
    public void getPayload(JSONObject obj) throws JSONException {
        if (obj.has("inapp_data")) {
            data = Util.getJSONObject(obj.getString("inapp_data"));
        }
    }

    @Override
    public void processResponse() {
        if (isOk()) {
            Configuration.getInstance().setClickId("");

            if (((OpenMessage) getRequest()).getListener() != null) {
                long currentTime = System.currentTimeMillis();
                Util.inappDataTime.put(currentTime - Util.startTicks);
                ((OpenMessage) getRequest()).getListener().onInappDataReturned(getParams());
            }

        } else if (((OpenMessage) getRequest()).getListener() != null) {
            ((OpenMessage) getRequest()).getListener().onFailed(getError());
        }
    }

    public JSONObject getParams() {
        return data;
    }
}
