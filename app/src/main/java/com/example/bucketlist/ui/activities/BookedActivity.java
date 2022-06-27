package com.example.bucketlist.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bucketlist.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class BookedActivity extends AppCompatActivity {
    FirebaseFirestore db;
//    CollectionReference userListRef;
    CollectionReference eventsRef;
//    FirebaseAuth auth;
    QRCodeWriter qrCodeWriter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked);


        db = FirebaseFirestore.getInstance();
//        auth = FirebaseAuth.getInstance();
//        FirebaseUser user = auth.getCurrentUser();
//        String uid = user.getUid();
//        String email = user.getEmail();

//        userListRef = db.collection("users").document(uid).collection("list");
        eventsRef = db.collection("events");

        Intent intent = getIntent();
        String eventId = intent.getStringExtra("id");
//        loadEvent(eventId);
//        Grab textViews
        TextView eventTitle = findViewById(R.id.textView_detail_title);
        TextView description = findViewById(R.id.textView_description);
//        ImageView imgBanner = findViewById(R.id.imageView_detail);
        RatingBar ratingBar = findViewById(R.id.ratingBar_detail);
        ImageView iv_qrcode = findViewById(R.id.iv_qrcode);

       iv_qrcode.post(new Runnable() {
           @Override
           public void run() {
               iv_qrcode.setImageBitmap(createQRcode(iv_qrcode, eventId));
           }
       });



//        Set TextViews
        eventTitle.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("info"));
//        Float price = intent.getFloatExtra("price", 0);
//        eventPrice.setText(Float.toString(price));
        ratingBar.setRating(intent.getFloatExtra("rating", 2));
//        set Image banner
//        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("image_resource")).into(imgBanner);
    }

    private Bitmap createQRcode(ImageView iv_qrcode, String id) {
        Bitmap bitmap = Bitmap.createBitmap(iv_qrcode.getWidth(), iv_qrcode.getHeight(), Bitmap.Config.RGB_565);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(id, BarcodeFormat.QR_CODE, iv_qrcode.getWidth(), iv_qrcode.getHeight());
            for (int i = 0; i<iv_qrcode.getWidth(); i++){
                for (int j = 0; j<iv_qrcode.getHeight(); j++){
                    bitmap.setPixel(i,j,bitMatrix.get(i,j)? Color.BLACK: Color.WHITE);
                }
            }
            iv_qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}