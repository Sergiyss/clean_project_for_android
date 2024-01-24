package ua.dev.webnauts.cleanproject.test_project

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.rememberAsyncImagePainter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import ua.dev.webnauts.cleanproject.network.ktor.NetworkResponse
import ua.dev.webnauts.cleanproject.network.ktor.ServiceApi
import javax.inject.Inject

data class DownloadImage(val itemId : Int, val imageLink : String)

val imageList = listOf<String>(
    "https://images.pexels.com/photos/943907/pexels-photo-943907.jpeg?cs=srgb&dl=pexels-irina-iriser-943907.jpg&fm=jpg&_gl=1*14j0rgn*_ga*MTMwMzY4NjQwNy4xNzA1NjkwMzI1*_ga_8JE65Q40S6*MTcwNTY5MDMyNC4xLjEuMTcwNTY5MDMzNC4wLjAuMA..",
    "https://images.pexels.com/photos/405202/pexels-photo-405202.jpeg?cs=srgb&dl=pexels-norja-vanderelst-405202.jpg&fm=jpg&_gl=1*14j0rgn*_ga*MTMwMzY4NjQwNy4xNzA1NjkwMzI1*_ga_8JE65Q40S6*MTcwNTY5MDMyNC4xLjEuMTcwNTY5MDMzNC4wLjAuMA..",
    "https://wallpapershq.ru/wallpapers/1092_81ff88b22f505de528467694924fb49d4ad9885db513a3e6ececaca15bc92ae7_porsche-911-sports-car/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/5216_0d33702a8f2dfc89586fc3304d62dde84b5f6f0a9132d302103b633147928ea7_forest-nature/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/5786_54066935c6c853961fdf669e621bd426994cfd991fb951cd126a14d002324d6b_lake-in-the-mountains/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/1252_797687cf1098e5f771c4756dad87ce7a6c6a95929d14fb8f4d06359a25af6d33_lake-moraine-landscape-mountains/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/147_7b36d3c450d3effa2e830d41d8049931614dedfb964ce270043c5c82eb4c6939_boat-sea-asia/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/7363_7017f71af8aa47e3b4d1b167e190d68a4dd1b3358cbfa905b4013a22f781b36e_burfell-gorge/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/2298_5857ce958648cb69d6ab3731bbe032c81ed7db1b3dab8100044f9d04f7a624e5_north-lake-california-usa-north-lake-that-united-states-of-america-road-eastern-sierra-outdoors-freeway-highway-mountain-range-ground-asphalt/7680x4320/download",
    "https://wallpapershq.ru/wallpapers/7596_159372231b6ca206e4d8184c484d7cc5f0d31b43296fb3f6ba59ad6e66025981_sunset-in-the-field/7680x4320/download",
)
@HiltViewModel
class BigFileViewModel  @Inject constructor(
    val serviceApi : ServiceApi
) : ViewModel(){
    private val channel = Channel<DownloadImage>()

    var loadedImagesList  = mutableStateOf<List<DownloadImage>?>(null)
        private set

    init{
        CoroutineScope(Dispatchers.Default).launch {
            for (operation in channel) {
                CoroutineScope(Dispatchers.Default).async {
                    serviceApi.downloadFileLink(operation.imageLink).collect{response->
                        if(response is NetworkResponse.Success){
                            updateItem(DownloadImage(operation.itemId, response.data))
                        }
                    }
                }
            }
        }
    }

    private fun updateItem(downloadImage: DownloadImage) {
        loadedImagesList .value = loadedImagesList .value.orEmpty() + downloadImage
    }
    fun downloadPhoto(itemId : Int, url : String){
        viewModelScope.launch {
            channel.send(DownloadImage(itemId, url))
        }
    }
}

@Composable
fun BigFileList(bigFileViewModel : BigFileViewModel = hiltViewModel()){
    LazyColumn(content = {
        items(1){
            FlowRowItem(bigFileViewModel)
        }
    })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowItem( bigFileViewModel : BigFileViewModel){

    FlowRow(
        modifier = Modifier
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        for(item in 0..23){
            ItemPhoto((0..10000).random(), imageList.random(), bigFileViewModel)
        }
    }
}

@Composable
fun ItemPhoto(itemId : Int, setLink : String, bigFileViewModel : BigFileViewModel){
    val link = remember { mutableStateOf<String?>( null ) }
    val updateList by bigFileViewModel.loadedImagesList

    LaunchedEffect(key1 = Unit, block = {
        bigFileViewModel.downloadPhoto(itemId, setLink)
    })

    LaunchedEffect(key1 = updateList, block = {
        updateList?.forEach {item->
            if(item.itemId == itemId){
                link.value = item.imageLink
            }
        }
    })

    link.value?.let {url->
        Image(
            painter = rememberAsyncImagePainter(
                url,
                filterQuality = FilterQuality.None,
            ),
            contentDescription = null,
            modifier = itemModifier2,
            contentScale = ContentScale.Crop
        )
    } ?: run {
        Box(contentAlignment = Alignment.Center) {
           Column(
               modifier = itemModifier2,
               verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               CircularProgressIndicator()
               Text("$itemId")
           }
        }
    }
}


val itemModifier2 = Modifier
    .padding(4.dp)
    .fillMaxWidth(0.31f)
    .height(100.dp)
    .clip(RoundedCornerShape(8.dp))
    .background(Color(0xFFF0F0F0))