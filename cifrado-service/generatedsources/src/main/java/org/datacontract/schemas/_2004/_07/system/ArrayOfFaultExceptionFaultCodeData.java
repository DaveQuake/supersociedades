
package org.datacontract.schemas._2004._07.system;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfFaultException.FaultCodeData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFaultException.FaultCodeData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FaultException.FaultCodeData" type="{http://schemas.datacontract.org/2004/07/System.ServiceModel}FaultException.FaultCodeData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFaultException.FaultCodeData", propOrder = {
    "faultExceptionFaultCodeData"
})
public class ArrayOfFaultExceptionFaultCodeData {

    @XmlElement(name = "FaultException.FaultCodeData", nillable = true)
    protected List<FaultExceptionFaultCodeData> faultExceptionFaultCodeData;

    /**
     * Gets the value of the faultExceptionFaultCodeData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the faultExceptionFaultCodeData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFaultExceptionFaultCodeData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FaultExceptionFaultCodeData }
     * 
     * 
     */
    public List<FaultExceptionFaultCodeData> getFaultExceptionFaultCodeData() {
        if (faultExceptionFaultCodeData == null) {
            faultExceptionFaultCodeData = new ArrayList<FaultExceptionFaultCodeData>();
        }
        return this.faultExceptionFaultCodeData;
    }

}
