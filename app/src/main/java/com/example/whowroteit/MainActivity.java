package com.example.whowroteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText mBookInput;
    private TextView mTitleText;
    private TextView mAuthorText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.et_bookImput);
        mTitleText = findViewById(R.id.tv_titleText);
        mAuthorText = findViewById(R.id.tv_authorText);
    }

    public void searchBooks(View view) {

        String queryString = mBookInput.getText().toString();
        new FechBook(mTitleText, mAuthorText).execute(queryString);
    }
}