package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailEventActivity extends AppCompatActivity {
    int memberId, eventId, gambar;
    String judul, date, time, deskripsi, type, category;

    @BindView(R.id.img_detail_event)
    ImageView imgDetailEvent;
    @BindView(R.id.txt_judul_detail)
    TextView txtJudulDetail;
    @BindView(R.id.txt_date_detail)
    TextView txtDateDetail;
    @BindView(R.id.txt_time_detail)
    TextView txtTimeDetail;
    @BindView(R.id.txt_deskripsi_event_detail)
    TextView txtDeskripsiEventDetail;
    @BindView(R.id.btn_VIP_detail)
    Button btnVIPDetail;
    @BindView(R.id.btn_REGULER_detail)
    Button btnREGULERDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        ButterKnife.bind(this);

        init();

        imgDetailEvent.setImageResource(gambar);
        txtJudulDetail.setText(judul);
        txtDateDetail.setText(date);
        txtDeskripsiEventDetail.setText(deskripsi);
        txtTimeDetail.setText(time);
    }

    void init (){
        memberId = getIntent().getIntExtra("memberId", 0);
        eventId = getIntent().getIntExtra("eventId", 0);
        gambar = getIntent().getIntExtra("gambar", 0);

        judul = getIntent().getStringExtra("judul");
        date = getIntent().getStringExtra("date");
        time = getIntent().getStringExtra("time");
        deskripsi = getIntent().getStringExtra("deskripsi");
        category = getIntent().getStringExtra("category");


    }

    @OnClick({R.id.btn_VIP_detail, R.id.btn_REGULER_detail})
    public void onViewClicked(View view) {
        Intent intent = new Intent(DetailEventActivity.this, TypeEventActivity.class);
        intent.putExtra("memberId", memberId);
        intent.putExtra("eventId", eventId);
        intent.putExtra("date", date);
        intent.putExtra("time", time);


        switch (view.getId()) {
            case R.id.btn_VIP_detail:
                intent.putExtra("type", btnVIPDetail.getText().toString());
                break;
            case R.id.btn_REGULER_detail:
                intent.putExtra("type", btnREGULERDetail.getText().toString());
                break;
        }

        startActivity(intent);
    }
}
