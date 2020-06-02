package CRUD3.CRUD3.config;


//@Configuration
//public class KeycloakConfig implements WebMvcConfigurer {
////
////    /**
////     * Load Keycloak configuration from application.properties or application.yml, rather than keycloak.json.
////     */
//////    @Bean
//////    public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
//////        return new KeycloakSpringBootConfigResolver();
//////    }
////
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/static/")
//                .resourceChain(true)
//                .addResolver(new PathResourceResolver() {
//                    @Override
//                    protected Resource getResource(String resourcePath,  Resource location) throws IOException    {
//                        Resource requestedResource =  location.createRelative(resourcePath);
//
//                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
//                                :  new ClassPathResource("/static/index.html");
//                    }
//                });
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("classpath:/img/");
//    }
//
//
//}
