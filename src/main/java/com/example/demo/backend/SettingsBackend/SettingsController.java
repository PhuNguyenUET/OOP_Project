package com.example.demo.backend.SettingsBackend;

import static java.lang.Character.isLetter;

public class SettingsController {
    FunctionRepository functionRepository = new FunctionRepository();
    SettingsService settingsService = new SettingsService(functionRepository);

    public String updateProfilePictureID(int userID, int profilePictureID) {
        return settingsService.updateProfilePictureID(userID, profilePictureID);
    }

    public String updateName(int userID, String newName, String password) {
        if (newName.isEmpty()) {
            return "You cannot leave name empty";
        }
        if (password.isEmpty()) {
            return "You cannot leave password empty";
        }
        return settingsService.updateName(userID, newName, password);
    }

    public String updateUsername(int userID, String newUsername, String password) {
        int n = newUsername.length();
        if (newUsername.isEmpty()) {
            return "You cannot leave username empty";
        }
        if (password.isEmpty()) {
            return "You cannot leave password empty";
        }
        for (int i = 0; i < n; i++) {
            if (newUsername.charAt(i) == ' ') {
                return "Spaces are not allowed in username";
            }
        }
        return settingsService.updateUsername(userID, newUsername, password);
    }


    public String updatePassword(int userID, String oldPassword, String newPassword, String newPasswordConfirm) {
        if (oldPassword.isEmpty()) {
            return "You cannot leave password empty";
        }
        int n = newPassword.length();
        if(n < 8) {
            return "Password must contain at least 8 characters";
        }
        boolean hasLetter = false;
        boolean hasNumber = false;
        for (int i = 0; i < n; i++) {
            char c = newPassword.charAt(i);
            if (Character.isLetter(c)) {
                hasLetter = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }
        if (!hasNumber || !hasLetter) {
            return "Password must contain both letters and numbers";
        }
        return settingsService.updatePassword(userID, oldPassword, newPassword, newPasswordConfirm);
    }

    public int getProfileID(int userID) {
        return settingsService.getProfileID(userID);
    }

    public String getName(int userID) {
        return settingsService.getName(userID);
    }
}
