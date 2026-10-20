package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.ScenarioMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize database & create table if not exists
        DatabaseConnection.initializeDatabase();

        // Start the menu
        new ScenarioMenu().show();
    }
}