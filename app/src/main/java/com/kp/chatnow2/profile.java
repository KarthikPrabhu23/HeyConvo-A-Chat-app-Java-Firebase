package com.kp.chatnow2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class profile extends AppCompatActivity {

    TextView caller;
    EditText targetuser;
    ZegoSendCallInvitationButton callbtn;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        caller = findViewById(R.id.text);
        targetuser = findViewById(R.id.editId);
        callbtn = findViewById(R.id.callBtn);

        caller.setText("Logging "+getIntent().getStringExtra("caller"));

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

    public void startvideocall(String targetuserid)
    {
        callbtn.setIsVideoCall(true);
        callbtn.setResourceID("zego_uikit_call");
        callbtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetuserid,targetuserid)));
    }
}