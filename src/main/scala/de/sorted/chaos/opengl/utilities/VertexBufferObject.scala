package de.sorted.chaos.opengl.utilities

import de.sorted.chaos.opengl.utilities.service.CleanUp
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL15.{ glBindBuffer, glBufferData, glGenBuffers, GL_ARRAY_BUFFER }

import java.nio.FloatBuffer

/**
  * A Vertex Buffer Object (VBO) allows vertex data to be stored in high-performance graphics memory on the server side.
  */
object VertexBufferObject {

  /**
    * This creates a VBO for vertex data (e.g. positions, texture coordinates, normals, etc.). The id gets added to
    * the CleanUp service, so the cleanup can be done centralized.
    * @param data the data that should be uploaded (e.g. all positions of a model mesh, stored
    *             as concatenated coordinates (e.g. x, y, z) e.g. [x1, y1, z1, x2, y2, z2, ... xn, yn, zn])
    * @param drawType e.g. [[org.lwjgl.opengl.GL15.GL_STATIC_DRAW]]
    * @return the ID of the VBO
    */
  def create(data: Array[Float], drawType: Int): Int = {
    val buffer = createBuffer(data);
    val id     = uploadData(buffer, drawType)
    CleanUp.addVertexBufferObjectId(id)

    id
  }

  private def createBuffer(data: Array[Float]) = {
    val buffer = BufferUtils.createFloatBuffer(data.length)
    buffer.put(data)
    buffer.flip()

    buffer
  }

  private def uploadData(buffer: FloatBuffer, drawType: Int) = {
    val id = glGenBuffers()
    glBindBuffer(GL_ARRAY_BUFFER, id)
    glBufferData(GL_ARRAY_BUFFER, buffer, drawType)

    id
  }
}
