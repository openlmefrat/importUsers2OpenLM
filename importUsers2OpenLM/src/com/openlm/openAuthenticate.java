package com.openlm;

/**
 * Created by adi on 08/07/2014.
 */
import javax.xml.soap.*;

public class openAuthenticate {

    public String username;
    public String password;
    public String wsdlServiceURI;

    private SOAPConnection soapConnection;

    public void connect() {
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            soapConnection = soapConnectionFactory.createConnection();
        }
        catch (SOAPException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        // print SOAP Response
        /*
        System.out.print("Response SOAP Message:");
        soapResponse.writeTo(System.out);

        soapConnection.close();
        */

    }

    public String authenticate () {
        // Send SOAP Message to SOAP Server
        try {
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(), wsdlServiceURI);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "session";
    }

    private SOAPMessage createSOAPRequest() throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = wsdlServiceURI;

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("example", serverURI);

        /*
        <?xml version="1.0" encoding="utf-8"?>
        <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
          <soap:Body>
            <PerformUserAuthentication xmlns="OpenLM.Server.Services">
              <request>
                <BaseInfo xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.Services.DataContracts">
                  <SessionData>
                    <SessionID xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.VO" />
                  </SessionData>
                  <PagingData>
                    <Direction xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.VO" />
                    <Search xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.VO" />
                    <Sort xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.VO">
                      <string xmlns="http://schemas.microsoft.com/2003/10/Serialization/Arrays" />
                    </Sort>
                  </PagingData>
                  <UserLocalSettings>
                    <DecimalSeparator />
                    <ThousandsSeparator />
                    <TimeFormat />
                    <TimezoneStandardName />
                  </UserLocalSettings>
                  <CustomerId />
                </BaseInfo>
                <SaasToken xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.Services.DataContracts" />
                <UserName xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.Services.DataContracts">adi</UserName>
                <Password xmlns="http://schemas.datacontract.org/2004/07/OpenLM.Server.Services.DataContracts">adi</Password>
              </request>
            </PerformUserAuthentication>
          </soap:Body>
        </soap:Envelope>
         */

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("VerifyEmail", "example");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("email", "example");
        soapBodyElem1.addTextNode("mutantninja@gmail.com");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("LicenseKey", "example");
        soapBodyElem2.addTextNode("123");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "VerifyEmail");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message:");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
