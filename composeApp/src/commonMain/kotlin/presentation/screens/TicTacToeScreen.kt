package presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.elements.CharBox
import presentation.elements.HeaderOfGame
import utils.CharStates

@Composable
fun TicTacToeScreen() {

	Column(
		modifier = Modifier.padding(16.dp)
	) {
		HeaderOfGame(
			text = { "Какой-то статус" },
			onNewGameClickListener = {

			},
			modifier = Modifier
		)
		Column {
			repeat(3){ columnIndex ->
				Row(Modifier.weight(1f)) {
					repeat(3){ rowIndex ->
						CharBox(
							charState = CharStates.entries[rowIndex],
							onClick = {
								columnIndex + rowIndex
							},
							modifier = Modifier.weight(1f).fillMaxSize()
						)
					}
				}
			}
		}
	}

}