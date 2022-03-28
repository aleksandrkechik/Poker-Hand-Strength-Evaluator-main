package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards._
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.Flush

object FlushComboEvaluator extends ComboEvaluator(5, Flush) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean = cards.sameSuit()
}
