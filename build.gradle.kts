plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.freefair.lombok") version "6.3.0"
    id("io.github.patrick.remapper") version "1.2.0"
}


group = "network.frostless"
version = "1.0-SNAPSHOT"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        name = "FrostlessRepo"
        url = uri("https://repo.ricecx.cc/frostless")
        credentials(PasswordCredentials::class)
    }
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots"
    }
    maven(url = "https://repo.triumphteam.dev/releases/")


    maven {
        url = uri("https://jitpack.io")
    }

    maven {
        name = "CodeMC"
        url = uri("https://repo.codemc.org/repository/maven-public/")
    }
    maven(url = "https://repo.citizensnpcs.co/")

}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")

    // Frostshit
    compileOnly("network.frostless:BukkitAPI:0.0.1")
    compileOnly("network.frostless:frostcore:0.0.1")
    implementation("network.frostless:glacier:0.0.1")


    // ORM
    compileOnly("network.frostless:FrostEntities:0.0.1")
    compileOnly("com.j256.ormlite:ormlite-core:6.1")
    compileOnly("com.j256.ormlite:ormlite-jdbc:6.1")

    // Triumph
    compileOnly("dev.triumphteam:triumph-gui:3.1.2")

    compileOnly("de.tr7zw:item-nbt-api-plugin:2.9.2")
}

tasks {
    shadowJar {
        minimize {
            enabled = true
        }

        // This here does not work, results in the remap task not being able to find it.
        archiveFileName.set("${project.name}-${project.version}.jar")
        destinationDirectory.set(file("$rootDir/output"))
    }
    remap {
        version.set("1.18.2")
        inputTask.set(shadowJar)
        //    archiveName.set("${project.name}-${project.version}-remapped.jar")
        archiveClassifier.set("remapped")
    }
}