import com.softwaremill.diffx.generic.auto._

import java.time.Instant
import java.time.temporal.ChronoUnit
object Main3 extends App {

  trait Subject

  case object Benefit extends Subject
  case object Material extends Subject

  case class Demand(
      demandRef: String,
      orgRef: String,
      beneRef: String,
      authorRef: String,
      coordinationCenterRef: String,
      subDemands: List[SubDemand],
      createdAt: java.time.Instant
  )
  case class SubDemand(
      subject: Subject,
      frequency: java.time.Instant,
      remark: List[String],
      activation: java.time.Instant
  )
  val now = Instant.now()
  val nowPlus1Hour = now.plus(1,ChronoUnit.HOURS)
  val subDemandOld = SubDemand(Benefit, now, List.empty, now)



  val demandOld = Demand("1", "1", "1", "1", "1", List(subDemandOld), nowPlus1Hour)

  val demandNew =
    demandOld.copy(
      orgRef = "3",
      coordinationCenterRef = "87",
      subDemands = List(subDemandOld.copy(remark = List("first remark")))
    )


  val differentor = Differentor[Demand](demandOld,demandNew)



}
