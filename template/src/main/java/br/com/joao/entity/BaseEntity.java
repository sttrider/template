package br.com.joao.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class BaseEntity<I extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private I id;

}
