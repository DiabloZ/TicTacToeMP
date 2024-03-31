package presentation.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HeaderOfGame(
	text: () -> String,
	onNewGameClickListener: () -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.padding(vertical = 16.dp)
			.height(IntrinsicSize.Min),
	) {
		Button(onClick = onNewGameClickListener){
			Text("Новая игра")
		}
		Spacer(modifier = Modifier.padding(4.dp))
		Text(
			text = text.invoke(),
			modifier = Modifier.weight(1f)
				.fillMaxHeight()
				.border(border = BorderStroke(1.dp, Color.Black)),
			fontSize = 14.sp,
			textAlign = TextAlign.Center
		)
	}
}

@Preview
@Composable
fun HeaderOfGamePreview() {
	HeaderOfGame(
		text = { -> "" },
		onNewGameClickListener = { -> },
		modifier = Modifier

	)
}