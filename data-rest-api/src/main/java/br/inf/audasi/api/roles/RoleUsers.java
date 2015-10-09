package br.inf.audasi.api.roles;

import org.springframework.security.access.annotation.Secured;

/**
 * @author renatomoitinhodias@gmail.com
 */
@Secured("ROLE_USER")
public @interface RoleUsers {}
