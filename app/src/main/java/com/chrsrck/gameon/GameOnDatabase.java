package com.chrsrck.gameon;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

/*
 *  The class that is responsible with communicating with the database
 */
public class GameOnDatabase {
    private static final String TAG = "GameOnDatabase";
    private DatabaseReference notificationFlag;
    private DatabaseReference equipmentDatabase;
    private DatabaseReference requestDatabase;
    private LinkedList<EquipmentRequest> requestList;
    private LinkedList<Equipment> checkedOutList;
    private LinkedList<Equipment> brokenReportList;
    private LinkedList<String> requestIDList;

    public Context mContext;

    /*
     *  Initialize the database and the lists of data we need to store locally for reports
     */
    public GameOnDatabase() {
        equipmentDatabase = FirebaseDatabase.getInstance().getReference().child("Inventory");
        requestDatabase = FirebaseDatabase.getInstance().getReference().child("Requests");
        notificationFlag = FirebaseDatabase.getInstance().getReference().child("NotificationFlag");
        notificationFlag.setValue(0);
        requestList = new LinkedList<EquipmentRequest>();
        checkedOutList = new LinkedList<Equipment>();
        brokenReportList = new LinkedList<Equipment>();
        requestIDList = new LinkedList<String>();
        setupRequestListener();
        setupEquipmentListner();
        setupNotifcation();
    }

    public LinkedList<EquipmentRequest> getAllRequests() {
        return requestList;
    }

    public LinkedList<Equipment> getAllCheckedOutEquipment() {return checkedOutList;}

    public LinkedList<Equipment> getAllBrokenReports() {return brokenReportList;}

    public LinkedList<String> getAllRequestsID() {return requestIDList;}

    /*
     *  This method listens for a new request. It automatically updates the lists of data needed to
     *  generate the request reports
     */
    public void setupRequestListener() {
        requestDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                requestList.clear();
                requestIDList.clear();
                for (DataSnapshot requestSnapshot : dataSnapshot.getChildren()) {
                    if (requestSnapshot.getValue() != null) {
                        String requester = (String )requestSnapshot.child("requester").getValue();
                        String item = (String )requestSnapshot.child("item").getValue();
                        long quantity = (long) requestSnapshot.child("quantity").getValue();
                        String location = (String)requestSnapshot.child("location").getValue();
                        String time = (String )requestSnapshot.child("time").getValue();
                        EquipmentRequest request = new EquipmentRequest(requester, item, quantity, location, time);
                        requestList.add(request);
                        requestIDList.add(requestSnapshot.getKey());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     *  This method listens for any change made to the data to generate checked out information and
     *  damage report information. Would need to be optimized in the future
     */
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
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /*
     *  Push a new request onto the database given all data from the request activity screen
     */
    public void addToRequestDatabase(String requester, String item, long quantity, String location, String time) {
        EquipmentRequest equipmentRequest = new EquipmentRequest(requester, item, quantity, location, time);
        DatabaseReference requestRef = requestDatabase.push();
        requestRef.setValue(equipmentRequest);
    }

    /*
     *  Removes a request from the database given its ID
     */
    public void removeFromRequestDatabase(String idNum) {
        requestDatabase.child(idNum).removeValue();
    }

    /*
     *  This method checks out equipment from the database by updating the owner and location fields
     *  of that specific piece of information
     */
    public int borrowEquipment(String ownerName, String location, String idKey) {
        if (checkItemID(idKey)) {
            DatabaseReference item = equipmentDatabase.child(idKey).child("Item");
            item.child("Owner").setValue(ownerName);
            item.child("Location").setValue(location);
            return 0;
        }
        else {
            return -1;
        }
    }

    /*
     *  This method checks that the ID is a valid ID in the database. Currently there are 1128
     *  items in the database and may need to change in the future. The unique item IDs
     *  are SI-1 to SI-1128. Any derivation from those IDs are considered invalid
     */
    public boolean checkItemID(String idKey) {
        if (idKey.contains("-")) {
            int last = idKey.lastIndexOf("-");
            if (last == idKey.length() - 1) {
                return false;
            }
            String parts[] = idKey.split("-");
            String s1 = parts[0];
            if (parts.length != 2) {
                return false;
            }
            String s2 = parts[1];
            if (!s1.equals("SI")) {
                return false;
            }
            if (s2 != null) {
                try {
                    if (s2.length() > 18) {
                        return false;
                    }
                    long num = Long.parseLong(s2);
                    if (num < 1 || num > 1128) {
                        return false;
                    }
                    else {
                        return true;
                    }
                }
                catch (NumberFormatException e) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    /*
     *  Adds a damage report to a specific piece of equipment
     */
    public int addDamageReport(String reporter, String idKey, String description) {
        if (checkItemID(idKey)) {
            DatabaseReference item = equipmentDatabase.child(idKey).child("Broken Report");
            item.child("Description").setValue(description);
            item.child("Reporter").setValue(reporter);

            equipmentDatabase.child(idKey).child("Broken Report ID").setValue(0);
            return 0;
        }
        else {
            return -1;
        }
    }

    /*
     *  Checks in a piece of equipment by resetting the location and owner of the equipment
     */
    public int returnEquipment(String idKey) {
        if (checkItemID(idKey)) {
            DatabaseReference item = equipmentDatabase.child(idKey).child("Item");
            item.child("Owner").setValue("");
            item.child("Location").setValue("");
            return 0;
        }
        else {
            return -1;
        }
    }

    /*
     *  Sets up the notification system that will be called when a request has been added to the database
     *  so supervisors can immediately fulfil the request.
     */
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
                                    .setContentText("You've received a new equipment request!")
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

    /*
     *  Sends the notification to the database to alert all of a new pending request. Also gives
     *  confirmation for a scorekeeper that there request was received and added to the database.
     */
    public void triggerNotification() {
        Log.d(TAG, "trigger notifcation called");
        notificationFlag.push();
        notificationFlag.setValue(1);
    }

}
