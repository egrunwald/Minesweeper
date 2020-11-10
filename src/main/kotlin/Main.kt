package minesweeper
import java.util.* // import java util for scanner
import kotlin.random.Random // import the kotlin random fun to use random.nextInt()

val scanner = Scanner(System.`in`)

fun main() {
// in the main ask for # of mines to place on the field
    println("How many mines do you want on the field ?")
    var numberOfMines = scanner.nextInt()
// set the hight and width of the play field. later use player input for this
    val hight = 9
    val width = 9
    val characters = charArrayOf('.', 'X') // stores the 2 objects that can be on the field in an array
    val startingField = Array(hight) { CharArray(width) { characters[0] } } // creates a starting field

// place mines on the field
    while (numberOfMines != 0) {
        val x = Random.nextInt(hight)
        val y = Random.nextInt(width)
        if (startingField[x][y] != characters[1]) {
            startingField[x][y] = characters[1]
            --numberOfMines
        }
    }

// output the new playing field
    for (i in 0 until hight) {
        println(startingField[i])
    }
}