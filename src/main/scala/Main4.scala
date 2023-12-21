import com.softwaremill.diffx._
import com.softwaremill.diffx.generic.auto.diffForCaseClass

object Main4 extends App {

  case class Country(name: String, capital: String)

  case class Car(color : String, description : String, isGood : Boolean, numberOfDoors : Int, year : Int, country: Country)


  val text = "je suis de mons"
  val text2 = "le chocolat est vraiment bon !"

  val country1 = Country("Belgique","Bruxelles")
  val country2 = Country("France","Paris")

  val car1 = Car("red",text, false, 3, 2002,country1)
  val car2 = Car("red",text2, true, 5, 2023,country2)

  val list1 = List("zaib","khan")
  val list2 = List("zaib","kahn")

  val differentor = Differentor[List[String]](list1,list2)

  //val differentor = Differentor[Car](car1,car2)
  //val differentor = Differentor[String](text,text2)


}
