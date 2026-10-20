package com.scc.model;

// Java Record (max 5 attributes — exactly as required)
public record ScenarioEntity(
        int id,
        String customerName,
        String drinkItem,
        double price,
        String status
) {}