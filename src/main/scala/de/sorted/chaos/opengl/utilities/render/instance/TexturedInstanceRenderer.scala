package de.sorted.chaos.opengl.utilities.render.instance

import de.sorted.chaos.opengl.utilities.core.InstanceVertexBufferObject
import de.sorted.chaos.opengl.utilities.render.Shader
import org.joml.Matrix4f
import org.lwjgl.opengl.GL11.GL_TRIANGLES
import org.lwjgl.opengl.GL20.{ glDisableVertexAttribArray, glEnableVertexAttribArray, glUseProgram }
import org.lwjgl.opengl.GL30.glBindVertexArray
import org.lwjgl.opengl.GL31.glDrawArraysInstanced

object TexturedInstanceRenderer {

  private val ShaderProgramId = Shader.InstanceShader
  private val Uploader        = DataUploader(ShaderProgramId)

  def draw(modelMatrices: Vector[Matrix4f],
           viewMatrix: Matrix4f,
           projectionMatrix: Matrix4f,
           instanceIds: TexturedInstanceIds): Unit = {
    glBindVertexArray(instanceIds.vertexArrayObjectId)
    glUseProgram(ShaderProgramId)

    enableAttribArray()
    Uploader.upload(viewMatrix, projectionMatrix, instanceIds.textureId)
    pushModelMatrices(instanceIds, modelMatrices)
    glDrawArraysInstanced(
      GL_TRIANGLES,
      0,
      instanceIds.size,
      modelMatrices.size
    )
    disableAttribArray()
  }

  private def pushModelMatrices(mesh: TexturedInstanceIds, modelMatrices: Vector[Matrix4f]): Unit = {
    val id = mesh.modelMatrixVboId
    InstanceVertexBufferObject.pushData(id, modelMatrices)
  }

  private def enableAttribArray(): Unit =
    (0 until 3).foreach(index => glEnableVertexAttribArray(index))

  private def disableAttribArray(): Unit =
    (0 until 3).foreach(index => glDisableVertexAttribArray(index))
}
