package com.example.demo.backend.SettingsBackend;

import java.sql.Connection;
import com.example.demo.backend.userDatabaseConnect;
import javafx.scene.Parent;

public class SettingsService {
    private final FunctionRepository functionRepository;

    private final Connection connection = userDatabaseConnect.getInstance().connect();

    SettingsService (FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    public String updateProfilePictureID(int userID, int profilePictureID) {
        int oldPicID = functionRepository.getProfileID(connection, userID);
        if (oldPicID == profilePictureID) {
            return "No changes detected.";
        } else {
            functionRepository.updateProfilePicture(connection, userID, profilePictureID);
            return "Profile Picture updated successfully";
        }
    }
    
    public String updateName(int userID, String newName, String password) {
        if (!functionRepository.isPasswordCorrect(connection, userID, password)) {
            return "Wrong password.";
        }
        String oldName = functionRepository.getName(connection, userID);
        if(oldName.equals(newName)) {
            return "No changes detected.";
        } else {
            functionRepository.updateName(connection, userID, newName);
            return "Name updated successfully";
        }
    }
    
    public String updateUsername(int userID, String newUsername, String password) {
        if (!functionRepository.isPasswordCorrect(connection, userID, password)) {
            return "Wrong password.";
        }
        String oldUsername = functionRepository.getUserName(connection, userID);
        if(oldUsername.equals(newUsername)) {
            return "No changes detected.";
        }
        if(functionRepository.containsUsername(connection, newUsername)) {
            return "Username already exists";
        }
        functionRepository.updateUsername(connection, userID, newUsername);
        return "Username updated successfully";
    }

    public String updatePassword(int userID, String oldPassword, String newPassword, String newPasswordConfirm) {
        if (!functionRepository.isPasswordCorrect(connection, userID, oldPassword)) {
            return "Wrong password.";
        }
        if(!newPassword.equals(newPasswordConfirm)) {
            return "Password confirmation does not match";
        }
        functionRepository.updatePassword(connection, userID, newPassword);
        return "Password updated successfully";
    }

    public int getProfileID(int userID) {
        return functionRepository.getProfileID(connection, userID);
    }

    public String getName(int userID) {
        return functionRepository.getName(connection, userID);
    }
}
