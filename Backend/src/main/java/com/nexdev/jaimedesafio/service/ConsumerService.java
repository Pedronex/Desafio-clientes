package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.dto.request.FormConsumerDto;
import com.nexdev.jaimedesafio.dto.request.IndividualDto;
import com.nexdev.jaimedesafio.dto.request.LegalDto;
import com.nexdev.jaimedesafio.entity.Consumer;
import com.nexdev.jaimedesafio.entity.Individual;
import com.nexdev.jaimedesafio.entity.Legal;
import com.nexdev.jaimedesafio.entity.User;
import com.nexdev.jaimedesafio.provider.TokenProvider;
import com.nexdev.jaimedesafio.repository.ConsumerRepository;
import com.nexdev.jaimedesafio.repository.IndividualRepository;
import com.nexdev.jaimedesafio.repository.LegalRepository;
import com.nexdev.jaimedesafio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;
    private final IndividualRepository individualRepository;
    private final LegalRepository legalRepository;
    private final UserRepository userRepository;
    private final TokenProvider provider;

    // Método para criar um consumidor (cliente) com base nos dados fornecidos no formulário (FormConsumerDto)
    public ResponseEntity<String> createConsumer(FormConsumerDto formConsumerDto, String token) {
        String result = provider.verifyToken(token.split(" ")[1]);
        try {
            if (userRepository.findByLogin(result).isPresent()) {
                User user = userRepository.findByLogin(result).get();

                Consumer consumer = Consumer.builder()
                        .phone(formConsumerDto.getPhone())
                        .user(user)
                        .build();

                // Criar ou atualizar os dados do Consumidor Individual ou Jurídico com base no formulário
                createPerson(formConsumerDto, consumer);
                consumerRepository.save(consumer);

                // Retornar uma resposta com código de status CREATED (201) e uma mensagem indicando que o cliente foi cadastrado
                return ResponseEntity.status(HttpStatus.CREATED).body("Cliente Cadastrado!");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não localizado");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Se ocorrer um erro durante a criação do consumidor, retornar uma resposta com código de status BAD REQUEST (400) e uma mensagem de erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível determinar o erro");
        }
    }

    // Método para atualizar um consumidor (cliente) existente com base nos dados fornecidos no formulário (FormConsumerDto) e no ID do consumidor
    public ResponseEntity<String> updateConsumer(FormConsumerDto formConsumerDto, int id, String token) {
        String result = provider.verifyToken(token.split(" ")[1]);
        try {
           if (userRepository.findByLogin(result).isPresent()) {
               if (consumerRepository.findById(id).isPresent()) {
                    User user = userRepository.findByLogin(result).get();
                    Consumer consumer = consumerRepository.findById(id).get();
                    consumer.setId(id);
                    consumer.setUser(user);

                    consumer.setPhone(formConsumerDto.getPhone());

                    createPerson(formConsumerDto, consumer);
                   System.out.println(consumer);
                    consumerRepository.save(consumer);

                    return ResponseEntity.status(HttpStatus.OK).body("Consumer Atualizado");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumer não existe");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não existe");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no server");
        }
    }

    // Método auxiliar para criar ou atualizar os dados do Consumidor Individual, ou Jurídico com base no formulário (FormConsumerDto)
    private void createPerson(FormConsumerDto formConsumerDto, Consumer consumer) {
        if (formConsumerDto.getIndividual() != null) {
            var individualBuilder = Individual.builder();
            IndividualDto individualDto = formConsumerDto.getIndividual();
            Individual consumerData = consumer.getIndividual();

            String name = (consumerData != null && consumerData.getName() != null) ? consumerData.getName() : individualDto.getName();
            String ir = (consumerData != null && consumerData.getIr() != null) ? consumerData.getIr() : individualDto.getIr();
            Date birthday = (consumerData != null && consumerData.getBirthday() != null) ? consumerData.getBirthday() : individualDto.getBirthday();

            name = (individualDto.getName() != null) ? individualDto.getName() : name;
            ir = (individualDto.getIr() != null) ? individualDto.getIr() : ir;
            birthday = (individualDto.getBirthday() != null)? individualDto.getBirthday() : birthday;
            individualBuilder.name(name).ir(ir).birthday(birthday);

            consumer.setIndividual(individualBuilder.build());
            individualRepository.save(individualBuilder.build());
        }
        if (formConsumerDto.getLegal() != null) {
            var legalBuilder = Legal.builder();
            LegalDto legalDto = formConsumerDto.getLegal();
            Legal consumerData = consumer.getLegal();

            String corporateName = (consumerData != null && consumerData.getCorporateName() != null) ? consumerData.getCorporateName() : legalDto.getCorporateName();
            String cnpj = (consumerData != null && consumerData.getCnpj() != null) ? consumerData.getCnpj() : legalDto.getCnpj();
            String trade = (consumerData != null && consumerData.getTrade() != null) ? consumerData.getTrade() : legalDto.getTrade();

            legalBuilder.trade(trade).cnpj(cnpj).corporateName(corporateName);

            consumer.setLegal(legalBuilder.build());
            legalRepository.save(legalBuilder.build());
        }
    }

    public ResponseEntity<List<Consumer>> getAllConsumers() {
        List<Consumer> consumers = consumerRepository.findAll();
        if (consumers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(consumers);
        }
    }

    public ResponseEntity<String> deleteConsuler(int idClient) {
        consumerRepository.deleteById(idClient);
        if(consumerRepository.findById(idClient).isPresent()){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }
}

