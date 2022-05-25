
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
 *         &lt;element name="ConvertirTasaAnualAMensualResult" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
    "convertirTasaAnualAMensualResult"
})
@XmlRootElement(name = "ConvertirTasaAnualAMensualResponse")
public class ConvertirTasaAnualAMensualResponse {

    @XmlElement(name = "ConvertirTasaAnualAMensualResult")
    protected Float convertirTasaAnualAMensualResult;

    /**
     * Obtiene el valor de la propiedad convertirTasaAnualAMensualResult.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getConvertirTasaAnualAMensualResult() {
        return convertirTasaAnualAMensualResult;
    }

    /**
     * Define el valor de la propiedad convertirTasaAnualAMensualResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setConvertirTasaAnualAMensualResult(Float value) {
        this.convertirTasaAnualAMensualResult = value;
    }

}
