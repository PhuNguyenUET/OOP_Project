package com.example.demo.frontend.SettingsFrontEnd;

import com.example.demo.backend.SettingsBackend.SettingsController;

public class SettingsIntegration {
    private static SettingsIntegration _instance = null;

    private SettingsIntegration() {
    }

    protected static SettingsIntegration Instance() {
        if (_instance == null) {
            _instance = new SettingsIntegration();
        }
        return _instance;
    }

    private final SettingsController backend = new SettingsController();

    public String updateProfilePictureID(int userID, int profilePictureID, String password) {
        return backend.updateProfilePictureID(userID, profilePictureID, password);
    }
    public String updateUsername(int userID, String newUsername, String password) {
        return backend.updateUsername(userID, newUsername, password);
    }

    public String updatePassword(int userID, String oldPassword, String newPassword, String newPasswordConfirm) {
        return backend.updatePassword(userID, oldPassword, newPassword, newPasswordConfirm);
    }
}
