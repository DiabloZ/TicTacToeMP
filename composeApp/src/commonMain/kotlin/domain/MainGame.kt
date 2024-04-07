package domain

import domain.models.CharStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object MainGame : ResultListener {

	private val gameLogic: GameLogic = GameLogic()

	private val _fieldState: MutableStateFlow<List<List<CharStates>>> = MutableStateFlow(
		listOf(
			listOf(CharStates.E, CharStates.E, CharStates.E),
			listOf(CharStates.E, CharStates.E, CharStates.E),
			listOf(CharStates.E, CharStates.E, CharStates.E),
		)
	)
	private val _textState = MutableStateFlow("")

	val textState = _textState.asStateFlow()
	val fieldState = _fieldState.asStateFlow()
	fun start() {
		gameLogic.subscribeOnResultListener(this)
		gameLogic.startNewGame()
	}

	fun restart() {
		gameLogic.startNewGame()
	}

	fun click(column: Int, row: Int) {
		gameLogic.putTurn(column, row)
	}

	override fun listenResult(array: Array<Array<CharStates>>) {
		val list = array.map { it.asList() }
		println("emit")
		list.forEach {
			println(it)
		}
		_fieldState.update { list }
	}

	override fun listenMessage(text: String) {
		_textState.update { text }
	}
}
