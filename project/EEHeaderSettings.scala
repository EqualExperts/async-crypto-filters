object EEHeaderSettings {

  import de.heikoseeberger.sbtheader.license.Apache2_0
  import org.joda.time.DateTime

  val copyrightYear = DateTime.now().getYear.toString
  val copyrightOwner = "Equal Experts"

  def apply() = {
    Map(
      "scala" -> Apache2_0(copyrightYear, copyrightOwner),
      "conf" -> Apache2_0(copyrightYear, copyrightOwner, "#")
    )
  }
}