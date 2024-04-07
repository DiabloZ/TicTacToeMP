package domain

import domain.models.CharStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameLogic {
	private lateinit var map: Array<Array<CharStates>>
	private val size = 3
	private var resultListener: ResultListener? = null
	private val defaultValueNumber = 999

	private val coordinatesChanel = Channel<Pair<Int, Int>> {  }

	private val scope = CoroutineScope(Dispatchers.IO)
	private var currentJob: Job? = null
	fun subscribeOnResultListener(resultListener: ResultListener?) {
		this.resultListener = resultListener
	}

	private suspend fun runGame() {
		initMap()
		printMap()
		while (emptyFields()) {
			turnHuman()
			if (checkWinSymbol(CharStates.X)) {
				winner()
				break
			} else {
				turnComp()
				if (checkWinSymbol(CharStates.O)) {
					winner()
					break
				}
			}
			if (!emptyFields()) {
				showMessage("ЭТО НИЧЬЯ!")
				break
			}
		}
	}

	fun putTurn(x: Int, y: Int) {
		coordinatesChanel.trySend(Pair(y, x))
	}

	fun startNewGame() {
		currentJob = scope.launch {
			runGame()
		}
	}

	private fun showMessage(message: String) {
		resultListener?.listenMessage(message)
	}

	private fun winner() {
		if (checkWinSymbol(CharStates.X)) {
			showMessage("Игрок победил")
		}
		if (checkWinSymbol(CharStates.O)) {
			showMessage("Увы! Компьютер победил!")
		}
	}

	private fun emptyFields(): Boolean {
		var result = true
		var count = 0
		for (i in 0 until size) {
			for (j in 0 until size) {
				if (map[i][j] == CharStates.X || map[i][j] == CharStates.O) {
					count++
				}
			}
			if (count == size * size) {
				result = false
			}
		}
		return result
	}

	private fun initMap() {
		map = Array(
			size = size,
			init = { _ ->
				Array(
					size = size,
					init = { CharStates.E}
				)
			}
		)
	}

	private fun printMap() {
		for (i in 0..size) {
			print(i.toString() + "\t")
		}
		println()
		for (i in 0 until size) {
			print((i + 1).toString() + "\t")
			for (j in 0 until size) {
				print(map[i][j].toString() + "\t")
			}
			println()
		}
		resultListener?.listenResult(map)
	}

	private suspend fun turnHuman() {
		showMessage("Ход игрока")
		var one: Int
		var two: Int
		do {
			val res = coordinatesChanel.receive()
			one = res.first
			two = res.second
		} while (!cellValid(two, one))
		map[two][one] = CharStates.X
		printMap()
	}

	private suspend fun turnComp() {
		var rd: Int
		var st: Int
		showMessage("Ход компьютера")
		do {
			rd = Random.nextInt(size)
			st = Random.nextInt(size)
		} while (!cellValid(rd, st))
		delay(100)
		map[rd][st] = CharStates.O
		printMap()
	}

	private fun cellValid(rd: Int, st: Int): Boolean {
		if (rd == defaultValueNumber || st == defaultValueNumber) return false
		var result = true
		if (map[rd][st] == CharStates.X || map[rd][st] == CharStates.O) {         // Проверяет занятость ячейки. Проверить нужно ли -1.
			result = false
		}
		if (rd > size || st > size) {              // Проверяет превышение размера
			result = false
		}
		return result
	}

	private fun checkWinSymbol(playerSymbol: CharStates): Boolean {
		return checkColumn(playerSymbol) || checkRows(playerSymbol) || checkDiagonal(playerSymbol) || checkDiagonalNegative(
			playerSymbol
		)
	}

	private fun checkDiagonalNegative(playerSymbol: CharStates): Boolean {
		var diagonalCounterNeg = 0
		for (i in 0 until size) {
			var j = 0
			var x = map.size - 1
			while (j < map.size) {
				if (i == x && map[i][j] == playerSymbol) {
					diagonalCounterNeg++
				}
				j++
				x--
			}
		}
		return diagonalCounterNeg == size
	}

	private fun checkDiagonal(playerSymbol: CharStates): Boolean {
		var diagonalCounter = 0
		for (i in 0 until size) {
			for (j in map.indices) {
				if (i == j && map[i][j] == playerSymbol) {
					diagonalCounter++
				}
			}
		}
		return diagonalCounter == size
	}

	private fun checkRows(playerSymbol: CharStates): Boolean {
		for (i in 0 until size) {
			var rowCounter = 0
			for (j in map.indices) {
				if (map[i][j] == playerSymbol) {            // условие, для присвоения результата
					rowCounter++
				}
			}
			if (rowCounter == size) {
				return true
			}
		}
		return false
	}

	private fun checkColumn(playerSymbol: CharStates): Boolean {
		for (i in 0 until size) {
			var colCounter = 0
			for (j in map.indices) {
				if (map[j][i] == playerSymbol) {            // условие, для присвоения результата
					colCounter++
				}
			}
			if (colCounter == size) {
				return true
			}
		}
		return false
	}


}
