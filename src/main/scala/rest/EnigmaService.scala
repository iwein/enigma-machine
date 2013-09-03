package rest

import spray.routing.HttpService
import spray.httpx.SprayJsonSupport
import spray.httpx.encoding.{Gzip, NoEncoding}
import org.iwein.enigma.{Alphabets, Reflector, Rotor, Enigma}

trait EnigmaService extends HttpService with SprayJsonSupport with Alphabets {

  def enigma: Enigma = Enigma(
    Rotor(realAlphabet, alphabetIII, 'V')
      (Rotor(realAlphabet, alphabetII, 'E')
        (Rotor(realAlphabet, alphabetI, 'Q')(Reflector())
          .rotateUpto('M'))
        .rotateUpto('C'))
      .rotateUpto('K'))

  val enigmaRoute =
    path("") {
      get {
        complete ("Post your encrypted code and see")
      } ~
      post {
        (decodeRequest(Gzip) | decodeRequest(NoEncoding)) {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) { body =>
            complete(enigma.transform(body))
          }
        }

      }
    } ~ path("puzzle1") {
      post {
        (decodeRequest(Gzip) | decodeRequest(NoEncoding)) {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) { body =>
            complete(enigma.transform(enigma.transform(body).reverse))
          }
        }
      }
    } ~ path("puzzle2") {
      post {
        (decodeRequest(Gzip) | decodeRequest(NoEncoding)) {
          // unmarshal with in-scope unmarshaller
          entity(as[String]) {
            body =>
              complete(enigma.transform(enigma.transform(body.reverse)))
          }
        }
      }
    }

}

