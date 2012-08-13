// xsbt-web-plugin
resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"  

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % "0.11.1-0.2.10")

// sbteclipse
resolvers += Classpaths.typesafeResolver

addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "2.0.0")

// GWT compiler
//resolvers += "GWT plugin repo" at "http://ripla.github.com/maven"

//addSbtPlugin("net.thunderklaus" % "sbt-gwt-plugin" % "1.1-SNAPSHOT")
