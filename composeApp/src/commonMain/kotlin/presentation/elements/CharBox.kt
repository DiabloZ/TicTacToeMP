package presentation.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview
import utils.CharStates

@Composable
fun CharBox(
	charState: CharStates,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {

	Button(
		modifier = modifier.border(border = BorderStroke(2.dp, Color.Black)).fillMaxSize(),
		onClick = onClick,
		//contentAlignment = Alignment.Center,
	){
		Text(
			text = charState.char,
			textAlign = TextAlign.Center,
			modifier = modifier.wrapContentSize(),
			fontSize = 15.sp
		)
	}
}

@Preview
@Composable
private fun CharBoxPreview() {
	val listColors = listOf(Color.Red, Color.Green, Color.Magenta)
	Column {
		repeat(3){ _ ->
			Row(Modifier.weight(1f)) {
				repeat(3){ index ->
					CharBox(
						charState = CharStates.entries[index],
						onClick = {

						},
						modifier = Modifier.weight(1f).fillMaxSize()
					)
				}
			}
		}
	}
}