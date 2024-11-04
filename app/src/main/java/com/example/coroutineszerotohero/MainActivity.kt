package com.example.coroutineszerotohero


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.lifecycleScope
import com.example.coroutineszerotohero.ui.theme.CoroutinesZeroToHeroTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var retrofit = RetrofitHelper.getInstance()
        lifecycleScope.launch(Dispatchers.IO) {
            var resultado : Response<SuperHeroDataResponse> = retrofit.getSuperheroes("a")
            withContext(Dispatchers.Main){
                if (resultado.isSuccessful){
                    Log.i("ejemplo", "$resultado")

                    Toast.makeText(this@MainActivity, "Funciona",Toast.LENGTH_SHORT).show()

                    Log.i("ejemplo", resultado.body().toString())
                }
            }
        }
        setContent {
            CoroutinesZeroToHeroTheme {
                Surface{
                    SuperheroList()
                }

            }
        }
    }
}

@Composable
fun SuperheroList() {

    Text(
        text = ""
    )
}