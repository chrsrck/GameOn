package com.chrsrck.gameon;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

import java.util.LinkedList;

/*
 *  This activity is responsible for populating the list of pending requests.
 */
public class ViewRequests extends AppCompatActivity implements View.OnClickListener{
    TextView text;
    TextView[] strings;
    TableRow[] rows;
    String[] requestsID;
    Button[] buttons;
    View[] vs;
    TableLayout layout;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_requests);
        MainActivity.gameOnDatabase.mContext = this;

        text = findViewById(R.id.totalRequest);
        layout = findViewById(R.id.tablelayout);

        //Grab the list of requests and their ids from the database
        LinkedList<EquipmentRequest> requests = MainActivity.gameOnDatabase.getAllRequests();
        LinkedList<String> ids = MainActivity.gameOnDatabase.getAllRequestsID();
        int length = requests.size();
        count = length;

        //Set the amount of pending requests at the top of the view
        text.setText("There are " + length + " pending requests.");
        buttons = new Button[length];
        requestsID = new String[length];
        vs = new View[length];
        strings = new TextView[length*4];
        rows = new TableRow[length*2];

        //Calculate the padding for the views
        int paddingPixel = 10;
        float density = this.getResources().getDisplayMetrics().density;
        int paddingDp = (int)(paddingPixel * density);

        int paddingPixel2 = 2;
        float density2 = this.getResources().getDisplayMetrics().density;
        int paddingDp2 = (int)(paddingPixel2 * density2);

        //Set the weight of the table rows based on whether the user is a supervisor or not
        float weight;
        if (MainActivity.isSupervisor) {
            weight = 2f;
        }
        else {
            weight = 1f;
        }

        for (int i = 0; i < length; i++) {
            //Dynamically build the table rows for the views
            //The top left cell displays the item name
            TableRow row = new TableRow(this);
            strings[i*4] = new TextView(this);
            strings[i*4].setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight));
            strings[i*4].setText(requests.get(i).getItem());
            strings[i*4].setTextColor(Color.WHITE);
            strings[i*4].setTextSize(18);
            strings[i*4].setGravity(Gravity.CENTER);
            strings[i*4].setPadding(0, paddingDp, 0, 0);

            //The top right cell displays the requester
            strings[i*4 + 1] = new TextView(this);
            strings[i*4 + 1].setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight));
            strings[i*4 + 1].setText(requests.get(i).getRequester());
            strings[i*4 + 1].setTextColor(Color.WHITE);
            strings[i*4 + 1].setTextSize(18);
            strings[i*4 + 1].setGravity(Gravity.CENTER);
            strings[i*4 + 1].setPadding(0, paddingDp, 0, 0);

            row.addView(strings[i*4]);
            row.addView(strings[i*4 + 1]);

            //The bottom left cell displays the quantity requested
            TableRow row2 = new TableRow(this);
            strings[i*4 + 2] = new TextView(this);
            strings[i*4 + 2].setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight));
            strings[i*4 + 2].setText("Quantity: " + requests.get(i).getQuantity());
            strings[i*4 + 2].setTextColor(Color.WHITE);
            strings[i*4 + 2].setTextSize(18);
            strings[i*4 + 2].setGravity(Gravity.CENTER);
            strings[i*4 + 2].setPadding(0, paddingDp, 0, paddingDp);

            //The bottom right cell displays the location of the requester
            strings[i*4 + 3] = new TextView(this);
            strings[i*4 + 3].setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, weight));
            strings[i*4 + 3].setText(requests.get(i).getLocation());
            strings[i*4 + 3].setTextColor(Color.WHITE);
            strings[i*4 + 3].setTextSize(18);
            strings[i*4 + 3].setGravity(Gravity.CENTER);
            strings[i*4 + 3].setPadding(0, paddingDp, 0, paddingDp);

            row2.addView(strings[i*4 + 2]);
            row2.addView(strings[i*4 + 3]);

            //If the user is a supervisor we can add a button for them to delete the request when they are fulfilled
            if (MainActivity.isSupervisor) {
                buttons[i] = new Button(this);
                buttons[i].setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
                buttons[i].setText("\u2713");
                buttons[i].setTextColor(Color.WHITE);
                buttons[i].setTextSize(20);
                buttons[i].setBackgroundResource(R.drawable.button_border);
                buttons[i].setOnClickListener(this);
                buttons[i].setPadding(0, paddingDp, 0, paddingDp);
                buttons[i].setGravity(Gravity.CENTER);
                buttons[i].setId(i);
                requestsID[i] = ids.get(i);
                row.addView(buttons[i]);

                Button b2 = new Button(this);
                b2.setLayoutParams(new TableRow.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f));
                b2.setText("\u2713");
                b2.setTextColor(Color.WHITE);
                b2.setTextSize(20);
                b2.setBackgroundResource(R.drawable.button_border);
                b2.setGravity(Gravity.CENTER);
                row2.addView(b2);
                b2.setVisibility(View.INVISIBLE);
            }

            rows[i*2] = row;
            rows[i*2+1] = row2;
            layout.addView(row,i*3);
            layout.addView(row2, i*3 + 1);

            //Separator for the requests
            View view = new View(this);
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, paddingDp2));
            view.setBackgroundColor(Color.WHITE);
            vs[i] = view;
            layout.addView(view, i*3 + 2);
        }
    }

    /*
     *  Handles the delete button clicks by finding the id for the requests and removing them from the
     *  database and the view. Also updates the counter at the top with the total number of requests.
     */
    @Override
    public void onClick(View view) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].getId() == view.getId()) {
                rows[i*2].setVisibility(View.GONE);
                rows[i*2+1].setVisibility(View.GONE);
                vs[i].setVisibility(View.GONE);
                MainActivity.gameOnDatabase.removeFromRequestDatabase(requestsID[i]);
                count--;
                text.setText("There are " + count + " pending requests.");
            }
        }
    }
}
