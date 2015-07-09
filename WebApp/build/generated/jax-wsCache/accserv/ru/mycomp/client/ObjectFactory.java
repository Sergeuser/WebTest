
package ru.mycomp.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.mycomp.client package. 
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

    private final static QName _AddAmount_QNAME = new QName("http://ws.mycomp.ru/", "addAmount");
    private final static QName _AddAmountResponse_QNAME = new QName("http://ws.mycomp.ru/", "addAmountResponse");
    private final static QName _GetAmount_QNAME = new QName("http://ws.mycomp.ru/", "getAmount");
    private final static QName _GetAmountResponse_QNAME = new QName("http://ws.mycomp.ru/", "getAmountResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.mycomp.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAmountResponse }
     * 
     */
    public GetAmountResponse createGetAmountResponse() {
        return new GetAmountResponse();
    }

    /**
     * Create an instance of {@link AddAmount }
     * 
     */
    public AddAmount createAddAmount() {
        return new AddAmount();
    }

    /**
     * Create an instance of {@link AddAmountResponse }
     * 
     */
    public AddAmountResponse createAddAmountResponse() {
        return new AddAmountResponse();
    }

    /**
     * Create an instance of {@link GetAmount }
     * 
     */
    public GetAmount createGetAmount() {
        return new GetAmount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAmount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mycomp.ru/", name = "addAmount")
    public JAXBElement<AddAmount> createAddAmount(AddAmount value) {
        return new JAXBElement<AddAmount>(_AddAmount_QNAME, AddAmount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mycomp.ru/", name = "addAmountResponse")
    public JAXBElement<AddAmountResponse> createAddAmountResponse(AddAmountResponse value) {
        return new JAXBElement<AddAmountResponse>(_AddAmountResponse_QNAME, AddAmountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAmount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mycomp.ru/", name = "getAmount")
    public JAXBElement<GetAmount> createGetAmount(GetAmount value) {
        return new JAXBElement<GetAmount>(_GetAmount_QNAME, GetAmount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAmountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.mycomp.ru/", name = "getAmountResponse")
    public JAXBElement<GetAmountResponse> createGetAmountResponse(GetAmountResponse value) {
        return new JAXBElement<GetAmountResponse>(_GetAmountResponse_QNAME, GetAmountResponse.class, null, value);
    }

}
