package br.inf.audasi.api.helper;

import java.io.Serializable;
import java.util.List;

/**
 * @author renatomoitinhodias@gmail.com
 */
public class ApiAuthorization implements Serializable {

    private String resourceId;
    private String serverRedirect;
    private List<ApiAuthorizationConfiguration> apiAuthorizationConfigurations;

    public String getServerRedirect() {
        return serverRedirect;
    }

    public void setServerRedirect(String serverRedirect) {
        this.serverRedirect = serverRedirect;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public List<ApiAuthorizationConfiguration> getApiAuthorizationConfigurations() {
        return apiAuthorizationConfigurations;
    }

    public void setApiAuthorizationConfigurations(List<ApiAuthorizationConfiguration> apiAuthorizationConfigurations) {
        this.apiAuthorizationConfigurations = apiAuthorizationConfigurations;
    }

    public static class ApiAuthorizationConfiguration implements Serializable {
        private String clientId;
        private String clientSecret;
        private String[] scope;
        private String[] authorizedGrantTypes;
        private String[] authorities;
        private Integer accessTokenValiditySeconds;
        private Integer refreshTokenValiditySeconds;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String[] getScope() {
            return scope;
        }

        public void setScope(String... scope) {
            this.scope = scope;
        }

        public String[] getAuthorizedGrantTypes() {
            return authorizedGrantTypes;
        }

        public void setAuthorizedGrantTypes(String... authorizedGrantTypes) {
            this.authorizedGrantTypes = authorizedGrantTypes;
        }

        public String[] getAuthorities() {
            return authorities;
        }

        public void setAuthorities(String... authorities) {
            this.authorities = authorities;
        }

        public Integer getAccessTokenValiditySeconds() {
            return accessTokenValiditySeconds;
        }

        public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
            this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        }

        public Integer getRefreshTokenValiditySeconds() {
            return refreshTokenValiditySeconds;
        }

        public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
            this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        }
    }
}
