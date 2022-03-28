package com.evolution.bootcamp.assignment.poker.game

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards

/**
 * Found in Omaha rules that a player supposed to use exactly 2 of the cards on their hands
 */
object OmahaGameSolver extends GameSolver(4, 5) {
  def possibleCombinations(onHand: Cards, board: Cards): Iterator[Cards] = {
    for {
      handCombo <- onHand.sorted.combinations(2)
      boardCombo <- board.sorted.combinations(3)
    } yield (handCombo ++ boardCombo).sorted
  }
}
