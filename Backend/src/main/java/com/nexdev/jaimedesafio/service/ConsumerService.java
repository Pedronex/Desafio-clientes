package com.nexdev.jaimedesafio.service;

import com.nexdev.jaimedesafio.repository.ConsumerRepository;
import com.nexdev.jaimedesafio.repository.IndividualRepository;
import com.nexdev.jaimedesafio.repository.LegalRepository;
import com.nexdev.jaimedesafio.repository.UserRepository;
import com.nexdev.jaimedesafio.dto.request.IndividualDto;
import com.nexdev.jaimedesafio.dto.request.LegalDto;
import com.nexdev.jaimedesafio.entity.Consumer;
import com.nexdev.jaimedesafio.entity.Individual;
import com.nexdev.jaimedesafio.entity.Legal;
import com.nexdev.jaimedesafio.util.Objects;
import com.nexdev.jaimedesafio.dto.request.ConsumerDto;
import com.nexdev.jaimedesafio.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    private final IndividualRepository individualRepository;

    private final LegalRepository legalRepository;

    private final UserRepository userRepository;

    public ConsumerService(ConsumerRepository consumerRepository, IndividualRepository individualRepository, LegalRepository legalRepository, UserRepository userRepository) {
        this.consumerRepository = consumerRepository;
        this.individualRepository = individualRepository;
        this.legalRepository = legalRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<String> createConsumer(ConsumerDto consumerDto){
        try{
            if(userRepository.findById(consumerDto.getUserID()).isPresent()){
                User user = userRepository.findById(consumerDto.getUserID()).get();
                Consumer consumer = new Consumer();
                consumer.setPhone(consumerDto.getPhone());
                createPerson(consumerDto, consumer);
                consumer.setUser(user);
                consumerRepository.save(consumer);
                return ResponseEntity.status(HttpStatus.CREATED).body("Cliente Cadastrado!");

            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não localizado!");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível determinar o erro");
        }
    }

    public ResponseEntity<String> updateConsumer(ConsumerDto consumerDto, String id) {
        try{
            if (userRepository.findById(consumerDto.getUserID()).isPresent()){
                if(consumerRepository.findById(id).isPresent()){
                    User user = userRepository.findById(consumerDto.getUserID()).get();
                    Consumer consumer = consumerRepository.findById(id).get();
                    consumer.setPhone(consumerDto.getPhone());
                    new Objects().merge(consumer, consumerDto);
                    createPerson(consumerDto, consumer);
                    consumer.setUser(user);
                    consumer.setId(id);
                    consumerRepository.save(consumer);
                    return ResponseEntity.status(HttpStatus.OK).body("Consumer Atualizado");
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consumer não existe");
                }

            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não existe");
            }
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no server");
        }
    }

    private void createPerson(ConsumerDto consumerDto, Consumer consumer) {
        if (consumerDto.getIndividualConsumer() != null){
            Individual individual;
            if(consumer.getIndividual() != null){
                individual = consumer.getIndividual();
                new Objects().merge(individual, consumerDto.getIndividualConsumer());
            }else{
                individual = new Individual();
                IndividualDto individualDto = consumerDto.getIndividualConsumer();

                individual.setName(individualDto.getName());
                individual.setIr(individualDto.getIr());
                individual.setBirthday(individualDto.getBirthday());
            }
            consumer.setIndividual(individual);

            individualRepository.save(individual);

        }
        if (consumerDto.getLegalConsumer() != null){
            Legal legal;
            if(consumer.getLegal() != null){
                legal = consumer.getLegal();
                new Objects().merge(legal, consumerDto.getLegalConsumer());
            }else{
                legal = new Legal();
                LegalDto legalDto = consumerDto.getLegalConsumer();

                legal.setTrade(legalDto.getTrade());
                legal.setCnpj(legalDto.getCnpj());
                legal.setCorporateName(legalDto.getCorporateName());
            }
            consumer.setLegal(legal);

            legalRepository.save(legal);
        }
    }
}
