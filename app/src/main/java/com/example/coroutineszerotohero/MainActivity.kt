package com.example.coroutineszerotohero

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

        // Estado para almacenar la lista de álbumes
        var albums by mutableStateOf<List<Album>>(emptyList())

        // Llamada a la API para obtener la lista de álbumes
        lifecycleScope.launch(Dispatchers.IO) {
            val resultado: Response<List<Album>> = RetrofitHelper.apiService.getAlbums()
            withContext(Dispatchers.Main) {
                if (resultado.isSuccessful) {
                    Log.i("ejemplo", "Respuesta de API exitosa")
                    albums = resultado.body() ?: emptyList()  // Actualizar la lista de álbumes

                    Toast.makeText(this@MainActivity, "Funciona", Toast.LENGTH_SHORT).show()
                } else {
                    Log.i("ejemplo", "Error en la respuesta: ${resultado.errorBody()}")
                }
            }
        }

        // Mostrar los álbumes en la interfaz
        setContent {
            CoroutinesZeroToHeroTheme {
                Surface {
                    AlbumList(albums = albums)
                }
            }
        }
    }
}

@Composable
fun AlbumList(albums: List<Album>) {
    // Usar LazyColumn para mostrar la lista de álbumes
    LazyColumn {
        items(albums) { album ->
            AlbumItem(album)
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Column {
        Text(text = "ID: ${album.id}")
        Text(text = "Usuario: ${album.userId}")
        Text(text = "Título: ${album.title}")
        Text(text = "--------------------------")
    }
}
