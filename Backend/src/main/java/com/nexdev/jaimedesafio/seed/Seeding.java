package com.nexdev.jaimedesafio.seed;

import com.nexdev.jaimedesafio.repository.ConsumerRepository;
import com.nexdev.jaimedesafio.repository.IndividualRepository;
import com.nexdev.jaimedesafio.repository.LegalRepository;
import com.nexdev.jaimedesafio.entity.Consumer;
import com.nexdev.jaimedesafio.entity.Individual;
import com.nexdev.jaimedesafio.entity.Legal;
import com.nexdev.jaimedesafio.entity.User;
import com.nexdev.jaimedesafio.security.Role;
import com.nexdev.jaimedesafio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
@RequiredArgsConstructor
public class Seeding implements Seed {

    final
    IndividualRepository individualRepository;

    final
    LegalRepository legalRepository;

    final
    ConsumerRepository consumerRepository;

    final
    UserService userService;

    final PasswordEncoder encoder;

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
    public void create() {
        User user = User.builder()
                .login("admin")
                .pass(encoder.encode("master"))
                .role(Role.USER)
                .build();
        userService.createOrUpdateUser(user);

        Individual pessoaIndividual = new Individual();
        Legal pessoaLegal = new Legal();

        pessoaIndividual.setIr("111.111.111-11");
        pessoaIndividual.setName("Lacerda");
        pessoaIndividual.setBirthday(new Date());
        individualRepository.save(pessoaIndividual);

        pessoaLegal.setCorporateName("NEXDEV LTDA");
        pessoaLegal.setCnpj("44.955.411/0001-06");
        pessoaLegal.setTrade("Pedro Soares Silva");
        legalRepository.save(pessoaLegal);

        Consumer consumer = new Consumer();
        consumer.setPhone("62 91111-1111");
        consumer.setIndividual(pessoaIndividual);

        Consumer consumer2 = new Consumer();
        consumer2.setPhone("62 92222-1111");
        consumer2.setLegal(pessoaLegal);

        consumer.setUser(user);
        consumer2.setUser(user);
        consumerRepository.save(consumer);
        consumerRepository.save(consumer2);
    }
}
