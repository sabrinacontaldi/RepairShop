package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.database.Repair;
import com.depauw.repairshop.database.Vehicle;
import com.depauw.repairshop.databinding.ActivityAddRepairBinding;

import java.util.Calendar;
import java.util.List;


public class AddRepairActivity extends AppCompatActivity {

    private ActivityAddRepairBinding binding;

    private View.OnClickListener edittext_repair_date_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar myCalendar = Calendar.getInstance();
            DatePickerDialog myPicker = new DatePickerDialog(AddRepairActivity.this, datepicker_repairdate_dateSetListener, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH));
            myPicker.show();

        }
    };

    private DatePickerDialog.OnDateSetListener datepicker_repairdate_dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int dateOfMonth) {
            month++;
            binding.edittextRepairDate.setText(year + "-" + month + "-" + dateOfMonth);
        }
    };

    private View.OnClickListener button_add_repair_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DBHelper helper = DBHelper.getInstance(AddRepairActivity.this);

            Vehicle vehicleObject = (Vehicle)binding.spinnerVehicles.getSelectedItem();

            String date = binding.edittextRepairDate.getText().toString();
            double cost = Double.valueOf(binding.edittextRepairCost.getText().toString());
            String description = binding.edittextRepairDescription.getText().toString();
            int vehicle_vid = vehicleObject.getId();

            //Log.d("CSC396", String.valueOf(vehicle_vid) + ":" + vehicleObject.getMakeAndModel());
            Repair newRepair = new Repair(vehicle_vid, date, cost, description);
            //Log.d("CSC396", newRepair.toString());
            long result = helper.insertRepair(newRepair);

//            if (result >= 0) {
//                Toast.makeText(AddRepairActivity.this, "Success", Toast.LENGTH_SHORT).show();
//            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRepairBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = DBHelper.getInstance(this);

        List<Vehicle> vehicles = helper.getAllVehicles();
        ArrayAdapter<Vehicle> adapter = new ArrayAdapter<Vehicle>(this, android.R.layout.simple_spinner_item, vehicles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerVehicles.setAdapter(adapter);

        binding.buttonAddRepair.setOnClickListener(button_add_repair_clickListener);
        binding.edittextRepairDate.setOnClickListener(edittext_repair_date_clickListener);
    }



}