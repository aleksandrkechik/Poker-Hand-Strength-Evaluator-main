package com.evolution.bootcamp.assignment.poker

import com.evolution.bootcamp.assignment.poker.TexasHoldemGameSolverTest.GameTestCase
import com.evolution.bootcamp.assignment.poker.game.{GameSolver, TexasHoldemGameSolver}
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

import java.{util => ju}

@RunWith(value = classOf[Parameterized])
class TexasHoldemGameSolverTest(gameTestCase: GameTestCase) {
  @Test def testDecode =
    assertEquals(gameTestCase._3, TexasHoldemGameSolver.solve(gameTestCase._1, gameTestCase._2))
}

object TexasHoldemGameSolverTest {
  type GameTestCase = (List[String], String, String)

  @Parameters def parameters: ju.Collection[GameTestCase] = {
    val list = new ju.ArrayList[GameTestCase]()
    List((List("JcJs", "QsQh"), "JhQc8sAsKc", "JcJs QsQh"),
      (List("QcQd", "QsQh"), "JhQc8sAsKc", "QsQh=QcQd"),
      (List("TsJc", "Qh8c"), "3d4s5dJsQd", "TsJc Qh8c"),
      (List("Ks4c", "Kc7h", "Kc9h", "KdJs"), "5c6dAcAsQs", "Ks4c Kc7h Kc9h KdJs")).foreach(list.add)
    list
  }
}