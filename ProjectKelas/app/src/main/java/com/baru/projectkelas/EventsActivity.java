package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.adapter.EventsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsActivity extends AppCompatActivity {

    int memberId;
    String category;

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.txt_event_pilihan)
    TextView txtEventPilihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        memberId = getIntent().getIntExtra("memberId", 0);

        category = getIntent().getStringExtra("category");

        txtEventPilihan.setText("Event " + category);

        EventsAdapter adapter = new EventsAdapter(getApplicationContext(), category);

        listView.setAdapter(adapter);

        clickHotel();

    }

    void clickHotel(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(EventsActivity.this, DetailEventActivity.class);
                intent.putExtra("memberId", memberId);
                intent.putExtra("eventId", EventsAdapter.eventId.get(i));
                intent.putExtra("judul", EventsAdapter.judul.get(i));
                intent.putExtra("date", EventsAdapter.date.get(i));
                intent.putExtra("time", EventsAdapter.time.get(i));
                intent.putExtra("deskripsi", EventsAdapter.deskripsi.get(i));
                intent.putExtra("category", EventsAdapter.category.get(i));
                intent.putExtra("gambar", EventsAdapter.gambar.get(i));

                startActivity(intent);
            }
        });
    }
}
