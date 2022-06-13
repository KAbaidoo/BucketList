package com.example.bucketlist.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bucketlist.BuildConfig;
import com.example.bucketlist.R;
import com.google.android.material.textfield.TextInputLayout;

import co.paystack.android.Paystack;
import co.paystack.android.PaystackSdk;
import co.paystack.android.Transaction;
import co.paystack.android.model.Card;
import co.paystack.android.model.Charge;

public class CheckoutActivity extends AppCompatActivity {
    private TextInputLayout mCardNumber;
    private TextInputLayout mCardExpiry;
    private TextInputLayout mCardCVV;
    //    Button button;
    int amount;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        Intent i = getIntent();
        amount = (int) i.getFloatExtra("price", 0);
        email = i.getStringExtra("email");

        initializePaystack();
        initializeFormVariables();


    }
//
//    private void initViews() {
//        mCardNumber = findViewById(R.id.til_card_number);
//        mCardExpiry = findViewById(R.id.til_card_expiry);
//        mCardCVV = findViewById(R.id.til_card_cvv);
//
//
//       Button button = findViewById(R.id.btn_make_payment);
//        String btnLabel = "PAY GHC " + amount;
//        button.setText(btnLabel);
////        addTextWatcherToEditText();
//
//
//    }


    private void initializePaystack() {
        PaystackSdk.initialize(getApplicationContext());
        PaystackSdk.setPublicKey(BuildConfig.PSTK_PUBLIC_KEY);
    }

    private void initializeFormVariables() {

        mCardNumber = findViewById(R.id.til_card_number);
        mCardExpiry = findViewById(R.id.til_card_expiry);
        mCardCVV = findViewById(R.id.til_card_cvv);


//        Objects.requireNonNull(mCardExpiry.getEditText()).addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString().length() == 2 && !s.toString().contains("/")) {
//                    s.append("/");
//                }
//            }
//        });
        mCardNumber.getEditText().setText("4084084084084081");
        mCardExpiry.getEditText().setText("05/23");
        mCardCVV.getEditText().setText("408");

        Button button = findViewById(R.id.btn_make_payment);
        String btnLabel = "PAY GH₵" + amount;
        button.setText(btnLabel);
        button.setOnClickListener(v -> performCharge());

    }

    private void performCharge() {

        String cardNumber = mCardNumber.getEditText().getText().toString();
        String cardExpiry = mCardExpiry.getEditText().getText().toString();
        String cvv = mCardCVV.getEditText().getText().toString();
        String[] cardExpiryArray = cardExpiry.split("/");
        int expiryMonth = Integer.parseInt(cardExpiryArray[0]);
        int expiryYear = Integer.parseInt(cardExpiryArray[1]);
//        int amt = (int) intent.getFloatExtra("price", 0);
        amount *= 100;

        Card card = new Card(cardNumber, expiryMonth, expiryYear, cvv);
        Charge charge = new Charge();

        charge.setAmount(amount);
        charge.setEmail(email);
        charge.setCard(card);
        charge.setCurrency("GHS");


        PaystackSdk.chargeCard(this, charge, new Paystack.TransactionCallback() {
            @Override
            public void onSuccess(Transaction transaction) {
                parseResponse(transaction.getReference());
                Log.d("CheckoutActivity", "Payment Successful - " + transaction.getReference());
//        Take user back to sign in activity
                Intent i = new Intent(CheckoutActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void beforeValidate(Transaction transaction) {
                Log.d("CheckoutActivity", "beforeValidate: " + transaction.getReference());
            }

            @Override
            public void onError(Throwable error, Transaction transaction) {
                Log.d("CheckoutActivity", "onError: " + error.getLocalizedMessage());
                Log.d("CheckoutActivity", "onError: " + error);
                Log.d("CheckoutActivity", "onError: " + charge.getCurrency());
            }
        });
    }

    private void parseResponse(String transactionReference) {
        String message = "Payment Successful - " + transactionReference;
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}