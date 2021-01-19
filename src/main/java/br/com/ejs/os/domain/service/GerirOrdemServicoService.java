package br.com.ejs.os.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ejs.os.api.input.ComentarioInput;
import br.com.ejs.os.api.input.OrdemServicoInput;
import br.com.ejs.os.domain.exception.BusinessException;
import br.com.ejs.os.domain.exception.NaoEncotrado;
import br.com.ejs.os.domain.model.Cliente;
import br.com.ejs.os.domain.model.Comentario;
import br.com.ejs.os.domain.model.OrdemServico;
import br.com.ejs.os.domain.model.StatusOrdemSevicoEnum;
import br.com.ejs.os.domain.model.dto.ComentarioDTO;
import br.com.ejs.os.domain.model.dto.OrdemServicoDTO;
import br.com.ejs.os.domain.repository.ClienteRepository;
import br.com.ejs.os.domain.repository.ComentarioRepository;
import br.com.ejs.os.domain.repository.OrdemServicoRepository;

@Service
public class GerirOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;

	@Autowired
	private ModelMapper mapper;

	public OrdemServicoDTO adicionar(OrdemServicoInput in) {

		var ordemServico = in.toEntity();
		Cliente cliente = clienteRepository.findById(in.getidCliente())
				.orElseThrow(() -> new NaoEncotrado("Cliente não encontrado."));
		ordemServico.setCliente(cliente);
		try {
			ordemServico.setDataAbertura(OffsetDateTime.now());
			ordemServico.setStatus(StatusOrdemSevicoEnum.ABERTA);
			ordemServico = ordemServicoRepository.save(ordemServico);

		} catch (Exception e) {
			throw new BusinessException("Falha ao salvar.");
		}
		return this.entityToDTO(ordemServico);
	}

	public OrdemServicoDTO atualizar(Long ordemServicoId, OrdemServicoInput in) {

		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NaoEncotrado("Ordem servico não encontrada."));

		Cliente cliente = clienteRepository.findById(in.getidCliente())
				.orElseThrow(() -> new NaoEncotrado("Cliente não encontrado."));

		ordemServico.setDescricao(in.getDescricao());
		ordemServico.setPreco(in.getPreco());
		ordemServico.setStatus(StatusOrdemSevicoEnum.ABERTA);
		ordemServico.setCliente(cliente);

		ordemServico = ordemServicoRepository.save(ordemServico);

		return this.entityToDTO(ordemServico);
	}

	public OrdemServicoDTO buscar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NaoEncotrado(" Orddem serviço não encontrada."));
		return this.entityToDTO(ordemServico);
	}

	public OrdemServicoDTO cancelar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NaoEncotrado("Ordem serciço não encontrada."));

		if (ordemServico.podeFinalizarOuCancelar()) {
			ordemServico.setStatus(StatusOrdemSevicoEnum.CANCELADA);
			ordemServico = ordemServicoRepository.save(ordemServico);
			return this.entityToDTO(ordemServico);
		}

		throw new BusinessException("Ordem serviço não pode ser cancelada."
				+ " Pois não está aberta.");
	}
	
	public String excluir(Long ordemServicoId) {

		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NaoEncotrado("Ordem de serviço não encontrada."));

		ordemServicoRepository.delete(ordemServico);

		return "Ordem excluída";
	}

	public OrdemServicoDTO finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NaoEncotrado("Ordem serciço não encontrada."));

		if (ordemServico.podeFinalizarOuCancelar()) {
			ordemServico.setStatus(StatusOrdemSevicoEnum.FINALIZADA);
			ordemServico = ordemServicoRepository.save(ordemServico);
			return this.entityToDTO(ordemServico);
		}

		throw new BusinessException("Ordem serviço não pode ser finalizada."
				+ " Pois está cancelada ou finalizada.");
		
	}

	public List<OrdemServicoDTO> listar() {

		List<OrdemServico> lista = ordemServicoRepository.findAll();

		if (lista.isEmpty()) {
			throw new NaoEncotrado("Não há ordem de serviços registradas.");
		}

		return this.listaEntityToDTO(lista);
	}

	public List<OrdemServicoDTO> listarPorCliente(Long clienteId) {
		List<OrdemServico> lista = ordemServicoRepository.findByCliente_Id(clienteId);

		if (lista.isEmpty()) {
			throw new NaoEncotrado("Cliente não tem ordem serviços.");
		}
		return this.listaEntityToDTO(lista);
	}
	
	//########################## Métodos comentários ########################################
	
	public ComentarioDTO adicionar( ComentarioInput in) {
		
		OrdemServico  ordemServico = ordemServicoRepository.findById(in.getOrdemServicoId())
				.orElseThrow( () -> new NaoEncotrado("Ordem de serviço não encotrada.") );
		
		Comentario coment = new Comentario();
		coment.setDataEnvio(OffsetDateTime.now());
		coment.setDescricao(in.getDescricao());
		coment.setOrdemServico(ordemServico);
		
		coment = comentarioRepository.save(coment);
		return this.comentarioToDTO(coment);
	}
	
	public ComentarioDTO atualizar( Long comentarioId, String descricao ) {
		Comentario coment = comentarioRepository.findById(comentarioId)
				.orElseThrow( () -> new NaoEncotrado("Comentário não encontrado.") );
		
		coment.setDescricao(descricao);
		
		coment = comentarioRepository.save(coment);
		
		return this.comentarioToDTO(coment);
	}
	
	
	public List<ComentarioDTO> listar( Long ordemServicoId){
		
		List<Comentario> comentarios = comentarioRepository.findAll();
		
		if( comentarios.isEmpty())
			throw new NaoEncotrado("Não há comentários para esta ordem.");
		
		return listaComentarioParaDTO(comentarios);
	}
	
	
	// Métodos privados
	private OrdemServicoDTO entityToDTO(OrdemServico ordem) {
		return mapper.map(ordem, OrdemServicoDTO.class);
	}

	private List<OrdemServicoDTO> listaEntityToDTO(List<OrdemServico> ordensServico) {
		return ordensServico.stream().map(ordem -> entityToDTO(ordem)).collect(Collectors.toList());
	}
	
	private List<ComentarioDTO> listaComentarioParaDTO( List<Comentario> comentarios){
		return comentarios.stream()
				.map(comentario -> comentarioToDTO(comentario))
				.collect(Collectors.toList());
	}
	
	private ComentarioDTO comentarioToDTO( Comentario coment) {
		return mapper.map(coment, ComentarioDTO.class);
	}

}
