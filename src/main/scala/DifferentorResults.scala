trait DifferentorResults
object DifferentorResults {
  case class NotHandled(name : String) extends DifferentorResults


  case class NoChange[T](variableName: String,value: T) extends DifferentorResults
  case class VariableChanges[T](variableName: String, before: T, after: T) extends DifferentorResults

  case class ElementAdded[T](value: T) extends DifferentorResults

  case class ObjectChanges(variableName: String,listOfChanges : List[DifferentorResults]) extends DifferentorResults

}
