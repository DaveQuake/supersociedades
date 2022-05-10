
package org.datacontract.schemas._2004._07.system;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07.system package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FaultExceptionFaultCodeData_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultCodeData");
    private final static QName _ArrayOfFaultExceptionFaultReasonData_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "ArrayOfFaultException.FaultReasonData");
    private final static QName _FaultException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException");
    private final static QName _Exception_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "Exception");
    private final static QName _SystemException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System", "SystemException");
    private final static QName _ArrayOfFaultExceptionFaultCodeData_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "ArrayOfFaultException.FaultCodeData");
    private final static QName _FaultExceptionFaultReasonData_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "FaultException.FaultReasonData");
    private final static QName _CommunicationException_QNAME = new QName("http://schemas.datacontract.org/2004/07/System.ServiceModel", "CommunicationException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07.system
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FaultException }
     * 
     */
    public FaultException createFaultException() {
        return new FaultException();
    }

    /**
     * Create an instance of {@link CommunicationException }
     * 
     */
    public CommunicationException createCommunicationException() {
        return new CommunicationException();
    }

    /**
     * Create an instance of {@link ArrayOfFaultExceptionFaultCodeData }
     * 
     */
    public ArrayOfFaultExceptionFaultCodeData createArrayOfFaultExceptionFaultCodeData() {
        return new ArrayOfFaultExceptionFaultCodeData();
    }

    /**
     * Create an instance of {@link FaultExceptionFaultReasonData }
     * 
     */
    public FaultExceptionFaultReasonData createFaultExceptionFaultReasonData() {
        return new FaultExceptionFaultReasonData();
    }

    /**
     * Create an instance of {@link FaultExceptionFaultCodeData }
     * 
     */
    public FaultExceptionFaultCodeData createFaultExceptionFaultCodeData() {
        return new FaultExceptionFaultCodeData();
    }

    /**
     * Create an instance of {@link ArrayOfFaultExceptionFaultReasonData }
     * 
     */
    public ArrayOfFaultExceptionFaultReasonData createArrayOfFaultExceptionFaultReasonData() {
        return new ArrayOfFaultExceptionFaultReasonData();
    }

    /**
     * Create an instance of {@link SystemException }
     * 
     */
    public SystemException createSystemException() {
        return new SystemException();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultExceptionFaultCodeData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "FaultException.FaultCodeData")
    public JAXBElement<FaultExceptionFaultCodeData> createFaultExceptionFaultCodeData(FaultExceptionFaultCodeData value) {
        return new JAXBElement<FaultExceptionFaultCodeData>(_FaultExceptionFaultCodeData_QNAME, FaultExceptionFaultCodeData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFaultExceptionFaultReasonData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "ArrayOfFaultException.FaultReasonData")
    public JAXBElement<ArrayOfFaultExceptionFaultReasonData> createArrayOfFaultExceptionFaultReasonData(ArrayOfFaultExceptionFaultReasonData value) {
        return new JAXBElement<ArrayOfFaultExceptionFaultReasonData>(_ArrayOfFaultExceptionFaultReasonData_QNAME, ArrayOfFaultExceptionFaultReasonData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "FaultException")
    public JAXBElement<FaultException> createFaultException(FaultException value) {
        return new JAXBElement<FaultException>(_FaultException_QNAME, FaultException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Exception }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "Exception")
    public JAXBElement<Exception> createException(Exception value) {
        return new JAXBElement<Exception>(_Exception_QNAME, Exception.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System", name = "SystemException")
    public JAXBElement<SystemException> createSystemException(SystemException value) {
        return new JAXBElement<SystemException>(_SystemException_QNAME, SystemException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFaultExceptionFaultCodeData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "ArrayOfFaultException.FaultCodeData")
    public JAXBElement<ArrayOfFaultExceptionFaultCodeData> createArrayOfFaultExceptionFaultCodeData(ArrayOfFaultExceptionFaultCodeData value) {
        return new JAXBElement<ArrayOfFaultExceptionFaultCodeData>(_ArrayOfFaultExceptionFaultCodeData_QNAME, ArrayOfFaultExceptionFaultCodeData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultExceptionFaultReasonData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "FaultException.FaultReasonData")
    public JAXBElement<FaultExceptionFaultReasonData> createFaultExceptionFaultReasonData(FaultExceptionFaultReasonData value) {
        return new JAXBElement<FaultExceptionFaultReasonData>(_FaultExceptionFaultReasonData_QNAME, FaultExceptionFaultReasonData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CommunicationException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", name = "CommunicationException")
    public JAXBElement<CommunicationException> createCommunicationException(CommunicationException value) {
        return new JAXBElement<CommunicationException>(_CommunicationException_QNAME, CommunicationException.class, null, value);
    }

}
