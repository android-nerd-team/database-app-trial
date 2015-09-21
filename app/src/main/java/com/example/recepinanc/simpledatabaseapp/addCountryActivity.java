package com.example.recepinanc.simpledatabaseapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Recepinanc on 19.09.2015.
 */
public class addCountryActivity extends Activity {

    private Button mButton;
    private EditText code;
    private Button delete;
    private Boolean codeEntered;

    private String mCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_country_layout);

        codeEntered = false;

        final DBHelper dbHelper = new DBHelper(getApplicationContext());

        code = (EditText) findViewById(R.id.editText);

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                codeEntered = true;
                mCode = code.getText().toString();
            }
        });

        delete = (Button) findViewById(R.id.delete_button);
        delete.setFocusable(codeEntered);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteCountry(mCode);
                finish();
            }
        });

        mButton = (Button) findViewById(R.id.add_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertCountry(new Country("Eklendi!","1996"));
                finish();
            }
        });
    }
}
