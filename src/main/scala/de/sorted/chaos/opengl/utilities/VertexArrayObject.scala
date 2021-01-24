package de.sorted.chaos.opengl.utilities

import de.sorted.chaos.opengl.utilities.service.CleanUp
import org.lwjgl.opengl.GL11.GL_FLOAT
import org.lwjgl.opengl.GL15.{ glBindBuffer, GL_ARRAY_BUFFER }
import org.lwjgl.opengl.GL20.glVertexAttribPointer
import org.lwjgl.opengl.GL30.{ glBindVertexArray, glGenVertexArrays }

/**
  * Only a named data container instead of a unnamed Tuple2
  * @param vboId The ID of the VBO which should be used inside the VAO
  * @param sizeOfDataType The size of the data (e.g. three for 3D coordinates x, y, z or two for texture coordinates u and v
  */
final case class VaoItem(vboId: Int, sizeOfDataType: Int)

/**
 * The VAO is 'something' like a geometric objects. It holds every VBO which is needed to draw it, like
 * Vertices, TextureCoordinates, Normals, etc.
 */
object VertexArrayObject {

  /**
   * This creates the ID of the VAO
   * @param vaoItems The VBOs and their data size
   * @return the ID of the VAO
   */
  def create(vaoItems: Vector[VaoItem]): Int = {
    val id = glGenVertexArrays()
    glBindVertexArray(id)
    bindData(vaoItems)
    CleanUp.addVertexArrayObjectId(id)

    id
  }

  private def bindData(vaoItems: Vector[VaoItem]): Unit =
    vaoItems.zipWithIndex.foreach(bindVbo)

  private def bindVbo(tuple: (VaoItem, Int)): Unit = {
    val id    = tuple._1.vboId
    val size  = tuple._1.sizeOfDataType
    val index = tuple._2
    glBindBuffer(GL_ARRAY_BUFFER, id)
    glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0)
  }
}
