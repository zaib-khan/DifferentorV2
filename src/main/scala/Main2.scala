import com.softwaremill.diffx.generic.auto._
import com.softwaremill.diffx._

object Main2 extends App {
  sealed trait Car{
    val color: String
    val year: Int
  }

  case class BMW(color: String,year: Int) extends Car

  val car1 = BMW("red",2002)
  val car2 = BMW("red",2013)


  val comparasonRes = compare(car1,car2)

  val diffResultObject = comparasonRes.asInstanceOf[DiffResultObject]
  val yearValue = diffResultObject.fields("year")
  val diffResultValue = yearValue.asInstanceOf[DiffResultValue[Int]]

  println(diffResultObject.name)
  println(diffResultObject.fields)
  println(yearValue)
  println(diffResultValue.left)
  println(diffResultValue.right)
  println(comparasonRes)



  case class House(adresse: String, isUgly : Boolean)

  case class Differentor[T](left: T, right:T){
    def getRight() : T ={
      left
    }
  }














}
