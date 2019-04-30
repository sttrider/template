package br.com.joao.entity;

import br.com.joao.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "user")
@ToString(callSuper = true, exclude = {"password"})
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<Long> implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String name;

    private String email;

    @Column(updatable = false)
    private String password;

    private boolean status;

    @Column(name = "creation_date_time")
    private LocalDateTime creationDateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
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

        if (getAuthorities() != null && !getAuthorities().isEmpty()) {
            vo.setAuthorities(new String[getAuthorities().size()]);

            int i = 0;
            for (GrantedAuthority g : getAuthorities()) {
                vo.getAuthorities()[i++] = g.getAuthority();
            }
        }

        return vo;
    }
}
