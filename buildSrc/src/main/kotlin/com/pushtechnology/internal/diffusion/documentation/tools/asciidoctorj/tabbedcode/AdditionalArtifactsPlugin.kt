package com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginConvention
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.bundling.Jar
import org.gradle.api.tasks.javadoc.Groovydoc
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.kotlin.dsl.*

class AdditionalArtifactsPlugin: Plugin<Project> {
    override fun apply(project: Project): Unit = project.run {
        val sourceSets = the<SourceSetContainer>()
        val javadoc: Javadoc by tasks.getting

        tasks.create("sourcesJar", Jar::class) {
            classifier = "sources"
            from(sourceSets["main"].allSource)
        }

        tasks.create("javadocJar", Jar::class) {
            dependsOn(javadoc)
            classifier = "javadoc"
            from(javadoc.destinationDir)
        }
    }
}