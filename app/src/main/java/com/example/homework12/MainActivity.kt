package com.example.homework12

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSend = findViewById<Button>(R.id.btnSend)

        val phoneNumber = findViewById<EditText>(R.id.edTPhoneNumber)
        val messageText = findViewById<EditText>(R.id.edTMessage)



        btnSend.setOnClickListener {
            val intent = Intent (this, MessengerChoose::class.java)
            val bundle = Bundle()
            bundle.putParcelable("ParcelableMessage", Message(phoneNumber.text.toString(),messageText.text.toString()))
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}

class Message (val destination: String?, val msgText: String?) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(destination)
        parcel.writeString(msgText)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }


}