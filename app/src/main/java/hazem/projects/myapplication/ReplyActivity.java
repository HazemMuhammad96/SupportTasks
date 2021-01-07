package hazem.projects.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ReplyActivity extends AppCompatActivity {

    TextView mMessageText;
    EditText mReplyEditText;
    public static final String REPLY = "reply";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        
        mMessageText = findViewById(R.id.message_text_view);
        mReplyEditText = findViewById(R.id.reply_text_field);
        
        String message = receiveMessage();
        mMessageText.setText(message);

        findViewById(R.id.fab_reply).setOnClickListener(v -> {
            sendReply();
        });
    }
    private void sendReply() {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(REPLY,mReplyEditText.getText().toString());
        setResult(RESULT_OK,replyIntent);
        finish();
    }

    private String receiveMessage() {
        String message = "";
        Intent intent = getIntent();
        message = intent.getStringExtra(SendActivity.MESSAGE);
        return message;
    }
}