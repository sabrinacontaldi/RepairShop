package com.depauw.repairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.depauw.repairshop.database.RepairWithVehicle;

import java.util.List;

public class CustomAdapterRepair extends BaseAdapter {

    private Context context;
    private List<RepairWithVehicle> repairWithVehicleList;

    public CustomAdapterRepair(Context context, List<RepairWithVehicle> repairWithVehicleList)
    {
        this.context = context;
        this.repairWithVehicleList = repairWithVehicleList;
    }
    @Override
    public int getCount() {
        return repairWithVehicleList.size();
    }

    @Override
    public Object getItem(int position) {
        return repairWithVehicleList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_results_row, parent, false);
        }
        RepairWithVehicle thisRecord = repairWithVehicleList.get(position);

        TextView yearMake = convertView.findViewById(R.id.text_year_make_model);
        TextView repairDate = convertView.findViewById(R.id.text_repair_date);
        TextView repairCost = convertView.findViewById(R.id.text_repair_cost);
        TextView description = convertView.findViewById(R.id.text_repair_description);

        yearMake.setText(thisRecord.getVehicle().toString());
        repairDate.setText(thisRecord.getRepair().getDate());
        repairCost.setText(String.valueOf(thisRecord.getRepair().getCost()));
        description.setText(thisRecord.getRepair().getDescription());

        return convertView;
    }
}
