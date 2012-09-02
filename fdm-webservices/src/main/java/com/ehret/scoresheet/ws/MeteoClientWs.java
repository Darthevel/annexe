package com.ehret.scoresheet.ws;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.ehret.scoresheet.ws.dto.AppelWsMeteo;
import com.ehret.scoresheet.ws.dto.ReponseWsMeteo;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

/**
 * Cette classe permet d'appeler un webservice permettant de fournir 
 * la méteo
 * @author Guillaume Ehret
 */
public class MeteoClientWs {
   /**
    * webService Template definie dans le ctx spring
    */
   @Autowired
   private WebServiceTemplate webServiceTemplate;
   
   /**
    * Appel du webservice donnant la méteo pour une ville pour une date
    * @param ville
    * @param date
    * @return
    */
   public ReponseWsMeteo getMeteoVille(String ville, Date date) {
      Preconditions.checkArgument(StringUtils.isNotEmpty(ville),"Renseignez la ville!");
      Preconditions.checkNotNull(date, "Saisissez la date d'interrogation!");
      
      //Preparation de l'objet permettant d'appeler le WS
      AppelWsMeteo appel = new AppelWsMeteo(ville, date);
      
     return (ReponseWsMeteo)  webServiceTemplate.marshalSendAndReceive(appel);
   }

   /**
    * Ce setter n'est utilise que dans un contexte de test
    * 
    * @param webServiceTemplatePublication
    */
   @VisibleForTesting
   protected void setWebServiceTemplate(WebServiceTemplate webServiceTemplate) {
      this.webServiceTemplate = webServiceTemplate;
   }
}