package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.depauw.repairshop.database.Client;
import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.databinding.ActivityEditClientBinding;

public class EditClientActivity extends AppCompatActivity {

    private ActivityEditClientBinding binding;

    private int clientId;

    private View.OnClickListener button_update_client_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            String name = String.valueOf(binding.edittextFullName2.getText());
            int number = Integer.valueOf(String.valueOf(binding.edittextContactNumber2.getText()));
            String dln = String.valueOf(binding.edittextDln2.getText());
            String address = String.valueOf(binding.edittextStreetAddress2.getText());
            String city = String.valueOf(binding.edittextCity2.getText());
            String state = String.valueOf(binding.edittextState2.getText());
            int zip = Integer.valueOf(String.valueOf(binding.edittextZipCode2.getText()));

            Client updatedClient = new Client(clientId,dln,name,number,address,city,state,zip);
            //Passenger updatedPassenger = new Passenger(id, first_name, last_name, passport, birthdate, city, state);
            DBHelper helper = DBHelper.getInstance(EditClientActivity.this);
            int numRowsAffected = helper.updateClient(updatedClient);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();

        clientId = intent.getIntExtra(ClientListActivity.EXTRA_ID, 0);

        binding.edittextFullName2.setText(intent.getStringExtra(ClientListActivity.EXTRA_FULL_NAME));
        binding.edittextContactNumber2.setText(String.valueOf(intent.getIntExtra(ClientListActivity.EXTRA_CONTACT_NUMBER, 0)));
        binding.edittextDln2.setText(intent.getStringExtra(ClientListActivity.EXTRA_DLN));
        binding.edittextStreetAddress2.setText(intent.getStringExtra(ClientListActivity.EXTRA_ADDRESS));
        binding.edittextCity2.setText(intent.getStringExtra(ClientListActivity.EXTRA_CITY));
        binding.edittextState2.setText(intent.getStringExtra(ClientListActivity.EXTRA_STATE));
        binding.edittextZipCode2.setText(String.valueOf(intent.getIntExtra(ClientListActivity.EXTRA_ZIP, 0)));

        binding.buttonUpdateClient.setOnClickListener(button_update_client_listener);
    }
}