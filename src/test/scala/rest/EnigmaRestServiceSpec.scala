package rest

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import akka.actor.ActorRefFactory

class EnigmaRestServiceSpec extends Specification with Specs2RouteTest with EnigmaService {
  implicit def actorRefFactory: ActorRefFactory = system

  "POST on root" should {
    "transform message" in {
      Post("/", "ENIGMA REVEALED") ~>  enigmaRoute ~> check {
        handled === true
        entityAs[String] === "QMJIDO MZWZJFJR"
      }
    }
  }

}
