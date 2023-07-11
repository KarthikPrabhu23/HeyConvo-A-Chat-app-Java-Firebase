package com.kp.chatnow2;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class calling_class extends AppCompatActivity {

    EditText editText;
    Button btn;
    ImageView chatBtn, userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calling_class);

        editText = findViewById(R.id.TextID);
        btn = findViewById(R.id.callLogin);
        chatBtn = findViewById(R.id.chatbut);
        userBtn = findViewById(R.id.userbut);

        getSupportActionBar().hide();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startmyservice(editText.getText().toString());
                Intent intent = new Intent(getApplicationContext(), profile.class);
                intent.putExtra("caller",editText.getText().toString().trim());
                startActivity(intent);
            }
        });

        chatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calling_class.this, MainActivity.class);
                startActivity(intent);
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(calling_class.this, setting.class);
                startActivity(intent);
            }
        });
    }

    public void startmyservice(String userid) {
        Application application = getApplication(); // Android's application context
        long appID = 1654123808;   // yourAppID
        String appSign = "196fc9b6880dab64f980d3f68419464094ac4f8e597afa540018fb7fe600a0f5";  // yourAppSign
        String userID = userid; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName = userid;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName, callInvitationConfig);
    }

    public void onDestroy()
    {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}