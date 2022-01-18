package io.github.Mfreitas98.localizacao;

import io.github.Mfreitas98.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {


	@Autowired
	private CidadeService service;


	@Override
	public void run(String... args) throws Exception {
		service.listarCidadePorNomeEIDSQL();
	}


	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}

	/*@Override
	public void run(String... args) throws Exception {
		var cidade = new Cidade(1L, "Sao Paulo", 100l);
		service.listarCidadesSpecsFiltroDinamico(cidade);
	}*/
