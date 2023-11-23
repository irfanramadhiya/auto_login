package com.example.autologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btn_logout = findViewById(R.id.btn_logout);

        Intent intent = getIntent();
        String email = intent.getStringExtra("E-mail");

        String logoutText = "Logout from " + email;
        btn_logout.setText(logoutText);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // turn off auto login if user logs out
                SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.clear().apply();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}