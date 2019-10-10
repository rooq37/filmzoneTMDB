package com.rooq37.filmzone.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    public static final String MESSAGES_INFO_SESSION_KEY = "notificationServiceInfoMessagesKey";
    public static final String MESSAGES_ERROR_SESSION_KEY = "notificationServiceErrorMessagesKey";
    private final HttpSession httpSession;

    @Autowired
    public NotificationService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public void addErrorMessage(String message) {
        addMessage(MessageType.NOTIFICATION_ERROR, message);
    }

    public void addInfoMessage(String message) {
        addMessage(MessageType.NOTIFICATION_INFO, message);
    }

    private void addMessage(MessageType messageType, String message) {
        List<String> messageList;
        String sessionAttributeKey = null;
        if (messageType == MessageType.NOTIFICATION_ERROR) {
            sessionAttributeKey = MESSAGES_ERROR_SESSION_KEY;
        } else if (messageType == MessageType.NOTIFICATION_INFO) {
            sessionAttributeKey = MESSAGES_INFO_SESSION_KEY;
        }
        if (sessionAttributeKey == null) {
            throw new IllegalArgumentException("Given MessageType is not supported");
        }
        messageList = (List<String>) httpSession.getAttribute(sessionAttributeKey);
        if (messageList == null) {
            messageList = new ArrayList<>();
        }
        messageList.add(message);
        httpSession.setAttribute(sessionAttributeKey, messageList);
    }

}
