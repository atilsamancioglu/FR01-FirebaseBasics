package com.atilsamancioglu.firebasetestproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.Timestamp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_upload.*

class UploadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)
    }

    fun upload(view: View){

        val db = Firebase.firestore
        val auth = Firebase.auth
        val analytics = Firebase.analytics

        val bundle = Bundle()
        bundle.putString("username",auth.currentUser!!.email.toString())
        analytics.logEvent("uploadClicked",bundle)

        val tweetText = tweetText.text.toString()
        val currentUser = auth.currentUser!!.email
        val date = Timestamp.now()

        val dataMap = hashMapOf<String, Any>()
        dataMap.put("tweet",tweetText)
        dataMap.put("user",currentUser!!)
        dataMap.put("date",date)

        db.collection("Posts").add(dataMap).addOnSuccessListener {
            finish()
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }


    }
}