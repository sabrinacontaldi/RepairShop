package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.depauw.repairshop.database.Client;
import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.databinding.ActivityAddClientBinding;

public class AddClientActivity extends AppCompatActivity {

    private ActivityAddClientBinding binding;

    private View.OnClickListener button_add_client_clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            String name = String.valueOf(binding.edittextFullName.getText());
            int number = Integer.valueOf(String.valueOf(binding.edittextContactNumber.getText()));
            String dln = String.valueOf(binding.edittextDln.getText());
            String address = String.valueOf(binding.edittextStreetAddress.getText());
            String city = String.valueOf(binding.edittextCity.getText());
            String state = String.valueOf(binding.edittextCity.getText());
            int zipCode = Integer.valueOf(String.valueOf(binding.edittextZipCode.getText()));

            Client c = new Client(dln,name,number,address,city,state,zipCode);
            Log.d("CSC396", c.toString());
            //            Log.d("CSC396", newVehicle.toString() + newVehicle.getId());


            DBHelper helper = DBHelper.getInstance(AddClientActivity.this);
            long result = helper.insertClient(c);
            finish();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddClientBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAddClient.setOnClickListener(button_add_client_clickListener);
    }
}