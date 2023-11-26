package org.example.model;

public class OrderResponse {
    private String name;
    private Orders order;
    private boolean success;
    private String message;

    public OrderResponse(String name, Orders order, boolean success, String message) {
        this.name = name;
        this.order = order;
        this.success = success;
        this.message = message;
    }
    public OrderResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
