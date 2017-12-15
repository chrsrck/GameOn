package com.chrsrck.gameon;

/*
 *  Represents how an equipment requests report would look like in the google firebase
 */
public class EquipmentRequest {

    private String requester;
    private String item;
    private long quantity;
    private String location;
    private String time;

    public EquipmentRequest() {

    }

    public EquipmentRequest(String requester, String item, long quantity, String location, String time) {
        this.requester = requester;
        this.item = item;
        this.quantity = quantity;
        this.location = location;
        this.time = time;
    }

    public String getRequester() {
        return requester;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getLocation() {
        return location;
    }
}
