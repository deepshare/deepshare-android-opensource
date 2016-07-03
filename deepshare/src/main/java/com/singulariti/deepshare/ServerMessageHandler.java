package com.singulariti.deepshare;

import com.singulariti.deepshare.protocol.ServerMessage;

import java.util.ArrayList;

public interface ServerMessageHandler {
    public abstract void handleServerMessage(ArrayList<ServerMessage> msgs);
}