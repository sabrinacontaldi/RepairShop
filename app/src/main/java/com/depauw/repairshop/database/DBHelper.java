package com.depauw.repairshop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "repair_shop.db";
    public static final int DB_VERSION = 1;
    private static DBHelper myInstance;

    //Vehicle table
    public static final String TABLE_VEHICLE = "Vehicle";
    public static final String COL_VEHICLE_VID = "vid";
    public static final String COL_VEHICLE_CLIENT_ID = "id";
    public static final String COL_VEHICLE_YEAR = "year";
    public static final String COL_VEHICLE_MAKEANDMODEL = "make_model";
    public static final String COL_VEHICLE_COLOR = "color";

    //Repair table
    public static final String TABLE_REPAIR = "Repair";
    public static final String COL_REPAIR_RID = "rid";
    public static final String COL_REPAIR_VEHICLE_VID = "vehicle_vid";
//    public static final String COL_REPAIR_VEHICLE = "vehicle";
    public static final String COL_REPAIR_DATE = "date";
    public static final String COL_REPAIR_COST = "cost";
    public static final String COL_REPAIR_DESCRIPTION = "description";

    //Customer table
    public static final String TABLE_CLIENT = "Client";
    public static final String COL_CLIENT_ID = "id";
    public static final String COL_CLIENT_DLN = "dln";
    public static final String COL_CLIENT_FULL_NAME = "full_name";
    public static final String COL_CLIENT_CONTACT_NUMBER = "contact_number";
    public static final String COL_CLIENT_STREET_ADDRESS = "street_address";
    public static final String COL_CLIENT_CITY = "city";
    public static final String COL_CLIENT_STATE = "state";
    public static final String COL_CLIENT_ZIP_CODE = "zip_code";


    public static DBHelper getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new DBHelper(context);
        }
        return myInstance;
    }


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_VEHICLE +" (" +
                COL_VEHICLE_VID + " INTEGER," +
                COL_VEHICLE_CLIENT_ID + " INTEGER NOT NULL," +
                COL_VEHICLE_YEAR + " INTEGER NOT NULL," +
                COL_VEHICLE_MAKEANDMODEL + " TEXT NOT NULL," +
                COL_VEHICLE_COLOR + " REAL NOT NULL," +

                "PRIMARY KEY(" + COL_VEHICLE_VID + " AUTOINCREMENT)," +
                "FOREIGN KEY(" + COL_VEHICLE_CLIENT_ID + ") REFERENCES " + TABLE_CLIENT + "(" + COL_CLIENT_ID + ")" +
                " ON DELETE CASCADE" +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_REPAIR +" (" +
                COL_REPAIR_RID + " INTEGER," +
                COL_REPAIR_VEHICLE_VID + " INTEGER NOT NULL," +
                //COL_REPAIR_VEHICLE + " TEXT NOT NULL," +
                COL_REPAIR_DATE + " INTEGER NOT NULL," +
                COL_REPAIR_COST + " REAL NOT NULL," +
                COL_REPAIR_DESCRIPTION + " TEXT NOT NULL," +
                "PRIMARY KEY( " + COL_REPAIR_RID + " AUTOINCREMENT)," +
                "FOREIGN KEY(" + COL_REPAIR_VEHICLE_VID + ") REFERENCES " + TABLE_VEHICLE + "(" + COL_VEHICLE_VID + ")" +
                " ON DELETE CASCADE" +
                ")";
        db.execSQL(sql);

        sql = "CREATE TABLE " + TABLE_CLIENT +" (" +
                COL_CLIENT_ID + " INTEGER," +
                COL_CLIENT_DLN + " VARCHAR(255) UNIQUE NOT NULL," +
                COL_CLIENT_FULL_NAME + " VARCHAR(255) NOT NULL," +
                COL_CLIENT_CONTACT_NUMBER + " INTEGER NOT NULL," +
                COL_CLIENT_STREET_ADDRESS + " VARCHAR(255) NOT NULL," +
                COL_CLIENT_CITY + " VARCHAR(255) NOT NULL," +
                COL_CLIENT_STATE + " VARCHAR(255) NOT NULL," +
                COL_CLIENT_ZIP_CODE + " INTEGER NOT NULL," +
                "PRIMARY KEY( " + COL_CLIENT_ID + " AUTOINCREMENT)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //add client
    public long insertClient (Client client) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_CLIENT_DLN, client.getDln());
        cv.put(COL_CLIENT_FULL_NAME, client.getFull_name());
        cv.put(COL_CLIENT_CONTACT_NUMBER, client.getContact_number());
        cv.put(COL_CLIENT_STREET_ADDRESS, client.getStreet_address());
        cv.put(COL_CLIENT_CITY, client.getCity());
        cv.put(COL_CLIENT_STATE, client.getState());
        cv.put(COL_CLIENT_ZIP_CODE, client.getZip_code());

        long result = db.insert(TABLE_CLIENT, null, cv);
        db.close();
        return result;
    }

    //add vehicle
    public long insertVehicle (Vehicle vehicle) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_VEHICLE_YEAR, vehicle.getYear());
        cv.put(COL_VEHICLE_MAKEANDMODEL, vehicle.getMake_Model());
        cv.put(COL_VEHICLE_CLIENT_ID, vehicle.getClient_id());
        cv.put(COL_VEHICLE_COLOR, vehicle.getColor());

        long result = db.insert(TABLE_VEHICLE, null, cv);
        db.close();

        return result;
    }

    //add repair
    public long insertRepair (Repair repair) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_REPAIR_VEHICLE_VID, repair.getVehicle_vid());
        //cv.put(COL_REPAIR_VEHICLE, repair.getVehicle());
        cv.put(COL_REPAIR_DATE, repair.getDate());
        cv.put(COL_REPAIR_COST, repair.getCost());
        cv.put(COL_REPAIR_DESCRIPTION, repair.getDescription());

        long result = db.insert(TABLE_REPAIR, null, cv);
        db.close();
        return result;
    }

    //return list of Vehicles for the spinner
    public List<Vehicle> getAllVehicles () {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_VEHICLE;
        Cursor myCursor = db.rawQuery(sql, null);

        int idx_id = myCursor.getColumnIndex(COL_VEHICLE_VID);
        int idx_clientId = myCursor.getColumnIndex(COL_VEHICLE_CLIENT_ID);
        int idx_year = myCursor.getColumnIndex(COL_VEHICLE_YEAR);
        int idx_makeModel = myCursor.getColumnIndex(COL_VEHICLE_MAKEANDMODEL);
        int idx_color = myCursor.getColumnIndex(COL_VEHICLE_COLOR);

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        if (myCursor.moveToFirst()) {
            do {
                int id = myCursor.getInt(idx_id);
                int client = myCursor.getInt(idx_clientId);
                int year = myCursor.getInt(idx_year);
                String makeAndModel = myCursor.getString(idx_makeModel);
                String color = myCursor.getString(idx_color);

                Vehicle v = new Vehicle(id, client, year, makeAndModel, color);
                vehicles.add(v);
            } while (myCursor.moveToNext());
        }

        db.close();

        return vehicles;
    }

    //return list of Clients for spinner
    public List<Client> getAllClients () {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_CLIENT;
        Cursor myCursor = db.rawQuery(sql, null);

        int idx_id = myCursor.getColumnIndex(COL_CLIENT_ID);
        int idx_dln = myCursor.getColumnIndex(COL_CLIENT_DLN);
        int idx_full_name = myCursor.getColumnIndex(COL_CLIENT_FULL_NAME);
        int idx_contact_number = myCursor.getColumnIndex(COL_CLIENT_CONTACT_NUMBER);
        int idx_street_address = myCursor.getColumnIndex(COL_CLIENT_STREET_ADDRESS);
        int idx_city = myCursor.getColumnIndex(COL_CLIENT_CITY);
        int idx_state = myCursor.getColumnIndex(COL_CLIENT_STATE);
        int idx_zip_code = myCursor.getColumnIndex(COL_CLIENT_ZIP_CODE);
        List<Client> clients = new ArrayList<Client>();

        if (myCursor.moveToFirst()) {
            do {
                int id = myCursor.getInt(idx_id);
                String dln = myCursor.getString(idx_dln);
                String fullName = myCursor.getString(idx_full_name);
                int number = myCursor.getInt(idx_contact_number);
                String address = myCursor.getString(idx_street_address);
                String city = myCursor.getString(idx_city);
                String state = myCursor.getString(idx_state);
                int zipCode = myCursor.getInt(idx_zip_code);

                Client c = new Client(id,dln,fullName,number,address,city,state,zipCode);
                clients.add(c);
            } while (myCursor.moveToNext());
        }

        db.close();

        return clients;
    }

    //return joined list of repairs and vehicles
    public List<RepairWithVehicle> getRepairsWithVehicle (String keyword) {

        SQLiteDatabase db = getReadableDatabase();

        String sql = "";

        if(keyword.length() > 0)
        {
            sql = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s LIKE '%%%s%%'",
                    TABLE_VEHICLE, TABLE_REPAIR, TABLE_VEHICLE, COL_VEHICLE_VID, TABLE_REPAIR,COL_REPAIR_VEHICLE_VID, COL_REPAIR_DESCRIPTION, keyword);
        }
        else
        {
            sql = String.format("SELECT * FROM %s INNER JOIN %s ON %s.%s = %s.%s",
                    TABLE_VEHICLE, TABLE_REPAIR, TABLE_VEHICLE, COL_VEHICLE_VID, TABLE_REPAIR, COL_REPAIR_VEHICLE_VID);
        }

        Cursor myCursor = db.rawQuery(sql, null);

        int idx_rid = myCursor.getColumnIndex(COL_REPAIR_RID);
        int idx_vehicle_vid = myCursor.getColumnIndex(COL_REPAIR_VEHICLE_VID);
        int idx_date = myCursor.getColumnIndex(COL_REPAIR_DATE);
        int idx_repair_cost = myCursor.getColumnIndex(COL_REPAIR_COST);
        int idx_description = myCursor.getColumnIndex(COL_REPAIR_DESCRIPTION);

        int idx_vid = myCursor.getColumnIndex(COL_VEHICLE_VID);
        int idx_client = myCursor.getColumnIndex(COL_VEHICLE_CLIENT_ID);
        int idx_year = myCursor.getColumnIndex(COL_VEHICLE_YEAR);
        int idx_makeModel = myCursor.getColumnIndex(COL_VEHICLE_MAKEANDMODEL);
        int idx_color = myCursor.getColumnIndex(COL_VEHICLE_COLOR);

        List<RepairWithVehicle> repairsAndVehiclesList = new ArrayList<RepairWithVehicle>();

        if(myCursor.moveToFirst())
        {
            do{
                int rid = myCursor.getInt(idx_rid);
                int vehicle_vid = myCursor.getInt(idx_vehicle_vid);
                String date = myCursor.getString(idx_date);
                double cost = myCursor.getDouble(idx_repair_cost);
                String description = myCursor.getString(idx_description);

                Repair repair1 = new Repair(rid, vehicle_vid, date, cost, description);

                int client = myCursor.getInt(idx_client);
                int vid = myCursor.getInt(idx_vid);
                int year = myCursor.getInt(idx_year);
                String makeModel = myCursor.getString(idx_makeModel);
                String color = myCursor.getString(idx_color);

                Vehicle vehicle1 = new Vehicle(vid, client, year, makeModel, color);

                RepairWithVehicle repairVehicle = new RepairWithVehicle(repair1, vehicle1);

                repairsAndVehiclesList.add(repairVehicle);
                //Log.d("CSC969", repairVehicle.toString());

            }while(myCursor.moveToNext());
        }

        db.close();
        return repairsAndVehiclesList;
    }


    //delete repair
    public int deleteRepairById(int repairId)
    {
        SQLiteDatabase db = getWritableDatabase();

        String where = String.format("%s LIKE '%s'", COL_REPAIR_RID, repairId);
        int numRows = db.delete(TABLE_REPAIR, where, null);

        db.close();
        return numRows;
    }

    //delete client
    public int deleteClientById(int clientId)
    {
        SQLiteDatabase db = getWritableDatabase();

        String where = String.format("%s LIKE '%s'", COL_CLIENT_ID, clientId);
        int numRows = db.delete(TABLE_CLIENT, where, null);

        db.close();
        return numRows;
    }


    //update client
    public int updateClient(Client updatedClient)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL_CLIENT_FULL_NAME, updatedClient.getFull_name());
        cv.put(COL_CLIENT_CONTACT_NUMBER, updatedClient.getContact_number());
        cv.put(COL_CLIENT_DLN, updatedClient.getDln());
        cv.put(COL_CLIENT_STREET_ADDRESS, updatedClient.getStreet_address());
        cv.put(COL_CLIENT_CITY, updatedClient.getCity());
        cv.put(COL_CLIENT_STATE, updatedClient.getState());
        cv.put(COL_CLIENT_ZIP_CODE, updatedClient.getZip_code());

        String where = String.format("%s = %s", COL_CLIENT_ID, updatedClient.getId());
        int numRows = db.update(TABLE_CLIENT, cv, where, null);
        db.close();
        return numRows;
    }


//    public int getVidBySpinner (Repair repair) {
//        SQLiteDatabase db = getReadableDatabase();
//        String spinnerResult = repair.getVehicle();
//
//        String[] array = spinnerResult.split(" ", 1);
//        String sql = String.format("SELECT %s FROM %s WHERE %s = %s AND %s = %s",
//                COL_VEHICLE_VID, TABLE_VEHICLE, COL_VEHICLE_YEAR, array[0], COL_VEHICLE_MAKEANDMODEL, array[1]);
//        Cursor myCursor = db.rawQuery(sql, null);
//
//        int idx_vid = myCursor.getColumnIndex(COL_VEHICLE_VID);
//
//        //repair.get
//        int result = -1;
//        if (myCursor.moveToFirst()) {
//                result = myCursor.getInt(idx_vid);
//        }
////        else {
////            return -1;
////        }
//        db.close();
//        return result;
//    }

}
