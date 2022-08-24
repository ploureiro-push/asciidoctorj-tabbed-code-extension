plugins {
    `java-library`
    id("com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.additional-artifacts")
    id("com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.publishing")
}

group = "com.pushtechnology.internal.diffusion.documentation.tools"
version = "0.4.1"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    jcenter()
}

dependencies {
    compileOnly("org.asciidoctor:asciidoctorj:2.2.0")
    testImplementation("org.asciidoctor:asciidoctorj:2.2.0")
    testImplementation("org.jsoup:jsoup:1.11.3")
    val junitJupiterVersion = "5.3.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.junit-pioneer:junit-pioneer:0.3.0")
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
