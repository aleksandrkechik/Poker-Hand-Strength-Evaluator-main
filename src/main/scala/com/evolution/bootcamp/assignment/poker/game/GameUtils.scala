package com.evolution.bootcamp.assignment.poker.game

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator.EvalTrue

object GameUtils {
  /**
   * Produces all possible divisions of the cards into two sub-lists (of the same order)
   *
   * @param cards - cards to be divided
   * @return all possible divisions
   */
  //    TODO: I think it could be rewritten using tail recursion somehow
  def allTwoSplits(cards: Cards): List[(Cards, Cards)] = {
    def split(cardsLeft: Cards, combination: Cards, rest: Cards, twoSplits: List[(Cards, Cards)]): List[(Cards, Cards)] =
      cardsLeft match {
        case Nil => twoSplits ++ List((combination, rest))
        case a :: tail => split(tail, combination :+ a, rest, twoSplits) ++ split(tail, combination, rest :+ a, twoSplits)
      }

    split(cards, Nil, Nil, Nil).filter(_._1.nonEmpty)
  }

  /**
   * Formats the solution into string with hands, ranked by strength.
   * A "=" sign would be put between the equally strong hands.
   *
   * @param solution - list of pairs of (hand, (strongest combination, cards))
   */
  def formatSolution(solution: List[(String, (EvalTrue, Cards))]): String = {
    solution.tail.foldLeft((List(solution.head._1), solution.head._2._2)) {
      case ((acc: List[String], prevCards: Cards), (newHand: String, (_: EvalTrue, newCards: Cards))) =>
        val append = (if (prevCards.map(_.rank) == newCards.map(_.rank)) "=" else " ") + newHand
        (acc :+ append, newCards)
    }._1.mkString("")
  }
}
