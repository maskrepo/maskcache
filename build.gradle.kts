
val quarkusVersion: String = "1.5.0.Final"

plugins {
    kotlin("jvm") version "1.4.10"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    `maven-publish`
    id ("org.sonarqube") version "2.7"
    id ("jacoco")
}

group = "fr.convergence.proddoc.lib"
version = "1.0.0-SNAPSHOT"

publishing {
    repositories {
        mavenLocal()
        maven {
            url = uri("https://mymavenrepo.com/repo/ah37AFHxnt3Fln1mwTvi/")
            credentials {
                username = "myMavenRepo"
                password = "mask"
            }
        }
    }
    publications {
        create<MavenPublication>("MaskModel") {
            from(components["java"])
        }
    }
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://mymavenrepo.com/repo/OYRB63ZK3HSrWJfc2RIB/")
        credentials {
            username = "myMavenRepo"
            password = "mask"
        }
    }
    mavenCentral()
}

dependencies {
    implementation("javax.enterprise", "cdi-api","2.0.SP1")
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging:$quarkusVersion")
    implementation("io.quarkus:quarkus-kafka-client:$quarkusVersion")
    implementation("fr.convergence.proddoc.lib:mask-model:1.0.0-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
}