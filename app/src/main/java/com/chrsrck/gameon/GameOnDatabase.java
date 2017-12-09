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

    public GameOnDatabase() {
        messageDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        notificationFlag = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
        equipmentDatabase = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
    }

    public void addToMessageDatabase(String contentStr) {
        final DatabaseReference newPost = messageDatabase.push();

//             add value event listeners
        newPost.child("content").setValue(contentStr);

    }

    public void addToEquipmentDatabase(Equipment equipment) {
        final DatabaseReference equipmentPost = messageDatabase.push();


    }
}
