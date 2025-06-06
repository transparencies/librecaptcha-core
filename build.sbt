lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.example",
      scalaVersion := "3.6.4",
      version := "0.2.1-snapshot"
      // semanticdbEnabled := true,
      // semanticdbVersion := scalafixSemanticdb.revision

      // This is apparently not supported on Scala 3 currently
      // scalafixScalaBinaryVersion := "3.1"
    )
  ),
  name := "LibreCaptcha",
  libraryDependencies += "com.sksamuel.scrimage" % "scrimage-core" % "4.3.1",
  libraryDependencies += "com.sksamuel.scrimage" % "scrimage-filters" % "4.3.1",
  libraryDependencies += "org.json4s" %% "json4s-jackson" % "4.0.7"
)

Compile / unmanagedResourceDirectories += { baseDirectory.value / "lib" }
scalacOptions ++= List(
  "-deprecation"
)
javacOptions += "-g:none"
compileOrder := CompileOrder.JavaThenScala
// javafmtOnCompile := false
assembly / mainClass := Some("lc.LCFramework")
Compile / run / mainClass := Some("lc.LCFramework")
assembly / assemblyJarName := "LibreCaptcha.jar"

ThisBuild / assemblyMergeStrategy := {
  case PathList("module-info.class")         => MergeStrategy.discard
  case x if x.endsWith("/module-info.class") => MergeStrategy.discard
  case x                                     =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

run / fork := true
