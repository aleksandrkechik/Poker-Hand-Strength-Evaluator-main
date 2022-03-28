package com.evolution.bootcamp.assignment.poker.game

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards

object FiveCardDrawGameSolver extends GameSolver(5, 0) {
  def possibleCombinations(onHand: Cards, board: Cards): Iterator[Cards] = {
    val allCards = onHand
    allCards.sorted combinations 5
  }
}
