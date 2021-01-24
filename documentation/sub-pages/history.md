# History

## 2021-01-24
I added some more basic stuff like loading a Shader and creating a Shader Program.
I also added a TextureLoader.
After this I added some missing scalaDoc for public methods/functions.

## 2021-01-23
I started a new project.
Again with [Scala][scala] and [LWJGL][lwjgl].
... and again I had to write the same functions (e.g. creating a VBO, creating a VAO, loading Shaders, loading Textures, etc.).
I decided to create a small library (this one) to extract the similarities, I wrote in the last projects.
Today I set up the project skeleton and added some function for creating a VBO and VAO and a service for cleaning up.
The goal of this small project is to have a library I can use for game development.
I also want to release this library on Maven Central (like the [WavefrontReader][wavefront reader]).

[comment]: <> (collection of links sorted alphabetically ascending)
[lwjgl]: https://www.lwjgl.org/
[scala]: https://www.scala-lang.org/
[wavefront reader]: https://github.com/TheSortedChaos/wavefront-reader
