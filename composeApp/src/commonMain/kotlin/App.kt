import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.screens.TicTacToeIntent
import presentation.screens.TicTacToeScreen
import presentation.screens.TicTacToeViewModel

@Composable
@Preview
fun App() {
    MaterialTheme {
        val ticTacToeViewModel: TicTacToeViewModel = getViewModel(Unit, viewModelFactory { TicTacToeViewModel() })

        LaunchedEffect(Unit){
            ticTacToeViewModel.handleIntents(TicTacToeIntent.OnStartGame)
        }

        val state by ticTacToeViewModel.state.collectAsState()
        val text by remember(state) { mutableStateOf(state.textStatus) }
        val field by remember(state) { mutableStateOf(state.field) }

        TicTacToeScreen(
            status = { text },
            field = { field },
            onClickField = { onClick ->
                ticTacToeViewModel.handleIntents(onClick)
            },
            onRestart = {
                ticTacToeViewModel.handleIntents(TicTacToeIntent.OnNewGame)
            }
        )
    }
}
