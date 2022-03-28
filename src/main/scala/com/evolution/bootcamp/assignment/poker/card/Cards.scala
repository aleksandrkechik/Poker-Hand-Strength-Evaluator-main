package com.evolution.bootcamp.assignment.poker.card

/**
 * Convenient extensions for a list of cards
 */
object Cards {
  type Cards = List[Card]

  implicit class CardsProperties(cards: Cards) {
    def sameRank(): Boolean = cards.map(_.rank).distinct.size == 1
    def sameSuit(): Boolean = cards.map(_.suite).distinct.size == 1
    def highest(): Card = cards.max
  }

  def decode(encoded: String): List[Card] = {
    def decode(encodedCards: List[Char]): List[Card] = {
      encodedCards match {
        case Nil => Nil
        case r :: s :: rest => Card.decode(s"$r$s") +: decode(rest)
        case _ => throw new Error(s"Wrong card sequence encoding: $encodedCards")
      }
    }

    decode(encoded.toList)
  }

}
