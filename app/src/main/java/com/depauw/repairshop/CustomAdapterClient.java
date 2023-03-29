package com.depauw.repairshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.depauw.repairshop.database.Client;
import com.depauw.repairshop.database.RepairWithVehicle;

import java.util.List;

public class CustomAdapterClient extends BaseAdapter {

    private Context context;
    private List<Client> clients;

    public CustomAdapterClient(Context context, List<Client> clients) {
        this.context = context;
        this.clients = clients;
    }

    @Override
    public int getCount() {
        return clients.size();
    }

    @Override
    public Object getItem(int position) {
        return clients.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_clients_row, parent, false);
        }

        Client thisRecord = clients.get(position);

        TextView id = convertView.findViewById(R.id.text_client_id);
        TextView name = convertView.findViewById(R.id.text_client_name);
        TextView dln = convertView.findViewById(R.id.text_client_dln);

        //id.setText(thisRecord.getId());
        id.setText(String.valueOf(thisRecord.getId()));
        name.setText(thisRecord.getFull_name());
        dln.setText(String.valueOf(thisRecord.getDln()));

        return convertView;


////        RepairWithVehicle thisRecord = repairWithVehicleList.get(position);
////
////        TextView yearMake = convertView.findViewById(R.id.text_year_make_model);
////        TextView repairDate = convertView.findViewById(R.id.text_repair_date);
////        TextView repairCost = convertView.findViewById(R.id.text_repair_cost);
////        TextView description = convertView.findViewById(R.id.text_repair_description);
////
////        yearMake.setText(thisRecord.getVehicle().toString());
////        repairDate.setText(thisRecord.getRepair().getDate());
////        repairCost.setText(String.valueOf(thisRecord.getRepair().getCost()));
////        description.setText(thisRecord.getRepair().getDescription());
//
//        return convertView;
        //return null;
    }
}

