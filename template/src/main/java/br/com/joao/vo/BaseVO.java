package br.com.joao.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@JsonInclude(Include.NON_NULL)
@Data
public abstract class BaseVO<I extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private I id;

}
