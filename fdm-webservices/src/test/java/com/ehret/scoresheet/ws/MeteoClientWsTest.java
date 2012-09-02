package com.ehret.scoresheet.ws;

import static org.springframework.ws.test.client.RequestMatchers.anything;
import static org.springframework.ws.test.client.ResponseCreators.withPayload;
import static org.springframework.ws.test.client.ResponseCreators.withServerOrReceiverFault;

import java.util.Date;
import java.util.Locale;

import javax.xml.transform.Source;

import junit.framework.Assert;

import org.apache.commons.httpclient.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.xml.transform.StringSource;

import com.ehret.scoresheet.ws.dto.ReponseWsMeteo;

/**
 * Classe de test de {@link MeteoClientWs}
 * @author Guillaume Ehret
 */
@ContextConfiguration(locations = { "/communContext.xml","/clientWsContext.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class MeteoClientWsTest {
   /**
    * Bean à tester 
    */
   private MeteoClientWs meteoClientWs;
   /**
    * WebserviTemplate injecté manuellement
    */
   @Autowired
   private WebServiceTemplate webServiceTemplate;
   
   /**
    * Mock pour simuler le travail du serveur fournissant le web service 
    */
   private MockWebServiceServer mockServerWs;
   
   @Before
   public void setUp(){
      meteoClientWs = new MeteoClientWs();
      meteoClientWs.setWebServiceTemplate(webServiceTemplate);
      mockServerWs = MockWebServiceServer.createServer(webServiceTemplate);
   }
   
   @Test
   public void testGetMeteoVilleOk(){
      // Retour attendu du webservice
      Source responseOk = new StringSource("<reponseWsMeteo indiceDeConfiance=\"80\" ville=\"LYON\" tempsAnnonce=\"BEAU\" date=\"2012-07-16T00:05:26.203\"/>");
      // On indique le comportement du mock et notamment la reponse
      mockServerWs.expect(anything()).andRespond(withPayload(responseOk));
      // On appelle la classe à tester
      ReponseWsMeteo retour = meteoClientWs.getMeteoVille("LYON", new Date());
      mockServerWs.verify();
      
      Assert.assertEquals("LYON", retour.getVille());
      Assert.assertEquals("BEAU", retour.getTempsAnnonce());
      Assert.assertEquals(Integer.valueOf(80), retour.getIndiceDeConfiance());
      Assert.assertEquals("Sun, 15 Jul 2012 22:05:26 GMT", DateUtil.formatDate(retour.getDate()));
   }

   @Test
   public void testGetMeteoVilleSoapFaultErrorKo(){
      // On indique le comportement du mock et notamment la reponse
      mockServerWs.expect(anything()).andRespond(withServerOrReceiverFault("Ville inconnue!", Locale.FRANCE));
      // On appelle la classe à tester
      try{
         meteoClientWs.getMeteoVille("LYON", new Date());
         Assert.fail();
      }
      catch (SoapFaultClientException e) {
         //Attendu
      }
      mockServerWs.verify();
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void testGetMeteoVilleArgVilleNullKo(){
      meteoClientWs.getMeteoVille(null, new Date());
   }
   
   @Test(expected=IllegalArgumentException.class)
   public void testGetMeteoVilleArgVilleVideKo(){
      meteoClientWs.getMeteoVille("", new Date());
   }
   
   @Test(expected=NullPointerException.class)
   public void testGetMeteoVilleArgDateNulleKo(){
      meteoClientWs.getMeteoVille("LYON", null);
   }
}
