package com.chrsrck.gameon;

/*
 *  Represents how equipment data is stored in the google firebase
 */
public class Equipment {
    /*
     * Stuff needed for Equipment Damage Report
     */
    private long idNum;
    private String itemName;
    private String location;
    private String owner;

    private String reporter;
    private String description;
    private long idNumReport;

    public Equipment() {

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

    public String getReporter() {
        return reporter;
    }

    public String getDescription() {
        return description;
    }

    public String getItemName() {
        return itemName;
    }

    public String getLocation() {
        return location;
    }

    public String getOwner() {
        return owner;
    }
}
