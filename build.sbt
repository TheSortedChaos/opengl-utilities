ThisBuild / organization := "de.sorted.chaos"
ThisBuild / scalaVersion := "2.13.1"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val openglUtilities = project
  .in(file("."))
  .settings(
    name := "opengl-utilities",
    settings,
    libraryDependencies ++=
      loggingDependencies ++
        testingDependencies ++
        lwjglDependencies
  )

// ------------ [ DEPENDENCIES ] ----------------

lazy val dependencies = new {

  val jomlVersion = "1.9.19"
  val logbackVersion = "1.2.3"
  val lwjglVersion = "3.2.3"
  val scalaTestVersion = "3.0.8"
  val slf4jVersion = "1.7.30"

  val joml = "org.joml" % "joml" % jomlVersion
  val logback = "ch.qos.logback" % "logback-core" % logbackVersion
  val logbackClassic = "ch.qos.logback" % "logback-classic" % logbackVersion
  val lwjgl = "org.lwjgl" % "lwjgl" % lwjglVersion
  val lwjglGlfw = "org.lwjgl" % "lwjgl-glfw" % lwjglVersion
  val lwjglOpenGl = "org.lwjgl" % "lwjgl-opengl" % lwjglVersion
  val lwjglStb = "org.lwjgl" % "lwjgl-stb" % lwjglVersion
  val scalactic = "org.scalactic" %% "scalactic" % scalaTestVersion
  val scalatest = "org.scalatest" %% "scalatest" % scalaTestVersion % "test"
  val slf4j = "org.slf4j" % "slf4j-api" % slf4jVersion
}

lazy val loggingDependencies = Seq(
  dependencies.logback,
  dependencies.logbackClassic,
  dependencies.slf4j
)

lazy val lwjglDependencies = Seq(
  dependencies.lwjgl,
  dependencies.lwjglGlfw,
  dependencies.lwjglOpenGl,
  dependencies.lwjglStb,
  dependencies.joml
)

lazy val testingDependencies = Seq(
  dependencies.scalactic,
  dependencies.scalatest
)

// ------------ [ SETTINGS ] ----------------

lazy val settings =
  scalafmtSettings ++
    assemblySettings

lazy val scalafmtSettings = Seq(
  scalafmtOnCompile := true,
  scalafmtTestOnCompile := true,
  scalafmtVersion := "1.2.0"
)

lazy val assemblySettings = assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x                             => MergeStrategy.first
}
