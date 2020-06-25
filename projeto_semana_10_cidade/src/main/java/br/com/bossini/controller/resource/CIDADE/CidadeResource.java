package br.com.bossini.controller.resource.CIDADE;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.bossini.model.beans.CIDADE.Cidade;
import br.com.bossini.model.repository.CIDADE.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	private CidadeRepository cidadeRepo;

	
	
	//EXERCÍCIO PEDE: Listar cidades (feito)
	//Listar cidades com letra especifica
	//Listar cidade por lat lon (feito)
	//Cadastrar cidade (feito)
	
	@GetMapping("/listaSimples")
	public List <Cidade> todasAsCidades(){
		return cidadeRepo.findAll();
	}
	
	@GetMapping ("/buscaLatLong")
	public String LatLong (int lat, int lon) {
		return cidadeRepo.EncontrarPorLatLon(lat, lon).getNome();
	}
	
	
	@GetMapping ("/listaInicial")
	public String ProcuraInicial (String x) {
		return cidadeRepo.EncontrarPorInicial(x).getNome();
	}
	
	
	@PostMapping("/cadastrarCidade")
	public ResponseEntity<Cidade> cadastrarCidade (@RequestBody Cidade cidade, HttpServletResponse response){
		try {
			Cidade cidadeCriada = cidadeRepo.save(cidade);
			URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/{id}").buildAndExpand(cidadeCriada.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());
			return ResponseEntity.created(uri).body(cidadeCriada);
		}
		catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
}
