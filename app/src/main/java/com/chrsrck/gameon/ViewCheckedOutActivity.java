package com.chrsrck.gameon;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;

/*
 *  This activity is responsible for populating the checked-out report.
 */
public class ViewCheckedOutActivity extends AppCompatActivity {
    TextView[] strings;
    TableLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_checked);
        MainActivity.gameOnDatabase.mContext = this;

        //Gets the data from the database
        layout = findViewById(R.id.tablelayout);
        LinkedList<Equipment> checked = MainActivity.gameOnDatabase.getAllCheckedOutEquipment();
        int length = checked.size();
        strings = new TextView[length*4];

        //Calculates the padding for the views
        int paddingPixel = 10;
        float density = this.getResources().getDisplayMetrics().density;
        int paddingDp = (int)(paddingPixel * density);

        int paddingPixel2 = 2;
        float density2 = this.getResources().getDisplayMetrics().density;
        int paddingDp2 = (int)(paddingPixel2 * density2);

        for (int i = 0; i < length; i++) {
            //Dynamically build the table rows to display on the screen
            //The top left cell displays the item name
            TableRow row = new TableRow(this);
            strings[i*4] = new TextView(this);
            strings[i*4].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4].setText(checked.get(i).getItemName());
            strings[i*4].setTextColor(Color.WHITE);
            strings[i*4].setTextSize(18);
            strings[i*4].setGravity(Gravity.CENTER);
            strings[i*4].setPadding(0, paddingDp, 0, 0);

            //The top right cell displays the current handler of the equipment
            strings[i*4 + 1] = new TextView(this);
            strings[i*4 + 1].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 1].setText(checked.get(i).getOwner());
            strings[i*4 + 1].setTextColor(Color.WHITE);
            strings[i*4 + 1].setTextSize(18);
            strings[i*4 + 1].setGravity(Gravity.CENTER);
            strings[i*4 + 1].setPadding(0, paddingDp, 0, 0);

            row.addView(strings[i*4]);
            row.addView(strings[i*4 + 1]);

            //The bottom left cell displays the Item ID number
            TableRow row2 = new TableRow(this);
            strings[i*4 + 2] = new TextView(this);
            strings[i*4 + 2].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 2].setText("Item ID: " + checked.get(i).getIdNum());
            strings[i*4 + 2].setTextColor(Color.WHITE);
            strings[i*4 + 2].setTextSize(18);
            strings[i*4 + 2].setGravity(Gravity.CENTER);
            strings[i*4 + 2].setPadding(0, paddingDp, 0, paddingDp);

            //The bottom right cell displays the current location of the equipment
            strings[i*4 + 3] = new TextView(this);
            strings[i*4 + 3].setLayoutParams(new TableRow.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
            strings[i*4 + 3].setText(checked.get(i).getLocation());
            strings[i*4 + 3].setTextColor(Color.WHITE);
            strings[i*4 + 3].setTextSize(18);
            strings[i*4 + 3].setGravity(Gravity.CENTER);
            strings[i*4 + 3].setPadding(0, paddingDp, 0, paddingDp);

            row2.addView(strings[i*4 + 2]);
            row2.addView(strings[i*4 + 3]);

            layout.addView(row,i*3);
            layout.addView(row2, i*3 + 1);

            //Add a separator between two pieces of equipment
            View view = new View(this);
            view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, paddingDp2));
            view.setBackgroundColor(Color.WHITE);
            layout.addView(view, i*3 + 2);
        }
    }
}
