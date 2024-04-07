package presentation.screens

import domain.models.CharStates

data class TicTacToeState(
	val textStatus: String = "",
	val field: List<List<CharStates>> = listOf(
		listOf(CharStates.E, CharStates.E, CharStates.E),
		listOf(CharStates.E, CharStates.E, CharStates.E),
		listOf(CharStates.E, CharStates.E, CharStates.E),
	)
)