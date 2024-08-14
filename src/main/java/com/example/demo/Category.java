package com.example.demo;

import java.util.Map;

public class Category {
    public String categoryId;
    public String name;

    public Map<String, Category> categories;


    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public String toString() {
        return getCategoryName();
    }
}
