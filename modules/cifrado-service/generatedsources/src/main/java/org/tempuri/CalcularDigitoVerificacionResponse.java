
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
 *         &lt;element name="CalcularDigitoVerificacionResult" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "calcularDigitoVerificacionResult"
})
@XmlRootElement(name = "CalcularDigitoVerificacionResponse")
public class CalcularDigitoVerificacionResponse {

    @XmlElement(name = "CalcularDigitoVerificacionResult")
    protected Integer calcularDigitoVerificacionResult;

    /**
     * Obtiene el valor de la propiedad calcularDigitoVerificacionResult.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCalcularDigitoVerificacionResult() {
        return calcularDigitoVerificacionResult;
    }

    /**
     * Define el valor de la propiedad calcularDigitoVerificacionResult.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCalcularDigitoVerificacionResult(Integer value) {
        this.calcularDigitoVerificacionResult = value;
    }

}
