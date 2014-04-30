import Dependencies._

// To add UI library dependencies, prefer to use WebJars instead of a CDN.
// Find webjars here: http://www.webjars.org/

libraryDependencies ++= Seq(
  "org.webjars" % "angularjs" % "1.2.16",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "angular-ui-router" % "0.2.10",
  "org.webjars" % "bootstrap" % "3.1.1",
  akkaModule("actor"),
  sprayModule("routing")
)

// Helper for traversing a directory and returning a flat sequence of files
def filesOnly(source: File): Seq[File] =
  if (!source.isDirectory) source :: Nil
  else Option(source.listFiles) match {
    case None        => Nil
    case Some(files) => files flatMap filesOnly
  }

// Helper for copying one directory into another
def copyFiles(baseDirectory: File, newBase: File): Seq[File] = {
  if (!baseDirectory.exists) baseDirectory.mkdirs()
  if (!newBase.exists) newBase.mkdirs()
  val sourceFiles = filesOnly(baseDirectory)
  val mappings = sourceFiles pair rebase(Seq(baseDirectory), newBase)
  IO.copy(mappings, true).toSeq
}

compile in Compile <<= (compile in Compile).dependsOn(WebKeys.assets in Assets)

resourceGenerators in Compile <+= Def.task {
  val assets = (WebKeys.assets in Assets).value
  val assetsTarget = (WebKeys.webTarget in Assets).value
  val newBase = (resourceManaged in Compile).value / "web"
  copyFiles(assetsTarget, newBase)
}
