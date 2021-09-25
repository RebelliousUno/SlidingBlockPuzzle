import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class TestBoardState {


    @Test
    fun `board state produces an actual copy`() {
        val state = BoardState(arrayOf(intArrayOf(5, 1, 2), intArrayOf(4, 0, 6), intArrayOf(7, 8, 9)))
        Direction.values().forEach {
            val state2 = state.move(it)
            assertNotEquals(state.toString(), state2.toString(), "Direction was $it")
        }

    }

    @Test
    fun `board states are equal`() {
        val state = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val state2 = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        assertEquals(state, state2)
    }

    @Test
    fun `test move up with no space`() {
        val state = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.UP)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move up with space`() {
        val state = BoardState(arrayOf(intArrayOf(4, 1, 2), intArrayOf(0, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.UP)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move right with no space`() {
        val state = BoardState(arrayOf(intArrayOf(1, 2, 0), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(1, 2, 0), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.RIGHT)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move right with space`() {
        val state = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.RIGHT)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move down with no space`() {
        val state = BoardState(arrayOf(intArrayOf(9, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0)))
        val expectedState = BoardState(arrayOf(intArrayOf(9, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0)))
        val newState = state.move(Direction.DOWN)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move down with space`() {
        val state = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(1, 5, 2), intArrayOf(4, 0, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.DOWN)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move left with no space`() {
        val state = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.LEFT)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test move left with space`() {
        val state = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val expectedState = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        val newState = state.move(Direction.LEFT)
        assertEquals(expectedState, newState)
    }

    @Test
    fun `test isSorted false`() {
        val state = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        assertFalse(state.isSorted())
    }

    @Test
    fun `test isSorted true`() {
        val state = BoardState(arrayOf(intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8)))
        assertTrue(state.isSorted())
    }

/*  To test findZero independently but required findZero to be public...
    @Test
    fun `test findZero`() {
        val start = System.currentTimeMillis()
        var state = BoardState(arrayOf(intArrayOf(1, 0, 2), intArrayOf(4, 5, 6), intArrayOf(7, 8, 9)))
        var zero = state.findZero()
        var expected = Pair(0, 1)
        assertEquals(expected, zero)

        state = BoardState(arrayOf(intArrayOf(1, 4, 2), intArrayOf(4, 0, 6), intArrayOf(7, 8, 9)))
        zero = state.findZero()
        expected = Pair(1, 1)
        assertEquals(expected, zero)

        state = BoardState(arrayOf(intArrayOf(1, 3, 2), intArrayOf(4, 5, 0), intArrayOf(7, 8, 9)))
        zero = state.findZero()
        expected = Pair(1, 2)
        assertEquals(expected, zero)

        state = BoardState(arrayOf(intArrayOf(1, 3, 2), intArrayOf(4, 5, 6), intArrayOf(0, 8, 9)))
        zero = state.findZero()
        expected = Pair(2, 0)
        assertEquals(expected, zero)
        val stop = System.currentTimeMillis()
        println(stop-start)
    }
*/

}