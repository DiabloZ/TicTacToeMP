package presentation.screens

sealed interface TicTacToeIntent {
	data object OnStartGame: TicTacToeIntent
	data object OnNewGame: TicTacToeIntent
	class OnClick(val column: Int, val row: Int): TicTacToeIntent
}