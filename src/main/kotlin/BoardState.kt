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
        val zero = findZero() ?: return BoardState(state)
        state[zero.first][zero.second]
        if (zero.second == 0) return BoardState(state)
        val newState = state.clone()
        newState[zero.first][zero.second] = state[zero.first][zero.second-1]
        newState[zero.first][zero.second-1] = 0
        return BoardState(newState)
    }

    fun moveRight(): BoardState  {
        val zero = findZero() ?: return BoardState(state)
        state[zero.first][zero.second]
        if (zero.second == state[0].size-1) return BoardState(state)
        val newState = state.clone()
        newState[zero.first][zero.second] = state[zero.first][zero.second+1]
        newState[zero.first][zero.second+1] = 0
        return BoardState(newState)
    }

    fun moveUp(): BoardState  {
        val zero = findZero() ?: return BoardState(state)
        state[zero.first][zero.second]
        if (zero.first == 0) return BoardState(state)
        val newState = state.clone()
        newState[zero.first][zero.second] = state[zero.first-1][zero.second]
        newState[zero.first-1][zero.second] = 0
        return BoardState(newState)
    }

    fun moveDown(): BoardState  {
        val zero = findZero() ?: return BoardState(state)
        state[zero.first][zero.second]
        if (zero.first == state.size-1) return BoardState(state)
        val newState = state.clone()
        newState[zero.first][zero.second] = state[zero.first+1][zero.second]
        newState[zero.first+1][zero.second] = 0
        return BoardState(newState)
    }

    private fun findZero(): Pair<Int, Int>? {
        //use state.filter for this...

        state.forEachIndexed {rowIndex, row ->
            row.forEachIndexed { colIndex, col -> if (col==0) return Pair(rowIndex, colIndex) }
        }
        return null
    }
}