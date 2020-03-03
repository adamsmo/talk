package talk

import java.math.BigInteger

import org.bouncycastle.crypto.params.{
  ECPrivateKeyParameters,
  ECPublicKeyParameters
}

object Main {
  def main(args: Array[String]): Unit = {
    val privateKey =
      "44020329788893490135623262343737356251704756286890709590024382843043053401949"
    val D = new BigInteger(privateKey)
    val kp = SigningService.keyPairFromPrivate(D)

    println("==============keys==============")
    println(kp.getPrivate.asInstanceOf[ECPrivateKeyParameters].getD)
    println(kp.getPublic.asInstanceOf[ECPublicKeyParameters].getQ)
    println("==============keys==============")

    val token = Token("uuid", 4242424242L)
    val signature = SigningService.sign(token.bytesForSigning, kp)

    println("r1")
    println(signature.r)
    println("s1")
    println(signature.s)

  }
}
