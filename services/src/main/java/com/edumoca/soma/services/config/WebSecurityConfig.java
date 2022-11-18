package com.edumoca.soma.services.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edumoca.soma.services.security.services.AppUserDetailsService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final AppUserDetailsService appUserDetailsService;

    private final JwtRequestFilter jwtRequestFilter;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(appUserDetailsService).passwordEncoder(passwordEncoder());
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new PasswordEncoder() {
//			
//			@Override
//			public boolean matches(CharSequence rawPassword, String encodedPassword) {
//				return true;
//			}
//			
//			@Override
//			public String encode(CharSequence rawPassword) {
//				return rawPassword.toString();
//			}
//		};
//    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        	.ignoring()
        	.antMatchers("/v3/api-docs")
        	.antMatchers("/swagger-ui.html")
        	.antMatchers("/swagger-ui/*", "/v3/api-docs/*")
        	.antMatchers("/swagger-resources/**/");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // We don't need CSRF for this example
        httpSecurity
                .cors().and()
                .csrf().disable()
                // dont authenticate this particular request
                .authorizeRequests().antMatchers("/authenticate").permitAll().and()
//                .authorizeRequests().antMatchers("/v2/api-docs").permitAll().and()
//                .authorizeRequests().antMatchers("/swagger-ui.html").permitAll().and()
//                .authorizeRequests().antMatchers("/swagger-ui.html/**/").permitAll().and()
//                .authorizeRequests().antMatchers("/swagger-resources/**/").permitAll().and()
                .authorizeRequests().antMatchers("/notifications/**").permitAll().and()
                .authorizeRequests().antMatchers("/webjars/**/").permitAll().and()
                .authorizeRequests().antMatchers("/institution/**/").permitAll().and()
                .authorizeRequests().antMatchers("/academicYear/**/").permitAll().and()
                .authorizeRequests().antMatchers("/grade/**/").permitAll().and()
                .authorizeRequests().antMatchers("/section/**/").permitAll().and()
                .authorizeRequests().antMatchers("/gradeSectionInstitutionMapping/**/").permitAll().and()
                .authorizeRequests().antMatchers("/gradeSectionInstitutionYearMapping/**/").permitAll().and()
                .authorizeRequests().antMatchers("/users/**/").permitAll().and()
                .authorizeRequests().antMatchers("/subject/**/").permitAll().and()
                .authorizeRequests().antMatchers("/role/**/").permitAll().and()
                .authorizeRequests().antMatchers("/schedule/**/").permitAll().and()
                .authorizeRequests().antMatchers("/scheduleStatus/**/").permitAll().and()
                .authorizeRequests().antMatchers("/courseSession/**/").permitAll().and()
                .authorizeRequests().antMatchers("/department/**/").permitAll().and()
                .authorizeRequests().antMatchers("/period/**/").permitAll().and()
                .authorizeRequests().antMatchers("/book/**/").permitAll().and()
                .authorizeRequests().antMatchers("/chapter/**/").permitAll().and()
                .authorizeRequests().antMatchers("/topic/**/").permitAll().and()
                .authorizeRequests().antMatchers("/teacherGradeSectionSubjectMapping/**/").permitAll().and()
                .authorizeRequests().antMatchers("/loadExcelSheet/**/").permitAll().and()
                .authorizeRequests().antMatchers("/**/").denyAll()
                // all other requests need to be authenticated
                .anyRequest().authenticated().and().
                // make sure we use stateless session; session won't be used to
                // store user's state.
                        exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}

