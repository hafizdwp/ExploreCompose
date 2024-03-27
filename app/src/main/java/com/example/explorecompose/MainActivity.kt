package com.example.explorecompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.explorecompose.ui.theme.ExploreComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    //TODO: hoist
    var shouldShowOnboarding by remember {
        mutableStateOf(true)
    }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(
                    onContinueClicked = {
                        shouldShowOnboarding = false
                    }
            )
        } else {
            GreetingsScreen()
        }
    }
}

@Composable
fun GreetingsScreen(modifier: Modifier = Modifier,
                    names: List<String> = mutableListOf<String>().apply {
                        repeat(5) {
                            this.add("Nama: $it")
                        }
                    }) {
    Column(modifier) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String,
             modifier: Modifier = Modifier) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val extraPadding = if (expanded) 48.dp else 24.dp

    Surface(color = MaterialTheme.colorScheme.primary,
            modifier = modifier.padding(4.dp)) {
        Row(modifier = modifier.padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp,
                bottom = extraPadding
        )) {
            Surface(color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.weight(1f)) {
                Column {
                    Text(
                            text = "Hello!",
                    )
                    Text(text = name)
                }
            }

            ElevatedButton(
                    onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show Less" else "Show More")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ExploreComposeTheme {
        Greeting("Android")
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier,
                     onContinueClicked: () -> Unit) {
    Surface(color = MaterialTheme.colorScheme.error) {
        Column(modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Welcome to the Basics Codelab!")
            ElevatedButton(onClick = {
                onContinueClicked()
            }) {
                Text(text = "Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ExploreComposeTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}