import Main.HousePet.Dog
import difflicious.DiffInput.ObtainedOnly
import difflicious._
import difflicious.implicits._


object Main extends App {

  sealed trait HousePet{
    def name: String
  }

  object HousePet{
    final case class Dog(name: String, age:Int) extends HousePet

    implicit val differ: Differ[HousePet] = Differ.derived
  }
/*

  val petsDiffer = Differ[List[HousePet]]

  val dog = Dog("jack",2)
  val dogOld = Dog("jack",10)

  val res = petsDiffer.diff(ObtainedOnly(List(dog,dogOld)))

  println(res)
*/




























}
