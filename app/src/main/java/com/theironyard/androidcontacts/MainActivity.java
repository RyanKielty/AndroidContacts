package com.theironyard.androidcontacts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    ArrayAdapter<String> contactsList;

    ListView contacts;
    EditText contactName;
    EditText contactPhoneNumber;
    Button addContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = (ListView) findViewById(R.id.listView);
        contactName = (EditText) findViewById(R.id.contactName);
        contactPhoneNumber = (EditText) findViewById(R.id.contactPhoneNumber);
        addContact = (Button) findViewById(R.id.addContact);

        contactsList = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1);
        contacts.setAdapter(contactsList);

        addContact.setOnClickListener(this);
        contacts.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View action) {
        String addName = contactName.getText().toString();
        String addNumber = contactPhoneNumber.getText().toString();

        contactsList.add(addName + " (" + addNumber + ")");

        contactName.setText("");
        contactPhoneNumber.setText("");
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String individualContact = contactsList.getItem(position);
        contactsList.remove(individualContact);
        return true;
    }
}
