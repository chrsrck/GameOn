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

import java.util.LinkedList;

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
    private LinkedList<EquipmentRequest> requestList;
    private LinkedList<Equipment> checkedOutList;
    private LinkedList<Equipment> brokenReportList;
    private LinkedList<Equipment> mEquipmentsList;

    public Context mContext;


    public GameOnDatabase() {
//        messageDatabase = FirebaseDatabase.getInstance().getReference().child("Messages");
        equipmentDatabase = FirebaseDatabase.getInstance().getReference().child("Inventory");
        requestDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        notificationFlag = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
        notificationFlag.setValue(0);
        requestList = new LinkedList<EquipmentRequest>();
        checkedOutList = new LinkedList<Equipment>();
        brokenReportList = new LinkedList<Equipment>();
        mEquipmentsList = new LinkedList<Equipment>();
        setupRequestListener();
        setupEquipmentListner();
        setupNotifcation();
    }

    public LinkedList<EquipmentRequest> getAllRequests() {
        return requestList;
    }

    public LinkedList<Equipment> getAllCheckedOutEquipment() {return checkedOutList;}

    public LinkedList<Equipment> getAllBrokenReports() {return brokenReportList;}

    public LinkedList<Equipment> getAllEquipment() {return brokenReportList;}

    public void setupRequestListener() {
        requestDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestList.clear();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    if (requestSnapshot.getValue() != null) {
                        String requester = (String )requestSnapshot.child("requester").getValue();
                        String item = (String )requestSnapshot.child("item").getValue();
                        long quantity = (long) requestSnapshot.child("quantity").getValue();
                        String location = (String)requestSnapshot.child("location").getValue();
                        String time = (String )requestSnapshot.child("time").getValue();
                        EquipmentRequest request = new EquipmentRequest(requester, item, quantity, location, time);
                        requestList.add(request);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setupEquipmentListner() {
        equipmentDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                checkedOutList.clear();
                brokenReportList.clear();
                for (DataSnapshot equipSnapshot : dataSnapshot.getChildren()) {
                    if (equipSnapshot.getValue() != null) {
                        DataSnapshot itemSnapshot = equipSnapshot.child("Item");
                        DataSnapshot brokenReportSnapshot = equipSnapshot.child("Broken Report");
                        String location = "";
                        String owner = "";
                        String itemName = "";
                        long idNum = -1;
                        if (itemSnapshot.exists()) {
                            location = (String) itemSnapshot.child("Location").getValue();
                            owner = (String) itemSnapshot.child("Owner").getValue();
                            itemName = (String) itemSnapshot.child("Name").getValue();
                            idNum = (long) equipSnapshot.child("ID").getValue();
                        }
                        String description = "";
                        String reporter = "";
                        long idNumReport = -1;
                        if (brokenReportSnapshot.exists()) {
                            description = (String) brokenReportSnapshot.child("Description").getValue();
                            reporter = (String) brokenReportSnapshot.child("Reporter").getValue();
                            idNumReport = (long) equipSnapshot.child("Broken Report ID").getValue();
                        }
                        Equipment equipment = new Equipment(idNum, itemName, location, owner, reporter, description, idNumReport);
                        if (!location.equals("") && !owner.equals("")) {
                            checkedOutList.add(equipment);
                        }

                        if (brokenReportSnapshot.exists() && idNumReport != -1 && !reporter.equals("") && !description.equals("")) {
                            brokenReportList.add(equipment);
                        }
                        mEquipmentsList.add(equipment);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    // used to test server functionality
//    public void addToMessageDatabase(String contentStr) {
//        final DatabaseReference newPost = messageDatabase.push();
//
////             add value event listeners
//        newPost.child("content").setValue(contentStr);
//
//    }

    public void addToRequestDatabase(String requester, String item, long quantity, String location, String time) {
        EquipmentRequest equipmentRequest = new EquipmentRequest(requester, item, quantity, location, time);
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

    public int borrowEquipment(String ownerName, String location, String idKey) {

        DatabaseReference item = equipmentDatabase.child(idKey).child("Item");
        item.child("Owner").setValue(ownerName);
        item.child("Location").setValue(location);
        return 0;
    }

    public int addDamageReport(String reporter, String idKey, String description) {
        //long BRID = Long.parseLong(equipmentDatabase.child(idKey).child("Broken Report ID").getKey());


        //if(BRID != -1) {
        //    return -1;
        //}
        DatabaseReference item = equipmentDatabase.child(idKey).child("Broken Report");
        item.child("Description").setValue(description);
        item.child("Reporter").setValue(reporter);

        equipmentDatabase.child(idKey).child("Broken Report ID").setValue(0);
        return 0;
    }

    public int returnEquipment(String idKey) {
        DatabaseReference item = equipmentDatabase.child(idKey).child("Item");

        item.child("Owner").setValue("");
        item.child("Location").setValue("");
        return 0;
    }

    public void setupNotifcation() {
        Log.d(TAG, "Setup notification called");
        // reset chris
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
                                    .setContentText("You've received a new notification")
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
