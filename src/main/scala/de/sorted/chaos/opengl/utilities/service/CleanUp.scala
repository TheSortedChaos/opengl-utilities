package de.sorted.chaos.opengl.utilities.service

import org.lwjgl.opengl.GL11.glDeleteTextures
import org.lwjgl.opengl.GL15.glDeleteBuffers
import org.lwjgl.opengl.GL20.{ glDeleteProgram, glDeleteShader }
import org.lwjgl.opengl.GL30.glDeleteVertexArrays
import org.slf4j.LoggerFactory

/**
  * This cleanUp Service is used to release all IDs
  */
object CleanUp {
  private val Log = LoggerFactory.getLogger(this.getClass)

  private var vertexBufferObjectIds = Vector.empty[Int]
  private var vertexArrayObjectIds  = Vector.empty[Int]
  private var shaderIds             = Vector.empty[Int]
  private var shaderProgramIds      = Vector.empty[Int]
  private var textureIds            = Vector.empty[Int]

  /**
    * Adds a VBO ID to the cleanup service
    * @param id the ID of the VBO
    */
  def addVertexBufferObjectId(id: Int): Unit = vertexBufferObjectIds = vertexBufferObjectIds :+ id

  /**
    * Adds a VAO ID to the cleanup service
    * @param id the ID of the VAO
    */
  def addVertexArrayObjectId(id: Int): Unit = vertexArrayObjectIds = vertexArrayObjectIds :+ id

  /**
    * Adds a Shader ID to the cleanup service
    * @param id the ID of the shader
    */
  def addShaderIds(id: Int): Unit = shaderIds = shaderIds :+ id

  /**
    * Adds a Shader program ID to the cleanup service
    * @param id the ID of the shader program
    */
  def addShaderProgramId(id: Int): Unit = shaderProgramIds = shaderProgramIds :+ id

  /**
    * Adds a texture ID to the cleanup service
    * @param id - the ID of the texture
    */
  def addTextureId(id: Int): Unit = textureIds = textureIds :+ id

  /**
    * Starts the cleanup
    */
  def apply(): Unit = {
    Log.info("Start CleanUp...")
    cleanupVbos()
    cleanupVaos()
    cleanupShaders()
    cleanupShaderPrograms()
    cleanupTextures()
    Log.info("CleanUp done.")
  }

  private def cleanupVbos(): Unit = {
    Log.debug("  Remove VBO IDs")
    vertexBufferObjectIds.foreach(id => glDeleteBuffers(id))
    vertexBufferObjectIds = Vector.empty[Int]
  }

  private def cleanupVaos(): Unit = {
    Log.debug("  Remove VAO IDs")
    vertexArrayObjectIds.foreach(id => glDeleteVertexArrays(id))
    vertexArrayObjectIds = Vector.empty[Int]
  }

  private def cleanupShaders(): Unit = {
    Log.debug("  Remove Shader IDs")
    shaderIds.foreach(id => glDeleteShader(id))
    shaderIds = Vector.empty[Int]
  }

  private def cleanupShaderPrograms(): Unit = {
    Log.debug("  Remove Shader program IDs")
    shaderProgramIds.foreach(id => glDeleteProgram(id))
    shaderProgramIds = Vector.empty[Int]
  }

  private def cleanupTextures(): Unit = {
    Log.debug("  Remove texture IDs")
    textureIds.foreach(id => glDeleteTextures(id))
    textureIds = Vector.empty[Int]
  }
}
