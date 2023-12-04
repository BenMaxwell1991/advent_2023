plugins {
    id("java")
}

group = "org.ben.maxwell"
version = "1.0-SNAPSHOT"

tasks.jar {
    manifest {
        attributes("Main-Class" to "org.ben.maxwell.Main")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:11.0.2")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}