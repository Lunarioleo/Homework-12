package com.example.homework12

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MessengerChoose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_messenger_choose)


        var message = ""
        var destination = ""
        intent?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                message = it.getParcelableExtra(
                    "ParcelableMessage",
                    Message::class.java
                )?.msgText.toString()
                destination = it.getParcelableExtra(
                    "ParcelableMessage",
                    Message::class.java
                )?.destination.toString()
            } else {
                message = it.getParcelableExtra<Message>("ParcelableMessage")?.msgText.toString()
                destination =
                    it.getParcelableExtra<Message>("ParcelableMessage")?.destination.toString()
            }
        }

        val sms = findViewById<ImageView>(R.id.sms)
        val gmail = findViewById<ImageView>(R.id.telegram)

        sms.setOnClickListener {
            if (destination.contains("@")) {
                Toast.makeText(
                    this,
                    "Ooops.You cant send sms using email adress. Choose GMAIL.",
                    Toast.LENGTH_LONG
                ).show()

            } else {


                val uri = Uri.parse("smsto:$destination")
                val intent = Intent(Intent.ACTION_SENDTO, uri)

                with(intent) {
                    putExtra("address", destination)
                    putExtra("sms_body", message)
                }
                startActivity(intent)
            }
        }

        gmail.setOnClickListener {
            if (!destination.contains("@")) {
                Toast.makeText(
                    this,
                    "Ooops.You cant send an email, using phone number. Choose SMS.",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                val uri = Uri.parse("mailto:$destination")
                    .buildUpon()
                    .appendQueryParameter("body", message)
                    .build()
                val intent = Intent(Intent.ACTION_SENDTO, uri)
                startActivity(intent)
            }
        }
    }
}