package com.chrsrck.gameon;

/**
 * Created by chrsrck on 12/8/17.
 */

public class Equipment {

    private int idNum;
//    private String sport;
//    private String name;
    private String reporter;
    private String description;
    private boolean isBroken;


    public Equipment() {
        idNum = -1;
        reporter = "None";
        description = "None";
        isBroken = false;
    }

    public Equipment(int idNum, String reporter, String description, boolean isBroken) {
        this.idNum = idNum;
        this.reporter = reporter;
        this.description = description;
        this.isBroken = isBroken;
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

    /*
    Used for when a piece of equipment is fixed
     */
    public void resetEquipment() {
        reporter = "None";
        description = "None";
        isBroken = false;
    }
}
