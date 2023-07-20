package com.twogenidentity.iam.identity.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class AcrClaimConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	public static final String ACR_PREFIX = "ACR_";
	public static final String ACR_CLAIM= "acr";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

	private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

	@SuppressWarnings("unchecked")
	@Override
	public Collection<GrantedAuthority> convert(final Jwt jwt) {
        log.debug("Parsing JWT claims {}", jwt.getTokenValue());
		Collection<GrantedAuthority> collection  = jwtGrantedAuthoritiesConverter.convert(jwt);

		String acrValue = jwt.getClaims().get(ACR_CLAIM).toString();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(ACR_PREFIX + acrValue);
		collection.add(grantedAuthority);
		
		log.debug("Adding acr granted authority {}" , grantedAuthority);

		return collection;
	}
}