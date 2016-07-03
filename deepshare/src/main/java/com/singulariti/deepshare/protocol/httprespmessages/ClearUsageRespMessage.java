package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httpsendmessages.ClearUsageMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class ClearUsageRespMessage extends ServerHttpRespJsonMessage {


    public ClearUsageRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }


    @Override
    public void getPayload(JSONObject obj) throws JSONException {
    }

    @Override
    public void processResponse() {
        if (isOk()) {
            //TODO:Should have a success callback
        } else if (((ClearUsageMessage) getRequest()).getListener() != null) {
            ((ClearUsageMessage) getRequest()).getListener().onFailed(getError());
        }
    }
}
