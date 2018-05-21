package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by natas on 5/7/2018.
 */

public class RegisterActivity  extends AppCompatActivity {

    EditText nameEdit;
    EditText userNameEdit;
    EditText  passwordEdit;
    String name, userName,password;
    Button regButton;
    final String message = "Did not provide information needed. Try again.";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEdit = (EditText) findViewById(R.id.name_edittext);
        userNameEdit = (EditText) findViewById(R.id.username_edittext);
        passwordEdit = (EditText)  findViewById(R.id.password_edittext);
        regButton = (Button) findViewById(R.id.register_button);

        name = nameEdit.getText().toString();
        userName = userNameEdit.getText().toString();
        password = passwordEdit.getText().toString();

        regButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if(name.length() == 0 || userName.length() == 0 || password.length() == 0){
                    Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_LONG).show();
                }

                 else {

                    userRegister(v);

                    Intent intent = new Intent(RegisterActivity.this, CalendarActivity.class);
                    startActivity(intent);
               }

           }
        });
}

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void userRegister(View v){

        String method ="register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, userName,password);
        finish();
    }
}


