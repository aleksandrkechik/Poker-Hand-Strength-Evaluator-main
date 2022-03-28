package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards._
import com.evolution.bootcamp.assignment.poker.combination.SameRankComboEvaluator.PairComboEvaluator

object TwoPairsComboEvaluator extends ComboEvaluator(4, CombinationType.TwoPairs) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean = {
    val (first, second) = cards.splitAt(2)
    PairComboEvaluator.evaluate(first).isTrue && PairComboEvaluator.evaluate(second).isTrue
  }
}
