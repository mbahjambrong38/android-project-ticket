package com.baru.projectkelas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.baru.projectkelas.db.Preference;
import com.baru.projectkelas.db.db;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    int memberId;
    String name;

    @BindView(R.id.edt_email_login)
    EditText edtEmailLogin;
    @BindView(R.id.edt_password_login)
    EditText edtPasswordLogin;
    @BindView(R.id.btn_login_login)
    Button btnLoginLogin;
    @BindView(R.id.btn_signUp_login)
    TextView btnSignUpLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        edtEmailLogin.addTextChangedListener(loginTextWatcher);
        edtPasswordLogin.addTextChangedListener(loginTextWatcher);

    }

    TextWatcher loginTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String userEmail = edtEmailLogin.getText().toString().trim();
            String userPass = edtPasswordLogin.getText().toString().trim();

            btnLoginLogin.setEnabled(!userEmail.isEmpty() && !userPass.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @OnClick({R.id.btn_login_login, R.id.btn_signUp_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:

                String email = edtEmailLogin.getText().toString();
                String password = edtPasswordLogin.getText().toString();

                if (checkLogin(email, password)) {

                    Preference.setContext(MainActivity.this);
                    Preference.actionLogin(email, password, memberId, name);
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("memberId", memberId);
                    startActivity(intent);
                    finish();

                }else {
                    Log.d("memberID" , String.format("%d", memberId));
                    Toast.makeText(MainActivity.this, "Error Login ", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_signUp_login:
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
                break;
        }
    }


    boolean checkLogin(String email, String password){


        for (int i = 0; i < db.VEC_USER.size(); i++) {
            if (db.VEC_USER.get(i).email.equals(email) &&
                    db.VEC_USER.get(i).password.equals(password)) {
                memberId = db.VEC_USER.get(i).memberId;
                name = db.VEC_USER.get(i).fName + " " + db.VEC_USER.get(i).lName;
                Log.d("memberId", String.format("%d", memberId));
                return true;
            }
        }

        return false;
    }
}
