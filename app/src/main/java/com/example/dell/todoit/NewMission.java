package com.example.dell.todoit;

public class NewMission {
    String Date;
    String Name;
    String Time;
    String About;
    int importancelevel;
    public NewMission(int number, String Date, String Name, String Time, String About, int importancelevel){
        this.About=About;
        this.Date=  Date;
        this.Time=Time;
        this.Name=Name;
        this.importancelevel=importancelevel;

    }

    public String getTime() {
        return Time;
    }

    public String getName() {
        return Name;
    }

    public String getAbout() {
        return About;
    }

    public String getDate() {
        return Date;
    }

    public int getImportancelevel() {
        return importancelevel;
    }
}
