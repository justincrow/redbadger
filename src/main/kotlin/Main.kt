package com.mindfulbytes

import com.mindfulbytes.adapter.inbound.file.RobotFileInputHandler
import com.mindfulbytes.application.RobotService
import com.mindfulbytes.domain.BoundaryChecker
import com.mindfulbytes.domain.Coordinates

fun main() {
    Application().run()
}

class Application {
    fun run() {
        val input: List<String>? = this::class.java.getResourceAsStream("/testRobotCommands.txt")?.bufferedReader()?.use {
            it.readLines()
        }
        if (input.isNullOrEmpty()) {
            println("No input provided or file is empty.")
            return
        }
        val maxCoordinates = input.first().split(" ").let {
            Coordinates(it[0].toInt(), it[1].toInt())
        }
        val commands = input.drop(1)
        val handler = RobotFileInputHandler(
            RobotService(BoundaryChecker(maxCoordinates)),
            commands
        )
        println(handler.handleCommands())
    }
}