package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.db.Event;
import com.baru.projectkelas.db.Preference;
import com.baru.projectkelas.db.db;
import com.baru.projectkelas.init.SplashScreen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    int memberId;
    String name;

    @BindView(R.id.img_flipper)
    ViewFlipper imgFlipper;
    @BindView(R.id.btn_musik)
    LinearLayout btnMusik;
    @BindView(R.id.btn_seni)
    LinearLayout btnSeni;
    @BindView(R.id.btn_workshop)
    LinearLayout btnWorkshop;
    @BindView(R.id.btn_talkshow)
    LinearLayout btnTalkshow;
    @BindView(R.id.btn_fashion)
    LinearLayout btnFashion;
    @BindView(R.id.btn_bazzar)
    LinearLayout btnBazzar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        memberId = getIntent().getIntExtra("memberId", 0);
        init_banner();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        MenuItem item = menu.findItem(R.id.menuNameDisplay);
        Preference.setContext(HomeActivity.this);
        name = Preference.getName();
        item.setTitle("" + name);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menuLogout:
                Preference.setContext(HomeActivity.this);
                Preference.actionLogout();

                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.menuHistory:
                intent = new Intent(HomeActivity.this, HistoryActivity.class);
                intent.putExtra("memberId", memberId);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void init_banner() {
        int image[] = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3};

        for (int i = 0; i < image.length; i++) {
            flipperImage(image[i]);
        }
    }

    public void flipperImage(int image) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        imgFlipper.addView(imageView);
        imgFlipper.setFlipInterval(4000);
        imgFlipper.setAutoStart(true);

        imgFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        imgFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }

    @OnClick({R.id.btn_musik, R.id.btn_seni, R.id.btn_workshop, R.id.btn_talkshow, R.id.btn_fashion, R.id.btn_bazzar})
    public void onViewClicked(View view) {
        Intent intent = new Intent(HomeActivity.this, EventsActivity.class);
        intent.putExtra("memberId", memberId);
        switch (view.getId()) {
            case R.id.btn_musik:
                intent.putExtra("category", "Musik");
                break;
            case R.id.btn_seni:
                intent.putExtra("category", "Seni");
                break;
            case R.id.btn_workshop:
                intent.putExtra("category", "Workshop");
                break;
            case R.id.btn_talkshow:
                intent.putExtra("category", "Talkshow");
                break;
            case R.id.btn_fashion:
                intent.putExtra("category", "Fashion");
                break;
            case R.id.btn_bazzar:
                intent.putExtra("category", "Bazzar");
                break;
        }
        startActivity(intent);
    }
}
