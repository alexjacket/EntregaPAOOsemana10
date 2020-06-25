package br.com.bossini.model.repository.CIDADE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bossini.model.beans.CIDADE.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

	public Cidade EncontrarPorLatLon (int lat, int lon); 
	
	public Cidade EncontrarPorInicial(String x);
	
}


