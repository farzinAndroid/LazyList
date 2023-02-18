package com.farzin.lazylist

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.farzin.lazylist.ui.theme.LazyListTheme

class MainActivity : ComponentActivity() {

    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        itemArray = resources.getStringArray(R.array.car_array)

        super.onCreate(savedInstanceState)
        setContent {
            LazyListTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {

                    MainScreen(array = itemArray as Array<String>)

                }
            }
        }
    }
}

@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {

    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onItemClick(item) }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            ImageLoader(logoName = item)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier.padding(8.dp)
            )

        }

    }

}

@Composable
fun MainScreen(array: Array<String>) {

    val context = LocalContext.current

    LazyColumn {

        items(array) { model ->
            MyListItem(item = model, onItemClick = {
                Toast.makeText(
                    context,
                    model,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageLoader(logoName: String) {

    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/" +
            logoName.substringBefore(" ") + "_logo.png"

    Image(
        painter = rememberImagePainter(url),
        contentDescription = "",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(75.dp),
    )

}

