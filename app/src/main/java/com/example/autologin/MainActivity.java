package com.example.autologin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
        String email = sharedPreferences.getString("E-mail", "");
        String password = sharedPreferences.getString("Password", "");

        if(!email.isEmpty() && !password.isEmpty()){ // auto login
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("E-mail", email);
            startActivity(intent);
        }

        EditText et_email = findViewById(R.id.et_login_email);
        EditText et_password = findViewById(R.id.et_login_password);
        Button btn_login = findViewById(R.id.btn_login);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = String.valueOf(et_email.getText());
                String password = String.valueOf(et_password.getText());

                if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "E-mail can't be empty", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Password can't be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    builder.setTitle("Turn on auto login")
                            .setMessage("Do you want to turn on auto login?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    SharedPreferences sharedPreferences = getSharedPreferences("USER_DETAILS", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();

                                    editor.putString("E-mail", email);
                                    editor.putString("Password", password);

                                    editor.apply();

                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    intent.putExtra("E-mail", email);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                    intent.putExtra("E-mail", email);
                                    startActivity(intent);
                                }
                            });

                    builder.show();
                }
            }
        });
    }
}