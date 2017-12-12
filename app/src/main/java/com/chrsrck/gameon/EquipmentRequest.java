package com.chrsrck.gameon;

/**
 * Created by chrsrck on 12/8/17.
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

    public void setRequester(String requester) {
        this.requester = requester;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



}
