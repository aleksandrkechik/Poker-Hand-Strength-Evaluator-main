package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards._
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.FullHouse
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator.{EvalFalse, EvalResult, EvalTrue}
import com.evolution.bootcamp.assignment.poker.combination.SameRankComboEvaluator.{PairComboEvaluator, ThreeOfAKindComboEvaluator}

object FullHouseComboEvaluator extends ComboEvaluator(5, FullHouse) {
  override def checkIfConfirmsToCombination(cards: Cards): Boolean = {
    throw new Exception("Shouldn't have been called!")
  }

  override def evaluate(cards: Cards): EvalResult = if (cards.size != cardsNum) {
    EvalFalse()
  } else {
    val (two, three) = cards.splitAt(2)
    if (checkIfFullHouse(two, three)) {
      EvalTrue(three ++ two, combinationType)
    } else {
      val (three, two) = cards.splitAt(3)
      if (checkIfFullHouse(two, three)) {
        EvalTrue(three ++ two, combinationType)
      } else EvalFalse()
    }
  }

  private def checkIfFullHouse(two: Cards, three: Cards): Boolean = {
    ThreeOfAKindComboEvaluator.evaluate(three).isTrue && PairComboEvaluator.evaluate(two).isTrue
  }
}
