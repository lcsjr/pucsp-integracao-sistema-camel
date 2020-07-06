package br.com.study;

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
    	.routeId("teste")
    	  .convertBodyTo(String.class)
    	  .log("${body}")
    	  .bean(myBean, "saySomething")
    	  .log("${body}")
    	  //.to("bean:myBean")
    	  .to("file:./temp/outbox");
    	
    	
            
            
    }
}
