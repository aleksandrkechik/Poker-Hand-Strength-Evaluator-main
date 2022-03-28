package com.evolution.bootcamp.assignment.poker.card

import com.evolution.bootcamp.assignment.poker.card
import com.evolution.bootcamp.assignment.poker.card.Card.Rank.Rank
import com.evolution.bootcamp.assignment.poker.card.Card.Suite.Suite

object Card {
  implicit val orderingByRank: Ordering[Card] = Ordering.by(-_.rank.id)

  def decode(encodedCard: String): Card = {
    encodedCard.toList match {
      case r :: s :: Nil => card.Card(Rank.withName(r.toString), Suite.withName(s.toString))
      case _ => throw new Error(s"Wrong card encoding: $encodedCard")
    }
  }

  object Rank extends Enumeration {
    type Rank = Value

    val two = Value("2")
    val three = Value("3")
    val four = Value("4")
    val five = Value("5")
    val six = Value("6")
    val seven = Value("7")
    val eight = Value("8")
    val nine = Value("9")
    val ten = Value("T")
    val jack = Value("J")
    val queen = Value("Q")
    val king = Value("K")
    val ace = Value("A")
  }

  object Suite extends Enumeration {
    type Suite = Value
    val hearts = Value("h")
    val diamonds = Value("d")
    val clubs = Value("c")
    val spades = Value("s")
  }
}

case class Card(rank: Rank, suite: Suite)