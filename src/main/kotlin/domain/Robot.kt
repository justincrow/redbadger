package com.mindfulbytes.domain

data class Robot(val heading: Heading, val coordinates: Coordinates) {
    fun turn(turn: Turn): Robot = when (turn) {
        Turn.L -> when (heading) {
            Heading.N -> copy(heading = Heading.W)
            Heading.W -> copy(heading = Heading.S)
            Heading.S -> copy(heading = Heading.E)
            Heading.E -> copy(heading = Heading.N)
        }
        Turn.R -> when (heading) {
            Heading.N -> copy(heading = Heading.E)
            Heading.E -> copy(heading = Heading.S)
            Heading.S -> copy(heading = Heading.W)
            Heading.W -> copy(heading = Heading.N)
        }
    }

    fun move(move: Move): Robot = when (move) {
        Move.F -> {
            copy(coordinates = coordinates.move(heading))
        }
    }
}

data class Coordinates(val x: Int, val y: Int) {
    fun move(heading: Heading): Coordinates = when (heading) {
        Heading.N -> copy(y = y + 1)
        Heading.S -> copy(y = y - 1)
        Heading.E -> copy(x = x + 1)
        Heading.W -> copy(x = x - 1)
    }
}

enum class Heading {
    N,
    S,
    E,
    W,
}

enum class Turn {
    L,
    R,
}

enum class Move {
    F,
}

