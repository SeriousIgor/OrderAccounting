package com.springstudy.configs;

import com.springstudy.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.springstudy")
@PropertySource({"classpath:application.properties"})
public class SpringConfiguration {
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Autowired
    Environment environment;

    /**
     *          <p>A Java Spring Bean f</p>
     * @return  void
     */
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));

        return dataSource;
    }

    /**
     *          <p>A Java Spring Bean for receiving JdbcTemplate instance for providing interaction with DataBase</p>
     * @return  A new JdbcTemplate instance with generated Data Source
     * @since   1.0
     * // TODO: 16.04.2023  Replace JDBC with Hibernate
     */
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public AuthenticationProvider authProvider(UserRepository userRepository) {
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService(userRepository));
        daoProvider.setPasswordEncoder(passwordEncoder());
        return daoProvider;
    }

    /**
     *          <p>A method for receiving encoder instance for the authentication provider</p>
     * @return  instance of BCryptPasswordEncoder class which implements a PasswordEncoder interface
     * @since   2.0
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     *          <p>Spring Bean for UserDetailsService interface. It is used for Spring Boot Security</p>
     * @return  An instance of class that implements an UserDetailsService interface
     * @since   2.0
     */
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return  username -> userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
    }

    /**
     *          <p>Spring Bean for AuthenticationManager. It is used for Spring Boot Security</p>
     * @return  An instance of AuthenticationManager received from the AuthenticationConfiguration class instance
     * @since   2.0
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
