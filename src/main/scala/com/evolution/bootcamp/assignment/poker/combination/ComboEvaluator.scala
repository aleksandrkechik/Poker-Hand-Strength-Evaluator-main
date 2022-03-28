package com.evolution.bootcamp.assignment.poker.combination

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards
import com.evolution.bootcamp.assignment.poker.combination.CombinationType.{CombinationType, HighCard}
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator.{EvalFalse, EvalResult, EvalTrue}
import com.evolution.bootcamp.assignment.poker.combination.SameRankComboEvaluator.{FourOfAKindComboEvaluator, PairComboEvaluator, ThreeOfAKindComboEvaluator}

object ComboEvaluator {
  private val comboEvaluators = List(StraightFlushComboEvaluator, FourOfAKindComboEvaluator, FullHouseComboEvaluator, FlushComboEvaluator,
    StraightComboEvaluator, ThreeOfAKindComboEvaluator, TwoPairsComboEvaluator, PairComboEvaluator, HighCardComboEvaluator)
  private val cardsNum2comboEvaluator: Map[Int, List[ComboEvaluator]] = comboEvaluators.groupBy(_.cardsNum).map {
    case (cardsNum, evaluators) =>
      cardsNum -> evaluators.sortBy(_.combinationType.id)
  }

  /**
   * @param cards - cards to test
   * @return - whether the cards compose any combination. EvalTrue if they do, EvalFalse if they don't.
   */
  def evaluate(cards: Cards): EvalResult = {
    cardsNum2comboEvaluator(cards.size).map(_.evaluate(cards)).find(_.isTrue).getOrElse(EvalFalse())
  }

  trait EvalResult {
    def isTrue: Boolean
  }

  /**
   * @param cards           - cards composing the combination, sorted in the convenient order
   * @param combinationType - cards combination type
   */
  case class EvalTrue(cards: Cards, combinationType: CombinationType) extends EvalResult {
    override def isTrue: Boolean = true
  }

  case class EvalFalse() extends EvalResult {
    override def isTrue: Boolean = false
  }

  object HighCardComboEvaluator extends ComboEvaluator(1, HighCard) {
    override def checkIfConfirmsToCombination(cards: Cards): Boolean = true
  }
}

abstract class ComboEvaluator(val cardsNum: Int, val combinationType: CombinationType) {

  def evaluate(cards: Cards): EvalResult = {
    if (cards.size != cardsNum) {
      EvalFalse()
    } else {
      if (checkIfConfirmsToCombination(cards)) EvalTrue(cards, combinationType) else EvalFalse()
    }
  }

  def checkIfConfirmsToCombination(cards: Cards): Boolean
}
