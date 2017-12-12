package com.chrsrck.gameon;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by chrsrck on 12/8/17.
 */

public class GameOnDatabase {
    private static final String TAG = "GameOnDatabase";
    private DatabaseReference messageDatabase;
    private DatabaseReference notificationFlag;
    private DatabaseReference equipmentDatabase;
    private DatabaseReference requestDatabase;
    private ValueEventListener notificationListener;
    public Context mContext;


    public GameOnDatabase() {
//        messageDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        equipmentDatabase = FirebaseDatabase.getInstance().getReference().child("Inventory");
        requestDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        notificationFlag = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
        notificationFlag.setValue(0);
        setupNotifcation();
    }

    // used to test server functionality
//    public void addToMessageDatabase(String contentStr) {
//        final DatabaseReference newPost = messageDatabase.push();
//
////             add value event listeners
//        newPost.child("content").setValue(contentStr);
//
//    }

    public void addToRequestDatabase(String requester, String item, int quantity, String location, String specialRequest, String time) {
        EquipmentRequest equipmentRequest = new EquipmentRequest(requester, item, quantity, location, specialRequest, time);
//        String key = requestDatabase.push().getKey();
//        requestDatabase.child(key).setValue(equipmentRequest);
//        String idNumKey = Integer.toString(idNum + idAdder);
        DatabaseReference requestRef = requestDatabase.push();
        requestRef.setValue(equipmentRequest);

    }

    public void removeFromRequestDatabase(int idNum) {
        String requestKey = Integer.toString(idNum);
        requestDatabase.child(requestKey).removeValue();
    }

    public void borrowEquipment(String idKey, String ownerName, String location) {
//        StringBuilder sd = new StringBuilder("SD-");
//        sd.append(idKey);
        DatabaseReference item = requestDatabase.child(idKey).child("Item");
        item.child("Owner").setValue(ownerName);
        item.child("Location").setValue(location);
    }

    public void returnEquipment(String idKey) {
//        StringBuilder sd = new StringBuilder("SD-");
//        sd.append(idKey);
        DatabaseReference item = requestDatabase.child(idKey).child("Item");
        item.child("Owner").setValue("");
        item.child("Location").setValue("");
    }

//    public void addToEquipmentDatabase(int idNum, boolean isBroken, String reporter, String description) {
    public void addToEquipmentDatabase(int idNum, String itemName, String location, String owner, boolean isBroken, String reporter, String description) {
        DamageReport damageReport = new DamageReport();
        Equipment equipment = new Equipment(idNum, itemName, location, owner, isBroken, reporter, description);
//        String key = equipmentDatabase.push().getKey();
        DatabaseReference inventoryItem = equipmentDatabase.child(Integer.toString(idNum));
        inventoryItem.setValue(equipment);
    }

    public void setupNotifcation() {
        Log.d(TAG, "Setup notification called");
        notificationFlag.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "On data change called");

                if (dataSnapshot.exists() && (long) dataSnapshot.getValue() == 1) {
                    int notificationId = 1;
                    Intent viewIntent = new Intent(mContext, ViewRequests.class);
                    PendingIntent viewPendingIntent = PendingIntent.getActivity(mContext, 0, viewIntent, 0);
                    NotificationCompat.Builder notificationBuilder =
                            (NotificationCompat.Builder) new NotificationCompat.Builder(mContext)
                                    .setSmallIcon(R.mipmap.logo)
                                    .setContentTitle("Game On!")
                                    .setContentText("First notification")
                                    .setContentIntent(viewPendingIntent)
                                    .setVibrate(new long[]{1000, 1000});

                    NotificationManagerCompat notificationManagerCompat =
                            NotificationManagerCompat.from(mContext);

                    notificationManagerCompat.notify(notificationId, notificationBuilder.build());

                    notificationFlag.setValue(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void triggerNotification() {
        Log.d(TAG, "trigger notifcation called");
        notificationFlag.push();
        notificationFlag.setValue(1);
    }

}
