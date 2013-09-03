import akka.actor.Actor
import rest.EnigmaService

class RestActor extends Actor with EnigmaService {
  def actorRefFactory = context
  def receive = runRoute(enigmaRoute)
}