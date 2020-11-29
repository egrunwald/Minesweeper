package minesweeper

import java.util.* // import java util for scanner
import kotlin.random.Random // import the kotlin random fun to use random.nextInt()

val scanner = Scanner(System.`in`)
// objects that can be on the field stored in an array
val characters = charArrayOf('.', '1', '2', '3', '4', '5', '6', '7', '8', '*', '/')
const val height = 9
const val width = 9
val gameField = Array(height) { CharArray(width) { characters[10] } } // creates a starting field
val displayField = Array(height) { CharArray(width) { characters[0] } } // hide mines for display of field
val coords = IntArray(4)
var counter = 0
var stepOnMine = false
class Free(var x: Int, var y: Int)
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

        fun flagPlacement(x: Int, y: Int, field: Array<CharArray>): Char {
            // should work if x y are already tested and valid
            return if (field[x][y] == characters[0]) {
                characters[9]
            } else {
                characters[0]
            }
        }

        fun display() {
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

        fun test(numOfMines: Int): Boolean {
            var cellCount = 0
            var mineCount = 0
            for (x in 0 until height) {
                for (y in 0 until width) {
                    if (displayField[x][y] == characters[9]) {
                        if (gameField[x][y] == characters[9]) {
                            mineCount++
                        } else mineCount--
                    } else {
                        if (displayField[x][y] == gameField[x][y]) {
                            cellCount++
                        }
                    }
                }
            }
            return if (mineCount == numOfMines) {
                true
            } else {
                cellCount == height * width - numOfMines
            }

        }

        fun getCoords() {
            // test the coordinates and get new ones if necessary
            print("Set/unset mines marks or claim a cell as free: ")
            val y = scanner.nextInt()
            val x = scanner.nextInt()
            val cell = scanner.next()
            if (x <= height && y <= width) {
                if (displayField[x - 1][y - 1] == characters[0] || displayField[x - 1][y - 1] == characters[9]) {
                    coords[0] = x
                    coords[1] = y
                    coords[2] = 1 //true
                } else {
                    coords[2] = 0 // false
                }
            } else {
                coords[2] = 0 // false
            }
            when (cell) {
                "mine" -> coords[3] = 0
                "free" -> coords[3] = 1
                else -> coords[3] = 2
            }
        }

        fun free(coords: Free, list: Array<Free>): Array<Free> {
            val x = coords.x
            val y = coords.y
            var freeList = list
            var new: Free
            if (gameField[x][y] != characters[9]) {
                if (gameField[x][y] == characters[10]) {
                    displayField[x][y] = gameField[x][y]
                    if (x == 0) {
                        when (y) {
                            0 -> { // top left corner
                                if (gameField[x][y + 1] == characters[10]) {
                                    new = Free(x, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y + 1] = characters[10]
                                    }
                                } else displayField[x][y + 1] = gameField[x][y + 1]
                                if (gameField[x + 1][y] == characters[10]) {
                                    new = Free(x + 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y] = characters[10]
                                    }
                                } else displayField[x + 1][y] = gameField[x + 1][y]
                                if (gameField[x + 1][y + 1] == characters[10]) {
                                    new = Free(x + 1, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y + 1] = characters[10]
                                    }
                                } else displayField[x + 1][y + 1] = gameField[x + 1][y + 1]
                            }
                            8 -> { // top right corner
                                if (gameField[x][y - 1] == characters[10]) {
                                    new = Free(x, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y - 1] = characters[10]
                                    }
                                } else displayField[x][y - 1] = gameField[x][y - 1]
                                if (gameField[x + 1][y] == characters[10]) {
                                    new = Free(x + 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y] = characters[10]
                                    }
                                } else displayField[x + 1][y] = gameField[x + 1][y]
                                if (gameField[x + 1][y - 1] == characters[10]) {
                                    new = Free(x + 1, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y - 1] = characters[10]
                                    }
                                } else displayField[x + 1][y - 1] = gameField[x + 1][y - 1]
                            }
                            else -> { // top edge not corners
                                if (gameField[x][y - 1] == characters[10]) {
                                    new = Free(x, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y - 1] = characters[10]
                                    }
                                } else displayField[x][y - 1] = gameField[x][y - 1]
                                if (gameField[x][y + 1] == characters[10]) {
                                    new = Free(x, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y + 1] = characters[10]
                                    }
                                } else displayField[x][y + 1] = gameField[x][y + 1]
                                if (gameField[x + 1][y - 1] == characters[10]) {
                                    new = Free(x + 1, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y - 1] = characters[10]
                                    }
                                } else displayField[x + 1][y - 1] = gameField[x + 1][y - 1]
                                if (gameField[x + 1][y] == characters[10]) {
                                    new = Free(x + 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y] = characters[10]
                                    }
                                } else displayField[x + 1][y] = gameField[x + 1][y]
                                if (gameField[x + 1][y + 1] == characters[10]) {
                                    new = Free(x + 1, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x + 1][y + 1] = characters[10]
                                    }
                                } else displayField[x + 1][y + 1] = gameField[x + 1][y + 1]
                            }
                        }
                    } else if (x == height - 1) {
                        when (y) {
                            0 -> { // bottom left corner
                                if (gameField[x][y + 1] == characters[10]) {
                                    new = Free(x, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y + 1] = characters[10]
                                    }
                                } else displayField[x][y + 1] = gameField[x][y + 1]
                                if (gameField[x - 1][y] == characters[10]) {
                                    new = Free(x - 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y] = characters[10]
                                    }
                                } else displayField[x - 1][y] = gameField[x - 1][y]
                                if (gameField[x - 1][y + 1] == characters[10]) {
                                    new = Free(x - 1, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y + 1] = characters[10]
                                    }
                                } else displayField[x - 1][y + 1] = gameField[x - 1][y + 1]
                            }
                            8 -> { // bottom right corner
                                if (gameField[x][y - 1] == characters[10]) {
                                    new = Free(x, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y - 1] = characters[10]
                                    }
                                } else displayField[x][y - 1] = gameField[x][y - 1]
                                if (gameField[x - 1][y] == characters[10]) {
                                    new = Free(x - 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y] = characters[10]
                                    }
                                } else displayField[x - 1][y] = gameField[x - 1][y]
                                if (gameField[x - 1][y - 1] == characters[10]) {
                                    new = Free(x - 1, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y - 1] = characters[10]
                                    }
                                } else displayField[x - 1][y - 1] = gameField[x - 1][y - 1]
                            }
                            else -> { // bottom edge not corners
                                if (gameField[x][y - 1] == characters[10]) {
                                    new = Free(x, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y - 1] = characters[10]
                                    }
                                } else displayField[x][y - 1] = gameField[x][y - 1]
                                if (gameField[x][y + 1] == characters[10]) {
                                    new = Free(x, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x][y + 1] = characters[10]
                                    }
                                } else displayField[x][y + 1] = gameField[x][y + 1]
                                if (gameField[x - 1][y - 1] == characters[10]) {
                                    new = Free(x - 1, y - 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y - 1] = characters[10]
                                    }
                                } else displayField[x - 1][y - 1] = gameField[x - 1][y - 1]
                                if (gameField[x - 1][y] == characters[10]) {
                                    new = Free(x - 1, y)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y] = characters[10]
                                    }
                                } else displayField[x - 1][y] = gameField[x - 1][y]
                                if (gameField[x - 1][y + 1] == characters[10]) {
                                    new = Free(x - 1, y + 1)
                                    if (!this.listCheck(new, freeList)) {
                                        freeList += new
                                        displayField[x - 1][y + 1] = characters[10]
                                    }
                                } else displayField[x - 1][y + 1] = gameField[x - 1][y + 1]
                            }
                        }
                    } else {
                        if (x in 1..7) {
                            when (y) {
                                0 -> { // left edge not corners
                                    if (gameField[x - 1][y] == characters[10]) {
                                        new = Free(x - 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y] = characters[10]
                                        }
                                    } else displayField[x - 1][y] = gameField[x - 1][y]
                                    if (gameField[x - 1][y + 1] == characters[10]) {
                                        new = Free(x - 1, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y + 1] = characters[10]
                                        }
                                    } else displayField[x - 1][y + 1] = gameField[x - 1][y + 1]
                                    if (gameField[x][y + 1] == characters[10]) {
                                        new = Free(x, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x][y + 1] = characters[10]
                                        }
                                    } else displayField[x][y + 1] = gameField[x][y + 1]
                                    if (gameField[x + 1][y] == characters[10]) {
                                        new = Free(x + 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y] = characters[10]
                                        }
                                    } else displayField[x + 1][y] = gameField[x + 1][y]
                                    if (gameField[x + 1][y + 1] == characters[10]) {
                                        new = Free(x + 1, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y + 1] = characters[10]
                                        }
                                    } else displayField[x + 1][y + 1] = gameField[x + 1][y + 1]
                                }
                                8 -> { // right edge not corners
                                    if (gameField[x - 1][y - 1] == characters[10]) {
                                        new = Free(x - 1, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y - 1] = characters[10]
                                        }
                                    } else displayField[x - 1][y - 1] = gameField[x - 1][y - 1]
                                    if (gameField[x - 1][y] == characters[10]) {
                                        new = Free(x - 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y] = characters[10]
                                        }
                                    } else displayField[x - 1][y] = gameField[x - 1][y]
                                    if (gameField[x][y - 1] == characters[10]) {
                                        new = Free(x, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x][y - 1] = characters[10]
                                        }
                                    } else displayField[x][y - 1] = gameField[x][y - 1]
                                    if (gameField[x + 1][y - 1] == characters[10]) {
                                        new = Free(x + 1, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y - 1] = characters[10]
                                        }
                                    } else displayField[x + 1][y - 1] = gameField[x + 1][y - 1]
                                    if (gameField[x + 1][y] == characters[10]) {
                                        new = Free(x + 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y] = characters[10]
                                        }
                                    } else displayField[x + 1][y] = gameField[x + 1][y]
                                }
                                else -> { // all interior
                                    if (gameField[x - 1][y - 1] == characters[10]) {
                                        new = Free(x - 1, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y - 1] = characters[10]
                                        }
                                    } else displayField[x - 1][y - 1] = gameField[x - 1][y - 1]
                                    if (gameField[x - 1][y] == characters[10]) {
                                        new = Free(x - 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y] = characters[10]
                                        }
                                    } else displayField[x - 1][y] = gameField[x - 1][y]
                                    if (gameField[x - 1][y + 1] == characters[10]) {
                                        new = Free(x - 1, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x - 1][y + 1] = characters[10]
                                        }
                                    } else displayField[x - 1][y + 1] = gameField[x - 1][y + 1]
                                    if (gameField[x][y - 1] == characters[10]) {
                                        new = Free(x, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x][y - 1] = characters[10]
                                        }
                                    } else displayField[x][y - 1] = gameField[x][y - 1]
                                    if (gameField[x][y + 1] == characters[10]) {
                                        new = Free(x, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x][y + 1] = characters[10]
                                        }
                                    } else displayField[x][y + 1] = gameField[x][y + 1]
                                    if (gameField[x + 1][y - 1] == characters[10]) {
                                        new = Free(x + 1, y - 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y - 1] = characters[10]
                                        }
                                    } else displayField[x + 1][y - 1] = gameField[x + 1][y - 1]
                                    if (gameField[x + 1][y] == characters[10]) {
                                        new = Free(x + 1, y)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y] = characters[10]
                                        }
                                    } else displayField[x + 1][y] = gameField[x + 1][y]
                                    if (gameField[x + 1][y + 1] == characters[10]) {
                                        new = Free(x + 1, y + 1)
                                        if (!this.listCheck(new, freeList)) {
                                            freeList += new
                                            displayField[x + 1][y + 1] = characters[10]
                                        }
                                    } else displayField[x + 1][y + 1] = gameField[x + 1][y + 1]
                                }
                            }
                        }
                    }
                } else displayField[x][y] = gameField[x][y]
            } else stepOnMine = true
            counter += freeList.size - list.size
            return freeList
        }
        private fun listCheck(coords: Free, list: Array<Free>): Boolean {
            var freeList = list
            var count = 0
            for (element in list) {
                if (coords.x == element.x && coords.y == element.y) {
                    count++
                }
            }
            if (count == 0) {
                freeList += coords
            }
            return freeList.contentEquals(list)
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

    // todo make game play instructions more clear
    // game loop
    loop@ while (!Game.test(numberOfMines)) { // tests displayField vs gameField
        Game.display() // displays the current displayField w/ mines hidden
        coords[2] = 0
        while (coords[2] != 1) {
            Game.getCoords() // get coordinates from player
        }
        if (coords[3] != 2) {
            when (coords[3]) {
                0 -> { // places or removes flag @ coordinates
                    displayField[coords[0] - 1][coords[1] - 1] = Game.flagPlacement(coords[0] - 1, coords[1] - 1, displayField)
                }
                1 -> { // free cell
                    val start = Free(coords[0] - 1, coords[1] - 1)
                    var check = arrayOf(start)
                    do {
                        counter = 0
                        for (element in check) {
                            check = Game.free(element, check)
                            if (stepOnMine) break@loop // breaks out of the loop@ while loop
                        }
                    } while (counter != 0)
                }
            }
        }
    }
    when (stepOnMine) {
        false -> {
            Game.display()
            println("Congratulations! You found all the mines!") //game over output
        }
        else -> {
            for (x in 0 until height) {
                for (y in 0 until width) {
                    if (gameField[x][y] == characters[9]) {
                        displayField[x][y] = 'X' // displays all mines as 'X'
                    }
                }
            }
            Game.display()
            println("You stepped on a mine and failed!")
        }
    }
}