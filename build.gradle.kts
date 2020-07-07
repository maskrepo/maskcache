plugins {
    kotlin("jvm") version "1.3.61"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    `maven-publish`
}

group = "fr.convergence.proddoc.libs"
version = "1.0.2-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("MaskCache") {
            from(components["java"])
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    // https://mvnrepository.com/artifact/javax.enterprise/cdi-api
    implementation("javax.enterprise", "cdi-api","2.0.SP1")

    implementation("fr.convergence.proddoc.libs:MaskModel:1.0.0-SNAPSHOT")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("javax.ws.rs.Path")
}