plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("gradle-plugin"))
    implementation("com.jfrog.bintray.gradle:gradle-bintray-plugin:1.8.4")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

gradlePlugin {
    plugins {
        register("additional-artifacts-plugin") {
            id = "com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.additional-artifacts"
            implementationClass = "com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.AdditionalArtifactsPlugin"
        }
        register("publishing-plugin") {
            id = "com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.publishing"
            implementationClass = "com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode.PublishingPlugin"
        }
    }
}