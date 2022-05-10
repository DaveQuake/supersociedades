
package org.datacontract.schemas._2004._07.system;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ArrayOfFaultException.FaultReasonData complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfFaultException.FaultReasonData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FaultException.FaultReasonData" type="{http://schemas.datacontract.org/2004/07/System.ServiceModel}FaultException.FaultReasonData" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfFaultException.FaultReasonData", propOrder = {
    "faultExceptionFaultReasonData"
})
public class ArrayOfFaultExceptionFaultReasonData {

    @XmlElement(name = "FaultException.FaultReasonData", nillable = true)
    protected List<FaultExceptionFaultReasonData> faultExceptionFaultReasonData;

    /**
     * Gets the value of the faultExceptionFaultReasonData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the faultExceptionFaultReasonData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFaultExceptionFaultReasonData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FaultExceptionFaultReasonData }
     * 
     * 
     */
    public List<FaultExceptionFaultReasonData> getFaultExceptionFaultReasonData() {
        if (faultExceptionFaultReasonData == null) {
            faultExceptionFaultReasonData = new ArrayList<FaultExceptionFaultReasonData>();
        }
        return this.faultExceptionFaultReasonData;
    }

}
