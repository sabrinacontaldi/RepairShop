package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;

import com.depauw.repairshop.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;

    private View.OnClickListener button_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch(v.getId())
            {
                case R.id.button_add_repair:
                {
                    Intent theIntent = new Intent (MainActivity.this, AddRepairActivity.class);
                    startActivity(theIntent);
                    break;
                }
                case R.id.button_add_vehicle:
                {
                    Intent theIntent = new Intent(MainActivity.this, AddVehicleActivity.class);
                    startActivity(theIntent);
                    break;
                }
                case R.id.button_search_repairs:
                {
                    Intent theIntent = new Intent(MainActivity.this, SearchRepairsActivity.class);
                    startActivity(theIntent);
                    break;
                }
                case R.id.button_add_customer:
                {
                    Intent theIntent = new Intent(MainActivity.this, AddClientActivity.class);
                    startActivity(theIntent);
                    break;
                }
                case R.id.button_client_list:
                {
                    Intent theIntent = new Intent(MainActivity.this, ClientListActivity.class);
                    startActivity(theIntent);
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonAddVehicle.setOnClickListener(button_clickListener);
        binding.buttonAddRepair.setOnClickListener(button_clickListener);
        binding.buttonSearchRepairs.setOnClickListener(button_clickListener);
        binding.buttonAddCustomer.setOnClickListener(button_clickListener);
        binding.buttonClientList.setOnClickListener(button_clickListener);
    }

}