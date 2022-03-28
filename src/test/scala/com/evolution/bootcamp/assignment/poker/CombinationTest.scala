package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.card.Card
import com.evolution.bootcamp.assignment.poker.card.Card.{Rank, Suite}
import com.evolution.bootcamp.assignment.poker.combination.CombinationType._
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator
import com.evolution.bootcamp.assignment.poker.combination.ComboEvaluator.EvalTrue
import org.junit.Assert.assertEquals
import org.junit.Test

class CombinationTest {
  @Test def testStraightFlush = {
    val cards = List(Card(Rank.eight, Suite.hearts),
      Card(Rank.seven, Suite.hearts),
      Card(Rank.six, Suite.hearts),
      Card(Rank.five, Suite.hearts),
      Card(Rank.four, Suite.hearts))

    val expected = EvalTrue(cards.sorted, StraightFlush)
    assertEquals(expected, ComboEvaluator.evaluate(cards))
  }

  @Test def testTwoPairs = {
    val cards = List(Card(Rank.five, Suite.hearts),
      Card(Rank.five, Suite.spades),
      Card(Rank.four, Suite.hearts),
      Card(Rank.four, Suite.spades))

    val expected = EvalTrue(cards.sorted, TwoPairs)

    assertEquals(expected, ComboEvaluator.evaluate(cards))
  }

  @Test def testFourOfAKind = {
    val cards = List(Card(Rank.five, Suite.clubs),
      Card(Rank.five, Suite.diamonds),
      Card(Rank.five, Suite.hearts),
      Card(Rank.five, Suite.spades))

    val expected = EvalTrue(cards.sorted, FourOfAKind)

    assertEquals(expected, ComboEvaluator.evaluate(cards))
  }

  @Test def testFullHouse = {
    val cards = List(Card(Rank.queen, Suite.hearts),
      Card(Rank.queen, Suite.hearts),
      Card(Rank.queen, Suite.spades),
      Card(Rank.four, Suite.hearts),
      Card(Rank.four, Suite.spades))

    val expected = EvalTrue(cards, FullHouse)

    assertEquals(expected, ComboEvaluator.evaluate(cards))
  }
}
