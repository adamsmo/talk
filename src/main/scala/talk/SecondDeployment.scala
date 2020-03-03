package talk

import java.math.BigInteger

object SecondDeployment {
  def main(args: Array[String]): Unit = {
    val privateKey =
      "44020329788893490135623262343737356251704756286890709590024382843043053401949"
    val D = new BigInteger(privateKey)
    val kp = SigningService.keyPairFromPrivate(D)

    val token = Token("uuid", 4242424242L)
    val signature = SigningService.sign(token.bytesForSigning, kp.getPrivate)

    println("r1")
    println(signature.r)
    println("s1")
    println(signature.s)

    val token2 = Token("uuid", 4242424243L)
    val signature2 = SigningService.sign(token2.bytesForSigning, kp.getPrivate)

    println("r2")
    println(signature2.r)
    println("s2")
    println(signature2.s)
  }
}
