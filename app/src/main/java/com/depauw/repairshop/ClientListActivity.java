package com.depauw.repairshop;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.depauw.repairshop.database.Client;
import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.database.RepairWithVehicle;
import com.depauw.repairshop.databinding.ActivityAddClientBinding;
import com.depauw.repairshop.databinding.ActivityClientListBinding;

import java.util.List;

public class ClientListActivity extends AppCompatActivity {

    private ActivityClientListBinding binding;
    private List<Client> clientList;

    public static final String EXTRA_ID = "com.depauw.repairshop.EXTRA_ID";
    public static final String EXTRA_FULL_NAME = "com.depauw.repairshop.EXTRA_FULL_NAME";
    public static final String EXTRA_CONTACT_NUMBER = "com.depauw.repairshop.EXTRA_CONTACT_NUMBER";
    public static final String EXTRA_DLN = "com.depauw.repairshop.EXTRA_DLN";
    public static final String EXTRA_ADDRESS = "com.depauw.repairshop.EXTRA_ADDRESS";
    public static final String EXTRA_CITY = "com.depauw.repairshop.EXTRA_CITY";
    public static final String EXTRA_STATE = "com.depauw.repairshop.EXTRA_STATE";
    public static final String EXTRA_ZIP = "com.depauw.repairshop.EXTRA_ZIP";


    private AdapterView.OnItemClickListener client_item_clickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(ClientListActivity.this);
            myBuilder.setTitle("Edit Client")
                    .setMessage("Would you like to delete this client or update their information?")
                    .setPositiveButton("Delete", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Client client = clientList.get(position);
                            int idToDelete = Integer.valueOf(client.getId());

                            DBHelper helper = DBHelper.getInstance(ClientListActivity.this);
                            int numRowsAffected = helper.deleteClientById(idToDelete);

                            clientList.remove(position);
                            binding.listviewClients.invalidateViews();
                        }
                    })
                    .setNegativeButton("Update", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            //Code to update
                            Intent theIntent = new Intent(ClientListActivity.this, EditClientActivity.class);

                            Client client = clientList.get(position);
                            int id = client.getId();
                            String name = client.getFull_name();
                            int number = client.getContact_number();
                            String dln = client.getDln();
                            String address = client.getStreet_address();
                            String city = client.getCity();
                            String state = client.getState();
                            int zip = client.getZip_code();

                            theIntent.putExtra(EXTRA_ID, id);
                            theIntent.putExtra(EXTRA_FULL_NAME, name);
                            theIntent.putExtra(EXTRA_CONTACT_NUMBER, number);
                            theIntent.putExtra(EXTRA_DLN, dln);
                            theIntent.putExtra(EXTRA_ADDRESS, address);
                            theIntent.putExtra(EXTRA_CITY, city);
                            theIntent.putExtra(EXTRA_STATE, state);
                            theIntent.putExtra(EXTRA_ZIP, zip);

                            startActivity(theIntent);
                        }
                    })
                    .setNeutralButton("Cancel", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            //finish();
                        }
                    });

            AlertDialog myDialog = myBuilder.create();
            myDialog.show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding.listviewClients.setOnItemLongClickListener(client_item_click_listener);
        binding.listviewClients.setOnItemClickListener(client_item_clickListener);

//        DBHelper helper = DBHelper.getInstance(this);
//
//        clientList = helper.getAllClients();
//
//
//        CustomAdapterClient myAdapter = new CustomAdapterClient(this, clientList);
//        binding.listviewClients.setAdapter(myAdapter);
        //binding.listViewClients.setAdapter(myAdapter);

    }

    @Override
    protected void onStart() {

        super.onStart();
        DBHelper helper = DBHelper.getInstance(this);

        clientList = helper.getAllClients();


        CustomAdapterClient myAdapter = new CustomAdapterClient(this, clientList);
        binding.listviewClients.setAdapter(myAdapter);

    }
}