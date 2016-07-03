package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httpsendmessages.ChangeValueByMessage;
import com.singulariti.deepshare.utils.Util;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangeValueByRespMessage extends ServerHttpRespJsonMessage {

    public ChangeValueByRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }


    @Override
    public void getPayload(JSONObject obj) throws JSONException {

    }

    @Override
    public void processResponse() {
        if (isOk()) {
            //TODO:Should have a success callback
            long currentTime = System.currentTimeMillis();
            Util.attributeTime.put(currentTime - Util.startTicks);
        } else if (((ChangeValueByMessage) getRequest()).getListener() != null) {
            ((ChangeValueByMessage) getRequest()).getListener().onFailed(getError());
        }
    }

}
