package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.db.db;

import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUp extends AppCompatActivity{

    String gender;

    @BindView(R.id.edt_firstName_signUp)
    EditText edtFirstNameSignUp;
    @BindView(R.id.edt_lastName_signUp)
    EditText edtLastNameSignUp;
    @BindView(R.id.edt_email_signUp)
    EditText edtEmailSignUp;
    @BindView(R.id.edt_password_signUp)
    EditText edtPasswordSignUp;
    @BindView(R.id.edt_phone_signUp)
    EditText edtPhoneSignUp;
    @BindView(R.id.listDate)
    Spinner listDate;
    @BindView(R.id.listMonth)
    Spinner listMonth;
    @BindView(R.id.listYear)
    Spinner listYear;
    @BindView(R.id.rd_feMale_signUp)
    RadioButton rdFeMaleSignUp;
    @BindView(R.id.rd_male_signUp)
    RadioButton rdMaleSignUp;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.radio_group_gender)
    RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        edtEmailSignUp.addTextChangedListener(registerTextWatcher);
        edtFirstNameSignUp.addTextChangedListener(registerTextWatcher);
        edtLastNameSignUp.addTextChangedListener(registerTextWatcher);
        edtPasswordSignUp.addTextChangedListener(registerTextWatcher);
        edtPhoneSignUp.addTextChangedListener(registerTextWatcher);

        radioGroupGender.setOnCheckedChangeListener(rdChangeListener);

        init();

    }

    public void init(){
        Vector<String> vecDay = new Vector<>();
        Vector<String> vecMonth = new Vector<>();
        Vector<String> vecYear = new Vector<>();


        for(int i = 1; i <= 31; i++){
            vecDay.add(Integer.toString(i));
        }
        for(int i = 1; i <= 12; i++){
            vecMonth.add(Integer.toString(i));
        }
        for(int i = 1950; i <= 2019; i++){
            vecYear.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vecDay);
        listDate.setAdapter(adapter);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vecMonth);
        listMonth.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vecYear);
        listYear.setAdapter(adapter2);

    }

    RadioGroup.OnCheckedChangeListener rdChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.rd_feMale_signUp:
                    gender = rdFeMaleSignUp.getText().toString();
                    break;
                case R.id.rd_male_signUp:
                    gender = rdMaleSignUp.getText().toString();
                    break;
            }
        }
    };

    TextWatcher registerTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String email = edtEmailSignUp.getText().toString().trim();
            String pass = edtPasswordSignUp.getText().toString().trim();
            String fName = edtFirstNameSignUp.getText().toString().trim();
            String lName = edtLastNameSignUp.getText().toString().trim();
            String phone = edtPhoneSignUp.getText().toString().trim();

            btnLoginLogin.setEnabled(!email.isEmpty() && !pass.isEmpty() && !fName.isEmpty() && !lName.isEmpty() && !phone.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @OnClick(R.id.btn_login_login)
    public void onViewClicked() {
        String fName = edtFirstNameSignUp.getText().toString();
        String lName = edtLastNameSignUp.getText().toString();
        String email = edtEmailSignUp.getText().toString();
        String password = edtPasswordSignUp.getText().toString();
        String phone = edtPhoneSignUp.getText().toString();
        String bDay = listDate.getSelectedItem().toString() + "-" + listMonth.getSelectedItem() + "-" + listYear.getSelectedItem().toString();

        if (!email.contains(".") || !email.contains("@"))
        {
            Toast.makeText(this, "Error Email must contain . and @", Toast.LENGTH_SHORT).show();
        }else if (password.length() < 5)
        {
            Toast.makeText(this, "Error Password", Toast.LENGTH_SHORT).show();
        }else if (phone.length() < 11 || phone.length() > 13)
        {
            Toast.makeText(this, "Error Phone", Toast.LENGTH_SHORT).show();
        }else
        {
            db.insertUserToDatabaseAndVector(this, email, password, fName, lName, phone, gender, bDay);

            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUp.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}
