package org.example.model;

public class CreateOrderResponse {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrderResponse getOrder() {
        return order;
    }

    public void setOrder(OrderResponse order) {
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

    private OrderResponse order;
    private boolean success;
    private String message;

    public CreateOrderResponse(String name, OrderResponse order, boolean success, String message) {
        this.name = name;
        this.order = order;
        this.success = success;
        this.message=message;
    }
}
