package com.goodinfluenceshop.security;

import com.goodinfluenceshop.domain.Admin;
import com.goodinfluenceshop.exception.NoMatchingDataException;
import com.goodinfluenceshop.repository.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

	private final AdminRepository adminRepository;

	public PrincipalDetailsService(AdminRepository adminRepository) {
		this.adminRepository = adminRepository;
	}

    /**
	 *  principalDetails 생성을 위한 함수.
	 *  username으로 amdin 조회, principalDetails 생성
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(email);
		if(admin == null) {
			throw new NoMatchingDataException("admin email : " + email);
		}
		return new PrincipalDetails(admin);
	}

}
