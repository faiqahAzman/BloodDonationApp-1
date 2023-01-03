package com.blooddonationapp.startactivity;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //creating variables for each element in the login page

        final EditText usernameInput = findViewById(R.id.LoginPage_ET_username);
        final EditText passwordInput = findViewById(R.id.LoginPage_ET_password);
        final Button loginButton = findViewById(R.id.LoginPage_BTN_login);
        final TextView registerNow = findViewById(R.id.LoginPage_TV_registerNow);


        //firebase variables (database)

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance("https://blood-donation-applicati-79711-default-rtdb.asia-southeast1.firebasedatabase.app/");
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        //SharedPreference to pass username to other activities
        SharedPreferences sharedPref = this.getSharedPreferences("userCredentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        //onClickListeners

        //1) Login Button

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameInput.getText().toString();
                final String password = passwordInput.getText().toString();

                //exceptions
                if(username.isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                else if(password.isEmpty())
                    Toast.makeText(LoginActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            //check if this username exist
                            if(snapshot.hasChild(username)){
                                //username present
                                //check if password is right
                                final String databasePassword = snapshot.child(username).child("Password").getValue(String.class);
                                final boolean isAdmin = (boolean) snapshot.child(username).child("isAdmin").getValue();
                                final String bloodGroup = snapshot.child(username).child("bloodGroup").getValue(String.class);
                                final String userID = snapshot.child(username).child("userID").getValue(String.class);

                                if(databasePassword.equals(password)){
                                    Toast.makeText(LoginActivity.this, "Successfully logged in", Toast.LENGTH_SHORT).show();


                                        //saving data in SharedPreferences
                                        editor.putString("username", username);
                                        editor.putBoolean("isAdmin", isAdmin);
                                        editor.putString("bloodGroup", bloodGroup);
                                        editor.putString("userID", userID);
                                        editor.commit();


                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("username",username);
                                        intent.putExtra("isAdmin",isAdmin);
                                        startActivity(intent);
                                        finish();



                                }

                                else{
                                    Toast.makeText(LoginActivity.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            else{
                                Toast.makeText(LoginActivity.this, "Wrong username", Toast.LENGTH_SHORT).show();
                            }


                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        //2) Register Now (Button)

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });






    }
}