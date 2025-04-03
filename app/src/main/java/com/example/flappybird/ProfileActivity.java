package com.example.flappybird;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Đóng ProfileActivity và trở về MainActivity
            }
        });

        // Ánh xạ các thành phần UI
        /*ImageView imgProfile = findViewById(R.id.imgProfile);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtAge = findViewById(R.id.txtAge);
        TextView txtSkills = findViewById(R.id.txtSkills);

        // Thiết lập dữ liệu cá nhân
        imgProfile.setImageResource(R.drawable.bo1); // Thay bằng hình cá nhân của bạn
        txtName.setText("Nguyễn Phan Thảo Nguyên");
        txtAge.setText("Age: 22");
        txtSkills.setText("Skill:\n- Java\n- Android\n- UI/UX\n- Firebase");*/
    }
}
