package com.mindfulbytes.application

import com.mindfulbytes.domain.*

class RobotService {

    fun createRobot(heading: String, x: Int, y: Int): Robot {
        val headingEnum = try {
            Heading.valueOf(heading)
        } catch (e: IllegalArgumentException) {
            throw InvalidHeadingException(heading)
        }
        return Robot(headingEnum, Coordinates(x, y))
    }

    fun turnRobot(robot: Robot, turn: String): Robot {
        val turnEnum = try {
            Turn.valueOf(turn)
        } catch (e: IllegalArgumentException) {
            throw InvalidTurnException(turn)
        }
        return robot.turn(turnEnum)
    }

    fun moveRobot(robot: Robot, move: String): Robot {
        val moveEnum = try {
            Move.valueOf(move)
        } catch (e: IllegalArgumentException) {
            throw InvalidMoveException(move)
        }
        return robot.move(moveEnum)
    }
}