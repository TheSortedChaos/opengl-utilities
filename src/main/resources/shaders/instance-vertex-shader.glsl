#version 410

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 textureCoordinate;
layout (location = 2) in mat4 modelMatrix;

uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

out vec2 fragTextureCoordinate;

void main() {
    fragTextureCoordinate = textureCoordinate;
    gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(position, 1.0);
}
