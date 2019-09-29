package com.udbac.versionpublish.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author dundun.wang
 * @date 2019/05/24
 */
//@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /* (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//            .formLogin().loginPage("/login")
//            .and().logout().permitAll()
//            .and()
            .authorizeRequests()
            .antMatchers("/user/login").permitAll()
            .anyRequest().authenticated();
            
            
        // TODO Auto-generated method stub
//        super.configure(http);
    }
}
