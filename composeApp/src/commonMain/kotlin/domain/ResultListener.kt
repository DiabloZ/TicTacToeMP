package domain

import domain.models.CharStates

interface ResultListener {
	fun listenResult(array: Array<Array<CharStates>>)
	fun listenMessage(text: String)
}
