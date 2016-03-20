package com.example.alejandro.firebaselogin;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private Firebase mFirebase;

    private Button mRegisterButton;

    private EditText mEditText;

    private EditText mEditText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_register);

        mFirebase = new Firebase(getResources().getString(R.string.firebase_url));

        mRegisterButton = (Button) findViewById(R.id.button);
        mEditText       = (EditText)findViewById(R.id.editText);
        mEditText2      = (EditText)findViewById(R.id.editText2);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = mEditText.getText().toString();
                final String password = mEditText2.getText().toString();
                mFirebase.createUser(email,
                        password,
                        new Firebase.ValueResultHandler<Map<String, Object>>() {

                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                        logUserIn(email, password);
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                    }
                });
            }
        });
    }

    private void logUserIn(String email, String password){
        mFirebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User Id: " + authData.getUid() + ", Provider: " + authData.getProvider());
                goToLoggedInActivity();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // do something here
            }
        });
    }

    private void goToLoggedInActivity(){
        Intent intent = new Intent(this, LoggedInActivity.class);
        startActivity(intent);
    }

}
