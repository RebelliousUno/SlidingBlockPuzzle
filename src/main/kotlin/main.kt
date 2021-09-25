import java.util.*

enum class SearchType {
    DEPTH, BREADTH
}

fun main(args: Array<String>) {
//    val b = BoardState.randomBoardState()
    val b = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8)))

    println(b)
    boardStateStack.push(b)
    boardStateQueue.add(b)
    if (nonRecurisveSearch(SearchType.DEPTH)) {
        println(testedBoardState.size)
    } else {
        println("Not Solvable")
    }
}


val boardStateStack = Stack<BoardState>()
val testedBoardState = ArrayList<BoardState>()
val boardStateQueue = ArrayList<BoardState>()

fun nonRecurisveSearch(searchType: SearchType): Boolean {
    var stackSize: Int = 1
    var increasing: Boolean
    while (boardStateQueue.isNotEmpty() && boardStateStack.isNotEmpty()) {

        val stateToTest = if (searchType == SearchType.DEPTH) {
            increasing = (boardStateStack.size > stackSize)
            stackSize = boardStateStack.size
            boardStateStack.pop()
        } else {
            increasing = (boardStateQueue.size > stackSize)
            stackSize = boardStateQueue.size
            boardStateQueue.removeFirst()
        }
        println("Increasing: $increasing boardStateQueue: ${boardStateQueue.size} boardStateStack: ${boardStateStack.size} testedBoardState: ${testedBoardState.size}")
        testedBoardState.add(stateToTest)
        if (stateToTest.isSorted()) return true
        makeMoves(stateToTest, searchType)
    }
    return false
}

fun recursiveSearch(searchType: SearchType): Boolean {
    //println("boardStateQueue: ${boardStateQueue.size} boardStateStack: ${boardStateStack.size} testedBoardState: ${testedBoardState.size}")
    if (boardStateStack.isEmpty() || boardStateQueue.isEmpty()) return false
    val stateToTest = if (searchType == SearchType.DEPTH) boardStateStack.pop() else boardStateQueue.removeFirst()
    testedBoardState.add(stateToTest)
    if (stateToTest.isSorted()) return true
    makeMoves(stateToTest, searchType)
    return recursiveSearch(searchType)
}

fun makeMoves(boardState: BoardState, searchType: SearchType) {
    //println("Searching $boardState")
    Direction.values().toList().shuffled().forEach {
        val newState = boardState.move(it)
        if (!(boardState == newState || testedBoardState.contains(newState))) {
            when (searchType) {
                SearchType.DEPTH -> if (!boardStateStack.contains(newState)) boardStateStack.push(newState)
                SearchType.BREADTH -> if (!boardStateQueue.contains(newState)) boardStateQueue.add(newState)
            }
        }
    }
}


