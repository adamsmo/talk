package talk

import java.math.BigInteger

import org.bouncycastle.crypto.params.{ECPrivateKeyParameters, ECPublicKeyParameters}

object Main {
  def main(args: Array[String]): Unit = {
    val privateKey =
      "44020329788893490135623262343737356251704756286890709590024382843043053401949"
    val D = new BigInteger(privateKey)
    val kyePair = SigningService.keyPairFromPrivate(D)

    println("==============keys==============")
    println(kyePair.getPrivate.asInstanceOf[ECPrivateKeyParameters].getD)
    println(kyePair.getPublic.asInstanceOf[ECPublicKeyParameters].getQ)
    println("==============keys==============")
    println
    println

    val token = Token("uuid", 4242424242L)
    val signature = SigningService.sign(token.bytesForSigning, kyePair.getPrivate)

    println(s"r ${signature.r}")
    println(s"s ${signature.s}")
    println
    println
    println(s"${token.timestamp}:${token.id}:${signature.r}:${signature.s}")
    println
    println

    val valid = SigningService.verify(token.bytesForSigning, signature, kyePair.getPublic)
    println(s"is valid: $valid")
  }
}
