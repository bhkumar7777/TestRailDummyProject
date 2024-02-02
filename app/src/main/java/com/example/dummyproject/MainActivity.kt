package com.example.dummyproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.dummyproject.ui.theme.DummyProjectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import testrail.APIClient

class MainActivity : ComponentActivity() {

    private val apiClient = APIClient("https://XXXXX.testrail.com/").apply {
        user = ""
        password = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                getTestCaseDetails(2375)
                executeAddResultPostApi(2375)
                getResultOfParticularTest(2375)
            }
        }

        setContent {
            DummyProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private suspend fun getTestCaseDetails(testId: Int) {
        withContext(Dispatchers.IO) {
            val result = apiClient.sendGet("get_case/$testId")
        }
    }

    private suspend fun getResultOfParticularTest(testId: Int) {
        withContext(Dispatchers.IO) {
            val result = apiClient.sendGet("get_results/$testId")
        }
    }

    private suspend fun executeAddResultPostApi(testId: Int) {
        val data: MutableMap<Any, Any> = HashMap()
        data["status_id"] = 1
        data["comment"] = "This test worked fine!"
        apiClient.sendPost("add_result/$testId/1", data)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DummyProjectTheme {
        Greeting("Android")
    }
}
