package com.efx.pet.membercenter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@PropertySource(value = "efx-ldap.yml")
@ConfigurationProperties(prefix = "com.efx.pet.ldap")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${uris}")
    List<String> ldapUris;

    @Value("${base_dn}")
    String baseDn;

    @Value("${dn_pattern}")
    String dnPattern;

    @Value("${search_base}")
    String searchBase;

    @Value("${password_attr}")
    String passwordAttr;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                .userDnPatterns(dnPattern)
                .groupSearchBase(searchBase)
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute(passwordAttr);
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(ldapUris, baseDn);
    }

}