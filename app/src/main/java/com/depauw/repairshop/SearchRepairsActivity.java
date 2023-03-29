package com.depauw.repairshop;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.depauw.repairshop.database.DBHelper;
import com.depauw.repairshop.database.RepairWithVehicle;
import com.depauw.repairshop.databinding.ActivitySearchRepairsBinding;

import java.util.List;

public class SearchRepairsActivity extends AppCompatActivity {

    private ActivitySearchRepairsBinding binding;

    private List<RepairWithVehicle> repairsAndVehiclesList;

    private View.OnClickListener button_find_repairs_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            DBHelper helper = DBHelper.getInstance(SearchRepairsActivity.this);

            String keyword = null;
            if(binding.edittextSearchPhrase.getText().toString() != null)
            {
                keyword = binding.edittextSearchPhrase.getText().toString();
            }

            repairsAndVehiclesList = helper.getRepairsWithVehicle(keyword);

            CustomAdapterRepair myAdapter = new CustomAdapterRepair(SearchRepairsActivity.this, repairsAndVehiclesList);
            binding.listviewResults.setAdapter(myAdapter);
        }
    };

    private AdapterView.OnItemLongClickListener repair_item_click_listener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {

            //RepairWithVehicle selectedRepairWithVehicle = (RepairWithVehicle)binding.listviewResults.getSelectedItem();
            RepairWithVehicle selectedRepairWithVehicle = repairsAndVehiclesList.get(position);

            int idToDelete = Integer.valueOf(selectedRepairWithVehicle.getRepair().getRid());

            DBHelper helper = DBHelper.getInstance(SearchRepairsActivity.this);
            int numRowsAffected = helper.deleteRepairById(idToDelete);

            repairsAndVehiclesList.remove(position);
            binding.listviewResults.invalidateViews();

//            if(numRowsAffected>0){
//                Toast.makeText(SearchRepairsActivity.this, "Success, numRows: " + numRowsAffected, Toast.LENGTH_LONG).show();
//            }

            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchRepairsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonFindRepairs.setOnClickListener(button_find_repairs_clickListener);
        binding.listviewResults.setOnItemLongClickListener(repair_item_click_listener);
    }
}