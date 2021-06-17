package com.example.homework3forandroidgrup2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText etLogin, etPassword;
    Button bntSave;
    private int pos;
    TaskMOdel mOdel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        etLogin = findViewById(R.id.first_edit);
        etPassword = findViewById(R.id.second_edit);
        bntSave = findViewById(R.id.button_text);



        // скидываем наши данные введенные в EditT на первый активити
        bntSave.setOnClickListener(v -> {
           // if (!etLogin.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()) {
                String login = etLogin.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                intent.putExtra("login", login);
                intent.putExtra("password", password);
                // тут мы еще скидываем поизицию чтобы понять понимать что мы отредактировали и передали уже отредактированную
                intent.putExtra("pos", pos);
                setResult(RESULT_OK, intent);
                if (mOdel != null){
                   mOdel.setTitle(login);
                    mOdel.setDescription(password);
                }
                finish();
           // }
        });
        getInformIntent();

    }

    private void getInformIntent() {
        //код для получения данных от мейн активити тоесть мы же скидываем сначала введенные данные потом при нажатии
        // на слово он переходит в second activity с данными оттуда
        Intent getData = getIntent();
        String title = getData.getStringExtra("login");
        String description = getData.getStringExtra("password");
        pos = getData.getIntExtra("pos", 0);
        etLogin.setText(title);
        etPassword.setText(description);
    }

}