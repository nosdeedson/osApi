package br.com.ejs.os.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ejs.os.domain.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
	
	
}
