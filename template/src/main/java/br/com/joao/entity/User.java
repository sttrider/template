package br.com.joao.entity;

import br.com.joao.vo.UserVO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@ToString(callSuper = true, exclude = {"password"})
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity<String> {

    private static final long serialVersionUID = 1L;

    private String name;

    @Indexed(unique = true)
    private String email;

    private String password;

    private boolean status;

    private LocalDateTime creationDateTime;

    public UserVO toVO() {

        UserVO vo = new UserVO();
        vo.setId(getId());
        vo.setEmail(email);
        vo.setName(name);
        vo.setStatus(status);
        vo.setCreationDateTime(creationDateTime);

        return vo;
    }
}
