package de.sorted.chaos.opengl.utilities.core

import org.scalatest.{Matchers, WordSpec}

class TextFileReaderTest extends WordSpec with Matchers {

  "A TextFileReader" should {

    "read a file from the resource 'folder'" in {
      val filename = "/text-file.txt"
      val actual   = TextFileReader.fromResource(filename)

      actual should contain theSameElementsInOrderAs Vector(
        "line 1",
        "line 2",
        "line 3"
      )
    }

    "throw an exception when something went wrong" in {
      val filename = "/does-not-exists.txt"
      a[RuntimeException] should be thrownBy TextFileReader.fromResource(filename)
    }
  }
}
