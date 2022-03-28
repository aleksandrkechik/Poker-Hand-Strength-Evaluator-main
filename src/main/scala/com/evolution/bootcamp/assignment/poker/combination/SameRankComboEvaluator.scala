package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards._
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.{CombinationType, FourOfAKind, Pair, ThreeOfAKind}

object SameRankComboEvaluator {
  object FourOfAKindComboEvaluator extends SameRankComboEvaluator(4, FourOfAKind)
  object ThreeOfAKindComboEvaluator extends SameRankComboEvaluator(3, ThreeOfAKind)
  object PairComboEvaluator extends SameRankComboEvaluator(2, Pair)
}

abstract class SameRankComboEvaluator(cardsNum: Int, combinationType: CombinationType) extends ComboEvaluator(cardsNum, combinationType) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean = {
    cards.sameRank()
  }
}
