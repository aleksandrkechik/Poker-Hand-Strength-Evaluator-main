package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.CardTest.CardTestCase
import com.evolution.bootcamp.assignment.poker.card.Card
import com.evolution.bootcamp.assignment.poker.card.Card.{Rank, Suite}
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

import java.{util => ju}

@RunWith(value = classOf[Parameterized])
class CardTest(cardTestCase: CardTestCase) {
  @Test def testDecode = assertEquals(cardTestCase._2, Card.decode(cardTestCase._1))
}

object CardTest {

  type CardTestCase = (String, Card)

  @Parameters def parameters: ju.Collection[CardTestCase] = {
    val list = new ju.ArrayList[CardTestCase]()
    List(("4s", Card(Rank.four, Suite.spades)),
      ("5h", Card(Rank.five, Suite.hearts)),
      ("Ts", Card(Rank.ten, Suite.spades)),
      ("Qh", Card(Rank.queen, Suite.hearts)),
      ("9h", Card(Rank.nine, Suite.hearts))).foreach(list.add)
    list
  }
}