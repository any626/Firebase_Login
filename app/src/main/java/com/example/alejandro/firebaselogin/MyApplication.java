package com.example.alejandro.firebaselogin;

import com.firebase.client.Firebase;

/**
 * Created by alejandro on 3/19/2016.
 */
public class MyApplication extends android.app.Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
