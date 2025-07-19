package com.mindfulbytes.application

import com.mindfulbytes.domain.*

class RobotService {

    fun createRobot(heading: String, x: Int, y: Int): Robot {
        return Robot(Heading.valueOf(heading), Coordinates(x, y))
    }

    fun turnRobot(robot: Robot, turn: String): Robot {
        return robot.turn(Turn.valueOf(turn))
    }

    fun moveRobot(robot: Robot, move: String): Robot {
        return robot.move(Move.valueOf(move))
    }
}