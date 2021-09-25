import kotlin.random.Random

class BoardState(val state: Array<IntArray>) {

    override fun equals(other: Any?): Boolean {
        return if (other is BoardState) {
            other.state.contentDeepToString() == state.contentDeepToString()
        } else false
    }

    override fun toString(): String {
        return state.contentDeepToString()
    }

    fun cloneState(): Array<IntArray> {
        val newState = state.copyOf()
        return newState.map { it.copyOf() }.toTypedArray()
    }

    fun isSorted(): Boolean {
        val s = state.map { it.toTypedArray() }.toTypedArray().flatten().toTypedArray()
        val final = s.indices.toList().toTypedArray()
        return s.contentEquals(final)
    }

    fun move(direction: Direction): BoardState {
        return when (direction) {
            Direction.UP -> moveUp()
            Direction.DOWN -> moveDown()
            Direction.LEFT -> moveLeft()
            Direction.RIGHT -> moveRight()
        }
    }

    private fun moveLeft(): BoardState {
        return findZero()?.let {
            if (it.second == 0) {
                BoardState(cloneState())
            } else {
                val newState = cloneState()
                newState[it.first][it.second] = state[it.first][it.second - 1]
                newState[it.first][it.second - 1] = 0
                BoardState(newState)
            }
        } ?: BoardState(cloneState())
    }

    private fun moveRight(): BoardState {
        return findZero()?.let {
            if (it.second == state[0].size - 1) {
                BoardState(cloneState())
            } else {
                val newState = cloneState()
                newState[it.first][it.second] = state[it.first][it.second + 1]
                newState[it.first][it.second + 1] = 0
                BoardState(newState)
            }
        } ?: BoardState(cloneState())
    }

    private fun moveUp(): BoardState {
        return findZero()?.let {
            if (it.first == 0) {
                BoardState(cloneState())
            } else {
                val newState = cloneState()
                newState[it.first][it.second] = state[it.first - 1][it.second]
                newState[it.first - 1][it.second] = 0
                BoardState(newState)
            }
        } ?: BoardState(cloneState())
    }

    private fun moveDown(): BoardState {
        return findZero()?.let {
            if (it.first == state.size - 1) {
                BoardState(cloneState())
            } else {
                val newState = cloneState()
                newState[it.first][it.second] = state[it.first + 1][it.second]
                newState[it.first + 1][it.second] = 0
                BoardState(newState)
            }
        } ?: BoardState(cloneState())
    }

    private fun findZero(): Pair<Int, Int>? {
        state.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, col -> if (col == 0) return Pair(rowIndex, colIndex) }
        }
        return null
    }

    companion object {

        fun randomBoardState(): BoardState {
            val random = Random.Default
            val rowSize = random.nextInt(1, 10)
            val colSize = random.nextInt(1, 10)
            val ints = (0 until (rowSize * colSize)).shuffled().chunked(colSize).map { it.toIntArray() }.toTypedArray()
            return BoardState(ints)
        }

    }
}