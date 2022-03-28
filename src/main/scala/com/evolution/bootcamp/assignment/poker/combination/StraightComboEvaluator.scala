package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.Straight

object StraightComboEvaluator extends ComboEvaluator(5, Straight) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean = cards.sliding(2).forall {
    case Seq(a, b) =>
      a.rank.id - b.rank.id == 1 // Checking whether the cards are consecutive
  }
}
