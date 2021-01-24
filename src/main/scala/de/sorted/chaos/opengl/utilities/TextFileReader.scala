package de.sorted.chaos.opengl.utilities

import scala.io.Source
import scala.util.{ Failure, Success, Try }

object TextFileReader {

  def fromResource(filename: String): Vector[String] =
    Try(this.getClass.getResourceAsStream(filename))
      .map(stream => Source.fromInputStream(stream).getLines().toVector) match {
      case Failure(exception) =>
        throw new RuntimeException(s"Something went wrong during reading a text file '$filename'. The exception was: $exception")
      case Success(value) => value
    }
}
