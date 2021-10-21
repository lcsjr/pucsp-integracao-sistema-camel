package br.com.study;

import br.com.study.model.Pessoa;
import br.com.study.model.PessoaDTO;
import br.com.study.model.PessoaRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TransformBody implements Processor {

    private final PessoaRepository repository;


    public TransformBody(PessoaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void process(Exchange exchange) throws Exception {

        PessoaDTO in = exchange.getIn().getBody(PessoaDTO.class);

        log.info("Pessoa DTO registrada: {}", in);

        Pessoa build = Pessoa.builder().age(in.getAge()).career(in.getCareer()).name(in.getName()).build();

        log.info("Pessoa Entity registrada: {}", build);

        repository.save(build);

    }
}
