package com.singulariti.deepshare.listeners;

import org.json.JSONObject;

/**
 * Created by zhaohai on 15/11/6.
 */
public interface DSInappDataListener extends DSFailListener {
    public void onInappDataReturned(JSONObject initParams);
}
