package de.sorted.chaos.opengl.utilities.render

import org.joml.Matrix4f
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL20.glUniformMatrix4fv

/**
  * The base of all uploader.
  */
trait Uploader {

  private val capacity     = 16
  private val MatrixBuffer = BufferUtils.createFloatBuffer(capacity)

  protected def uploadMatrix4f(locationId: Int, matrix: Matrix4f): Unit =
    glUniformMatrix4fv(
      locationId,
      false,
      matrix.get(MatrixBuffer)
    )
}
