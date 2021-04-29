package de.sorted.chaos.opengl.utilities.render

import org.lwjgl.opengl.GL20.glGetUniformLocation

/**
  * The base of all Uniforms.
  */
trait Uniforms {

  protected def createLocationFor(shaderProgramId: Int, name: String): (String, Int) = {
    val locationId = glGetUniformLocation(shaderProgramId, name)
    (name, locationId)
  }
}
