import scala.util.Properties
import spray.can.server.SprayCanHttpServerApp
import com.weiglewilczek.slf4s.Logging
import akka.actor.Props

object Boot extends App with SprayCanHttpServerApp with Logging {
  try {
    val service = system.actorOf(Props(new RestActor()), "enigma-service")
    newHttpServer(service) ! Bind("0.0.0.0", Properties.envOrElse("PORT", "9080").toInt)
  } catch {
    case e: Throwable => logger.error("Error while booting", e)
  }
}
