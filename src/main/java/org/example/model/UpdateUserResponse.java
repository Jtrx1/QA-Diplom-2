package org.example.model;

public class UpdateUserResponse {

        public boolean success;
        public User user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String message;

    public UpdateUserResponse() {
    }

    public UpdateUserResponse(boolean success, User user, String message) {
        this.success = success;
        this.user = user;
        this.message =message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
