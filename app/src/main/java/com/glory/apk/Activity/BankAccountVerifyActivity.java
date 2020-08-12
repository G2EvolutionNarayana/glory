package com.glory.apk.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.glory.apk.R;


public class BankAccountVerifyActivity extends AppCompatActivity {
    EditText editname,xEtIfscCode,xEtAccountNumber;
    Button butVerifyf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_account_verify);
        mInitWidgets();
    }

    private void mInitWidgets() {

    }
}
