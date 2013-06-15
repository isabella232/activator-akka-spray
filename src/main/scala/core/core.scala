package core

import akka.actor.{Props, ActorSystem}

/**
 * This trait implements ``Core`` by starting the required ``ActorSystem`` and registering the
 * termination handler to stop the system when the JVM exits.
 */
trait BootedCore {

  /**
   * Construct the ActorSystem we will use in our application
   */
  implicit val system = ActorSystem("akka-spray")

  /**
   * Ensure that the constructed ActorSystem is shut down when the JVM shuts down
   */
  system.registerOnTermination(system.shutdown())

}

/**
 * This trait contains the actors that make up our application; it can be mixed in with
 * ``BootedCore`` for running code or ``TestKit`` for unit and integration tests.
 */
trait CoreActors {
  this: Core =>

  val registration = system.actorOf(Props[RegistrationActor])

}