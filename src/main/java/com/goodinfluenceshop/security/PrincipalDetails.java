package com.goodinfluenceshop.security;

import com.goodinfluenceshop.domain.Admin;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class PrincipalDetails implements UserDetails {

  private final Admin admin;

	public PrincipalDetails(Admin admin) {
		this.admin = admin;
	}

	public Admin getAdmin() {
        return admin;
    }

	@Override
	public String getPassword() {
		return admin.getPassword();
	}
  @Override
  public String getUsername() {
    return admin.getEmail(); // 또는 적절한 사용자 이름 필드
  }

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { return true; }

    /**
	 *  Admin Role 파싱하는 함수.
	 *  @return Collection<? extends GrantedAuthority> authorities
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		admin.getRoleList().forEach(adminRoleType -> {
      authorities.add(() -> adminRoleType.getRoleType().getRoleType().name());
    });
		return authorities;
	}

}
