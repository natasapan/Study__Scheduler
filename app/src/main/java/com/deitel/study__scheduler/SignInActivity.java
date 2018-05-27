package com.deitel.study__scheduler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

/**
 * Created by natas on 5/7/2018.
 */

public class SignInActivity  extends AppCompatActivity {

    EditText usName, pass;
    String signIn_username, signIn_password;
    Button signIn;
    final String message = "Did not provide information needed. Try again.";
    String f_result;
    AlertDialog alertDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        usName = (EditText) findViewById(R.id.sign_in_edittext);
        pass = (EditText) findViewById(R.id.surname_edittext);
        signIn = (Button) findViewById(R.id.sign_in_button);

        signIn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            signIn_username = usName.getText().toString();
            signIn_password = pass.getText().toString();

            if(signIn_username.length() == 0 || signIn_password.length() == 0){
                Toast.makeText(SignInActivity.this, message, Toast.LENGTH_LONG).show();

            }else try {
                userSignIn(v);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
}

    public void userSignIn(View v) throws ExecutionException, InterruptedException {

        String method = "signin";
        BackgroundTask bt = new BackgroundTask(this);
        f_result = bt.execute(method, signIn_username, signIn_password).get();

         if(f_result.equals( "Signing in failed. Try again!")) {
             Toast.makeText(this, f_result, Toast.LENGTH_LONG).show();

         }else {

             Toast.makeText(this, f_result, Toast.LENGTH_LONG).show();

             Intent intent = new Intent(SignInActivity.this, CalendarActivity.class);
             startActivity(intent);
         }
        finish();
    }
}
