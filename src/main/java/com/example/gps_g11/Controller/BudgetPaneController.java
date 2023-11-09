package com.example.gps_g11.Controller;

import com.example.gps_g11.Data.Context;

public class BudgetPaneController {
    SideBarController sideBarController;
    private Context context;

    public void initialize(){
        context = Context.getInstance();
    }
    public void setSideBar(SideBarController sideBarController) {
        this.sideBarController = sideBarController;
    }



}
