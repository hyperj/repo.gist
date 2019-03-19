package net.hyperj.gist.scala.helper

trait Person

class Student(_name: String, _age: Int) extends Person {
  var name: String = _name
  var age: Int = _age
}

object ReflectorHelper {

  implicit class ReflectorClass(person: Person) {

    def setByM(name: String, value: Any): Unit = person.getClass.getMethods.find(_.getName == name + "_$eq").get.invoke(person, value.asInstanceOf[AnyRef])

    def getByM(name: String): Any = person.getClass.getMethods.find(_.getName == name).get.invoke(person)

  }

  implicit def reflectorDef(person: Person) = new {

    def setByF(name: String, value: Any): Any = {
      val f = person.getClass.getDeclaredField(name)
      f.setAccessible(true)
      f.set(person, value)
    }

    def getByF(name: String): Any = {
      val f = person.getClass.getDeclaredField(name)
      f.setAccessible(true)
      f.get(person)
    }

    def getOrElse(name: String, value: Any): Any = {
      try {
        val f = person.getClass.getDeclaredField(name)
        f.setAccessible(true)
        return f.get(person)
      } catch {
        case e: NoSuchFieldException =>
          return value
      }
    }

  }
}

object TestApp {

  import ReflectorHelper._

  def main(args: Array[String]): Unit = {
    val student = new Student("HyperJ", 18)
    println(student.name + ": " + student.age)
    student.setByM("name", "HyperD")
    println(student.getByM("name"))
    student.setByF("age", 30)
    println(student.getByF("age"))
    println(student.getOrElse("sex", 0))
  }

}

