package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.game.{FiveCardDrawGameSolver, OmahaGameSolver, TexasHoldemGameSolver}

object Solver {
  // TODO: implement correct solution logic
  def process(line: String): String = {
    val ErrorPrefix = "Error: "

    line.split("\\s+").toList match {
      case "texas-holdem" :: board :: hands => TexasHoldemGameSolver.solve(hands, board)
      case "omaha-holdem" :: board :: hands => OmahaGameSolver.solve(hands, board)
      case "five-card-draw" :: hands => FiveCardDrawGameSolver.solve(hands, "")
      case x :: _ => ErrorPrefix + "Unrecognized game type"
      case _ => ErrorPrefix + "Invalid input"
    }
  }
}
