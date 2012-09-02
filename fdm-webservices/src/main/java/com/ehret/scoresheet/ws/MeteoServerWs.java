package com.ehret.scoresheet.ws;

import org.apache.commons.lang.StringUtils;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

import com.ehret.scoresheet.ws.dto.AppelWsMeteo;
import com.ehret.scoresheet.ws.dto.ReponseWsMeteo;
import com.google.common.base.Preconditions;

/**
 * Cette classe est un webservice (server) permettant de renvoyer la 
 * méteo liée à une ville pour une date donnée
 * @author Guillaume Ehret
 */
@Endpoint
public class MeteoServerWs {
   
   @SoapAction("http://com.meteo.fake/meteoville/1.0/read")
   @ResponsePayload
   public ReponseWsMeteo getMeteoVille(@RequestPayload AppelWsMeteo param) {
      Preconditions.checkNotNull(param, "Aucun paramaètre d'appel");
      Preconditions.checkArgument(StringUtils.isNotEmpty(param.getVille()),"Renseignez la ville!");
      Preconditions.checkNotNull(param.getDate(), "Saisissez la date d'interrogation!");
      
      ReponseWsMeteo reponse = new ReponseWsMeteo(param.getVille(), param.getDate());
      
      //comme la météo est un grand hazard
      int random = Double.valueOf(Math.random()*100).intValue();
      reponse.setIndiceDeConfiance(random);
      switch(random % 6){
         case 0:
            reponse.setTempsAnnonce("NEIGE");
            break;
         case 1:
            reponse.setTempsAnnonce("PLUIE");
            break;
         case 2:
            reponse.setTempsAnnonce("VARIABLE");
            break;
         case 3:
            reponse.setTempsAnnonce("COUVERT");
            break;
         default:
            reponse.setTempsAnnonce("BEAU");
            break;
      }
      
      return reponse;
   }
}