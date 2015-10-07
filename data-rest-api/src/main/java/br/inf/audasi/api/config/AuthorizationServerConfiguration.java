package br.inf.audasi.api.config;

import br.inf.audasi.api.helper.ApiAuthorization;
import br.inf.audasi.api.service.SingleUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import java.util.Iterator;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ApiAuthorization apiAuthorization;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private SingleUserDetailsService userDetailsService;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        // @formatter:off
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        // @formatter:on
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        InMemoryClientDetailsServiceBuilder serviceBuilder = clients.inMemory();
        Iterator<ApiAuthorization.ApiAuthorizationConfiguration> authorizationIterator = apiAuthorization.getApiAuthorizationConfigurations().iterator();
        while (authorizationIterator.hasNext()) {
            ApiAuthorization.ApiAuthorizationConfiguration authorization = authorizationIterator.next();

            serviceBuilder = authorizationIterator.hasNext() ? serviceBuilder.and() : serviceBuilder;
            serviceBuilder
                    .withClient(authorization.getClientId())
                    .authorizedGrantTypes(authorization.getAuthorizedGrantTypes())
                    .authorities(authorization.getAuthorities())
                    .scopes(authorization.getScope())
                    .resourceIds(apiAuthorization.getResourceId())
                    .secret(authorization.getClientSecret())
                    .accessTokenValiditySeconds(authorization.getAccessTokenValiditySeconds())
                    .refreshTokenValiditySeconds(authorization.getRefreshTokenValiditySeconds())
                    .redirectUris(apiAuthorization.getServerRedirect());

        }
        // @formatter:on
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(tokenStore);
        return tokenServices;
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
