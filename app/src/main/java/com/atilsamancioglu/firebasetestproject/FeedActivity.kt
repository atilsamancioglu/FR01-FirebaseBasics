package com.atilsamancioglu.firebasetestproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main2.*

class FeedActivity : AppCompatActivity() {
    val db = Firebase.firestore
    var tweetList = ArrayList<Tweet>()
    lateinit var adapter : TweetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        getData()

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = TweetAdapter(tweetList)
        recyclerView.adapter = adapter

        throw RuntimeException("Test Crash") // Force a crash


    }

    fun getData() {
        db.collection("Posts").orderBy("date",Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            } else {
                if (value != null) {
                    if (!value.isEmpty) {
                        val documents = value.documents

                        tweetList.clear()

                        for (document in documents) {
                            val tweetText = document.get("tweet") as String
                            val user = document.get("user") as String

                            val tweet = Tweet(tweetText,user)
                            tweetList.add(tweet)
                        }

                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.options_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.add_tweet) {
            val intent = Intent(this,UploadActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}