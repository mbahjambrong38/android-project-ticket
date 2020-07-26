package com.baru.projectkelas;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.baru.projectkelas.db.db;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmationActivity extends AppCompatActivity {
    int memberId, orderId, totalPrice;

    int checkImage = 0;

    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.img_confirmation)
    ImageView imgConfirmation;
    @BindView(R.id.btn_choose_image)
    Button btnChooseImage;
    @BindView(R.id.edt_rekening_name)
    EditText edtRekeningName;
    @BindView(R.id.btn_submit_confirmation)
    Button btnSubmitConfirmation;

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        ButterKnife.bind(this);

        memberId = getIntent().getIntExtra("memberId", 0);
        orderId = getIntent().getIntExtra("orderId", 0);
        totalPrice = getIntent().getIntExtra("totalPrice", 0);

        txtTotalPrice.setText("Rp. " + Integer.toString(totalPrice));

        edtRekeningName.addTextChangedListener(textWatcher);
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String name = edtRekeningName.getText().toString().trim();

            btnSubmitConfirmation.setEnabled(!name.isEmpty() && checkImage == 1);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @OnClick({R.id.btn_choose_image, R.id.btn_submit_confirmation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_choose_image:
                ActivityCompat.requestPermissions(
                        ConfirmationActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
                break;
            case R.id.btn_submit_confirmation:
                try {
                    db.insertConfirmationToDatabaseAndVector(ConfirmationActivity.this,
                            imageViewToByte(imgConfirmation),
                            edtRekeningName.getText().toString().trim(),
                            orderId);

                    Toast.makeText(getApplicationContext(), "Success Confirmation", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ConfirmationActivity.this, HomeActivity.class);
                    intent.putExtra("memberId", memberId);
                    startActivity(intent);
                    finish();

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    private byte[] imageViewToByte(ImageView img){
        Bitmap bitmap = ((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don'h have permission to access file location!!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgConfirmation.setImageBitmap(bitmap);
                checkImage = 1;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
