plugins {
    `java-library`
    `maven-publish`
    signing
}

group "org.thoriumcube.configurate"
version "4.1.3"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.spongepowered:configurate-core:4.1.2")
    implementation("com.moandjiezana.toml:toml4j:0.7.2")

    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testRuntimeOnly("com.moandjiezana.toml:toml4j:0.7.2")
    testImplementation("org.spongepowered:configurate-core:4.1.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
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
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "${project.group}"
            artifactId = project.name
            version = "${project.version}"

            from(components["java"])

            pom {
                name.set("Configurate TOML")
                description.set("TOML support for the Configurate library")
                url.set("https://github.com/InfernalSuite/configurate-toml")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("Lucko")
                        name.set("Luck")
                        url.set("https://lucko.me")
                        email.set("git@lucko.me")
                    }
                    developer {
                        id.set("ComputerNerd100")
                        name.set("Philip")
                        url.set("https://github.com/ComputerNerd100")
                        email.set("philip@thoriumcube.org")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/InfernalSuite/configurate-toml.git")
                    developerConnection.set("scm:git:ssh://github.com/InfernalSuite/configurate-toml.git")
                    url.set("https://github.com/InfernalSuite/configurate-toml/")
                }
            }
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = project.property("ossrhUsername") as String?
                password = project.property("ossrhPassword") as String?
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["maven"])
}