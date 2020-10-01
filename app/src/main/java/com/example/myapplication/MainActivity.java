package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String msg = "Android : ";
    Button startBrowserButton;
    Button startPhoneButton;
    private Button browserButton1;
    private Button browserButton2;
    private Button browserButton3;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.next_activity);
        browserButton1 = findViewById(R.id.browserButton1);
        browserButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actionViewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));
                startActivity(actionViewIntent);
            }
        });

        browserButton2 = findViewById(R.id.browserButton2);
        browserButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actionViewIntent = new Intent("com.example.myapplication.LAUNCH",
                        Uri.parse("http://www.example.com"));
                startActivity(actionViewIntent);
            }
        });

        browserButton3 = findViewById(R.id.browserButton3);
        browserButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent actionViewIntent = new Intent("com.example.MyApplication.LAUNCH",
                        Uri.parse("https://www.example.com"));
                startActivity(actionViewIntent);
            }
        });
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Log.d(msg, "The onCreate() event");
//        startBrowserButton = findViewById(R.id.buttonStartBrowser);
//        startBrowserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent actionViewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/"));
//                startActivity(actionViewIntent);
//            }
//        });
//        startPhoneButton = findViewById(R.id.buttonStartPhone);
//        startPhoneButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent actionViewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:8698563269"));
//                startActivity(actionViewIntent);
//            }
//        });
//    }

    public void startService(View view) {
        startService(new Intent(getBaseContext(), HelloService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), HelloService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    public void broadcastIntent(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.myapplication.CUSTOM_INTENT");
        sendBroadcast(intent);
    }

    public void onClickAddName(View view){
        ContentValues values = new ContentValues();
        values.put(StudentsProvider.NAME, ((EditText)findViewById(R.id.editTextName)).getText().toString());
        values.put(StudentsProvider.GRADE, ((EditText)findViewById(R.id.editTextGrade)).getText().toString());
        Uri uri = getContentResolver().insert(StudentsProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(),uri.toString(),Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view){

        Uri uri =StudentsProvider.CONTENT_URI;
        Cursor c = managedQuery(uri, null, null, null, "name");
        if (c.moveToFirst()) {
            do{
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(StudentsProvider._ID)) +
                                ", " +  c.getString(c.getColumnIndex( StudentsProvider.NAME)) +
                                ", " + c.getString(c.getColumnIndex( StudentsProvider.GRADE)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
