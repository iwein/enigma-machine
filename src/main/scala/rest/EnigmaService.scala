package rest

import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import spray.httpx.encoding.{Gzip, NoEncoding}
import org.iwein.enigma.{Alphabets, Reflector, Rotor, Enigma}

trait EnigmaService extends HttpService with SprayJsonSupport with Alphabets {


  def enigma: Enigma = {
    Enigma(
      (Rotor(realAlphabet, alphabetIII)
        (Rotor(realAlphabet, alphabetII)
          (Rotor(realAlphabet, alphabetI)(Reflector())
            .rotateUpto('M'))
          .rotateUpto('C'))
        .rotateUpto('K')))
  }

  val enigmaRoute =
    path("") {
      get {
        complete ("Post your encrypted code and see")
      } ~
      post {
        (decodeRequest(Gzip) | decodeRequest(NoEncoding)) {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) { body =>
            complete(enigma.transform( body))
          }
        }

      }
    }

}

