package presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.elements.CharBox
import presentation.elements.HeaderOfGame
import domain.models.CharStates

@Composable
fun TicTacToeScreen(
	status: () -> String,
	field: () -> List<List<CharStates>>,
	onClickField: (TicTacToeIntent.OnClick) -> Unit,
	onRestart: () -> Unit,
) {

	Column(
		modifier = Modifier.padding(16.dp)
	) {
		HeaderOfGame(
			text = status,
			onNewGameClickListener = onRestart,
			modifier = Modifier
		)
		Column(
			modifier = Modifier.aspectRatio(1f)
		) {
			repeat(3){ columnIndex ->
				Row(Modifier.weight(1f)) {
					repeat(3){ rowIndex ->
						CharBox(
							charState = field.invoke()[columnIndex][rowIndex],
							onClick = {
								onClickField.invoke(
									TicTacToeIntent.OnClick(columnIndex, rowIndex)
								)
							},
							modifier = Modifier.weight(1f).fillMaxSize()
						)
					}
				}
			}
		}
	}

}