package br.com.joao.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.joao.vo.UserVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Document
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<String> implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String name;

	@Indexed(unique = true)
	private String email;

	private String password;

	private boolean status;

	private LocalDateTime creationDateTime;

	private List<GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return authorities;
	}

	@Override
	public String getPassword() {

		return password;
	}

	@Override
	public String getUsername() {

		return email;
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
	public boolean isEnabled() {

		return status;
	}

	public UserVO toVO() {

		UserVO vo = new UserVO();
		vo.setId(getId());
		vo.setEmail(email);
		vo.setName(name);
		vo.setStatus(status);
		vo.setCreationDateTime(creationDateTime);

		if (authorities != null && !authorities.isEmpty()) {
			vo.setAuthorities(new String[authorities.size()]);

			for (int i = 0; i < authorities.size(); i++) {
				vo.getAuthorities()[i] = authorities.get(i).getAuthority();
			}
		}

		return vo;
	}
}
