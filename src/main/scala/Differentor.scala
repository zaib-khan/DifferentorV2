import DifferentorResults.{ElementAdded, NoChange, NotHandled, ObjectChanges, VariableChanges}
import com.softwaremill.diffx._
case class Differentor[T: Diff](oldElement: T, newElement: T) {
  val res: DiffResult = implicitly[Diff[T]].apply(oldElement, newElement)

  obtainDiff()

  private def recreateString(stringValues: List[DiffResult]): (String, String) = {
    val oldString = new StringBuilder()
    val newString = new StringBuilder()
    stringValues.foreach {
      case IdenticalValue(value) =>
        oldString.append(value)
        newString.append(value)
      case DiffResultValue(before, after) =>
        oldString.append(before)
        newString.append(after)
      case DiffResultStringWord(diffList) =>
        diffList.foreach {
          case DiffResultChunk(left, right) =>
            oldString.append(left)
            newString.append(right)
          case IdenticalValue(value) =>
            oldString.append(value)
            newString.append(value)
          case DiffResultMissingChunk(value) =>
            newString.append(value)
          case DiffResultAdditionalChunk(value) =>
            oldString.append(value)
          case x: Any =>
            println(s"Unhandled case -> $x")
        }
      case DiffResultMissingChunk(value) =>
        newString.append(value)
      case _ =>
    }
    (oldString.toString(), newString.toString())
  }

  private def extractor(diffResult: DiffResult, variableName: String = "No Name"): DifferentorResults = {
    diffResult match {
      case IdenticalValue(value)          => NoChange(variableName, value)
      case DiffResultValue(before, after) => VariableChanges(variableName, before, after)
      case DiffResultMissing(value)       => ElementAdded(value)
      case DiffResultAdditional(value)    => NotHandled("DiffResultAdditional")
      case DiffResultString(diffList)     =>
        // println("DiffResultString")
        extractor(diffList.head, variableName)
      case DiffResultStringLine(diffList) =>
        // println("DiffResultStringLine")
        val (oldString, newString) = recreateString(diffList)
        VariableChanges(variableName, oldString, newString)
      case DiffResultStringWord(diffList)       => NotHandled("DiffResultStringWord")
      case DiffResultObject(className, listMap) =>
        // println(s"class name : $className")

        val result = listMap.map { case (variableName, value) =>
          // println("------------------")
          // println(variableName)
          extractor(value, variableName)
        }.toList
        //println(result)
        ObjectChanges(variableName, result)

      case DiffResultMap(variableName, map)      => NotHandled("DiffResultMap")
      case DiffResultIterable(variableName, map) => NotHandled("DiffResultIterable")
      case DiffResultSet(variableName, diffSet)  => NotHandled("DiffResultSet")
      case DiffResultChunk(left, right)          => NotHandled("DiffResultChunk")
      case _ =>
        println("unhandled")
        NotHandled("other")
    }
  }

  private def obtainDiff(): Unit = {
    val test = extractor(res) match {
      case res: NoChange[_]        => println("no")
      case res: VariableChanges[_] => println("variable")
      case res: ObjectChanges      => println(res.listOfChanges)
      case _                       =>
    }
  }

}
