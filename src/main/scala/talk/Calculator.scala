package talk

import java.math.BigInteger
import java.security.SecureRandom
import java.util.Random

import org.bouncycastle.crypto.signers.DSAKCalculator

class Calculator extends DSAKCalculator {
  val r = new Random(424242L)

  override def isDeterministic: Boolean = true

  override def init(n: BigInteger, random: SecureRandom): Unit = ()

  override def init(n: BigInteger, d: BigInteger, message: Array[Byte]): Unit =
    ()

  override def nextK(): BigInteger = BigInteger.valueOf(4)
}
