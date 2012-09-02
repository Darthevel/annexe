package com.ehret.scoresheet.ws.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cet objet est renvoyé par le webservice donnant la méteo d'une ville
 * @author Guillaume Ehret
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ReponseWsMeteo {
   @XmlAttribute
   private String ville;
   @XmlAttribute
   private String tempsAnnonce;
   @XmlAttribute
   private Integer indiceDeConfiance;
   @XmlAttribute
   private Date date;
  
   public ReponseWsMeteo(){
      super();
   }
   
   public ReponseWsMeteo(String ville, Date date) {
      super();
      this.ville = ville;
      this.date = date;
   }
   
   public String getTempsAnnonce() {
      return tempsAnnonce;
   }
   public void setTempsAnnonce(String tempsAnnonce) {
      this.tempsAnnonce = tempsAnnonce;
   }
   public Integer getIndiceDeConfiance() {
      return indiceDeConfiance;
   }
   public void setIndiceDeConfiance(Integer indiceDeConfiance) {
      this.indiceDeConfiance = indiceDeConfiance;
   }
   public String getVille() {
      return ville;
   }
   public void setVille(String ville) {
      this.ville = ville;
   }
   public Date getDate() {
      return date;
   }
   public void setDate(Date date) {
      this.date = date;
   }
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((date == null) ? 0 : date.hashCode());
      result = prime * result + ((indiceDeConfiance == null) ? 0 : indiceDeConfiance.hashCode());
      result = prime * result + ((tempsAnnonce == null) ? 0 : tempsAnnonce.hashCode());
      result = prime * result + ((ville == null) ? 0 : ville.hashCode());
      return result;
   }
   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      ReponseWsMeteo other = (ReponseWsMeteo) obj;
      if (date == null) {
         if (other.date != null)
            return false;
      } else if (!date.equals(other.date))
         return false;
      if (indiceDeConfiance == null) {
         if (other.indiceDeConfiance != null)
            return false;
      } else if (!indiceDeConfiance.equals(other.indiceDeConfiance))
         return false;
      if (tempsAnnonce == null) {
         if (other.tempsAnnonce != null)
            return false;
      } else if (!tempsAnnonce.equals(other.tempsAnnonce))
         return false;
      if (ville == null) {
         if (other.ville != null)
            return false;
      } else if (!ville.equals(other.ville))
         return false;
      return true;
   }
}
