package com.help.drummond;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button debugButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        debugButton = findViewById(R.id.button);

        debugButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Drummond.d("This is a simple debug");
    }
}
