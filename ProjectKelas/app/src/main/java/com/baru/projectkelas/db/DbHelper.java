package com.baru.projectkelas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "Tiket_Booking";
    public static int DB_VERSION = 1;

    public static String SQL_CREATE_USERS_TABLE =
            "CREATE TABLE IF NOT EXISTS users(" +
                    "memberId integer primary key autoincrement," +
                    "fName text," +
                    "lName text," +
                    "email text," +
                    "password text," +
                    "phoneNumber text," +
                    "bDay text," +
                    "gender text" +
                    ")";

    public static String SQL_CREATE_EVENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS events(" +
                    "eventId integer primary key autoincrement," +
                    "judul text," +
                    "date text," +
                    "time text," +
                    "deskripsi text," +
                    "category text," +
                    "gambar integer" +
                    ")";

    public static String SQL_CREATE_PRODUCTS_TABLE =
            "CREATE TABLE IF NOT EXISTS products(" +
                    "productId integer primary key autoincrement," +
                    "eventId integer references events(eventId)," +
                    "typeId integer references types(typeId)," +
                    "price integer" +
                    ")";

    public static String SQL_CREATE_TYPES_TABLE =
            "CREATE TABLE IF NOT EXISTS types(" +
                    "typeId integer primary key autoincrement," +
                    "type text," +
                    "deskripsiType text" +
                    ")";

    public static String SQL_CREATE_ORDERS_TABLE =
            "CREATE TABLE IF NOT EXISTS orders(" +
                    "orderId integer primary key autoincrement," +
                    "memberId integer references users(memberId)," +
                    "productId integer references products(productId)," +
                    "qty integer," +
                    "price integer" +
                    ")";

    public static String SQL_CREATE_CONFIRMATIONS_TABLE =
            "CREATE TABLE IF NOT EXISTS confirmations(" +
                    "confirmationId integer primary key autoincrement," +
                    "orderId integer references orders(orderId)," +
                    "image BLOG," +
                    "rekeningName text" +
                    ")";


    public static String SQL_DROP_USERS_TABLE ="DROP TABLE IF EXISTS users";
    public static String SQL_DROP_EVENTS_TABLE ="DROP TABLE IF EXISTS events";
    public static String SQL_DROP_ORDERS_TABLE ="DROP TABLE IF EXISTS orders";
    public static String SQL_DROP_TYPES_TABLE ="DROP TABLE IF EXISTS categorys";
    public static String SQL_DROP_PRODUCTS_TABLE ="DROP TABLE IF EXISTS products";
    public static String SQL_DROP_CONFIRMATIONS_TABLE ="DROP TABLE IF EXISTS confirmations";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    void refreshTable(SQLiteDatabase db){

        db.execSQL(SQL_DROP_USERS_TABLE);
        db.execSQL(SQL_DROP_EVENTS_TABLE);
        db.execSQL(SQL_DROP_ORDERS_TABLE);
        db.execSQL(SQL_DROP_TYPES_TABLE);
        db.execSQL(SQL_DROP_PRODUCTS_TABLE);
        db.execSQL(SQL_DROP_CONFIRMATIONS_TABLE);

        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_EVENTS_TABLE);
        db.execSQL(SQL_CREATE_ORDERS_TABLE);
        db.execSQL(SQL_CREATE_TYPES_TABLE);
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
        db.execSQL(SQL_CREATE_CONFIRMATIONS_TABLE);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        refreshTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshTable(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshTable(db);
    }
}
