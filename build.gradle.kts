plugins {
    `java-library`
    `maven-publish`
}

group "org.thoriumcube.configurate"
version "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spongepowered:configurate-core:4.1.2")
    compileOnly("com.moandjiezana.toml:toml4j:0.7.2")
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
    options.release.set(8)
}

tasks.withType<Javadoc> {
    options.encoding = Charsets.UTF_8.name()
}

java {
    toolchain {
        toolchain.languageVersion.set(JavaLanguageVersion.of(8))
    }
}