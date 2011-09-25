package org.oregu

import java.lang.Thread
import java.util.concurrent.LinkedBlockingQueue

/**
 * Simple thread pool implementation
 *
 * User: oregu
 * Date: 25.09.11 10:10
 */
class ThreadPool(workersCount: Int) {

  def this() = this(1)

  val threads = new Array[Thread](workersCount)
  val queue = new LinkedBlockingQueue[Runnable]

  init()

  private def init() {
    for (i <- 0 until workersCount) {
      threads.update(i, new Thread(Worker))
      threads(i).setDaemon(true)
      threads(i).start()
    }
  }

  def submit(r: Runnable) {
    queue.put(r)
  }

  private object Worker extends Runnable {
    def run() {
      while(true) {
        println("worker: Getting next job")
        val job = queue.take
        println("worker: Running job")
        job.run()
      }
    }
  }
}

object ThreadPool {
  def apply(wc: Int): ThreadPool = {
    new ThreadPool(wc)
  }
}