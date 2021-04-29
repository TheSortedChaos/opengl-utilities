package de.sorted.chaos.opengl.utilities.render.instance

final case class TexturedInstanceIds(
    vertexArrayObjectId: Int,
    modelMatrixVboId: Int,
    textureId: Int,
    size: Int
)
