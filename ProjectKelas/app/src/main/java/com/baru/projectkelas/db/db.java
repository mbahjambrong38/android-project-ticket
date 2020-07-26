package com.baru.projectkelas.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.baru.projectkelas.R;

import java.util.Vector;

public class db {
    public static Vector<User> VEC_USER = new Vector<>();
    public static Vector<Event> VEC_EVENT = new Vector<>();
    public static Vector<Order> VEC_ORDER = new Vector<>();
    public static Vector<Type> VEC_TYPE = new Vector<>();
    public static Vector<Product> VEC_PRODUCT = new Vector<>();
    public static Vector<Confirmation> VEC_CONFIRMATION = new Vector<>();


    public static boolean HAS_SYNC_USER = false;
    public static boolean HAS_SYNC_EVENT = false;
    public static boolean HAS_SYNC_TYPE = false;
    public static boolean HAS_SYNC_PRODUCT = false;
    public static boolean HAS_SYNC_ORDER = false;
    public static boolean HAS_SYNC_CONFIRMATION = false;

    public static void initUser(Context ctx){

        if(checkDataUserInDatabase(ctx)){
            syncUser(ctx);
            return;
        }

        insertUserToDatabase(ctx, "admin@gmail.com", "Pika123", "tasd", "zxc", "+6282374834657", "Laki-Laki", "27/06/2000");
        syncUser(ctx);
    }

    public static void initEvent(Context ctx){

        if(checkDataEventInDatabase(ctx)){
            syncEvent(ctx);
            return;
        }


        insertEventToDatabase(ctx, "Ultra Music Festival", "27/10/2020", "16.00", "Biggest Miami Music Festival", "Musik", R.drawable.ultra);
        insertEventToDatabase(ctx, "Djakarta Warehouse Project", "12/12/2020", "18.00", "Jakarta Greatest Music Festival", "Musik", R.drawable.dwp);
        insertEventToDatabase(ctx, "GAIKINDO Auto Show", "22/07/2020", "09.00", "Pameran Otomotif Terbesar di Indonesia", "Workshop", R.drawable.gaikindo);
        insertEventToDatabase(ctx, "FYRE Music Festivals", "22/11/2020", "09.00", "Biggest Music Festival", "Musik", R.drawable.fyre);
        syncEvent(ctx);
    }

    public static void initType(Context ctx){

        if(checkDataTypeInDatabase(ctx)){
            syncType(ctx);
            return;
        }

        insertTypeToDatabase(ctx, "VIP", "INI ADALAH VIP");
        insertTypeToDatabase(ctx, "REGULER", "INI ADALAH REGULER");
        syncType(ctx);
    }

    public static void initProduct(Context ctx){

        if(checkDataProductInDatabase(ctx)){
            syncProduct(ctx);
            return;
        }

        insertProductToDatabase(ctx, 1, 1, 4000000);
        insertProductToDatabase(ctx, 1, 2, 100000);
        insertProductToDatabase(ctx, 2, 1, 8000000);
        insertProductToDatabase(ctx, 2, 2, 150000);
        insertProductToDatabase(ctx, 3, 1, 7000000);
        insertProductToDatabase(ctx, 3, 2, 200000);
        insertProductToDatabase(ctx, 4, 1, 900000);
        insertProductToDatabase(ctx, 4, 2, 100000);
        syncProduct(ctx);
    }

    public static void initOrder(Context ctx){

        if(checkDataOrderInDatabase(ctx)){
            syncOrder(ctx);
            return;
        }

    }

    public static void initConfirmation(Context ctx){

        if(checkDataConfirmationInDatabase(ctx)){
            syncConfirmation(ctx);
            return;
        }

    }

    public static boolean checkDataTypeInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_TYPES = String.format("SELECT * FROM types");
        Cursor c = dbSelect.rawQuery(SELECT_TYPES, null);

