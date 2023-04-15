package com.example.composemovies.presenter.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composemovies.R

@Composable
fun MovieCard(
    moviePoster: String,
    title: String,
    vote_average : Double,
    vote_count : Int,
    overview: String,
) {
    Card(shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .width(180.dp)
            .height(350.dp)
            .clickable { }) {

        Column(modifier = Modifier.padding(8.dp)) {
            Box(modifier = Modifier.size(width = 180.dp, height = 250.dp)) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(moviePoster)
                        .crossfade(true)
                        .build(),
                    contentDescription = "movie poster",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter,
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                )
                Card(
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .align(Alignment.BottomCenter),
                    backgroundColor = Color(0x8A523030)
                ){
                    Row(modifier = Modifier , verticalAlignment = Alignment.CenterVertically) {
                        Icon(painter = painterResource(R.drawable.ic_star) , contentDescription = "star image" , tint = Color(0xFFFF6D00))
                        Text(text = "  $vote_average /10   $vote_count K votes" , fontSize = 12.sp, textAlign = TextAlign.End)
                    }

                }
            }
            Text(
                text = title, color = MaterialTheme.colors.onBackground,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 2.dp),
                style = TextStyle(fontWeight = FontWeight.Normal),
                minLines = 2
            )
            Text(
                text = overview,
                fontSize = 11.sp,
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier.padding(start = 2.dp, top = 5.dp),
                style = TextStyle(fontWeight = FontWeight.Thin),
                minLines = 2,
                maxLines = 3
            )
        }
    }
}