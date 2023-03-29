package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;


import com.depauw.repairshop.database.Client;
import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.database.Vehicle;
import com.depauw.repairshop.databinding.ActivityAddVehicleBinding;

import java.util.List;

public class AddVehicleActivity extends AppCompatActivity
{
    private ActivityAddVehicleBinding binding;

    private View.OnClickListener button_add_vehicle_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int year = Integer.valueOf(binding.edittextYear.getText().toString());
            String makeAndModel = binding.edittextMakeModel.getText().toString();
            String color = binding.edittextColor.getText().toString();

            //change to get client from spinner
            Client c = (Client)binding.spinnerClients.getSelectedItem();
            int client = c.getId();

            //Vehicle newVehicle = new Vehicle(year, makeAndModel, price, isNew);
            Vehicle newVehicle = new Vehicle(client, year, makeAndModel, color);
            DBHelper helper = DBHelper.getInstance(AddVehicleActivity.this);
            long result = helper.insertVehicle(newVehicle);
//            Log.d("CSC396", newVehicle.toString());
//            Log.d("CSC396", newVehicle.toString() + newVehicle.getId());
//            if (result>=0) {
//                Toast.makeText(AddVehicleActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddVehicleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = DBHelper.getInstance(this);

        List<Client> clients = helper.getAllClients();
        ArrayAdapter<Client> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clients);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerClients.setAdapter(adapter);

//        List<> vehicles = helper.getAllVehicles();
//        ArrayAdapter<Vehicle> adapter = new ArrayAdapter<Vehicle>(this, android.R.layout.simple_spinner_item, vehicles);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinnerVehicles.setAdapter(adapter);

        binding.buttonAddVehicle.setOnClickListener(button_add_vehicle_clickListener);
    }

}