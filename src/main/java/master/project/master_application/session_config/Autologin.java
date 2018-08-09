package master.project.master_application.session_config;

import master.project.master_application.model.UserEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class Autologin {

	public void setSecurityontext(UserEntity userEntity) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(userEntity.getProvider().toUpperCase()));
		Authentication authentication =
			new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword(), grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
