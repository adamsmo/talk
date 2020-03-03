package talk

import java.math.BigInteger
import java.security.SecureRandom

import org.bouncycastle.asn1.sec.SECNamedCurves
import org.bouncycastle.asn1.x9.X9ECParameters
import org.bouncycastle.crypto.AsymmetricCipherKeyPair
import org.bouncycastle.crypto.generators.ECKeyPairGenerator
import org.bouncycastle.crypto.params.{
  AsymmetricKeyParameter,
  ECDomainParameters,
  ECKeyGenerationParameters,
  ECPrivateKeyParameters,
  ECPublicKeyParameters
}
import org.bouncycastle.crypto.signers.{ECDSASigner, RandomDSAKCalculator}

object SigningService {

  //bitcoin curve
  val curveParams: X9ECParameters = SECNamedCurves.getByName("secp256k1")
  val curve: ECDomainParameters = new ECDomainParameters(
    curveParams.getCurve,
    curveParams.getG,
    curveParams.getN,
    curveParams.getH
  )

  def sign(message: Array[Byte],
           privateKey: AsymmetricKeyParameter): ECDSASignature = {

    val kCalculator = new RandomDSAKCalculator()
    //val kCalculator = new DiceKCalculator()

    val signer = new ECDSASigner(kCalculator)
    signer.init(true, privateKey)
    val components = signer.generateSignature(message)
    val x = components(0)
    val s = components(1)

    ECDSASignature(x, s)
  }

  def verify(message: Array[Byte],
             signature: ECDSASignature,
             publicKey: AsymmetricKeyParameter): Boolean = {
    val kCalculator = new RandomDSAKCalculator()
    val signer = new ECDSASigner(kCalculator)
    signer.init(false, publicKey)
    signer.verifySignature(message, signature.r, signature.s)
  }

  def generateKeyPair(): AsymmetricCipherKeyPair = {

    val secureRandom = new SecureRandom()

    val generator = new ECKeyPairGenerator
    generator.init(new ECKeyGenerationParameters(curve, secureRandom))
    generator.generateKeyPair()
  }

  def keyPairFromPrivate(D: BigInteger): AsymmetricCipherKeyPair = {
    val publicKey = curve.getG.multiply(D).normalize()
    new AsymmetricCipherKeyPair(
      new ECPublicKeyParameters(publicKey, curve),
      new ECPrivateKeyParameters(D, curve)
    )
  }
}

case class ECDSASignature(r: BigInteger, s: BigInteger)
