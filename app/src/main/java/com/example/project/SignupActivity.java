package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    EditText signupName, phoneNumber, verificationCode, signupUsername, signupPassword;
    Spinner signupPatientType;
    Button sendVerificationCodeButton, verifyButton, signupButton;
    TextView loginRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        signupName = findViewById(R.id.signup_name);
        phoneNumber = findViewById(R.id.phone_number);
        verificationCode = findViewById(R.id.verification_code);
        signupPatientType = findViewById(R.id.signup_PatientType);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        sendVerificationCodeButton = findViewById(R.id.send_verification_code_button);
        verifyButton = findViewById(R.id.verify_button);
        signupButton = findViewById(R.id.signup_button);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        // Set up the spinner
        String[] patientTypes = {"Doctor", "Patient"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patientTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        signupPatientType.setAdapter(adapter);
        signupPatientType.setSelection(0);

        // Set up button listeners
        sendVerificationCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to send verification code
                Toast.makeText(SignupActivity.this, "Verification code sent", Toast.LENGTH_SHORT).show();
            }
        });

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code to verify the verification code
                Toast.makeText(SignupActivity.this, "Verification code verified", Toast.LENGTH_SHORT).show();
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    registerUser();
                }
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean validateInputs() {
        String name = signupName.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String code = verificationCode.getText().toString().trim();
        String type = signupPatientType.getSelectedItem().toString(); // Get selected item from Spinner
        String username = signupUsername.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        if (name.isEmpty()) {
            signupName.setError("Name cannot be empty");
            return false;
        }

        if (phone.isEmpty()) {
            phoneNumber.setError("Phone number cannot be empty");
            return false;
        }

        if (code.isEmpty()) {
            verificationCode.setError("Verification code cannot be empty");
            return false;
        }

        if (username.isEmpty()) {
            signupUsername.setError("Username cannot be empty");
            return false;
        }

        if (password.isEmpty()) {
            signupPassword.setError("Password cannot be empty");
            return false;
        }

        return true;
    }

    private void registerUser() {
        String name = signupName.getText().toString().trim();
        String phone = phoneNumber.getText().toString().trim();
        String type = signupPatientType.getSelectedItem().toString(); // Get selected item from Spinner
        String username = signupUsername.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users");

        User user = new User(name, phone, type, username, password);
        reference.child(username).setValue(user);

        Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public static class User {
        public String name, phone, type, username, password;

        public User() {
        }

        public User(String name, String phone, String type, String username, String password) {
            this.name = name;
            this.phone = phone;
            this.type = type;
            this.username = username;
            this.password = password;
        }
    }
}
