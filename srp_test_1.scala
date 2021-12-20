import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._
import scala.language.postfixOps

class PerfTestSample extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8080")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .userAgentHeader(
      "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0"
    )
    .disableWarmUp
    .disableCaching

  val getScenario = scenario("Scenario Name")
    .exec(
      http("request_2")
        .get("/computers?f=macbook")
    )


  setUp(
    getScenario.inject(atOnceUsers(5)
  ).protocols(httpProtocol))
}
