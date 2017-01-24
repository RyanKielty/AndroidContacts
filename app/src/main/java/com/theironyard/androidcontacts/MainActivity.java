package com.theironyard.androidcontacts;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    ArrayAdapter<String> contactsList;

    ListView contacts;
    EditText contactName;
    EditText contactPhoneNumber;
    Button addContact;

    static final int CONTACT_PAGE = 1;

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

        contacts.setOnItemClickListener(this);
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
        final String individualContact = contactsList.getItem(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("To Do");
        builder.setMessage("Are you sure you want to remove this contact?");
        builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                contactsList.remove(individualContact);
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing here
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        return true;
    }

    @Override
    public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent(this, ContactPage.class);
        intent.putExtra("contactname", contactsList.getItem(position));
        intent.putExtra("position", position);

        startActivityForResult(intent, CONTACT_PAGE);
    }
}
