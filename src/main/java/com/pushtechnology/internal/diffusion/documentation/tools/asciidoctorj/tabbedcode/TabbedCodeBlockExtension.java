package com.pushtechnology.internal.diffusion.documentation.tools.asciidoctorj.tabbedcode;

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.extension.JavaExtensionRegistry;
import org.asciidoctor.jruby.extension.spi.ExtensionRegistry;

public class TabbedCodeBlockExtension implements ExtensionRegistry {
    @Override
    public void register(Asciidoctor asciidoctor) {
        JavaExtensionRegistry javaExtensionRegistry = asciidoctor.javaExtensionRegistry();
        javaExtensionRegistry.docinfoProcessor(TabbedCodeBlockDocInfoProcessor.class);
    }
}
