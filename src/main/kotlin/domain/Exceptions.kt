package com.mindfulbytes.domain

class InvalidHeadingException(heading: String) : Throwable("Invalid heading: $heading")
class InvalidMoveException(move: String) : Throwable("Invalid move: $move")
class InvalidTurnException(turn: String) : Throwable("Invalid turn: $turn")
class RobotLostException(val lastPosition: Robot) : Throwable("Robot lost at: ${lastPosition.coordinates}")
