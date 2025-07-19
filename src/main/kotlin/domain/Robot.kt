package com.mindfulbytes.domain

data class Robot(val heading: Heading) {
    fun turn(turn: Movement): Robot {
        return when (turn) {
            Movement.LEFT -> when (heading) {
                Heading.NORTH -> copy(heading = Heading.WEST)
                Heading.SOUTH -> copy(heading = Heading.EAST)
                Heading.EAST -> copy(heading = Heading.NORTH)
                Heading.WEST -> copy(heading = Heading.SOUTH)
            }
            Movement.RIGHT -> when (heading) {
                Heading.NORTH -> copy(heading = Heading.EAST)
                Heading.SOUTH -> copy(heading = Heading.WEST)
                Heading.EAST -> copy(heading = Heading.SOUTH)
                Heading.WEST -> copy(heading = Heading.NORTH)
            }
        }
    }
}

enum class Heading {
    NORTH,
    SOUTH,
    EAST,
    WEST
}

enum class Movement {
    LEFT,
    RIGHT
}