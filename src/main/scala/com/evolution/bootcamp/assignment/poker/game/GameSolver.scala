package com.evolution.bootcamp.assignment.poker.game

import com.evolution.bootcamp.assignment.poker.card.Cards.Cards
import com.evolution.bootcamp.assignment.poker.card.{Card, Cards}
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator.EvalTrue
import com.evolution.bootcamp.assignment.poker.game.GameUtils.{allTwoSplits, formatSolution}

object GameSolver {

  import scala.math.Ordering.Implicits._

  val combinationTypeWithCardsOrdering: Ordering[(EvalTrue, List[Card])] =
    Ordering.Tuple2(Ordering.by(_.combinationType.id), Ordering[List[Card]])
}

abstract class GameSolver(val handSize: Int, val boardSize: Int) {

  import GameSolver._

  /**
   * @param onHand - cards on hand
   * @param board  - cards on board
   * @return all possible five card combinations dictated by game rules
   */
  def possibleCombinations(onHand: Cards, board: Cards): Iterator[Cards]

  /**
   * Solves the game (orders hands by strength). In order to do this, the GameSolver:
   *  1. Iterates through all possible five-card combinations for each hand with the board (game-dependent)
   *     and processes them, finding the strongest one
   *  2. Then sorts hands by strength and formats the solution
   *
   * @param onHandStrs - strings with hand encodings
   * @param boardStr   - string with board encoding
   * @return solution string (hands ranked by strength)
   */
  def solve(onHandStrs: List[String], boardStr: String): String = {
    val board: Cards = Cards.decode(boardStr)
    require(board.size == boardSize)
    val solution = onHandStrs.map { onHandStr =>
      val onHand = Cards.decode(onHandStr)
      require(onHand.size == handSize)
      //  TODO: To optimise this, one could keep track of the best combination found (e.g. through foldLeft) and not check the less powerful ones at all
      val allValidCombinations: Seq[(EvalTrue, Cards)] = (possibleCombinations(onHand, board) map { fiveCards: Cards =>
        processCards(fiveCards)
      }).toList.flatten

      //      Picking the best 5-card combination possible
      val bestCombination = allValidCombinations.sorted(combinationTypeWithCardsOrdering).head
      (onHandStr, bestCombination)
    }.sortBy(_._2)(combinationTypeWithCardsOrdering).reverse

    formatSolution(solution)
  }

  /**
   * Process five cards:
   * 1. Iterate through all possible splits of a particular 5-card combination into two sub-lists
   * 2. For each combination, see if it evaluates to one of the CombinationTypes
   * 3. List all successful eval results alongside with cards sorted in a corresponding manner ([cards in combination] ++ [rest of the cards, sorted by strength])
   *
   * @param fiveCards - five card combination
   * @return all possible combinations saved in convenient format
   */
  private def processCards(fiveCards: Cards) = {
    allTwoSplits(fiveCards) flatMap {
      case (combination: Cards, rest: Cards) =>
        ComboEvaluator.evaluate(combination) match {
          case result: EvalTrue =>
            // Save the result and cards in convenient order (we later use it to sort the solutions)
            Some((result, result.cards ++ rest.sortBy(-_.rank.id)))
          case _ =>
            None
        }
    }
  }
}
