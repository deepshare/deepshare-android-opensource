package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.transport.ServerHttpConnection;
import com.singulariti.deepshare.utils.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class CloseRespMessage extends ServerHttpRespJsonMessage {

    public CloseRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }

    @Override
    public void getPayload(JSONObject obj) throws JSONException {
    }

    @Override
    public void processResponse() {
        Log.i("DeepShareImpl", "DeepShare session closed");
        //TODO:Should have a success callback
        //ServerHttpConnection.reset(0);
    }
}
