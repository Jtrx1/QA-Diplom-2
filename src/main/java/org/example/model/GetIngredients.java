package org.example.model;

import java.util.ArrayList;

public class GetIngredients {
    public boolean success;
    public ArrayList<IndredientsData> data;

    public GetIngredients() {
    }

    public GetIngredients(boolean success, ArrayList<IndredientsData> data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public ArrayList<IndredientsData> getData() {
        return data;
    }

    public void setData(ArrayList<IndredientsData> data) {
        this.data = data;
    }
}
