package de.sorted.chaos.opengl.utilities

import de.sorted.chaos.opengl.utilities.service.CleanUp
import org.lwjgl.opengl.GL11.GL_FALSE
import org.lwjgl.opengl.GL20._

/**
  * A Shader Program is a small program (in GLSL) responsible for drawing stuff. It can be executed (parallel)
  * by the shaders of your Graphic Card.
  */
object ShaderProgram {

  /**
    * This creates the Shader Program from two shader required shader files.
    * @param vertexShaderFile The shader file which is responsible for the geometry stuff
    * @param fragmentShaderFile The shader file which is responsible for the color stuff
    * @return The ID of the shader program
    */
  def load(vertexShaderFile: String, fragmentShaderFile: String): Int = {
    val vertexShaderCode   = TextFileReader.fromResource(vertexShaderFile)
    val fragmentShaderCode = TextFileReader.fromResource(fragmentShaderFile)
    val id                 = glCreateProgram()
    val vertexShaderId     = compile(vertexShaderCode, GL_VERTEX_SHADER)
    val fragmentShaderId   = compile(fragmentShaderCode, GL_FRAGMENT_SHADER)

    attachShaders(id, vertexShaderId, fragmentShaderId)
    linkProgram(id)
    validateProgram(id)
    detachShaders(id, vertexShaderId, fragmentShaderId)

    CleanUp.addShaderIds(vertexShaderId)
    CleanUp.addShaderIds(fragmentShaderId)
    CleanUp.addShaderProgramId(id)

    id
  }

  private def detachShaders(programId: Int, vertexShaderId: Int, fragmentShaderId: Int): Unit = {
    glDetachShader(programId, vertexShaderId)
    glDetachShader(programId, fragmentShaderId)
  }

  private def validateProgram(programId: Int): Unit = {
    glValidateProgram(programId)
    if (glGetProgrami(programId, GL_VALIDATE_STATUS) == GL_FALSE) {
      throw new RuntimeException("Validating shader failed: \n" + glGetProgramInfoLog(programId))
    }
  }

  private def linkProgram(programId: Int): Unit = {
    glLinkProgram(programId)
    if (glGetProgrami(programId, GL_LINK_STATUS) == GL_FALSE) {
      throw new RuntimeException("Linking shader failed: \n" + glGetProgramInfoLog(programId))
    }
  }

  private def attachShaders(programId: Int, vertexShaderId: Int, fragmentShaderId: Int): Unit = {
    glAttachShader(programId, vertexShaderId)
    glAttachShader(programId, fragmentShaderId)
  }

  private def compile(shaderCode: Vector[String], shaderType: Int) = {
    val shaderId = glCreateShader(shaderType)
    if (shaderId == 0) {
      throw new RuntimeException("Something went wrong during init shaderId.")
    }

    val sourceCode = shaderCode.filter(item => item != "").mkString("\n")
    glShaderSource(shaderId, sourceCode)
    glCompileShader(shaderId)

    if (glGetShaderi(shaderId, GL_COMPILE_STATUS) == GL_FALSE) {
      throw new RuntimeException("Compiling shader failed: \n" + glGetShaderInfoLog(shaderId))
    } else {
      shaderId
    }
  }
}
