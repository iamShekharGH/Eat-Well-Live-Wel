import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.shekharhandigol.features.homeScreen.HomeScreenViewModel


@Composable
fun HomeScreen() {
    val vm: HomeScreenViewModel = hiltViewModel()
    val scope = rememberCoroutineScope()


    val aa = vm.state.collectAsStateWithLifecycle()



    Surface(


        modifier = Modifier.fillMaxSize()
    ) {

//        Text(text = "Home Screen")
        Text(text = aa.value)
        vm.getData()


    }
}
