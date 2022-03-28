package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.card.Card.{Rank, Suite}
import com.evolution.bootcamp.assignment.poker.card.{Card, Cards}
import org.junit.Assert.assertEquals
import org.junit.Test

class CardsTest {
  @Test def testDecode = {
    val expected = List(Card(Rank.four, Suite.spades),
      Card(Rank.five, Suite.hearts),
      Card(Rank.ten, Suite.spades),
      Card(Rank.queen, Suite.hearts),
      Card(Rank.nine, Suite.hearts))

    assertEquals(expected, Cards.decode("4s5hTsQh9h"))
  }

  @Test def testCardsAreSortedByRank = {
    val cards = List(Card(Rank.four, Suite.spades),
      Card(Rank.five, Suite.hearts),
      Card(Rank.ten, Suite.spades),
      Card(Rank.queen, Suite.hearts),
      Card(Rank.nine, Suite.hearts))

    val cardsExpected = List(Card(Rank.queen, Suite.hearts),
      Card(Rank.ten, Suite.spades),
      Card(Rank.nine, Suite.hearts),
      Card(Rank.five, Suite.hearts),
      Card(Rank.four, Suite.spades))

    assertEquals(cardsExpected, cards.sorted)
  }
}
