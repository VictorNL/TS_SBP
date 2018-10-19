package view.backing;

import WsEjRep.ServiceBiPublisher;

import com.oracle.xmlns.adf.svc.types.ProcessControl;
import com.oracle.xmlns.adf.svc.types.ReturnMode;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.Opportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.OpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;

import dao.Oportunidad;

import java.util.ArrayList;
import java.util.List;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.output.RichMessages;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

public class Test {
    private RichForm f1;
    private RichDocument d1;
    private RichMessages m1;

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }


    public static void main(String[] args) {
      

        }
    }
    
