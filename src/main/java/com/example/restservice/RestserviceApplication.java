package com.example.restservice;

import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@SpringBootApplication
public class RestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestserviceApplication.class, args);
	}
}

@Data
class Estado{
	
	private Long id;
	private String nome;
	
	public Estado(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}
}

@Data
class Cidade{
	private Long id;
	private String nome;
	private Estado estado;	
	
	public Cidade(Long id, String nome, Estado estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
}

class DAO{
	public Cidade create(Cidade cidade) {
		return cidade;
	}
	
	public ArrayList read(){
		ArrayList cidades = new ArrayList();
		
		Estado saoPaulo = new Estado(new Long(12), "São Paulo");
		Estado parana = new Estado(new Long(22), "Paraná");
		Estado matoGrosso = new Estado(new Long(43), "Mato Grosso");
		Cidade cidade1 = new Cidade(new Long(1), "Londrina", parana);
		Cidade cidade2 = new Cidade(new Long(2), "Ilha Solteira", saoPaulo);
		Cidade cidade3 = new Cidade(new Long(3), "São Pualo", saoPaulo);
		
		cidades.add(cidade1);
		cidades.add(cidade2);
		cidades.add(cidade3);
		
		return cidades;
	}
	
	public Cidade update(@RequestBody Cidade cidade) {
		cidade.setNome(cidade.getNome()+" atualizado");
		return cidade;
	}
	
	public int delete(Long id) {
		return 200;
	}
		
}

@RestController
class CidadeController{
	
	@PostMapping("/cidade")
	public Cidade create(@RequestBody Cidade cidade) {
		DAO d = new DAO();
		return d.create(cidade);
	}
	
	@GetMapping("/cidade")
	public ArrayList read() {
		DAO d = new DAO();
		return d.read();
	}
	
	@PutMapping("/cidade")
	public Cidade update(@RequestBody Cidade cidade) {
		DAO d = new DAO();
		return d.update(cidade);
	}
	
	@DeleteMapping("/cidade/{id}")
	public int delete(@PathVariable Long id) {
		DAO d = new DAO();
		return d.delete(id);
	}
}

