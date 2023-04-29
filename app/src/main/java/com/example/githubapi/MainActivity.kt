package com.example.githubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ApiClient.getCommits("bookers")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { list -> list.isNotEmpty()}
            .subscribe { commitList ->
                val res: StringBuilder = StringBuilder()
                commitList.forEach {
                    res.append("${it.sha}\n")
                }
                val resultView = findViewById<TextView>(R.id.resultTxt)
                resultView.text = res
            }
    }
}