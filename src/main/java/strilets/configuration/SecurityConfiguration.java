package strilets.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("admin").password("12345")
				.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/")
				.permitAll()
				.antMatchers("/admin", "/admin-goods", "/admin-goods-new",
						"/admin-goods-edit", "/admin-goods-edit-{id}",
						"/admin-goods-delete-{id}", "/admin-goods-{id}",
						"/admin-goods-buy-{id}", "/admin-orders",
						"/admin-orders-delete-{id}", "/admin-goods-search",
						"/admin-goods-sort", "/admin-order-{id}",
						"/admin-order").access("hasRole('ROLE_ADMIN')").and()
				.formLogin().and().logout().permitAll().logoutUrl("/logout");
	}
}