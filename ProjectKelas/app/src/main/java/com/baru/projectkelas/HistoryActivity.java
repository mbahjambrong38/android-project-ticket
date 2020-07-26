package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.adapter.EventsAdapter;
import com.baru.projectkelas.adapter.HistoryAdapter;
import com.baru.projectkelas.db.Confirmation;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends AppCompatActivity {
    int memberId;
    @BindView(R.id.list_history)
    ListView listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        memberId = getIntent().getIntExtra("memberId", 0);

        HistoryAdapter adapter = new HistoryAdapter(getApplicationContext(), memberId);

        listHistory.setAdapter(adapter);

        clickHistory();

    }

    void clickHistory(){
        listHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (HistoryAdapter.V_STATUS.get(i).equalsIgnoreCase("Uncompleted")){
                    Intent intent = new Intent(HistoryActivity.this, ConfirmationActivity.class);

                    intent.putExtra("memberId", memberId);
                    intent.putExtra("orderId", HistoryAdapter.V_ORDER_ID.get(i));
                    intent.putExtra("totalPrice", HistoryAdapter.V_PRICE.get(i));

                    startActivity(intent);
                }
            }
        });
    }
}
