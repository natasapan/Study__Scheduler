package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by natas on 5/7/2018.
 */

public class SignInActivity  extends AppCompatActivity {

    EditText usName, pass;
    String signIn_username, signIn_password;
    Button signIn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usName = (EditText) findViewById(R.id.name_edittext);
        pass = (EditText) findViewById(R.id.surname_edittext);
        signIn = (Button) findViewById(R.id.sign_in_button);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignIn(v);
                Intent intent = new Intent(SignInActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });
    }

    public void userSignIn(View v){

        signIn_username = usName.getText().toString();
        signIn_password = pass.getText().toString();

        String method = "signin";
        BackgroundTask bt = new BackgroundTask(this);
        bt.execute(method,signIn_username,signIn_password);
    }
}
