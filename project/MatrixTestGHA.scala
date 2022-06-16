object MatrixTestGHA {
  import java.security.MessageDigest
  import scala.util.Try

  private val parseInt = (s: String) => Try { s.toInt } toOption

  private val matrixIdStringEnv: Option[Int] =
    sys.env.get("MATRIX_TEST_ID") flatMap parseInt
  private val matrixSize: Option[Int] =
    sys.env.get("MATRIX_SIZE") flatMap parseInt

  private val md5digest = MessageDigest.getInstance("MD5")
  private def calcTestDigest(s: String): Byte = {
    md5digest.digest(s.getBytes()).last
  }

  def filterTestIfMatrix(s: String): Boolean = {
    matrixIdStringEnv match {
      case None => true
      case Some(matrixId) =>
        val testNameDigest = calcTestDigest(s) % matrixSize.get
        println(s"this test is $testNameDigest: this job is for $matrixId")
        testNameDigest == matrixId
    }
  }
}
