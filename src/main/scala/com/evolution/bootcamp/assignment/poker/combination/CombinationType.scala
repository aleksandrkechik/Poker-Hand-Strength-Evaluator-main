package com.evolution.bootcamp.assignment.poker.combination

object CombinationType extends Enumeration {
  type CombinationType = Value

  implicit val orderingById: Ordering[CombinationType] = Ordering.by(_.id)

  val StraightFlush, FourOfAKind, FullHouse, Flush, Straight, ThreeOfAKind, TwoPairs, Pair, HighCard = Value
}
