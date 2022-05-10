
package org.tempuri;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="documentoWord" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="extensionWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "documentoWord",
    "extensionWord"
})
@XmlRootElement(name = "ConvertirPdf")
public class ConvertirPdf {

    @XmlElementRef(name = "documentoWord", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> documentoWord;
    @XmlElementRef(name = "extensionWord", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<String> extensionWord;

    /**
     * Obtiene el valor de la propiedad documentoWord.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getDocumentoWord() {
        return documentoWord;
    }

    /**
     * Define el valor de la propiedad documentoWord.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setDocumentoWord(JAXBElement<byte[]> value) {
        this.documentoWord = value;
    }

    /**
     * Obtiene el valor de la propiedad extensionWord.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtensionWord() {
        return extensionWord;
    }

    /**
     * Define el valor de la propiedad extensionWord.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtensionWord(JAXBElement<String> value) {
        this.extensionWord = value;
    }

}
