package com.mahmoud.movieapp.shared.presention
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mahmoud.movieapp.shared.model.Movie
import org.jetbrains.compose.resources.painterResource
import pp.composeapp.generated.resources.Res
import pp.composeapp.generated.resources.download

@Composable
fun MovieScreen() {
    val vm:MovieViewModel = viewModel()

    val movies by vm.movies.collectAsState(initial = emptyList())
    val isLoading by vm.isLoading.collectAsState(initial = false)
    val error by vm.error.collectAsState(initial = null)
    Scaffold(
        topBar = {
            TopBar()

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                ,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(40.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant
                        )
                    }
                }
                error != null -> {
                    Text(" Error: $error", color = Color.Red, style = MaterialTheme.typography.bodyLarge)
                    Button(onClick = { vm.getMovies() }) {
                        Text("Retry")
                    }
                }
                movies.isEmpty() -> {
                    Text("No movies found", style = MaterialTheme.typography.bodyLarge)
                }
                else -> {



                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(movies) { movie ->
                            ElevatedCardExample(movie)
//                            MovieItemCard(movie)
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "MovieScreen")
@Composable
private fun PreviewMovieScreen() {
    MovieScreen()
}



@Composable
fun ElevatedCardExample(movie: Movie) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {

            // Image
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.director,
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(10.dp)),
                error=painterResource(Res.drawable.download)
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Content
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Column {
                    Text(
                        text = movie.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = movie.year)
                        Text(text = movie.released)
                    }
                }

                Chip(movie.genre)
            }
        }
    }
}
@Composable
fun Chip(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color(0xFF4A2A24))
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  TopBar(){

    TopAppBar( colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Black
    ), title = {
        Text(text = " Movies", style = MaterialTheme.typography.headlineMedium, color = Color.White)
    })
}
