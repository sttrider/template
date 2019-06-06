package br.com.joao.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserVO extends BaseVO<String> {

	private static final long serialVersionUID = 1L;

	private String name;

	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	private Boolean status;

	private LocalDateTime creationDateTime;

	private String[] authorities;

}
