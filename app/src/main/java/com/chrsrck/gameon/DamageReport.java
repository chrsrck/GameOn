package com.chrsrck.gameon;

/**
 * Created by chrsrck on 12/9/17.
 */

public class DamageReport {

    private String reporter;
    private int idNum; // for equipment
    private String description;

    public DamageReport() {

    }

    public DamageReport(String reporter, int idNum, String description) {
        this.reporter = reporter;
        this.idNum = idNum;
        this.description = description;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public int getIdNum() {
        return idNum;
    }

    public void setIdNum(int idNum) {
        this.idNum = idNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
