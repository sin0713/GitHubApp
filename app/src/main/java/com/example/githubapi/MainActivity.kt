package com.example.githubapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.githubapi.data.pojo.RepositoryCard
import com.example.githubapi.data.pojo.SampleData
import com.example.githubapi.ui.theme.GithubApiAppTheme
import com.example.githubapi.ui.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val list: MutableList<RepositoryCard> = mutableListOf()
        (1..10).forEach {
            list.add(SampleData.repositoryCardInfo)
        }

        setContent {
            GithubApiAppTheme {
                Scaffold(
                    backgroundColor = colorResource(id = R.color.app_background),
                    modifier = Modifier,
                    topBar = {
                        TopAppBar(
                            title = { Text(stringResource(id = R.string.app_name)) }
                        )
                    }
                ) { contentPadding ->
                    Spacer(modifier = Modifier.padding(contentPadding.calculateTopPadding()))
                    HomeScreen(cardData = list)
                }
            }
        }

//        ApiClient.getCommits("bookers")
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .filter { list -> list.isNotEmpty()}
//            .subscribe { commitList ->
//                val res: StringBuilder = StringBuilder()
//                commitList.forEach {
//                    res.append("${it.sha}\n")
//                }
//                val resultView = findViewById<TextView>(R.id.resultTxt)
//                resultView.text = res
//            }
//
//        ApiClient.search()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { body ->
//                println(body)
//            }

    }
}