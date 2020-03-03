package talk

import java.nio.charset.StandardCharsets

import org.bouncycastle.jcajce.provider.digest.SHA1

case class Token(id: String, timestamp: Long) {
  def bytesForSigning: Array[Byte] = {
    val digest = new SHA1.Digest()
    digest.digest(s"$timestamp:$id".getBytes(StandardCharsets.UTF_8))
  }

}
