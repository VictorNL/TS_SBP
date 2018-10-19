package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Items;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class SBP_SLS_LIST_PROD {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichOutputLabel ol1;
    private RichTable tblProd;
    private RichInputText txtTelf;

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

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void buscarProductos(ActionEvent actionEvent) {
        
        Row rw = null;

        
        ServiceBiPublisher item = new ServiceBiPublisher();

        List<Items> lista2 = new ArrayList<Items>();
        lista2 = item.obtenerItems();
        

        CollectionModel _tablaProd = (CollectionModel)tblProd.getValue();
        JUCtrlHierBinding _adfTableProdBinding = (JUCtrlHierBinding)_tablaProd.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itProd = _adfTableProdBinding.getDCIteratorBinding();
        
        int b = 0;
        b = itProd.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itProd.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }

        for(Items listProd : lista2){
            
            
            rw = itProd.getNavigatableRowIterator().createRow();
            itProd.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);
            
            
            rw.setAttribute("IdProd", listProd.getIdItem()); 
            rw.setAttribute("NomProd", listProd.getNomItem()); 
            
        }

        
    }

    public void setTblProd(RichTable tblProd) {
        this.tblProd = tblProd;
    }

    public RichTable getTblProd() {
        return tblProd;
    }

    public void setTxtTelf(RichInputText txtTelf) {
        this.txtTelf = txtTelf;
    }

    public RichInputText getTxtTelf() {
        return txtTelf;
    }
}
