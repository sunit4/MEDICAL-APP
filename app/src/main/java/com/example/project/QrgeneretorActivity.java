package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QrgeneretorActivity extends AppCompatActivity {

    private EditText nameEditText, ageEditText, mobileEditText, addressEditText, problemEditText, bloodGroupEditText, additionalInfoEditText;
    private RadioGroup genderRadioGroup;
    private RadioButton maleRadioButton, femaleRadioButton;
    private Button uploadImageButton, generateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qrgeneretor);

        // Initialize views
        nameEditText = findViewById(R.id.name_edittext);
        ageEditText = findViewById(R.id.age_edittext_edittext);
        mobileEditText = findViewById(R.id.mobile_edittext);
        addressEditText = findViewById(R.id.address_edittext);
        problemEditText = findViewById(R.id.problem_edittext);
        bloodGroupEditText = findViewById(R.id.bloodgroup_edittext);
        additionalInfoEditText = findViewById(R.id.Aditionalinformation_edittext);
        genderRadioGroup = findViewById(R.id.gender_radiogroup);
        maleRadioButton = findViewById(R.id.male_radiobutton);
        femaleRadioButton = findViewById(R.id.female_radiobutton);
        uploadImageButton = findViewById(R.id.upload_image_button);
        generateButton = findViewById(R.id.Generate_buttot);

        // Set listeners
        uploadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Upload image logic here
                Toast.makeText(QrgeneretorActivity.this, "Upload image button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate QR code logic here
                Toast.makeText(QrgeneretorActivity.this, "Generate button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set padding for Generate QR text
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.GenerateQr_text), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}