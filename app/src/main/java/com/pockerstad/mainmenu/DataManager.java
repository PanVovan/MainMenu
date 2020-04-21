package com.pockerstad.mainmenu;


public class DataManager {
    public static DataManager instance;

    public static synchronized DataManager getInstance() {
        if(instance == null){
            instance = new DataManager();
        }
        return instance;
    }
}
