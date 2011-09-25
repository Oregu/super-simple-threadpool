package test

import Thread.sleep
import org.oregu._

/**
 * User: winRoot
 * Date: 25.09.11 11:57
 */
object TestMyPool {
  def main(args: Array[String]) {
    val pool = ThreadPool(2)

    for (i <- 0 until 5) {

      println("Submitting thread " + i)
      pool.submit(new Runnable {
        def run() {
          println("Thread " + i + " started")
          sleep(i * 1000)
          println("Thread " + i + " finished")
        }
      })
    }

    sleep(10 * 1000)
  }
}