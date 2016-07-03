package com.singulariti.deepshare.listeners;

public interface NewUsageFromMeListener extends DSFailListener {
    public void onNewUsageFromMe(int newInstall, int newOpen);
}
