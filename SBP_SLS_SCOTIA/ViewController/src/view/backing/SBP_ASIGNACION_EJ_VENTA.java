package view.backing;

import Client.MergeOppInvoke;

import Client.OportunidadLib;

import WsEjRep.ServiceBiPublisher;

import com.oracle.xmlns.adf.svc.types.ProcessControl;
import com.oracle.xmlns.adf.svc.types.ReturnMode;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.Opportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.OpportunityResource;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunity;
import com.oracle.xmlns.apps.sales.opptymgmt.opportunities.opportunityservice.types.ProcessOpportunityResponse;

import dao.Oportunidad;

import java.io.IOException;

import java.net.SocketException;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.xml.parsers.ParserConfigurationException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;

import org.xml.sax.SAXException;

import view.Util;

public class SBP_ASIGNACION_EJ_VENTA {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtResourceId;
    private RichOutputLabel txtPorcTot;
    private RichOutputLabel txtCantTotal;

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

    public void setTxtResourceId(RichInputText txtResourceId) {
        this.txtResourceId = txtResourceId;
    }

    public RichInputText getTxtResourceId() {
        return txtResourceId;
    }
    
    public void setTxtPorcTot(RichOutputLabel txtPorcTot) {
        this.txtPorcTot = txtPorcTot;
    }

    public RichOutputLabel getTxtPorcTot() {
        return txtPorcTot;
    }

    public void setTxtCantTotal(RichOutputLabel txtCantTotal) {
        this.txtCantTotal = txtCantTotal;
    }

    public RichOutputLabel getTxtCantTotal() {
        return txtCantTotal;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public String agregar() {
        
        boolean valida;

        valida = validacionAgregar();

        if (valida == false) {

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }

        }
        return null;

    }

    public boolean validacionAgregar() {


        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigCpgEjecVentView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());


                totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsignado").toString());


                rsi.next();
            }

            currRow = rsi.last();

            totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());


            totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsignado").toString());


        }

        txtPorcTot.setValue(totalPorcAsig);
        txtCantTotal.setValue(totalCant);

        if (Double.parseDouble(txtPorcTot.getValue().toString()) >= 100 || Double.parseDouble(txtCantTotal.getValue().toString()) >= totalOp) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "no hay oportunidades por asignar", ""));

            Util.Log("cumple");
            return true;


        }

        else {

            Util.Log("no cumple");
            return false;
        }
    }

    public boolean validacionGuardar() {

        double totalOp = 0;
        double totalPorcAsig = 0;
        double totalCant = 0;

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsAsigCpgEjecVentView1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());


                totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsignado").toString());


                rsi.next();
            }

            currRow = rsi.last();

            totalCant += Double.parseDouble(currRow.getAttribute("Cantidad").toString());


            totalPorcAsig += Double.parseDouble(currRow.getAttribute("PorcAsignado").toString());


        }

        txtPorcTot.setValue(totalPorcAsig);
        txtCantTotal.setValue(totalCant);

        if (Double.parseDouble(txtPorcTot.getValue().toString()) > 100 || Double.parseDouble(txtCantTotal.getValue().toString()) > totalOp) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "no hay oportunidades por asignar", ""));

            Util.Log("cumple");
            return true;


        }

        else {

            Util.Log("no cumple");
            return false;
        }
    }


    
    public void eliminarRegistro(ActionEvent actionEvent) {
        
        Util.Log("inicio de eliminar");
        DCBindingContainer bindingsReg = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingReg = bindingsReg.findIteratorBinding("SbpSlsAsigCpgEjecVentView1Iterator");

        ViewObject voReg = itorBindingReg.getViewObject();
        Row[] selectedRowsReg = voReg.getFilteredRows("eliminarFila", true);
        for (Row currRowReg : selectedRowsReg) {

            Util.Log("entra");
            currRowReg.remove();
        }
    }

    public void eliminarFila(ActionEvent actionEvent) {
        
        Util.Log("iniciar eliminar");
        DCBindingContainer bindingsEj = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingEj = bindingsEj.findIteratorBinding("SbpSlsAsigCpgEjecVentView1Iterator");

        ViewObject voEj = itorBindingEj.getViewObject();
        Row[] selectedRowsReg = voEj.getFilteredRows("borrarRegistro", true);
        for (Row currRowReg : selectedRowsReg) {

            Util.Log("entra");
            currRowReg.remove();
        }
        
        Util.Log(selectedRowsReg.length + "");
    }


    public String guardar() {
        
        boolean valida;

        valida = validacionGuardar();

        if (valida == false) {
            
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        }
        return null;
    }
}
