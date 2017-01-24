package com.theironyard.androidcontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactPage extends AppCompatActivity {

    TextView name;
    TextView number;

    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        name = (TextView) findViewById(R.id.textViewName);
        number = (TextView) findViewById(R.id.textViewNumber);

        String contactInfo = getIntent().getExtras().getString("contactname", "");
        name.setText(getIntent().getExtras().getString("contactname", ""));
        number.setText(getIntent().getExtras().getString("contactnumber", ""));
        position = getIntent().getExtras().getInt("position", 0);
    }

}
