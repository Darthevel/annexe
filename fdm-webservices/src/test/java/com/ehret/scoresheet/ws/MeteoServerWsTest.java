package com.ehret.scoresheet.ws;

import static org.springframework.ws.test.server.ResponseMatchers.noFault;
import static org.springframework.ws.test.server.ResponseMatchers.serverOrReceiverFault;

import javax.xml.soap.SOAPException;
import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

/**
 * Classe de test de {@link MeteoClientWs}
 * @author Guillaume Ehret
 */
@ContextConfiguration(locations = { "/communContext.xml","/serverWsContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MeteoServerWsTest {
   
   /**
    * L'application contexte est nécessaire pour initier le mock WS
    */
   @Autowired
   private ApplicationContext applicationContext; 
   /**
    * Mock pour simuler le travail du client web service 
    */
   private MockWebServiceClient mockClientWs;
   
   @Before
   public void setUp(){
      mockClientWs = MockWebServiceClient.createClient(applicationContext);
   }
   
   /**
    * Dans notre cas le webservice renvoi un résultat aléatoire. Si ce dernier
    * est déterministe préférez l'utlisation de {@link ResponseMatchers#withPayload()} dans 
    * le expect (avec un payload que vous spécifiez) plutôt que {@link ResponseMatchers#noFault()}
    * @throws SOAPException
    */
   @Test
   public void testGetMeteoVilleOk() throws SOAPException{
      Source requestPayload = new StringSource("<appelWsMeteo ville=\"LYON\" date=\"2012-07-16T00:05:26.203\"/>");
      
      mockClientWs.sendRequest(SoapRequestCreators.withPayload(requestPayload,"http://com.meteo.fake/meteoville/1.0/read"))
             .andExpect(noFault());
   }
   
   @Test
   public void testGetMeteoVilleArgDateNullKo() throws SOAPException{
      Source requestPayload = new StringSource("<appelWsMeteo ville=\"LYON\"/>");
      
      //Dans ce cas on attend une fault avec un message indiquant l'erreur
      mockClientWs.sendRequest(SoapRequestCreators.withPayload(requestPayload,"http://com.meteo.fake/meteoville/1.0/read"))
             .andExpect(serverOrReceiverFault("Saisissez la date d'interrogation!"));
   }
   
   @Test
   public void testGetMeteoVilleArgVilleNullKo() throws SOAPException{
      Source requestPayload = new StringSource("<appelWsMeteo date=\"2012-07-16T00:05:26.203\"/>");
      
      //Dans ce cas on attend une fault avec un message indiquant l'erreur
      mockClientWs.sendRequest(SoapRequestCreators.withPayload(requestPayload,"http://com.meteo.fake/meteoville/1.0/read"))
             .andExpect(serverOrReceiverFault("Renseignez la ville!"));
   }
   
   @Test
   public void testGetMeteoVilleArgVilleVideKo() throws SOAPException{
      Source requestPayload = new StringSource("<appelWsMeteo ville=\"\" date=\"2012-07-16T00:05:26.203\"/>");
      
      //Dans ce cas on attend une fault avec un message indiquant l'erreur
      mockClientWs.sendRequest(SoapRequestCreators.withPayload(requestPayload,"http://com.meteo.fake/meteoville/1.0/read"))
             .andExpect(serverOrReceiverFault("Renseignez la ville!"));
   }
}
