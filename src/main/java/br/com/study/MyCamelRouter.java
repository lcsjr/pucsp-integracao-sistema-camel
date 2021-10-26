package br.com.study;

import br.com.study.controller.DeadChannelProcessor;
import br.com.study.repository.DeadRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRouter extends RouteBuilder {

    @Autowired
    MyBean myBean;

	@Autowired
	DeadRepository repository;

    @Override
    public void configure() throws Exception {

		errorHandler(deadLetterChannel("file:./temp/dead")
				.onPrepareFailure(new DeadChannelProcessor(repository))
				.maximumRedeliveries(3));

    	from("file:./temp/inbox?charset=utf-8")
    	.routeId("transfer-file")
		.process("persisteProcessor")
    	.to("file:./temp/outbox")
		.delay(3000);
    }
}
