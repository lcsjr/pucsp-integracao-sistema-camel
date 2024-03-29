package br.com.study.controller;

import br.com.study.model.Pessoa;
import br.com.study.model.PessoaDTO;
import br.com.study.repository.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class PersisteProcessor implements Processor {

    private final PessoaRepository repository;

    public PersisteProcessor(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        String in = exchange.getIn().getBody(String.class);
        log.info("Pessoa String : {}", in);

        ObjectMapper mapper = new ObjectMapper();
        PessoaDTO obj = mapper.readValue( in, PessoaDTO.class);
        log.info("Pessoa DTO : {}", obj);

        Pessoa build = Pessoa.builder()
                .age(obj.getAge())
                .career(obj.getCareer())
                .name(obj.getName())
                .build();

        log.info("Pessoa Entity registrada: {}", build);

        repository.save(build);

    }
}
