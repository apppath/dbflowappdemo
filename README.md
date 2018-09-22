# DBFlow
DBFlow is a SQLite library for Android that makes it ridiculously easy to interact and use databases. Built with Annotation Processing that generates most of the boilerplate code for you, code use within a DB is fast, efficient, and type-safe. It removes the tedious (and tough-to-maintain) database interaction code.

## Gradle Dependencies

```gradle

allProjects {
  repositories {
    // required to find the project's artifacts
    maven { url "https://www.jitpack.io" }
  }
}

def dbflow_version = "4.2.4"

annotationProcessor "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
implementation "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
implementation "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"

```


