package com.mindfulbytes.application

import com.mindfulbytes.domain.*

class RobotService(private val boundaryChecker: BoundaryChecker) {

    private val knownBoundaries = mutableSetOf<Coordinates>()

    fun createRobot(heading: String, x: Int, y: Int): Result<Robot> =
        enumValues<Heading>().firstOrNull { it.name == heading }
            ?.let { headingEnum -> Result.success(Robot(headingEnum, Coordinates(x, y))) }
            ?: Result.failure(InvalidHeadingException(heading))

    fun turnRobot(robot: Robot, turn: String): Result<Robot> = enumValues<Turn>().firstOrNull { it.name == turn }
        ?.let { turnEnum -> Result.success(robot.turn(turnEnum)) }
        ?: Result.failure(InvalidTurnException(turn))

    fun moveRobot(robot: Robot, move: String): Result<Robot> {
        val moveEnum =
            enumValues<Move>().firstOrNull() { it.name == move } ?: return Result.failure(InvalidMoveException(move))

        val movedRobot = robot.move(moveEnum)

        return if (boundaryChecker.coordinatesValid(movedRobot.coordinates)) {
            Result.success(movedRobot)
        } else {
            if (knownBoundaries.add(movedRobot.coordinates)) {
                Result.failure(RobotLostException(robot))
            } else {
                Result.success(robot)
            }
        }
    }


}