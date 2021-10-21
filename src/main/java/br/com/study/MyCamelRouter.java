package br.com.study;

import br.com.study.model.Pessoa;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRouter extends RouteBuilder {

    @Autowired
    MyBean myBean;

    @Override
    public void configure() throws Exception {
       
    	from("file:./temp/inbox?charset=utf-8")
    	.routeId("transfer-file")
    	.log("${body}")
		.process("transformBody")
		.log("${body}")
    	.to("file:./temp/outbox");
    	
    	
            
            
    }
}
