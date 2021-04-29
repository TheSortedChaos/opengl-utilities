package de.sorted.chaos.opengl.utilities.render.instance

import de.sorted.chaos.opengl.utilities.render.Uploader
import org.joml.Matrix4f
import org.lwjgl.opengl.GL11.{ glBindTexture, GL_TEXTURE_2D }
import org.lwjgl.opengl.GL13.{ glActiveTexture, GL_TEXTURE0 }
import org.lwjgl.opengl.GL20.glUniform1i

/**
  * This class is for uploading data to the graphic card.
  * The data in this case is: ViewMatrix, ProjectionMatrix and a Texture
  * @param shaderProgramId the id of the shader Program
  */
class DataUploader(private val shaderProgramId: Int) extends Uploader {

  /**
    * This Map hold the location name and the corresponding id
    */
  private val UniformLocations = TexturedInstanceUniforms.createLocations(shaderProgramId)

  /**
    * This upload the data (to the defined locations) to the graphic card
    * @param viewMatrix the view matrix
    * @param projectionMatrix the projection matrix
    * @param textureId the id of the texture
    */
  def upload(viewMatrix: Matrix4f, projectionMatrix: Matrix4f, textureId: Int): Unit = {
    uploadMatrix4f(
      UniformLocations(LocationNames.ViewMatrixLocation),
      viewMatrix
    )
    uploadMatrix4f(
      UniformLocations(LocationNames.ProjectionMatrixLocation),
      projectionMatrix
    )
    activateTexture(
      UniformLocations(LocationNames.TextureSamplerLocation),
      textureId
    )
  }

  private def activateTexture(locationId: Int, textureId: Int): Unit = {
    glUniform1i(locationId, 0)
    glActiveTexture(GL_TEXTURE0)
    glBindTexture(GL_TEXTURE_2D, textureId)
  }
}

object DataUploader {

  def apply(shaderProgramId: Int): DataUploader = new DataUploader(shaderProgramId)
}
