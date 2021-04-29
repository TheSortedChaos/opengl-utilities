package de.sorted.chaos.opengl.utilities.core

import de.sorted.chaos.opengl.utilities.service.CleanUp
import org.joml.Matrix4f
import org.lwjgl.opengl.GL15.{ glBindBuffer, glBufferData, glGenBuffers, GL_ARRAY_BUFFER, GL_DYNAMIC_DRAW }
import org.lwjgl.system.MemoryUtil

object InstanceVertexBufferObject {

  private val Matrix4fSizeOfFloats = 4 * 4

  def init(): Int = {
    val id = glGenBuffers()
    CleanUp.addVertexBufferObjectId(id)

    id
  }

  def pushData(InstanceVertexBufferObjectId: Int, data: Vector[Matrix4f]): Unit = {
    // About this line I should think again
    // now every time memory is allocated - not sure what JVM GC is doing with it
    // a better approach would be to have a class (instead of object) with this memory allocated during instantiation of the class
    // and than reuse it every time - Problem the size has to be defined before.
    val matrix4fBuffer = MemoryUtil.memAllocFloat(data.size * Matrix4fSizeOfFloats)

    data.zipWithIndex.foreach {
      case (matrix, index) =>
        matrix.get(
          index * Matrix4fSizeOfFloats,
          matrix4fBuffer
        )
    }

    glBindBuffer(GL_ARRAY_BUFFER, InstanceVertexBufferObjectId)
    glBufferData(GL_ARRAY_BUFFER, matrix4fBuffer, GL_DYNAMIC_DRAW)

    MemoryUtil.memFree(matrix4fBuffer)
  }
}
