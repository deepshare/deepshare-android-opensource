package com.singulariti.deepshare.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Util {
    private static final String TAG = "Util";

    public static long startTicks = 0;

    public static JSONArray inappDataTime;
    public static JSONArray generateURLTime;
    public static JSONArray attributeTime;

    public static void initUtil() {
        inappDataTime = new JSONArray();
        generateURLTime = new JSONArray();
        attributeTime = new JSONArray();
    }

    public static JSONObject escapeJSONStrings(JSONObject inputObj) {
        JSONObject filteredObj = new JSONObject();
        if (inputObj != null) {
            Iterator<?> keys = inputObj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                try {
                    if (inputObj.has(key) && inputObj.get(key).getClass().equals(String.class)) {
                        filteredObj.put(key, inputObj.getString(key).replace("\n", "\\n").replace("\r", "\\r").replace("\"", "\\\""));
                    } else if (inputObj.has(key)) {
                        filteredObj.put(key, inputObj.get(key));
                    }
                } catch (JSONException ignore) {
                }
            }
        }
        return filteredObj;
    }

    public static JSONObject getJSONObject(String paramString) {
        if (TextUtils.isEmpty(paramString)) {
            return new JSONObject();
        } else {
            try {
                return new JSONObject(paramString);
            } catch (JSONException e) {
                byte[] encodedArray = Base64.decode(paramString.getBytes(), Base64.NO_WRAP);
                try {
                    return new JSONObject(new String(encodedArray));
                } catch (JSONException ex) {
                    Log.e(TAG, Log.getStackTraceString(ex));
                    return new JSONObject();
                }
            }
        }
    }
}
