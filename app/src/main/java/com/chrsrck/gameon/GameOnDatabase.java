package com.chrsrck.gameon;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by chrsrck on 12/8/17.
 */

public class GameOnDatabase {

    private DatabaseReference messageDatabase;
    private DatabaseReference notificationFlag;
    private DatabaseReference equipmentDatabase;
    private DatabaseReference requestDatabase;

    public GameOnDatabase() {
        messageDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        notificationFlag = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
        equipmentDatabase = FirebaseDatabase.getInstance().getReference().child("Equipment");
        requestDatabase = FirebaseDatabase.getInstance().getReference().child("Equipment");
    }

    // used to test server functionality
    public void addToMessageDatabase(String contentStr) {
        final DatabaseReference newPost = messageDatabase.push();

//             add value event listeners
        newPost.child("content").setValue(contentStr);

    }

    public void addToEquipmentDatabase(String requester, String item, int quantity, String location, String specialRequest) {
        EquipmentRequest equipmentRequest = new EquipmentRequest(requester, item, quantity, location, specialRequest);
//        final DatabaseReference equipmentPost = messageDatabase.push();
//        equipmentDatabase.setValue(idNum);
        String key = equipmentDatabase.push().getKey();
        equipmentDatabase.child(key).setValue(equipmentRequest);
//        equipmentPost.child("idNum").setValue(equipment.getIdNum());
//        equipmentPost.child("reporter").setValue(equipment.getReporter());
//        equipmentPost.child("description").setValue(equipment.getDescription());
//        equipmentPost.child("isBroken").setValue(equipment.getIdNum());
    }
}
