package com.mindfulbytes.domain

data class Robot(val heading: Heading, val coordinates: Coordinates) {
    fun turn(turn: Turn): Robot {
        return when (turn) {
            Turn.LEFT -> when (heading) {
                Heading.NORTH -> copy(heading = Heading.WEST)
                Heading.SOUTH -> copy(heading = Heading.EAST)
                Heading.EAST -> copy(heading = Heading.NORTH)
                Heading.WEST -> copy(heading = Heading.SOUTH)
            }
            Turn.RIGHT -> when (heading) {
                Heading.NORTH -> copy(heading = Heading.EAST)
                Heading.SOUTH -> copy(heading = Heading.WEST)
                Heading.EAST -> copy(heading = Heading.SOUTH)
                Heading.WEST -> copy(heading = Heading.NORTH)
            }
        }
    }

    fun move(move: Move): Robot {
        return when (move) {
            Move.FORWARD -> {
                copy(coordinates = coordinates.move(heading))
            }
        }
    }
}

data class Coordinates(val x: Int, val y: Int) {
    fun move(heading: Heading): Coordinates {
        return when (heading) {
            Heading.NORTH -> copy(y = y + 1)
            Heading.SOUTH -> copy(y = y - 1)
            Heading.EAST -> copy(x = x + 1)
            Heading.WEST -> copy(x = x - 1)
        }
    }
}

enum class Heading {
    NORTH,
    SOUTH,
    EAST,
    WEST,
}

enum class Turn {
    LEFT,
    RIGHT,
}

enum class Move {
    FORWARD,
}

