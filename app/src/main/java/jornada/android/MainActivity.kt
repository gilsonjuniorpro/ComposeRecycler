package jornada.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jornada.android.ui.theme.ComposeRecyclerTheme

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecyclerTheme {
                //loadMethodA()
                //loadMethodB()
                loadMethodC()
            }
        }
    }
}

@Composable
fun loadMethodA(){
    val repository = PersonRepository()
    val getAllData = repository.getAllData()

    LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        items(items = getAllData) { person ->
            CustomItem(person = person)
        }
    }
}

@Composable
fun loadMethodB(){
    val repository = PersonRepository()
    val getAllData = repository.getAllData()

    LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        itemsIndexed(
                items = getAllData,
                key = { index, person ->
                    person.id
                }
        ) { index, person ->
            Log.d("MainActivity", index.toString())
            CustomItem(person = person)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun loadMethodC(){
    val sections = listOf("A", "B", "C", "D")

    LazyColumn(
            contentPadding = PaddingValues(all = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        sections.forEach { section ->
            stickyHeader {
                Text(
                        modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .padding(12.dp),
                        text = "Section $section"
                )
            }
            items(5) {
                Text(
                        modifier = Modifier.padding(12.dp),
                        text = "Item $it from the section $section"
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeRecyclerTheme {
        Greeting("Android")
    }
}