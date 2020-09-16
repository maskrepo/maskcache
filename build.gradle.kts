val quarkusVersion: String = "1.5.0.Final"
val MaskModelVersion = "1.1.1-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.61"
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.3.72"
    `maven-publish`
}

group = "fr.convergence.proddoc.libs"
version = "1.0.5-SNAPSHOT"

// je mets ces 2 variables ici car je n'arrive pas à les mettre ailleurs
// (dans settings.gradle.kts par exemple)
val myMavenRepoUser = "myMavenRepo"
val myMavenRepoPassword ="mask"

publishing {
    repositories {
        maven {
            url = uri("https://mymavenrepo.com/repo/ah37AFHxnt3Fln1mwTvi/")
            credentials {
                username = myMavenRepoUser
                password = myMavenRepoPassword
            }
        }
        mavenLocal()
    }
    publications {
        create<MavenPublication>("MaskModel") {
            from(components["java"])
        }
    }
}

repositories {
    maven {
        url = uri("https://mymavenrepo.com/repo/OYRB63ZK3HSrWJfc2RIB/")
        credentials {
            username = myMavenRepoUser
            password = myMavenRepoPassword
        }
    }
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    // https://mvnrepository.com/artifact/javax.enterprise/cdi-api
    implementation("javax.enterprise", "cdi-api","2.0.SP1")
    implementation("fr.convergence.proddoc.libs:MaskModel:$MaskModelVersion")
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging:$quarkusVersion")
    implementation("io.quarkus:quarkus-kafka-client:$quarkusVersion")

}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

allOpen {
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("javax.ws.rs.Path")
}
