package minesweeper

import java.util.* // import java util for scanner
import kotlin.random.Random // import the kotlin random fun to use random.nextInt()

val scanner = Scanner(System.`in`)
// objects that can be on the field stored in an array
val characters = charArrayOf('.', '1', '2', '3', '4', '5', '6', '7', '8', '*')
const val height = 9
const val width = 9
val gameField = Array(height) { CharArray(width) { '.' } } // creates a starting field
val coords = IntArray(3)
// game class holds the dif methods/fun to run the game as companion objects
class Game {
    companion object {
        fun placeMines(numOfMines: Int, field: Array<CharArray>): Array<CharArray> {
            var count = numOfMines
            while (count != 0) {
                val x = Random.nextInt(height)
                val y = Random.nextInt(width)
                if (field[x][y] != characters[9]) {
                    field[x][y] = characters[9]
                    --count
                }
            }
            return field
        }

        fun addHints(field: Array<CharArray>): Array<CharArray> {
            for (i in 0 until height) {
                for (y in 0 until width) {
                    var hint = 0
                    if (field[i][y] != characters[9]) {
                        if (i == 0) {
                            when (y) {
                                0 -> { // top left corner
                                    if (field[i][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                                8 -> { // top right corner
                                    if (field[i][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                                else -> { // top edge not corners
                                    if (field[i][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i + 1][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                            }
                        } else if (i == height - 1) {
                            when (y) {
                                0 -> { // bottom left corner
                                    if (field[i][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                                8 -> { // bottom right corner
                                    if (field[i][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                                else -> { // bottom edge not corners
                                    if (field[i][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y - 1] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y] == characters[9]) {
                                        ++hint
                                    }
                                    if (field[i - 1][y + 1] == characters[9]) {
                                        ++hint
                                    }
                                }
                            }

                        } else {
                            if (i in 1..7) {
                                when (y) {
                                    0 -> { // left edge not corners
                                        if (field[i - 1][y] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i - 1][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                    }
                                    8 -> { // right edge not corners
                                        if (field[i - 1][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i - 1][y] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y] == characters[9]) {
                                            ++hint
                                        }
                                    }
                                    else -> { // all interior
                                        if (field[i - 1][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i - 1][y] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i - 1][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y - 1] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y] == characters[9]) {
                                            ++hint
                                        }
                                        if (field[i + 1][y + 1] == characters[9]) {
                                            ++hint
                                        }
                                    }
                                }

                            }
                        }
                        if (hint != 0) {
                            field[i][y] = characters[hint]
                        }
                    }
                }
            }
            return field
        }

        fun displayedField(): Array<CharArray> {
            val displayField = Array(height) { CharArray(width) }
            for (x in 0 until height) {
                for (y in 0 until width) {
                    if (gameField[x][y] == characters[9]) {
                        displayField[x][y] = characters[0]
                    } else {
                        displayField[x][y] = gameField[x][y]
                    }
                }
            }
            return displayField
        }

        fun flagPlacement(x: Int, y: Int, field: Array<CharArray>): Char {
            // should work if x y are already tested and valid
            return if (field[x] [y] == characters[0]) {
                characters[9]
            } else {
                characters[0]
            }
        }

        fun display(displayField: Array<CharArray>) {
            println(" |123456789|")
            println("-|---------|")
            for (i in 0 until height) {
                print(i + 1)
                print("|")
                print(displayField[i])
                println("|")
            }
            println("-|---------|")
        }

        fun test(field: Array<CharArray>): Int {
            val test = IntArray(height)
            var trueCount = 0
            for (i in 0 until height) {
                if (gameField[i].contentEquals(field[i])) {
                    test[i] = 1
                } else test[i] = 0
                trueCount += test[i]
            }
            return trueCount
        }

        fun getCoords() {
            // test the coordinates and get new ones if necessary
            print("Set/delete mines marks (x and y coordinates): ")
            val y = scanner.nextInt()
            val x = scanner.nextInt()
            if (x <= height && y <= width) {
                if (gameField[x - 1][y - 1] == characters[0] || gameField[x - 1][y - 1] == characters[9]) {
                    coords[0] = x
                    coords[1] = y
                    coords[2] = 1 //true
                } else {
                    coords[2] = 0 // false
                }
            } else {
                coords[2] = 0 // false
            }

        }

    }
}

fun main() {

// in the main ask for # of mines to place on the field
    print("How many mines do you want on the field? ")
    val numberOfMines = scanner.nextInt()
    // todo set the hight and width of the play field. later use player input for this
    // place mines on the field
    Game.placeMines(numberOfMines, gameField)
    // add number hints to the field
    Game.addHints(gameField)
    // hide mines for display of field
    val displayField = Game.displayedField()
    // game loop
    var count = 0 // a counter to use as a test to brake loop if all tests are true count will = hight
    while (count != height) {
        Game.display(displayField) // displays the current displayField w/ mines hidden
        coords[2] = 0
        while (coords[2] != 1) {
            Game.getCoords() // get coordinates from player
        }

        // places or removes flag @ coordinates
        displayField[coords[0] - 1] [coords[1] - 1] = Game.flagPlacement(coords[0] - 1, coords[1] - 1, displayField)
        count = Game.test(displayField) // tests displayField vs gameField
    }
    Game.display(displayField)
    println("Congratulations! You found all the mines!") //game over output
}