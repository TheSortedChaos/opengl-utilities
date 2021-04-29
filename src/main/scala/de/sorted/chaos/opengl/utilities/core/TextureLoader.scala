package de.sorted.chaos.opengl.utilities.core

import de.sorted.chaos.opengl.utilities.service.CleanUp
import org.lwjgl.opengl.GL11._
import org.lwjgl.opengl.GL30.glGenerateMipmap
import org.lwjgl.stb.STBImage
import org.lwjgl.system.MemoryStack
import org.slf4j.LoggerFactory

import java.io.File

/**
  * Used for loading textures
  */
object TextureLoader {
  private val Log = LoggerFactory.getLogger(this.getClass)

  /**
    * This load a texture (.png file)
    * @param filename the path and filename (from resource folder)
    * @return the id of the texture
    */
  def load(filename: String): Int = {
    val stack         = MemoryStack.stackPush()
    val widthBuffer   = stack.mallocInt(1)
    val heightBuffer  = stack.mallocInt(1)
    val channelBuffer = stack.mallocInt(1)

    val file = getClass.getResource(filename).getFile
    // TODO this perhaps will create a problem, because the texture file is inside the resource folder inside the jar
    val textureFile = new File(file).getAbsolutePath
    STBImage.stbi_set_flip_vertically_on_load(true)
    val buffer = STBImage.stbi_load(
      textureFile,
      widthBuffer,
      heightBuffer,
      channelBuffer,
      4
    )
    if (buffer == null) {
      throw new IllegalArgumentException(s"Image file '$textureFile' not loaded: ${STBImage.stbi_failure_reason()}")
    }

    val width  = widthBuffer.get()
    val height = heightBuffer.get()

    val textureId = glGenTextures()
    glBindTexture(GL_TEXTURE_2D, textureId)
    glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
    glTexImage2D(
      GL_TEXTURE_2D,
      0,
      GL_RGBA,
      width,
      height,
      0,
      GL_RGBA,
      GL_UNSIGNED_BYTE,
      buffer
    )
    glGenerateMipmap(GL_TEXTURE_2D)
    STBImage.stbi_image_free(buffer)
    CleanUp.addTextureId(textureId)
    Log.info(s"Loaded texture '$filename'")

    textureId
  }
}