        while (c.moveToNext()) {
            Log.d("ADD DATABASE TYPE", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static boolean checkDataOrderInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_ORDERS = String.format("SELECT * FROM orders");
        Cursor c = dbSelect.rawQuery(SELECT_ORDERS, null);

        while (c.moveToNext()) {
            Log.d("ADD DATABASE ORDER", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static boolean checkDataUserInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_USERS = String.format("SELECT * FROM users");
        Cursor c = dbSelect.rawQuery(SELECT_USERS, null);

        while (c.moveToNext()) {
            Log.d("ADD DATABASE USER", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static boolean checkDataEventInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_EVENTS = String.format("SELECT * FROM events");
        Cursor c = dbSelect.rawQuery(SELECT_EVENTS, null);

        while (c.moveToNext()) {
            Log.d("ADD DATABASE EVENT", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static boolean checkDataProductInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_EVENTS = String.format("SELECT * FROM products");
        Cursor c = dbSelect.rawQuery(SELECT_EVENTS, null);

        while (c.moveToNext()) {
            Log.d("ADD DATABASE PRODUCT", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static boolean checkDataConfirmationInDatabase(Context ctx){
        DbHelper helper = new DbHelper(ctx);

        SQLiteDatabase dbSelect = helper.getReadableDatabase();
        String SELECT_CONFIRMATIONS = String.format("SELECT confirmationId FROM confirmations");
        Cursor c = dbSelect.rawQuery(SELECT_CONFIRMATIONS, null);

        while (c.moveToNext()) {
            Log.d("ADD DB CONFIRMATION", String.format("DISINI GAGAL INIT DATABASE"));
            return true;                                                                           //validasi database kalo ada isinya sama belom ada isinya
        }
        return false;
    }

    public static void insertTypeToDatabase(Context ctx, String type, String deskripsiType){
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO types(type, deskripsiType) VALUES('%s','%s')", type, deskripsiType);

        db.execSQL(query);
        db.close();
    }

    public static void insertOrderToDatabase(Context ctx, int memberId, int productId, int qty, int price){
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO orders(memberId,productId,qty,price) VALUES('%d','%d','%d','%d')", memberId, productId, qty, price);

        db.execSQL(query);
        db.close();
    }

    public static void insertConfirmationToDatabase(Context ctx, byte[] image, String name, int orderId){
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO confirmations(orderId, image, rekeningName) VALUES(?, ?, ?)");

        SQLiteStatement statement = db.compileStatement(query);
        statement.clearBindings();

        statement.bindDouble(1, orderId);
        statement.bindBlob(2, image);
        statement.bindString(3, name);

        statement.executeInsert();
    }

    public static void insertUserToDatabase(Context ctx, String email, String password, String fName, String lName, String phone, String gender, String bDay){

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO users(fName, lName, email, password, phoneNumber, bDay, gender) VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')", fName, lName, email, password, phone, bDay, gender);

        db.execSQL(query);
        db.close();

    }

    public static void insertEventToDatabase(Context ctx, String judul, String date, String time, String deskripsi, String category, int gambar){
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO events(judul,date,time,deskripsi,category,gambar) VALUES('%s','%s','%s','%s','%s','%d')", judul, date, time, deskripsi, category, gambar);

        db.execSQL(query);
        db.close();
    }

    public static void insertProductToDatabase(Context ctx, int eventId, int typeId, int price){
        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO products(eventId,typeId,price) VALUES('%d','%d','%d')", eventId, typeId, price);

        db.execSQL(query);
        db.close();
    }

    public static void insertUserToDatabaseAndVector(Context ctx, String email, String password, String fName, String lName, String phone, String gender, String bDay){
        insertUserToDatabase(ctx, email, password, fName, lName, phone, gender, bDay);

        int memberId = 0;

        String SELECT_MEMBERID_USER = "SELECT memberId FROM users";

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SELECT_MEMBERID_USER, null);

        while (c1.moveToNext()) {
            memberId = c1.getInt(0);
        }
        c1.close();

        User user = new User();
        user.memberId = memberId;
        user.email = email;
        user.bDay = bDay;
        user.fName = fName;
        user.gender = gender;
        user.lName = lName;
        user.phoneNumber = phone;
        user.password = password;

        VEC_USER.add(user);
    }

    public static void insertOrderToDatabaseAndVector(Context ctx, int memberId, int productId, int qty, int price){
        insertOrderToDatabase(ctx, memberId, productId, qty, price);

        int orderId = 0;

        String SELECT_ORDERID_USER = "SELECT orderId FROM orders";

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SELECT_ORDERID_USER, null);

        while (c1.moveToNext()) {
            orderId = c1.getInt(0);
        }
        c1.close();

        Order o = new Order();
        o.orderId = orderId;
        o.memberId = memberId;
        o.productId = productId;
        o.qty = qty;
        o.price = price;

        VEC_ORDER.add(o);
    }

    public static void insertConfirmationToDatabaseAndVector(Context ctx, byte[] image, String name, int orderId){
        insertConfirmationToDatabase(ctx, image, name, orderId);

        int confirmationId = 0;

        String SELECT_CONFIRMATION = "SELECT confirmationId FROM confirmations";

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SELECT_CONFIRMATION, null);

        while (c1.moveToNext()) {
            confirmationId = c1.getInt(0);
        }
        c1.close();

        Confirmation c = new Confirmation();
        c.confirmationId = confirmationId;
        c.orderId = orderId;

        VEC_CONFIRMATION.add(c);
    }

    public static void syncEvent(Context ctx){
        if(HAS_SYNC_EVENT){
            Log.d("SYNC EVENT", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_EVENT = true;

        String SQL_SELECT_EVENTS = String.format("SELECT * FROM events");

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_EVENTS, null);
        while (c1.moveToNext()){
            Event e = new Event();
            e.eventId = c1.getInt(0);
            e.judul = c1.getString(1);
            e.date = c1.getString(2);
            e.time = c1.getString(3);
            e.deskripsi = c1.getString(4);
            e.category = c1.getString(5);
            e.gambar = c1.getInt(6);

            Log.d("SYNC EVENT", String.format("%s, %s", c1.getString(1), c1.getString(2)));

            VEC_EVENT.add(e);
        }
        c1.close();

        Log.d("SYNC EVENT", String.format("SYNC EVENTS BERHASIL"));
    }

    public static void syncUser(Context ctx){
        if(HAS_SYNC_USER){
            Log.d("SYNC USERS", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_USER = true;

        String SQL_SELECT_USERS = String.format("SELECT * FROM users");


        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_USERS, null);
        while (c1.moveToNext()){
            User u = new User();
            u.memberId = c1.getInt(0);
            u.fName = c1.getString(1);
            u.lName = c1.getString(2);
            u.email = c1.getString(3);
            u.password = c1.getString(4);
            u.phoneNumber = c1.getString(5);
            u.bDay = c1.getString(6);
            u.gender = c1.getString(7);

            Log.d("SYNC USERS", String.format("%s, %s", c1.getString(1), c1.getString(2)));

            VEC_USER.add(u);
        }
        c1.close();

        Log.d("SYNC", String.format("SYNC EVENT BERHASIL"));
    }

    public static void syncType(Context ctx){
        if(HAS_SYNC_TYPE){
            Log.d("SYNC TYPE", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_TYPE = true;

        String SQL_SELECT_USERS = String.format("SELECT * FROM types");

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_USERS, null);
        while (c1.moveToNext()){
            Type t = new Type();
            t.typeId = c1.getInt(0);
            t.type = c1.getString(1);
            t.deskripsi = c1.getString(2);

            Log.d("SYNC TYPE", String.format("%s, %s", c1.getString(1), c1.getString(2)));

            VEC_TYPE.add(t);
        }
        c1.close();

        Log.d("SYNC", String.format("SYNC EVENT BERHASIL"));
    }

    public static void syncProduct(Context ctx){
        if(HAS_SYNC_PRODUCT){
            Log.d("SYNC PRODUCT", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_PRODUCT = true;

        String SQL_SELECT_USERS = String.format("SELECT * FROM products");

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_USERS, null);
        while (c1.moveToNext()){
            Product p = new Product();
            p.productId = c1.getInt(0);
            p.eventId = c1.getInt(1);
            p.typeId = c1.getInt(2);
            p.price = c1.getInt(3);

            Log.d("SYNC PRODUCT", String.format("%s, %s", c1.getString(1), c1.getString(2)));

            VEC_PRODUCT.add(p);
        }
        c1.close();

        Log.d("SYNC", String.format("SYNC PRODUCT BERHASIL"));
    }

    public static void syncOrder(Context ctx){
        if(HAS_SYNC_ORDER){
            Log.d("SYNC ORDER", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_ORDER = true;

        String SQL_SELECT_ORDERS = String.format("SELECT * FROM orders");

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_ORDERS, null);
        while (c1.moveToNext()){
            Order o = new Order();
            o.orderId = c1.getInt(0);
            o.memberId = c1.getInt(1);
            o.productId = c1.getInt(2);
            o.qty = c1.getInt(3);
            o.price = c1.getInt(4);

            Log.d("SYNC ORDER", String.format("%s, %s", c1.getString(0), c1.getString(1)));

            VEC_ORDER.add(o);
        }
        c1.close();

        Log.d("SYNC", String.format("SYNC ORDER BERHASIL"));
    }

    public static void syncConfirmation(Context ctx){
        if(HAS_SYNC_CONFIRMATION){
            Log.d("SYNC CONFIRMATION", String.format("SYNC GAGAL DISINI"));
            return;
        }
        HAS_SYNC_CONFIRMATION = true;

        String SQL_SELECT_CONFIRMATIONS = "SELECT confirmationId,orderId FROM confirmations";

        DbHelper helper = new DbHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 =db.rawQuery(SQL_SELECT_CONFIRMATIONS, null);
        while (c1.moveToNext()){
            Confirmation c = new Confirmation();
            c.confirmationId = c1.getInt(0);
            c.orderId = c1.getInt(1);

            Log.d("SYNC ORDER", String.format("%s, %s", c1.getString(0), c1.getString(1)));

            VEC_CONFIRMATION.add(c);
        }
        c1.close();

        Log.d("SYNC", String.format("SYNC CONFIRMATION BERHASIL"));
    }
}
