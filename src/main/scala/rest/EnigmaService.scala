package rest

import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import spray.httpx.encoding.{Gzip, NoEncoding}

trait EnigmaService extends HttpService with SprayJsonSupport {

  val enigmaRoute =
    path("") {
      get {
        complete ("Post your encrypted code and see")
      } ~
      post {
        (decodeRequest(Gzip) | decodeRequest(NoEncoding)) {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) { body =>
            complete(body)
          }
        }

      }
    }

}

