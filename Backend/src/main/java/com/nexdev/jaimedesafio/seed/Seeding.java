package com.nexdev.jaimedesafio.seed;

import com.nexdev.jaimedesafio.Repository.ClienteRepository;
import com.nexdev.jaimedesafio.Repository.FisicaRepository;
import com.nexdev.jaimedesafio.Repository.JuridicaRepository;
import com.nexdev.jaimedesafio.entity.Cliente;
import com.nexdev.jaimedesafio.entity.Fisica;
import com.nexdev.jaimedesafio.entity.Juridica;
import com.nexdev.jaimedesafio.entity.Usuario;
import com.nexdev.jaimedesafio.service.UsuarioService;
import com.nexdev.jaimedesafio.util.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class Seeding implements Seed {

    @Autowired
    FisicaRepository fisicaRepository;

    @Autowired
    JuridicaRepository juridicaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    @EventListener
    public void seed(ContextRefreshedEvent event){
        try {
            create();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void create() throws Exception {
        Usuario usuario = new Usuario();

        usuario.setLogin("pedro.silva");
        usuario.setSenha(Encrypt.encrypt("master"));
        usuarioService.CreateOrUpdateUsuario(usuario);

        Fisica pessoaFisica = new Fisica();
        Juridica pessoaJuridica = new Juridica();

        pessoaFisica.setCpf("111.111.111-11");
        pessoaFisica.setNome("Lacerda");
        pessoaFisica.setDataNascimento(new Date());
        fisicaRepository.save(pessoaFisica);

        pessoaJuridica.setRazaoSocial("NEXDEV LTDA");
        pessoaJuridica.setCnpj("44.955.411/0001-06");
        pessoaJuridica.setNomeFantasia("Pedro Soares Silva");
        juridicaRepository.save(pessoaJuridica);

        Cliente cliente = new Cliente();
        cliente.setTelefone("62 91111-1111");
        cliente.setFisica(pessoaFisica);

        Cliente cliente2 = new Cliente();
        cliente2.setTelefone("62 92222-1111");
        cliente2.setJuridica(pessoaJuridica);

        System.out.println(usuario);
        cliente.setUsuario(usuario);
        cliente2.setUsuario(usuario);
        clienteRepository.save(cliente);
        clienteRepository.save(cliente2);
    }
}
