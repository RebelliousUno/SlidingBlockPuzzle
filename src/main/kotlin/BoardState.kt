class BoardState(val state: Array<IntArray>) {

    override fun equals(other: Any?): Boolean {
        return if (other is BoardState) {
            other.state.contentDeepToString() == state.contentDeepToString()
        } else false
    }

    override fun toString(): String {
        return state.contentDeepToString()
    }

    fun moveLeft(): BoardState {
        return findZero()?.let {
            if (it.second == 0) {
                BoardState(state)
            } else {
                val newState = state.clone()
                newState[it.first][it.second] = state[it.first][it.second - 1]
                newState[it.first][it.second - 1] = 0
                BoardState(newState)
            }
        } ?: BoardState(state)
    }

    fun moveRight(): BoardState {
        return findZero()?.let {
            if (it.second == state[0].size - 1) {
                BoardState(state)
            } else {
                val newState = state.clone()
                newState[it.first][it.second] = state[it.first][it.second + 1]
                newState[it.first][it.second + 1] = 0
                BoardState(newState)
            }
        } ?: BoardState(state)
    }

    fun moveUp(): BoardState {
        return findZero()?.let {
            if (it.first == 0) {
                BoardState(state)
            } else {
                val newState = state.clone()
                newState[it.first][it.second] = state[it.first - 1][it.second]
                newState[it.first - 1][it.second] = 0
                BoardState(newState)
            }
        } ?: BoardState(state)
    }

    fun moveDown(): BoardState {
        return findZero()?.let {
            if (it.first == state.size - 1) {
                BoardState(state)
            } else {
                val newState = state.clone()
                newState[it.first][it.second] = state[it.first + 1][it.second]
                newState[it.first + 1][it.second] = 0
                BoardState(newState)
            }
        } ?: BoardState(state)
    }

    private fun findZero(): Pair<Int, Int>? {
        state.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { colIndex, col -> if (col == 0) return Pair(rowIndex, colIndex) }
        }
        return null
    }
}