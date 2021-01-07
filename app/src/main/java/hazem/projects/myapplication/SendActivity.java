package hazem.projects.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SendActivity extends AppCompatActivity {


    public static final String MESSAGE = "message";
    public static final int REQUEST_CODE = 1;
    public static final String FLAG = "flag";
    boolean isReceived = false;

    LinearLayout mReplyContainer;
    TextView mReplyText;
    EditText mMessageEditText;
    private String mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        mReplyContainer = findViewById(R.id.reply_container);
        mReplyText = findViewById(R.id.reply_text_view);
        mMessageEditText = findViewById(R.id.message_text_field);

        findViewById(R.id.fab_send).setOnClickListener(v -> {
            sendMessage();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                receiveReply(data);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(FLAG,isReceived);
        outState.putString(ReplyActivity.REPLY, mReply);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        isReceived = savedInstanceState.getBoolean(FLAG);
        if (isReceived) {
            mReplyContainer.setVisibility(View.VISIBLE);
            mReply = savedInstanceState.getString(ReplyActivity.REPLY);
            mReplyText.setText(mReply);
        }
    }


    private void sendMessage() {
        Intent messageIntent = new Intent(SendActivity.this, ReplyActivity.class);
        messageIntent.putExtra(MESSAGE,mMessageEditText.getText().toString());
        startActivityForResult(messageIntent, REQUEST_CODE);
    }

    private void receiveReply(@Nullable Intent data) {
        mReply = data.getStringExtra(ReplyActivity.REPLY);
        mReplyContainer.setVisibility(View.VISIBLE);
        mReplyText.setText(mReply);
        isReceived = true;
    }
}