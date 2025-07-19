package com.mindfulbytes.domain

class InvalidHeadingException(heading: String) : RuntimeException("Invalid heading: $heading")
class InvalidMoveException(move: String) : RuntimeException("Invalid move: $move")
class InvalidTurnException(turn: String) : RuntimeException("Invalid turn: $turn")
