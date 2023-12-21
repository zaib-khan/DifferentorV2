ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "DifferentorTests"
  )

libraryDependencies ++= Seq(
  "com.github.jatcwang" %% "difflicious-scalatest" % "0.4.2",
  "com.softwaremill.diffx" %% "diffx-core" % "0.9.0"
)