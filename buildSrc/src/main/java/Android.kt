import org.gradle.api.JavaVersion

object AppConfig {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 29
    const val TARGET_SDK = 34
    const val VERSION_CODE = 3
    const val VERSION_NAME = "1.2"
}

object CompileOption {
    val JAVA_VERSION = JavaVersion.VERSION_1_8
    const val JVM_TARGET = "1.8"
}