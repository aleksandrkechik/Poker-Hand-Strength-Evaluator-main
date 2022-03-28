package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.StraightFlush

object StraightFlushComboEvaluator extends ComboEvaluator(5, StraightFlush) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean =
    StraightComboEvaluator.evaluate(cards).isTrue && FlushComboEvaluator.evaluate(cards).isTrue
}
