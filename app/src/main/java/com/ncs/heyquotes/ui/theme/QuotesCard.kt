package com.ncs.heyquotes.ui.theme

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.ncs.heyquotes.MainActivity
import com.ncs.heyquotes.R
import com.ncs.heyquotes.models.Quotes
import dagger.hilt.android.qualifiers.ActivityContext

@Composable
fun resbanner(
    items: List<Quotes>,
    intitalSelectedItem: Int = 0
) {
    var clicked by remember {
        mutableStateOf(intitalSelectedItem)
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        items.forEachIndexed { index, bannerContent ->
            resbannerItem(item = bannerContent, state = index == clicked) {
                clicked = index
            }
        }



    }
}

@Composable
fun resbannerItem(
    item: Quotes,
    state: Boolean = false,
    onItemClick: () -> Unit
) {
    var isClicked by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .height(500.dp)
        .width(350.dp)) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .width(350.dp)
                .padding(15.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.quote),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .size(40.dp)
                        .rotate(180F)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Column(Modifier.width(250.dp)) {
                    Text(text = item.text, color = Color.DarkGray, fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(30.dp))
                    Box(contentAlignment = Alignment.CenterEnd, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "~${item.author}",
                            color = Color.Gray,
                            fontSize = 15.sp
                        )

                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = {
                    isClicked = true
                })
                .padding(top = 360.dp, start = 250.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.DarkGray)
            ) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(45.dp)
                        .padding(7.dp)
                )
            }
        }

    }
    if (isClicked){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "A great quote for you : \n ${item.text} \n Download the app here \n https://rb.gy/2vjg2")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        val context = LocalContext.current
        context.startActivity(shareIntent)    }
}

