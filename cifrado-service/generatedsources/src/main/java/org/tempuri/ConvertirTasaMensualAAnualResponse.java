
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ConvertirTasaMensualAAnualResult" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "convertirTasaMensualAAnualResult"
})
@XmlRootElement(name = "ConvertirTasaMensualAAnualResponse")
public class ConvertirTasaMensualAAnualResponse {

    @XmlElement(name = "ConvertirTasaMensualAAnualResult")
    protected Float convertirTasaMensualAAnualResult;

    /**
     * Obtiene el valor de la propiedad convertirTasaMensualAAnualResult.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getConvertirTasaMensualAAnualResult() {
        return convertirTasaMensualAAnualResult;
    }

    /**
     * Define el valor de la propiedad convertirTasaMensualAAnualResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setConvertirTasaMensualAAnualResult(Float value) {
        this.convertirTasaMensualAAnualResult = value;
    }

}
