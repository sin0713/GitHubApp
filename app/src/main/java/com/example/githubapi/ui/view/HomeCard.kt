package com.example.githubapi.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.example.githubapi.R
import com.example.githubapi.data.pojo.RepositoryCard
import com.example.githubapi.data.pojo.SampleData

@Composable
fun HomeCard(cardData: RepositoryCard) {
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = 8.dp,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(all = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    cardData.name,
                    style = MaterialTheme.typography.h6
                )
                CardText(
                    text = stringResource(R.string.author_prefix),
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 30.dp)
                )
                CardText(
                    text = cardData.author,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 5.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                CardSubData(
                    data = cardData.watchers.toString(),
                    title = stringResource(R.string.watchers),
                    color = colorResource(R.color.pink)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                CardSubData(
                    data = cardData.forks.toString(),
                    title = stringResource(R.string.forks),
                    color = colorResource(R.color.purple)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 15.dp)
            ) {
                VerticalLine(
                    height = 50.dp,
                    color = colorResource(R.color.light_green)
                )
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    CardText(
                        text = stringResource(R.string.stars),
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.padding(top = 5.dp)

                    ) {
                        Icon(
                            painter = rememberVectorPainter(Icons.Default.Star),
                            contentDescription = null,
                            tint = colorResource(R.color.light_green),
                            modifier = Modifier.size(28.dp)
                        )
                        Text(
                            cardData.stars.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.light_green),
                            modifier = Modifier.padding(start = 10.dp)
                        )
                        CardText(
                            text = stringResource(R.string.gain),
                            color = Color.Gray,
                            Modifier.padding(start = 10.dp)
                        )
                        Spacer(
                            modifier = Modifier.weight(1.0f)
                        )
                        CardText(
                            text = cardData.createdAt,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardText(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    Text(
        text,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        modifier = modifier
    )
}

@Composable
fun CardSubData(
    data: String,
    title: String,
    color: Color,
) {
    VerticalLine(
        height = 20.dp,
        color = color,
    )
    CardText(
        text = title,
        color = color,
        modifier = Modifier.padding(start = 8.dp)
    )
    CardText(
        text = data,
        color = color,
        modifier = Modifier.padding(start = 10.dp)
    )
}

@Composable
fun VerticalLine(
    height: Dp,
    color: Color,
) {
    Spacer(
        Modifier
            .height(height)
            .width(3.dp)
            .background(color)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun HomeCardPreview() {
    HomeCard(SampleData.repositoryCardInfo)
}
