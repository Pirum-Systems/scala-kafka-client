package com.pirum.kafka.akka

import akka.actor.ActorSystem
import akka.testkit.TestKit
import com.pirum.kafka.testkit.KafkaServer
import org.scalatest.concurrent.Waiters
import org.scalatest.{BeforeAndAfterAll}
import org.scalatest.matchers.should.Matchers
import org.scalatest.flatspec.AnyFlatSpecLike

/** ScalaTest base class for scala-kafka-client-testkit based integration tests
  */
class KafkaIntSpec(_system: ActorSystem)
    extends TestKit(_system)
    with Waiters
    with AnyFlatSpecLike
    with Matchers
    with BeforeAndAfterAll {

  val kafkaServer = new KafkaServer()
  val kafkaPort = kafkaServer.kafkaPort

  override def beforeAll() = {
    kafkaServer.startup()
  }

  override def afterAll() = {
    kafkaServer.close()
    TestKit.shutdownActorSystem(system)
  }
}
