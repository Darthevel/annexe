package com.ehret.scoresheet.ws;

import java.io.IOException;

import javax.xml.transform.Source;

import org.springframework.util.Assert;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.WebServiceMessageFactory;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.test.server.RequestCreator;
import org.springframework.ws.test.support.creator.PayloadMessageCreator;
import org.springframework.ws.test.support.creator.WebServiceMessageCreator;

/**
 * Dans notre cas nous faisons du contract first et notre point de départ du webservice est 
 * le WSDL. Les actions (@SoapAction) liées aux opérations du webservice sont mappés à l'aide
 * de l'annotation @SoapAction. Spring-ws-test ne fournit pas de méthode permettant de 
 * spécifier correctement cette action. En effet les méthode liées à la classe {@link SoapRequestCreators}
 * ne permettent pas d'inteagir avec le header du message dans lequel nous devons indiquer la 
 * soap action 
 *
 * @author Guillaume Ehret
 */
public abstract class SoapRequestCreators {

    private SoapRequestCreators() {
    }

    /**
     * Create a request with the given {@link Source} XML as payload.
     *
     * @param payload the request payload
     * @return the request creator
     */
    public static RequestCreator withPayload(Source payload, String soapAction) {
        Assert.notNull(payload, "'payload' must not be null");
        return new WebServiceMessageCreatorAdapter(new PayloadMessageCreator(payload),soapAction);
    }

    /**
     * Adapts a {@link WebServiceMessageCreator} to the {@link RequestCreator} contract.
     */
    private static class WebServiceMessageCreatorAdapter implements RequestCreator {

        private final WebServiceMessageCreator adaptee;
        
        private String soapAction;

        private WebServiceMessageCreatorAdapter(WebServiceMessageCreator adaptee, String soapAction) {
            this.adaptee = adaptee;
            this.soapAction=soapAction;
        }

        public WebServiceMessage createRequest(WebServiceMessageFactory messageFactory) throws IOException {
           WebServiceMessage message = adaptee.createMessage(messageFactory);
           if(message instanceof SoapMessage){
              SoapMessage soapMessage = (SoapMessage) message;
              soapMessage.setSoapAction(soapAction);
           }
           return message;
        }
    }


}
