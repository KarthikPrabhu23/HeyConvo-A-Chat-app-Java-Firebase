package com.kp.chatnow2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class profile extends AppCompatActivity {

    TextView caller;
    EditText targetuser;
    ZegoSendCallInvitationButton callbtn;

    ImageView chatBtn, userBtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        getSupportActionBar().hide();

        caller = findViewById(R.id.text);
        targetuser = findViewById(R.id.editId);
        callbtn = findViewById(R.id.callBtn);

        chatBtn = findViewById(R.id.chatbut);
        userBtn = findViewById(R.id.useroff);

        caller.setText("HeyConvo! Logging " + getIntent().getStringExtra("caller") + " in call");

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, setting.class);
                startActivity(intent);
            }
        });

        targetuser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                startvideocall(targetuser.getText().toString().trim());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void startvideocall(String targetuserid) {
        callbtn.setIsVideoCall(true);
        callbtn.setResourceID("zego_uikit_call");
        callbtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetuserid, targetuserid)));
    }
}