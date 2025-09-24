package com.dusk.restaurant.util;

import com.dusk.restaurant.client.dto.UserDto;
import com.dusk.restaurant.client.dto.UserResponse;
import com.dusk.restaurant.service.UserSessionService;

import java.util.prefs.Preferences;

public class SessionManager {
    private static final UserSessionService userSessionService = new UserSessionService();
    private static final String KEY_USERNAME = "username";
    private static UserResponse currentUser;

    public static boolean restoreSession() {
        Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);
        String username = prefs.get(KEY_USERNAME, null);
        if (username != null) {
            currentUser = userSessionService.findByUsername(username);
            return true;
        }
        return false;
    }

    public static void saveSession(UserResponse user) {
        Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);
        prefs.put(KEY_USERNAME, user.getUsername());
        currentUser = user;
    }

    public static void clearSession() {
        Preferences prefs = Preferences.userNodeForPackage(SessionManager.class);
        prefs.remove(KEY_USERNAME);
        currentUser = null;
    }

    public static UserResponse getCurrentUser() {
        return currentUser;
    }
}
