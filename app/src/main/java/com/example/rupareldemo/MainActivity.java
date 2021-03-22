    package com.example.rupareldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {

        DB db = new DB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
        setUpListener();
    }

    private void setUpViews(){
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotice();
            }
        });

    }

    private void addNotice(){
        String noticeTitle = ((EditText) findViewById(R.id.title)).getText().toString();
        Notice notice = new Notice(noticeTitle);
        db.addNotice(notice);
    }

    private void setUpListener(){
        db.setUpListener(new DB.onNewNotice() {
            @Override
            public void newNotice(Notice notice) {
                ((TextView) findViewById(R.id.noticeTitle))
                        .setText(notice.getTitle());
            }
        });
    }
}