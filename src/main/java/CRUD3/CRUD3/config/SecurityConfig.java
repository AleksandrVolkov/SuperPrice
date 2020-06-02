package CRUD3.CRUD3.config;
//
//import com.okta.spring.boot.oauth.Okta;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.access.ExceptionTranslationFilter;
//import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//import javax.servlet.*;
//import java.io.IOException;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * Registers the KeycloakAuthenticationProvider with the authentication manager.
//     *
//     * Since Spring Security requires that role names start with "ROLE_",
//     * a SimpleAuthorityMapper is used to instruct the KeycloakAuthenticationProvider
//     * to insert the "ROLE_" prefix.
//     *
//     * e.g. Librarian -> ROLE_Librarian
//     *
//     * Should you prefer to have the role all in uppercase, you can instruct
//     * the SimpleAuthorityMapper to convert it by calling:
//     * {@code grantedAuthorityMapper.setConvertToUpperCase(true); }.
//     * The result will be: Librarian -> ROLE_LIBRARIAN.
//     */
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) {
////        SimpleAuthorityMapper grantedAuthorityMapper = new SimpleAuthorityMapper();
////        grantedAuthorityMapper.setPrefix("ROLE_");
////
////        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
////        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(grantedAuthorityMapper);
////        auth.authenticationProvider(keycloakAuthenticationProvider);
////    }
////
////    /**
////     * Defines the session authentication strategy.
////     *
////     * RegisterSessionAuthenticationStrategy is used because this is a public application
////     * from the Keycloak point of view.
////     */
////    @Bean
////    @Override
////    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
////        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
////    }
////
////    /**
////     * Define an HttpSessionManager bean only if missing.
////     *
////     * This is necessary because since Spring Boot 2.1.0, spring.main.allow-bean-definition-overriding
////     * is disabled by default.
////     */
////    @Bean
////    @Override
////    @ConditionalOnMissingBean(HttpSessionManager.class)
////    protected HttpSessionManager httpSessionManager() {
////        return new HttpSessionManager();
////    }
////
////    /**
////     * Define security constraints for the application resources.
////     */
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        super.configure(http);
////        http.antMatcher("/pc").anonymous();
////        http.cors().and().csrf().disable()
////                .antMatcher("/order/orders").anonymous().and()
////
////            .authorizeRequests()
////               // .antMatchers("/order/*").anonymous()
////                .antMatchers("/file/upload").anonymous()
////                .antMatchers("/user/*").hasRole("user")
////                .antMatchers("/api/*").permitAll()
////                //.antMatchers("/pc").anonymous()
////                .anyRequest().permitAll();
////
////    }
//
//
//
////        @Override
////        protected void configure(HttpSecurity http) throws Exception {
////            http
////                    //.antMatcher("/pc").anonymous().and()
////                    .authorizeRequests()
////                    .antMatchers("user/*","order/*")
////                    .authenticated().anyRequest().permitAll()
////                    //.antMatchers("/pc").permitAll()
////                    .and()
////                    .oauth2ResourceServer().jwt();
////
////            Okta.configureResourceServer401ResponseBody(http);
////
////        }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
////        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter("authHeaderName");
////
////        filter.setAuthenticationManager(new AuthenticationManager()
////        {
////            @Override
////            public Authentication authenticate(Authentication authentication)
////                    throws AuthenticationException
////            {
////                String principal = (String) authentication.getPrincipal();
////
//////                if (!authHeaderValue.equals(principal))
//////                {
//////                    throw new BadCredentialsException("The API key was not found "
//////                            + "or not the expected value.");
//////                }
////                authentication.setAuthenticated(true);
////                return authentication;
////            }
////        });
//        http.csrf().disable()
//                //.addFilter(filter)
////                .addFilterBefore(new ExceptionTranslationFilter(
////                                new Http403ForbiddenEntryPoint()),
////                        filter.getClass()
////                )
//
//                .authorizeRequests().antMatchers("/monitor").permitAll()//.anyRequest().permitAll()
//                .and()
//                //.oauth2Login().and()
//                .oauth2ResourceServer().jwt();
//        http.cors();
//        // @formatter:on
//
//       // Okta.configureResourceServer401ResponseBody(http);
//
//    }
//
////    @Bean
////    public FilterRegistrationBean corsFilter() {
////        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        CorsConfiguration config = new CorsConfiguration();
////        config.setAllowCredentials(true);
////        config.addAllowedOrigin("*"); // @Value: http://localhost:8080
////        config.addAllowedHeader("*");
////        config.addAllowedMethod("*");
////        source.registerCorsConfiguration("/**", config);
////        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
////        bean.setOrder(0);
////        return bean;
////    }
//
//
//
//}


import CRUD3.CRUD3.repository.repos.RoleRepository;
import CRUD3.CRUD3.security.jwt.JwtConfigurer;
import CRUD3.CRUD3.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource =  location.createRelative(resourcePath);

                        return requestedResource.exists() && requestedResource.isReadable() ? requestedResource
                                :  new ClassPathResource("/static/index.html");
                    }
                });
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file://"+"/home/ilya"+"/");
    }
    private final JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RoleRepository roleRepository;
    private static final String ADMIN_ENDPOINT = "/api/v1/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/v1/auth/**";
    private static final String USER_ENDPOINT = "/h**";
    private static final String PC_ENDPOINT = "/pc/**";
    private static final String MON_ENDPOINT = "/mon/**";
    private static final String PRINTER = "/printer/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                .mvcMatchers("/*","/home/**","/assets/**","/user/**","/file/**","/img/**","comment/getcomments","/es/**").permitAll()
                .antMatchers(PRINTER).permitAll()
                .antMatchers(PC_ENDPOINT).permitAll()
                .antMatchers(MON_ENDPOINT).permitAll()
                .antMatchers("/order/**").hasAnyRole("ADMIN","USER")
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));


//        http
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
////              .antMatchers("/**").permitAll()
////                .antMatchers("/mon/**").permitAll()
////                .antMatchers("/parcer/**").permitAll()
////                .antMatchers("/parseTable/**").permitAll()
////                .antMatchers("/pc/**").permitAll()
////                .antMatchers("/printer/**").permitAll()
////                .antMatchers("/scheduled/**").permitAll()
////                .antMatchers("/api/**").permitAll()
////                .antMatchers("/file/**").permitAll()
////                .antMatchers("/order/**").permitAll()
////                .antMatchers("/p/**").permitAll()
////                .antMatchers("/user/**").permitAll()
////                .antMatchers(LOGIN_ENDPOINT).permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
//////                .antMatchers(USER_ENDPOINT).hasRole("USER")
//////                .antMatchers(MODER_ENDPOINT).hasRole("MODER")
//                .anyRequest().authenticated()
//                .and()
//                .apply(new JwtConfigurer(jwtTokenProvider));



//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
////                .mvcMatchers("/login","/registration","/registration/google").permitAll()//test
////                .anyRequest().authenticated();//----------test
//                .antMatchers(LOGIN_ENDPOINT,"/**").permitAll()
//                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
//                .anyRequest().authenticated()
////добавляет мой токен провайдер, надо вернуть
//                .and()
//                .apply(new JwtConfigurer(jwtTokenProvider));
    }
//    @Bean
//    public PrincipalExtractor principalExtractor(MyUserRepository userDetailsRepo, RoleRepository roleRepository) {
//        return map -> {
//            String id = (String) map.get("sub");
//
//            MyUser user = userDetailsRepo.findById(id).orElseGet(() -> {
//                MyUser newUser = new MyUser();
//
////из за автоматической генерации айдишника в юзере not set google id
//                // поле с email сделать уникальным, а то какова хуйа
//
//                newUser.setId(id);
//                newUser.setUserName((String) map.get("name"));
//                newUser.setFirstName((String) map.get("given_name"));
//                newUser.setLastName((String) map.get("family_name"));
//                newUser.setPicture((String) map.get("picture"));
//                newUser.setEmail((String) map.get("email"));
//                newUser.setOrders(new ArrayList<>());
//                newUser.setBacket(new Backet(newUser, new ArrayList<>()));
//
//                Role roleUser = roleRepository.findByName("ROLE_USER");
//                List<Role> userRoles = new ArrayList<>();
//                userRoles.add(roleUser);
//
//                newUser.setRoles(userRoles);
////                newUser.setGender((String) map.get("gender"));
////                newUser.setLocale((String) map.get("locale"));
//
//                return newUser;
//            });
//
////            user.setLastVisit(LocalDateTime.now());
//
//            return userDetailsRepo.save(user);
//        };
//    }
}
