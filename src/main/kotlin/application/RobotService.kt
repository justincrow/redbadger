package com.mindfulbytes.application

import com.mindfulbytes.domain.*

class RobotService(val boundaryChecker: BoundaryChecker) {

    fun createRobot(heading: String, x: Int, y: Int): Result<Robot> {
        val headingEnum = try {
            Heading.valueOf(heading)
        } catch (e: IllegalArgumentException) {
            return Result.failure(InvalidHeadingException(heading))
        }
        return Result.success(Robot(headingEnum, Coordinates(x, y)))
    }

    fun turnRobot(robot: Robot, turn: String): Result<Robot> {
        val turnEnum = try {
            Turn.valueOf(turn)
        } catch (e: IllegalArgumentException) {
            return Result.failure(InvalidTurnException(turn))
        }
        return Result.success(robot.turn(turnEnum))
    }

    fun moveRobot(robot: Robot, move: String): Result<Robot> {
        val moveEnum = try {
            Move.valueOf(move)
        } catch (e: IllegalArgumentException) {
            return Result.failure(InvalidMoveException(move))
        }
        val movedRobot = robot.move(moveEnum)
        return if (boundaryChecker.coordinatesValid(movedRobot.coordinates))
            Result.success(movedRobot)
        else Result.failure(RobotLostException("${movedRobot.coordinates.x},${movedRobot.coordinates.y}"))
    }


}