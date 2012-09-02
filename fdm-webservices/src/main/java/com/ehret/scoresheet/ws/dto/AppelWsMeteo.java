package com.ehret.scoresheet.ws.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cet objet est envoyé au webservice pour avoir la méteo d'une ville
 * @author Guillaume Ehret
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AppelWsMeteo {
   @XmlAttribute
   private String ville;
   @XmlAttribute
   private Date date;
   
   public AppelWsMeteo(){
      super();
   }
   
   public AppelWsMeteo(String ville, Date date) {
      super();
      this.ville = ville;
      this.date = date;
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
      AppelWsMeteo other = (AppelWsMeteo) obj;
      if (date == null) {
         if (other.date != null)
            return false;
      } else if (!date.equals(other.date))
         return false;
      if (ville == null) {
         if (other.ville != null)
            return false;
      } else if (!ville.equals(other.ville))
         return false;
      return true;
   }
}
