package minesweeper
import java.util.* // import java util for scanner
import kotlin.random.Random // import the kotlin random fun to use random.nextInt()

val scanner = Scanner(System.`in`)
// todo split main into different functions or objects and methods for each task/stage of the game
fun main() {
// in the main ask for # of mines to place on the field
    println("How many mines do you want on the field ?")
    var numberOfMines = scanner.nextInt()
// set the hight and width of the play field. later use player input for this
    val hight = 9
    val width = 9
    // objects that can be on the field stored in an array
    val characters = charArrayOf('.', '1', '2', '3', '4', '5', '6', '7','8', 'X')
    val startingField = Array(hight) { CharArray(width) { characters[0] } } // creates a starting field

// place mines on the field
    while (numberOfMines != 0) {
        val x = Random.nextInt(hight)
        val y = Random.nextInt(width)
        if (startingField[x][y] != characters[9]) {
            startingField[x][y] = characters[9]
            --numberOfMines
        }
    }



// add number hints to the field
    for (i in 0 until hight) {
        for (y in 0 until width) {
            var hint = 0
            if (startingField[i][y] != characters[9]) {
                if (i == 0) {
                    when (y) {
                        0 -> { // top left corner
                            if (startingField[i][y + 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y + 1] == characters[9]) {
                                ++hint
                            }
                        }
                        8 -> { // top right corner
                            if (startingField[i][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y - 1] == characters[9]) {
                                ++hint
                            }
                        }
                        else -> { // top edge not corners
                            if (startingField[i][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i][y + 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i + 1][y + 1] == characters[9]) {
                                ++hint
                            }
                        }
                    }
                } else if (i == hight - 1) {
                    when (y) {
                        0 -> { // bottom left corner
                            if (startingField[i][y + 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y + 1] == characters[9]) {
                                ++hint
                            }
                        }
                        8 -> { // bottom right corner
                            if (startingField[i][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y - 1] == characters[9]) {
                                ++hint
                            }
                        }
                        else -> { // bottom edge not corners
                            if (startingField[i][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i][y + 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y - 1] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y] == characters[9]) {
                                ++hint
                            }
                            if (startingField[i - 1][y + 1] == characters[9]) {
                                ++hint
                            }
                        }
                    }

                } else {
                    if (i in 1..7) {
                        when (y) {
                            0 -> { // left edge not corners
                                if (startingField[i - 1][y] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i - 1][y + 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i][y + 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y + 1] == characters[9]) {
                                    ++hint
                                }
                            }
                            8 -> { // right edge not corners
                                if (startingField[i - 1][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i - 1][y] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y] == characters[9]) {
                                    ++hint
                                }
                            }
                            else -> { // all interior
                                if (startingField[i - 1][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i - 1][y] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i - 1][y + 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i][y + 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y - 1] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y] == characters[9]) {
                                    ++hint
                                }
                                if (startingField[i + 1][y + 1] == characters[9]) {
                                    ++hint
                                }
                            }
                        }

                    }
                }
                if (hint != 0) {
                    startingField[i][y] = characters[hint]
                }
            }
        }
    }
// output the new playing field
    for (i in 0 until hight) {
        println(startingField[i])
    }
}