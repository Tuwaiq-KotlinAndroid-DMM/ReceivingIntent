package com.example.receivingintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get the context of ImageView and TextView
        val picView: ImageView = findViewById(R.id.imageView)
        val txtView: TextView = findViewById(R.id.textView)

        // Receiving the intent
        val receivedIntent = intent

        // Parsing the Intent action information from the received intent
        val receivedAction = receivedIntent.action
        Log.d(TAG, receivedAction.toString())

        // Parsing the Intent type information from the received intent
        val receivedType = receivedIntent.type
        Log.d(TAG, receivedType.toString())

        // Checking, if the received type is not null and starts with "text/, so execute the if block
        if(receivedType != null && receivedType!!.startsWith("text/")){

            // Hide the ImageView
            picView.setVisibility(View.GONE)

            // Gets the received Text
            val receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT)

            // Handle the null condition of receivedText
            if(receivedText != null){
                // Display the received text
                txtView.setText(receivedText)
            }

        }
        // Checking, if the received type is not null and starts with "image/, so execute the if block
        else if(receivedType != null && receivedType!!.startsWith("image/")){

            // Hide the textView
            txtView.setVisibility(View.GONE)

            // Gets the received URI of the image
            val receivedURI: Uri? = receivedIntent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri?

            // Handle the null condition of receivedURI
            if(receivedURI != null){
                // Display the received image
                picView.setImageURI(receivedURI)
            }
        }

    }
}

