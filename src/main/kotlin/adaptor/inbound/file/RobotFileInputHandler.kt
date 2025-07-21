package com.mindfulbytes.adapter.inbound.file

import com.mindfulbytes.Helpers.flatMap
import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.Robot
import com.mindfulbytes.domain.RobotLostException

class RobotFileInputHandler(
    private val robotService: RobotService,
    private val input: List<String>
) {

    private fun Result<Robot>.status(): String =
        fold ({
            "${it.coordinates.x} ${it.coordinates.y} ${it.heading}"
        }, {
            when (it) {
                is RobotLostException ->
                    "${it.lastPosition.coordinates.x} ${it.lastPosition.coordinates.y} ${it.lastPosition.heading} LOST"

                else -> it.message ?: "Unknown error"
            }
        })


    fun handleCommands(): List<String> = input.windowed(2, 2) { commands ->
        val robot = handleCreateRobot(commands[0].split(" "))
        val results = commands[1].toList().fold(robot) { activeRobot, command ->
            activeRobot.flatMap {
                when (command) {
                    'F' -> robotService.moveRobot(it, "F")
                    'L' -> robotService.turnRobot(it, "L")
                    'R' -> robotService.turnRobot(it, "R")
                    else -> throw IllegalArgumentException("Command not recognised: $command")
                }
            }
        }
        results.status()
    }

    private fun handleCreateRobot(args: List<String>): Result<Robot> {
        val x = args[0].toInt()
        val y = args[1].toInt()
        val heading = args[2]
        return robotService.createRobot(heading, x, y)
    }

}