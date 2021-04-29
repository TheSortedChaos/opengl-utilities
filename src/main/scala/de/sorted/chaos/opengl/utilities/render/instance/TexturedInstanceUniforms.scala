package de.sorted.chaos.opengl.utilities.render.instance

import de.sorted.chaos.opengl.utilities.render.Uniforms

object TexturedInstanceUniforms extends Uniforms {

  def createLocations(shaderProgramId: Int): Map[String, Int] =
    Set(
      createLocationFor(shaderProgramId, LocationNames.ViewMatrixLocation),
      createLocationFor(
        shaderProgramId,
        LocationNames.ProjectionMatrixLocation
      ),
      createLocationFor(
        shaderProgramId,
        LocationNames.TextureSamplerLocation
      )
    ).toMap
}
