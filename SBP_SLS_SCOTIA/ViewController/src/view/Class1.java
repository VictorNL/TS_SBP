package view;

import java.sql.SQLException;

import oracle.jbo.domain.Number;

public class Class1 {
    public Class1() {
        super();
    }

    public static void main(String[] args) {
        String payload =
            "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:typ=\"http://xmlns.oracle.com/apps/marketing/leadMgmt/leads/leadService/types/\" xmlns:lead=\"http://xmlns.oracle.com/oracle/apps/marketing/leadMgmt/leads/leadService/\" xmlns:lead1=\"http://xmlns.oracle.com/apps/marketing/leadMgmt/leads/leadService/\" xmlns:not=\"http://xmlns.oracle.com/apps/crmCommon/notes/noteService\" xmlns:not1=\"http://xmlns.oracle.com/apps/crmCommon/notes/flex/noteDff/\">" +
            "<soapenv:Header/>" + "<soapenv:Body>" + "<typ:mergeSalesLead>" +
            "<typ:salesLead>" +  "<lead:LeadId>" + "leadID" +
            "</lead:LeadId>" + "<lead:SpeechVentaCollection_c>" + "<lead:Id>" + "leadID" +
            "</lead:Id>" + "<lead:Flag_Observado_c>" + "leadID" + "</lead:Flag_Observado_c>" +
            "<lead:Flag_Validado_c>" + "leadID" + "</lead:Flag_Validado_c>" + "</lead:SpeechVentaCollection_c>" +
            "</typ:salesLead>" + "</typ:mergeSalesLead>" + "</soapenv:Body>" +
            "</soapenv:Envelope>";
        
        System.out.println(payload);
    }
}
