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
          "81741178846682907846734819755831793963295588831864208186536683425733000613375"
        ),
        new BigInteger(
          "10388832148012979384240928313992155153621443269566018751998086440397450874940"
        )
      )

    //when
    val signature = SigningService.sign(token.bytesForSigning, kp.getPrivate)

    //then
    signature shouldBe expected
  }
}
