package com.evolution.bootcamp.assignment.poker.game

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards

object TexasHoldemGameSolver extends GameSolver(2, 5) {
  def possibleCombinations(onHand: Cards, board: Cards): Iterator[Cards] = {
    val allCards = onHand ++ board
    allCards.sorted combinations 5
  }
}
