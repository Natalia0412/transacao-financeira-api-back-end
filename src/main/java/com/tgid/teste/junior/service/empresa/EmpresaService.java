package com.tgid.teste.junior.service.empresa;

import com.tgid.teste.junior.dto.empresa.EmpresaInserirDTO;
import com.tgid.teste.junior.dto.empresa.EmpresaRespostaDTO;
import com.tgid.teste.junior.dto.empresa.EmpresaTransacaoDTO;
import com.tgid.teste.junior.model.cliente.Cliente;
import com.tgid.teste.junior.model.empresa.Empresa;
import com.tgid.teste.junior.model.taxa.Taxa;
import com.tgid.teste.junior.repository.empresa.EmpresaRepository;
import com.tgid.teste.junior.service.cliente.ClienteService;
import com.tgid.teste.junior.service.taxa.TaxaService;
import com.tgid.teste.junior.utils.exception.InsufficientBalanceException;
import com.tgid.teste.junior.utils.exception.ResourceConflict;
import com.tgid.teste.junior.utils.exception.ResourceNotFoundException;
import com.tgid.teste.junior.utils.mapper.empresa.EmpresaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TaxaService taxaService;

    public EmpresaRespostaDTO criarEmpresa(EmpresaInserirDTO empresaDto){
        if(this.verificarCnpjExistente(empresaDto.getCnpj())){
            throw new ResourceConflict("CNPJ já cadastrado.");
        }
        Empresa empresa = EmpresaMapper.empresaDTOParaEmpresa(empresaDto);
        Empresa saved = empresaRepository.save(empresa);
        return EmpresaMapper.empresaParaEmpresaRespostaDTO(saved);
    }

    public boolean verificarEmpresaExistente(Long id){
        return empresaRepository.existsById(id);
    }

    public boolean verificarCnpjExistente(String cnpj){
        return empresaRepository.existsByCnpj(cnpj);
    }

    public List<Cliente> verificarCLienteExiste(List<Long> ids ){
        List<Cliente> clientes = new ArrayList<>();
        ids.forEach(id -> clientes.add(clienteService.retornarCliente(id)) );
        return clientes;
    }


    public Empresa depositar(Long empresaId, EmpresaTransacaoDTO dto) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa not found"));


        empresa.getTaxaList().forEach(taxa1 -> {
            dto.setValor(dto.getValor() - taxa1.getValor());
        });


        empresa.setSaldo(empresa.getSaldo() + dto.getValor());


        return empresaRepository.save(empresa);
    }

    public Empresa sacar(Long empresaId, EmpresaTransacaoDTO dto) {
        Empresa empresa = empresaRepository.findById(empresaId).orElseThrow(() -> new ResourceNotFoundException("Empresa not found"));
        empresa.getTaxaList().forEach(taxa1 -> {
            dto.setValor(dto.getValor() - taxa1.getValor());
        });


        if (empresa.getSaldo() >= dto.getValor()) {
            empresa.setSaldo(empresa.getSaldo() - dto.getValor());


            return empresaRepository.save(empresa);
        } else {
            throw new InsufficientBalanceException("Saldo insuficiente para realizar o saque");
        }
    }


    public EmpresaRespostaDTO retornarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Empresa não encontrado");
        });
        return EmpresaMapper.empresaParaEmpresaRespostaDTO(empresa);
    }


    public List<EmpresaRespostaDTO> listarTodasEmpresas(){

        return EmpresaMapper.empresaParaEmpresaRespostaDTO(empresaRepository.findAll());
    }




}
