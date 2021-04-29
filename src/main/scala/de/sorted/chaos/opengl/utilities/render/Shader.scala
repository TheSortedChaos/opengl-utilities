package de.sorted.chaos.opengl.utilities.render

import de.sorted.chaos.opengl.utilities.core.ShaderProgram

object Shader {

  val InstanceShader: Int = ShaderProgram.load(
    "/shaders/instance-vertex-shader.glsl",
    "/shaders/instance-fragment-shader.glsl"
  )
}
