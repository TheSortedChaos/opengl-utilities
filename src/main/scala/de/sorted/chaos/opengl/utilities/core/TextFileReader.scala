package de.sorted.chaos.opengl.utilities.core

import scala.io.Source
import scala.util.{ Failure, Success, Try }

/**
  * Simply for reading a text file (like shader files)
  */
object TextFileReader {

  /**
    * This reads a text file from the resource folder
    * @param filename The filename of the text file in the resource folder (with path)
    * @return The content of the text file as a Vector of Strings (the lines of the text file)
    */
  def fromResource(filename: String): Vector[String] =
    Try(this.getClass.getResourceAsStream(filename))
      .map(stream => Source.fromInputStream(stream).getLines().toVector) match {
      case Failure(exception) =>
        throw new RuntimeException(s"Something went wrong during reading a text file '$filename'. The exception was: $exception")
      case Success(value) => value
    }
}
