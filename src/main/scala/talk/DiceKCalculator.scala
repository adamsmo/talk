package talk

import java.math.BigInteger
import java.security.SecureRandom

import org.bouncycastle.crypto.signers.DSAKCalculator

class DiceKCalculator extends DSAKCalculator {
  override def isDeterministic: Boolean = true

  override def init(n: BigInteger, random: SecureRandom): Unit = ()

  override def init(n: BigInteger, d: BigInteger, message: Array[Byte]): Unit =
    ()

  /*
  chosen by fair dice roll, guaranteed to be random!
   */
  override def nextK(): BigInteger = BigInteger.valueOf(4)
}
