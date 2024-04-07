package presentation.screens

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.MainGame
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(): ViewModel(){

	private val _state = MutableStateFlow(TicTacToeState())

	val state = _state.asStateFlow()
	private val game = MainGame

	fun handleIntents(intents: TicTacToeIntent){
		when(intents){
			is TicTacToeIntent.OnClick -> click(intents)
			TicTacToeIntent.OnNewGame -> restartGame()
			TicTacToeIntent.OnStartGame -> startGame()
		}
	}

	private fun click(intents: TicTacToeIntent.OnClick) {
		game.click(intents.column, intents.row)
	}

	private fun startGame() {
		game.start()
	}

	private fun restartGame() {
		game.restart()
	}
	private val scope = CoroutineScope(Dispatchers.IO)
	init {
		combine(game.fieldState, game.textState){ newFieldState, newTextState ->
			_state.update { oldState ->
				oldState.copy(
					textStatus = newTextState,
					field = newFieldState
				)
			}
		}.launchIn(scope)
	}
}