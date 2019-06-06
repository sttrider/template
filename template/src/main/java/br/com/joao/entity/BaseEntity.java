package br.com.joao.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseEntity<I extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private I id;

}
