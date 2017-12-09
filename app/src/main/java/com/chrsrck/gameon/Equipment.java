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
    private int idNum;
    private String itemName;
    private String location;
    private String owner;
    private boolean isBroken;
    private String reporter;
    private String description;
    private DamageReport damageReport;


    public Equipment() {
//        idNum = -1;
//        reporter = "None";
//        description = "None";
//        isBroken = false;
    }

    public Equipment(int idNum, String itemName, String location, String owner, boolean isBroken,
                     String reporter, String description) {
        this.idNum = idNum;
        this.itemName = itemName;
        this.location = location;
        this.owner = owner;
        this.reporter = reporter;
        this.description = description;
        this.isBroken = isBroken;
        this.damageReport = new DamageReport(reporter, idNum, description);
    }

    public int getIdNum() {
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

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
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

    public DamageReport getDamageReport() {
        return damageReport;
    }

    public void setDamageReport(DamageReport damageReport) {
        this.damageReport = damageReport;
    }

    /*
        Used for when a piece of equipment is fixed
         */
    public void resetEquipment() {
        reporter = "None";
        description = "None";
        isBroken = false;
    }
}
