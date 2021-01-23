package de.sorted.chaos.opengl.utilities.service

import org.lwjgl.opengl.GL15.glDeleteBuffers
import org.slf4j.LoggerFactory

/**
  * This cleanUp Service is used to release all IDs
  */
object CleanUp {
  private val Log = LoggerFactory.getLogger(this.getClass)

  private var vertexBufferObjectIds = Vector.empty[Int]

  /**
    * Adds a VBO ID to the cleanup service
    * @param id the ID of the VBO
    */
  def addVertexBufferObjectId(id: Int): Unit = vertexBufferObjectIds = vertexBufferObjectIds :+ id

  /**
    * Starts the cleanup
    */
  def apply(): Unit = {
    Log.info("Start CleanUp...")
    Log.debug("  Remove VBO IDs")
    vertexBufferObjectIds.foreach(id => glDeleteBuffers(id))
    Log.info("CleanUp done.")
  }
}
