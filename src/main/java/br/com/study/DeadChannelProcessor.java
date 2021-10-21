package br.com.study;

import br.com.study.model.Dead;
import br.com.study.model.DeadRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
public class DeadChannelProcessor implements Processor {

    @Autowired
    private  DeadRepository repository;

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        exchange.getIn().setHeader("FailedBecause", cause.getMessage());

        log.info("Error: {}",  cause.getMessage() );

        String body = exchange.getIn().getBody(String.class);
        log.info("Error Body: {}",  body );

        Dead build = Dead.builder().file(body).error(cause.getMessage()).build();
        log.info("Error Dead: {}",  build );

        repository.save(build);

    }
}
