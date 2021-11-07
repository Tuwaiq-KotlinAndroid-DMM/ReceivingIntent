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

        val picView: ImageView = findViewById(R.id.imageView)
        val txtView: TextView = findViewById(R.id.textView)

        // Receiving the intent
        val receivedIntent = intent
        val receivedAction = receivedIntent.action
        Log.d(TAG, receivedAction.toString())
        val receivedType = receivedIntent.type
        Log.d(TAG, receivedType.toString())

        if(receivedType != null && receivedType!!.startsWith("text/")){
            picView.setVisibility(View.GONE)

            // Gets the received Text
            val receivedText = receivedIntent.getStringExtra(Intent.EXTRA_TEXT)
            if(receivedText != null){
                txtView.setText(receivedText)
            }

        }
        else if(receivedType != null && receivedType!!.startsWith("image/")){
            txtView.setVisibility(View.GONE)

            // Gets the received URI of the image
            val receivedURI: Uri? = receivedIntent.getParcelableExtra<Parcelable>(Intent.EXTRA_STREAM) as Uri?

            if(receivedURI != null){
                picView.setImageURI(receivedURI)
            }
        }

    }
}

//Implicit Intents - URL Lab
// 1. Create a new Android Project.
// 2. Name it: ImplicitIntentsURLLab
// 3. In activity_main.xml, create a EditText field
// 4. We will use the text that user enters and upon button press we will use implicit intent to instruct android to open link we provided.
// 5. Now any URL we enter in text field will be opened in available browser.