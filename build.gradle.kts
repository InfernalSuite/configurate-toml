plugins {
    `java-library`
    `maven-publish`
}

group "org.thoriumcube.configurate"
version "4.1.2"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spongepowered:configurate-core:4.1.2")
    compileOnly("com.moandjiezana.toml:toml4j:0.7.2")
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testCompileOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testCompileOnly("org.spongepowered:configurate-core:4.1.2")
}

tasks.withType<Test> {
    useJUnit()
}

tasks.withType<JavaCompile> {
    options.encoding = Charsets.UTF_8.name()
}

tasks.withType<Javadoc> {
    options.encoding = Charsets.UTF_8.name()
}

java {
    toolchain {
        toolchain.languageVersion.set(JavaLanguageVersion.of(8))
    }
}