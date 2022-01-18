package io.github.Mfreitas98.localizacao.domain.repository;

import io.github.Mfreitas98.localizacao.domain.entity.Cidade;
import io.github.Mfreitas98.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    @Query(nativeQuery = true, value = "select * from tb_cidade as c where c.nome =:nome ")
    List<Cidade> findByNomeSqlNativo(@Param("nome") String nome);

    @Query(nativeQuery = true, value = "select c.nome from tb_cidade as c where c.nome =:nome ")
    List<String> findByApenasNomeSQLNativo(String nome);

    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome =:nome")
    List<CidadeProjection> findBySQLNativo(String nome);

    //Busca pelo nome correto
    List<Cidade> findByNome(String nome);

    //Busca pelo nome like ordenado
    @Query("select c from Cidade c where upper(c.nome) like upper(?1) ")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    //Busca pelo nome like paginado
    @Query("select c from Cidade c where upper(c.nome) like upper(?1) ")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    // Busca pelo nome comecando por..
    List<Cidade> findByNomeStartingWith(String nome);

    //Busca pelo nome terminando por..
    List<Cidade> findByNomeEndingWith(String nome);

    //Busca pelo nome contendo..
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long habitantes);

    List<Cidade> findByHabitantesLessThan(Long habitantes);

    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
}
