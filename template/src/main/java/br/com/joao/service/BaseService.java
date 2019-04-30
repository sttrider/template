package br.com.joao.service;

import br.com.joao.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Classe genérica que define funções para o serviço.
 *
 * @author joao.oliveira
 *
 * @param <I> ID da entidade.
 * @param <E> {@link BaseEntity} a ser utilizada.
 */
public interface BaseService<I extends Serializable, E extends BaseEntity<I>> {

	/**
	 * Busca uma entidade por id
	 *
	 * @param id ID a ser pesquisado
	 * @return Entidade encontrada
	 */
	Optional<E> findById(I id);

	/**
	 * Salvar uma entidade
	 *
	 * @param entity Entidade a ser salvo.
	 * @return Entidade salva.
	 */
	E save(E entity);

	/**
	 * Busca todos os registros de uma entidade
	 *
	 * @return Lista de registros
	 */
	List<E> findAll();

	/**
	 * Exclui uma entidade
	 *
	 * @param delete Entidade a ser excluida
	 */
	void delete(E delete);

	/**
	 * Exclui uma entidade
	 *
	 * @param id ID a ser excluido
	 */
	void delete(I id);

	/**
	 * Verifica se o ID existe no banco.
	 * 
	 * @param id ID a ser pesquisado
	 * @return true ou false
	 */
	boolean existsById(I id);
}
