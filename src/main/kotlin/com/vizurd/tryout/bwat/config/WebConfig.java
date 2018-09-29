package com.vizurd.tryout.bwat.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private enum CustomResource {
        WEB_JARS("/webjars/**", "classpath:/META-INF/resources/webjars/", false),
        IMG("/img/**", "classpath:/static/img/", true),
        CSS("/css/**", "classpath:/static/css/", false),
        JS("/js/**", "classpath:/static/js/", false);

        private String handler;
        private String location;
        private Boolean cached;

        CustomResource(String handler, String location, boolean cached) {
            this.handler = handler;
            this.location = location;
            this.cached = cached;
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        for (CustomResource res : CustomResource.values()) {
            if (!registry.hasMappingForPattern(res.handler)) {
                registry
                        .addResourceHandler(res.handler)
                        .addResourceLocations(res.location)
                        .resourceChain(res.cached);
            }
        }
//        registry.setOrder(1);
    }
}
