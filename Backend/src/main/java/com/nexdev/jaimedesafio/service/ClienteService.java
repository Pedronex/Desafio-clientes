package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.Repository.ClienteRepository;
import com.nexdev.jaimedesafio.Repository.FisicaRepository;
import com.nexdev.jaimedesafio.Repository.JuridicaRepository;
import com.nexdev.jaimedesafio.Repository.UsuarioRepository;
import com.nexdev.jaimedesafio.dto.request.FisicaDto;
import com.nexdev.jaimedesafio.util.Objects;
import com.nexdev.jaimedesafio.dto.request.ClienteDto;
import com.nexdev.jaimedesafio.entity.Cliente;
import com.nexdev.jaimedesafio.entity.Fisica;
import com.nexdev.jaimedesafio.entity.Juridica;
import com.nexdev.jaimedesafio.entity.Usuario;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FisicaRepository fisicaRepository;

    @Autowired
    private JuridicaRepository juridicaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity createCliente(ClienteDto clienteDto){
        System.out.println(clienteDto.getUserID());
        try{
            System.out.println(clienteDto);
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
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ih deu erro");
        }
    }

    public ResponseEntity updateCliente(ClienteDto clienteDto, String id) {
        try{
            System.out.println(clienteDto);
            System.out.println(id);

            if (usuarioRepository.findById(clienteDto.getUserID()).isPresent()){
                if(clienteRepository.findById(id).isPresent()){
                    Usuario usuario = usuarioRepository.findById(clienteDto.getUserID()).get();
                    Cliente cliente = clienteRepository.findById(id).get();
                    cliente.setTelefone(clienteDto.getTelefone());
                    new Objects().merge(cliente, clienteDto);
                    System.out.println(cliente);
                    createPerson(clienteDto, cliente);
                    cliente.setUsuario(usuario);
                    cliente.setId(id);
                    clienteRepository.save(cliente);
                    return ResponseEntity.status(HttpStatus.OK).body("Cliente atualizado");
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não existe");
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario não existe");
            }
        }catch (Exception e) {
            System.err.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no server");
        }
    }

    private void createPerson(ClienteDto clienteDto, Cliente cliente) {
        if (clienteDto.getFisicaCliente() != null){
            Fisica fisica;
            if(cliente.getFisica() != null){
                fisica = cliente.getFisica();
                new Objects().merge(fisica, clienteDto.getFisicaCliente());
                System.out.println(fisica);
            }else{
                fisica = new Fisica();
                FisicaDto fisicaDto = clienteDto.getFisicaCliente();

                fisica.setNome(fisicaDto.getNome());
                fisica.setCpf(fisicaDto.getCpf());
                fisica.setDataNascimento(fisicaDto.getNascimento());

                System.out.println("dados: " + fisica);
            }
            cliente.setFisica(fisica);

            fisicaRepository.save(fisica);

        }
        if (clienteDto.getJuridicaCliente() != null){
            Juridica juridica = new Juridica();
            new Objects().merge(juridica, clienteDto.getJuridicaCliente());
            System.out.println(juridica);
            cliente.setJuridica(juridica);
            juridicaRepository.save(juridica);
        }
    }
}
