package io.javabrains.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import io.javabrains.LoginController;

/**
 * Spring Security設定クラス.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private UserDetailsService userDetailsService;
//　あっているのか確認してくれる
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
//        httpRequestの認証を実際にやってくれる
//		/registは全員アクセスできる
//		/loginはログイン可能ではないと行けない userとpassで認証している
		httpSecurity.authorizeRequests().antMatchers("/regist").permitAll().anyRequest().authenticated();
//		.and().exceptionHandling().accessDeniedPage("/error.html");
        httpSecurity.formLogin().loginPage(LoginController.PAGE).usernameParameter("email")
                .passwordParameter("password").permitAll().defaultSuccessUrl("/?size=20&page=0&name=");
//        httpSecurity.and().exceptionHandling().accessDeniedPage("/access-d.html");
//        permitAllで認証可能なのかを確認している
    }
	 
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception
    {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService);
    }
    
//    @Configuration
//    protected static class AuthenticationConfiguration
//    extends GlobalAuthenticationConfigurerAdapter {
//        @Autowired
//        UserServiceImpl userDetailsService;
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            // 認証するユーザーを設定する
//            auth.userDetailsService(userDetailsService)
//            // 入力値をbcryptでハッシュ化した値でパスワード認証を行う
//            .passwordEncoder(new BCryptPasswordEncoder());
//
//        }
//    }
//    
//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
    
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }
}
