package com.singulariti.deepshare.protocol.httprespmessages;

import com.singulariti.deepshare.protocol.ServerHttpRespJsonMessage;
import com.singulariti.deepshare.protocol.ServerHttpSendJsonMessage;
import com.singulariti.deepshare.protocol.httpsendmessages.NewUsageMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class NewUsageRespMessage extends ServerHttpRespJsonMessage {

    private int new_install;
    private int new_open;

    public NewUsageRespMessage(ServerHttpSendJsonMessage sent) {
        super(sent);
    }


    @Override
    public void getPayload(JSONObject obj) throws JSONException {
        new_install = obj.getInt("new_install");
        new_open = obj.getInt("new_open");
    }

    @Override
    public void processResponse() {
        if (isOk()) {
            if (((NewUsageMessage) getRequest()).getListener() != null) {
                ((NewUsageMessage) getRequest()).getListener().onNewUsageFromMe(getNewInstall(), getNewOpen());
            }
        } else if (((NewUsageMessage) getRequest()).getListener() != null) {
            ((NewUsageMessage) getRequest()).getListener().onFailed(getError());
        }
    }

    public int getNewOpen() {
        return new_open;
    }

    public int getNewInstall() {
        return new_install;
    }
}
