package com.example.alejandro.firebaselogin;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                mFirebase.createUser(mEditText.getText().toString(),
                        mEditText2.getText().toString(),
                        new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> result) {
                        System.out.println("Successfully created user account with uid: " + result.get("uid"));
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        // there was an error
                    }
                });
            }
        });
    }
}
