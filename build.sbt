import build._

val msgpack4zPlay = crossProject.in(file(".")).settings(
  Common.settings,
  scalapropsCoreSettings,
  name := msgpack4zPlayName,
  libraryDependencies ++= (
    ("com.typesafe.play" %%% "play-json" % "2.6.0-M5") ::
    ("com.github.xuwei-k" %%% "msgpack4z-core" % "0.3.5") ::
    ("com.github.xuwei-k" %%% "msgpack4z-native" % "0.3.1" % "test") ::
    ("com.github.scalaprops" %%% "scalaprops" % "0.4.1" % "test") ::
    Nil
  )
).jsSettings(
  scalacOptions += {
    val a = (baseDirectory in LocalRootProject).value.toURI.toString
    val g = "https://raw.githubusercontent.com/msgpack4z/msgpack4z-play/" + Common.tagOrHash.value
    s"-P:scalajs:mapSourceURI:$a->$g/"
  },
  // https://github.com/marklister/base64/pull/14
  libraryDependencies += "com.github.marklister" %%% "base64" % "0.2.3" exclude("com.lihaoyi", s"utest_sjs0.6_${scalaBinaryVersion.value}"),
  scalaJSSemantics ~= { _.withStrictFloats(true) },
  scalaJSStage in Test := FastOptStage
).jvmSettings(
  libraryDependencies ++= (
    ("com.github.xuwei-k" % "msgpack4z-java" % "0.3.5" % "test") ::
    ("com.github.xuwei-k" % "msgpack4z-java06" % "0.2.0" % "test") ::
    Nil
  )
).jvmSettings(
  Sxr.settings
)

val msgpack4zPlayJVM = msgpack4zPlay.jvm
val msgpack4zPlayJS = msgpack4zPlay.js

val root = Project("root", file(".")).settings(
  Common.settings,
  commands += Command.command("testSequential"){
    Seq(msgpack4zPlayJVM, msgpack4zPlayJS).map(_.id + "/test") ::: _
  },
  PgpKeys.publishLocalSigned := {},
  PgpKeys.publishSigned := {},
  publishLocal := {},
  publish := {},
  publishArtifact in Compile := false
).aggregate(
  msgpack4zPlayJS, msgpack4zPlayJVM
)
