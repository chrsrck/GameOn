package com.chrsrck.gameon;

/**
 * Created by chrsrck on 12/8/17.
 */

public class Equipment {

//    private String sport;
//    private String name;
    /*
    Stuff needed for Equipment Damage Report
     */
    private long idNum;
    private String itemName;
    private String location;
    private String owner;

    private String reporter;
    private String description;
    private long idNumReport;



    public Equipment() {
//        idNum = -1;
//        reporter = "None";
//        description = "None";
//        isBroken = false;
    }

    public Equipment(long idNum, String itemName, String location, String owner,
                     String reporter, String description, long idNumReport) {
        // for the item
        this.idNum = idNum;
        this.itemName = itemName;
        this.location = location;
        this.owner = owner;
        // for the damage report
        this.reporter = reporter;
        this.description = description;
        this.idNumReport = idNumReport;
    }

    public long getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setIdNum(long idNum) {
        this.idNum = idNum;
    }

    public long getIdNumReport() {
        return idNumReport;
    }

    public void setIdNumReport(long idNumReport) {
        this.idNumReport = idNumReport;
    }

    /*
            Used for when a piece of equipment is fixed
             */
    public void resetEquipment() {
        reporter = "";
        description = "";
        location = "";
    }
}
