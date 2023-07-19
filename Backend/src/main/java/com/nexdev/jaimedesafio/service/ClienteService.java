package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.Repository.ClienteRepository;
import com.nexdev.jaimedesafio.Repository.FisicaRepository;
import com.nexdev.jaimedesafio.Repository.JuridicaRepository;
import com.nexdev.jaimedesafio.Repository.UsuarioRepository;
import com.nexdev.jaimedesafio.dto.request.FisicaDto;
import com.nexdev.jaimedesafio.dto.request.JuridicaDto;
import com.nexdev.jaimedesafio.util.Objects;
import com.nexdev.jaimedesafio.dto.request.ClienteDto;
import com.nexdev.jaimedesafio.entity.Cliente;
import com.nexdev.jaimedesafio.entity.Fisica;
import com.nexdev.jaimedesafio.entity.Juridica;
import com.nexdev.jaimedesafio.entity.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final FisicaRepository fisicaRepository;

    private final JuridicaRepository juridicaRepository;

    private final UsuarioRepository usuarioRepository;

    public ClienteService(ClienteRepository clienteRepository, FisicaRepository fisicaRepository, JuridicaRepository juridicaRepository, UsuarioRepository usuarioRepository) {
        this.clienteRepository = clienteRepository;
        this.fisicaRepository = fisicaRepository;
        this.juridicaRepository = juridicaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<String> createCliente(ClienteDto clienteDto){
        try{
            if(usuarioRepository.findById(clienteDto.getUserID()).isPresent()){
                Usuario usuario = usuarioRepository.findById(clienteDto.getUserID()).get();
                Cliente cliente = new Cliente();
                cliente.setTelefone(clienteDto.getTelefone());
                createPerson(clienteDto, cliente);
                cliente.setUsuario(usuario);
                clienteRepository.save(cliente);
                return ResponseEntity.status(HttpStatus.CREATED).body("Cliente Cadastrado!");

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não localizado!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ih deu erro");
        }
    }

    public ResponseEntity<String> updateCliente(ClienteDto clienteDto, String id) {
        try{
            if (usuarioRepository.findById(clienteDto.getUserID()).isPresent()){
                if(clienteRepository.findById(id).isPresent()){
                    Usuario usuario = usuarioRepository.findById(clienteDto.getUserID()).get();
                    Cliente cliente = clienteRepository.findById(id).get();
                    cliente.setTelefone(clienteDto.getTelefone());
                    new Objects().merge(cliente, clienteDto);
                    createPerson(clienteDto, cliente);
                    cliente.setUsuario(usuario);
                    cliente.setId(id);
                    clienteRepository.save(cliente);
                    return ResponseEntity.status(HttpStatus.OK).body("Cliente Atualizado");
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não existe");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no server");
        }
    }

    private void createPerson(ClienteDto clienteDto, Cliente cliente) {
        if (clienteDto.getFisicaCliente() != null){
            Fisica fisica;
            if(cliente.getFisica() != null){
                fisica = cliente.getFisica();
                new Objects().merge(fisica, clienteDto.getFisicaCliente());
            }else{
                fisica = new Fisica();
                FisicaDto fisicaDto = clienteDto.getFisicaCliente();

                fisica.setNome(fisicaDto.getNome());
                fisica.setCpf(fisicaDto.getCpf());
                fisica.setDataNascimento(fisicaDto.getNascimento());
            }
            cliente.setFisica(fisica);

            fisicaRepository.save(fisica);

        }
        if (clienteDto.getJuridicaCliente() != null){
            Juridica juridica;
            if(cliente.getJuridica() != null){
                juridica = cliente.getJuridica();
                new Objects().merge(juridica, clienteDto.getJuridicaCliente());
            }else{
                juridica = new Juridica();
                JuridicaDto juridicaDto = clienteDto.getJuridicaCliente();

                juridica.setNomeFantasia(juridicaDto.getFantasia());
                juridica.setCnpj(juridicaDto.getCnpj());
                juridica.setRazaoSocial(juridicaDto.getRazao());
            }
            cliente.setJuridica(juridica);

            juridicaRepository.save(juridica);
        }
    }
}
