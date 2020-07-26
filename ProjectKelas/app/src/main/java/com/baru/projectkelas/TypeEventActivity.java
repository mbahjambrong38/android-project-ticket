package com.baru.projectkelas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.db.DbHelper;
import com.baru.projectkelas.db.db;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TypeEventActivity extends AppCompatActivity {

    int memberId, eventId, typeId, productId;
    String type, date, time, deskripsiType;
    int price;

    @BindView(R.id.txt_type_event)
    TextView txtTypeEvent;
    @BindView(R.id.txt_date_type)
    TextView txtDateType;
    @BindView(R.id.txt_time_type)
    TextView txtTimeType;
    @BindView(R.id.txt_type_event1)
    TextView txtTypeEvent1;
    @BindView(R.id.txt_deskripsi_type)
    TextView txtDeskripsiType;
    @BindView(R.id.btn_pay)
    Button btnPay;
    @BindView(R.id.list_tiket)
    Spinner listTiket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_event2);
        ButterKnife.bind(this);

        init();

        initProduct();

        Log.d("memberId,eventId,typeId", String.format("%d, %d, %d", memberId, eventId, typeId));
    }

    void init() {
        memberId = getIntent().getIntExtra("memberId", 0);
        eventId = getIntent().getIntExtra("eventId", 0);
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        type = getIntent().getStringExtra("type");

        String SQL_SELECT_TYPES = String.format("SELECT * FROM types");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SQL_SELECT_TYPES, null);
        while (c1.moveToNext()) {
            if (type.equalsIgnoreCase(c1.getString(1))) {
                typeId = c1.getInt(0);
                deskripsiType = c1.getString(2);
            }

        }
        c1.close();


        txtTypeEvent.setText(type);
        txtTypeEvent1.setText(type);
        txtDateType.setText(date);
        txtTimeType.setText(time);
        txtDeskripsiType.setText(deskripsiType);

        Vector<Integer> qty = new Vector<>();
        for (int i = 1; i <= 20; i++){
            qty.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, qty);
        listTiket.setAdapter(adapter);
    }

    void initProduct(){
        String SQL_SELECT_PRODUCTS = String.format("SELECT * FROM products");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SQL_SELECT_PRODUCTS, null);
        while (c1.moveToNext()) {
            if (eventId == c1.getInt(1) && typeId == c1.getInt(2)) {
                productId = c1.getInt(0);
                price = c1.getInt(3);
            }

        }
        c1.close();
    }

    @OnClick(R.id.btn_pay)
    public void onViewClicked() {
        int qty =  Integer.parseInt(listTiket.getSelectedItem().toString());
        int totalPrice = price * qty;
        int orderId = 0;

        db.insertOrderToDatabaseAndVector(this, memberId, productId, qty, totalPrice);

        Log.d("ORDER ", String.format("ID : %d, memberId : %d, eventId : %d, qty : %d, totalPrice : %d", productId, memberId, eventId, qty, totalPrice));

        String SQL_SELECT_ORDERS = String.format("SELECT * FROM orders");

        DbHelper helper = new DbHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor c1 = db.rawQuery(SQL_SELECT_ORDERS, null);
        while (c1.moveToNext()) {
            orderId = c1.getInt(0);
        }
        c1.close();

        Intent intent = new Intent(TypeEventActivity.this, HomeActivity.class);
        intent.putExtra("memberId", memberId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}


