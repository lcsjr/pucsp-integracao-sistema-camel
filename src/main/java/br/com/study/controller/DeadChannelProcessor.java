package br.com.study.controller;

import br.com.study.model.Dead;
import br.com.study.repository.DeadRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j2
public class DeadChannelProcessor implements Processor {

    @Autowired
    private  DeadRepository repository;

    public DeadChannelProcessor(DeadRepository repository) {
        this.repository = repository;
    }

    @Override
    public void process(Exchange exchange) {
        try {
            Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
            exchange.getIn().setHeader("FailedBecause", cause.getMessage());

            String body = exchange.getIn().getBody(String.class);

            Dead build = Dead.builder().file(body).error(cause.getMessage().substring(0, 250)).build();
            log.info("Error Dead: {}",  build );

            repository.save(build);
        } catch (Exception err) {
            log.info("Error on EXCEPTION {}", err);
        }
    }
}
