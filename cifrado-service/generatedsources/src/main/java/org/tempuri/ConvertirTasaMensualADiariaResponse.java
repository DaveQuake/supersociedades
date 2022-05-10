
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
 *         &lt;element name="ConvertirTasaMensualADiariaResult" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
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
    "convertirTasaMensualADiariaResult"
})
@XmlRootElement(name = "ConvertirTasaMensualADiariaResponse")
public class ConvertirTasaMensualADiariaResponse {

    @XmlElement(name = "ConvertirTasaMensualADiariaResult")
    protected Float convertirTasaMensualADiariaResult;

    /**
     * Obtiene el valor de la propiedad convertirTasaMensualADiariaResult.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getConvertirTasaMensualADiariaResult() {
        return convertirTasaMensualADiariaResult;
    }

    /**
     * Define el valor de la propiedad convertirTasaMensualADiariaResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setConvertirTasaMensualADiariaResult(Float value) {
        this.convertirTasaMensualADiariaResult = value;
    }

}
