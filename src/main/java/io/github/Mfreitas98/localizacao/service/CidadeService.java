package io.github.Mfreitas98.localizacao.service;

import io.github.Mfreitas98.localizacao.domain.entity.Cidade;
import io.github.Mfreitas98.localizacao.domain.repository.CidadeRepository;
import static io.github.Mfreitas98.localizacao.domain.repository.specs.CidadeSpecs.*;
import io.github.Mfreitas98.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository repository;

    public CidadeService(CidadeRepository repository){
        this.repository = repository;
    }

    @Transactional
    public void listarCidadesPorNomeSort(){
        repository.
                findByNomeLike("%Porto%", Sort.by("habitantes"))
                .forEach(System.out::println);
    }

    @Transactional
    public void listarCidadesPorNomePage(){
        Pageable pageable = PageRequest.of(0, 10);
        repository.
                findByNomeLike("%%%%%", pageable)
                .forEach(System.out::println);
    }

    @Transactional
    public void listaCidadePorNomeSQL(){
        repository
                .findByNomeSqlNativo("Sao Paulo")
                .forEach(System.out::println);
    }

    @Transactional
    public void listaCidadeApenasPorNomeSQL(){
        repository
                .findByApenasNomeSQLNativo("Sao Paulo")
                .forEach(System.out::println);
    }

    @Transactional
    public void listarCidadePorNomeEIDSQL(){
        repository.findBySQLNativo("Sao Paulo")
                .stream().map(cidadeProjection ->
                        new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

    @Transactional
    public void listarCidadesPorHabitantes(){
        repository.findByHabitantes(63324L).forEach(System.out::println);
    }

    @Transactional
    public void listarCidadesQtdeHabitantesMenorQue(){
        repository.findByHabitantesLessThan(1000000L).forEach(System.out::println);
    }

    @Transactional
    public void listarCidadesQtdeHabitantesMaiorQue(){
        repository.findByHabitantesGreaterThan(1000000L).forEach(System.out::println);
    }

    @Transactional
    public void listarCidadeQtdeMenorQueEPorNome(){
        repository
                .findByHabitantesLessThanAndNomeLike(1000000L, "P%")
                .forEach(System.out::println);
    }

    @Transactional
    void salvarCidade(){
        var cidade = new Cidade(1L,"Sao Paulo", 12396372L);
        repository.save(cidade);
    }

    @Transactional
    void listarCidades(){
        repository.findAll().forEach(System.out::println);
    }


    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return repository
                .findAll(example);
    }

    public void listarCidadesByNomeSpec(){
        Specification<Cidade> spec = CidadeSpecs.nomeEqual("Sao Paulo");
        repository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadeHabitantesGreaterThanSpec(){
        Specification<Cidade> spec = CidadeSpecs.habitantesGreaterThan(100000L);
        repository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesByNomeEHabitantesGreaterThanSpec(){
        Specification<Cidade> specs =
                CidadeSpecs.nomeEqual("Porto Alegre")
                        .or(CidadeSpecs.habitantesGreaterThan(10000L));
        repository.findAll(specs).forEach(System.out::println);
    }

    public void propertyEqualSpec(){
        Specification<Cidade> spec =
                CidadeSpecs.propertyEqual("nome", "Sao Paulo")
                        .and(CidadeSpecs.propertyEqual("habitantes", 12396372L));
        repository.findAll(spec).forEach(System.out::println);
    }

    public void listarCidadesByIdSpec(){
        Specification<Cidade> spec =
                CidadeSpecs.idEqual(1l);
        repository.findAll(spec).forEach(System.out::println);
    }


    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specs =
                Specification.where((root, query, cb) -> cb.conjunction());
        // select * from cidade wher 1 = 1

        if(filtro.getId() != null){
            specs = specs.and(idEqual(filtro.getId()));
        }

        if(StringUtils.hasText(filtro.getNome())){
            specs = specs.and(nomeLike(filtro.getNome()));
        }

        if(filtro.getHabitantes() != null){
            specs = specs.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        repository.findAll(specs).forEach(System.out::println);
    }
}
