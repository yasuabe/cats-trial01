name := "cats-trial01"

version := "0.1"

scalaVersion := "2.13.0"
resolvers += Resolver.sonatypeRepo("releases")

addCompilerPlugin("org.typelevel" %% "kind-projector" % "0.10.3")

scalacOptions ++= Seq(
  "-encoding", "utf8", // Option and arguments on same line
  "-Xfatal-warnings",  // New lines for each options
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:higherKinds",
  "-language:existentials",
  "-language:postfixOps"
)
libraryDependencies ++= Seq(
  "org.typelevel" %% "cats-core" % "2.0.0-RC1",
  "org.typelevel" %% "discipline-core" % "1.0.0",
  "org.typelevel" %% "discipline-scalatest" % "1.0.0-M1" % Test,
  "org.typelevel" %% "cats-laws" % "2.0.0-RC1" % Test,
//  "com.github.alexarchambault" %% "scalacheck-shapeless_1.13" % "1.1.8" % Test

)