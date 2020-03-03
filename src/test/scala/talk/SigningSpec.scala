package talk

import java.math.BigInteger

import org.scalatest.{FlatSpec, Matchers}

class SigningSpec extends FlatSpec with Matchers {
  "signer" should "produce correct signature" in {
    //given
    val privateKey =
      "44020329788893490135623262343737356251704756286890709590024382843043053401949"
    val D = new BigInteger(privateKey)
    val kp = SigningService.keyPairFromPrivate(D)
    val token = Token("uuid", 4242424242L)

    val expected =
      ECDSASignature(
        new BigInteger(
          "103388573995635080359749164254216598308788835304023601477803095234286494993683"
        ),
        new BigInteger(
          "61045177497390332393427622694305753521685077519290424359131506620097151945827"
        )
      )

    //when
    val signature = SigningService.sign(token.bytesForSigning, kp)

    //then
    signature shouldBe expected
  }
}
