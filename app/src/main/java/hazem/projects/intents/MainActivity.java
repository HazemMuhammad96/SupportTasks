package hazem.projects.intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    EditText mWebsiteEditText, mLocationEditText, mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.website_edit_text);
        mLocationEditText = findViewById(R.id.location_edit_text);
        mShareEditText = findViewById(R.id.share_edit_text);

    }

    public void openWebsite(View view) {
        Uri website = Uri.parse(mWebsiteEditText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, website);
        handleIntent(intent);
    }

    public void openLocation(View view) {
        Uri address = Uri.parse("geo:0,0?q=" + mLocationEditText.getText().toString());
        Intent intent = new Intent(Intent.ACTION_VIEW, address);
        handleIntent(intent);
    }

    public void shareText(View view) {
        ShareCompat.IntentBuilder.from(this).setChooserTitle("Share this text with: ")
                .setType("text/plain").setText(mShareEditText.getText().toString()).startChooser();
    }

    private void handleIntent(Intent intent) {
        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
        else handleMissingApp();
    }

    private void handleMissingApp() {
        Snackbar.make(getWindow().getDecorView().getRootView(), "No App Found", Snackbar.LENGTH_SHORT).show();
    }
}