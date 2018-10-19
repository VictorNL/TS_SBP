package view.backing;

import Client.OportunidadLib;

import WsEjRep.ServiceBiPublisher;


import dao.CanalVenta;
import dao.CpgOppContAccProdFb;
import dao.Feedback;
import dao.Items;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.xml.ws.WebServiceException;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.collections.ListUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_PANT_OPTY {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb1;
    private RichPanelGroupLayout pgl5;
    private RichOutputLabel ol1;
    private RichPanelCollection pc1;
    private RichPanelCollection pc2;
    private RichPanelCollection pc3;
    private RichOutputLabel ol2;
    private RichOutputLabel ol3;
    private RichTable tblGnrl;
    private RichTable tblGnrl2;
    private RichTable tblGnrl3;
    private RichTable tblGnrl4;
    private RichTable tblGnrl5;
    private RichTable tblGnrl6;
    private RichTable tblGnrl7;
    private RichTable tblGnrl8;
    private RichInputText txtIdOpty;
    private RichCommandButton btnAgregar;
    private RichCommandButton btnAgregar2;
    private RichCommandButton btnAgregar3;
    private RichCommandButton btnAgregar4;
    private RichCommandButton btnAgregar5;
    private RichCommandButton btnAgregar6;
    private RichInputText txtMonto;
    private RichPopup popupFeedback;
    private RichInputText txtIdEjVenta;

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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setPc3(RichPanelCollection pc3) {
        this.pc3 = pc3;
    }

    public RichPanelCollection getPc3() {
        return pc3;
    }

    public void setOl2(RichOutputLabel ol2) {
        this.ol2 = ol2;
    }

    public RichOutputLabel getOl2() {
        return ol2;
    }

    public void setOl3(RichOutputLabel ol3) {
        this.ol3 = ol3;
    }

    public RichOutputLabel getOl3() {
        return ol3;
    }

    public void setTblGnrl(RichTable tblGnrl) {
        this.tblGnrl = tblGnrl;
    }

    public RichTable getTblGnrl() {
        return tblGnrl;
    }

    public void setTblGnrl2(RichTable tblGnrl2) {
        this.tblGnrl2 = tblGnrl2;
    }

    public RichTable getTblGnrl2() {
        return tblGnrl2;
    }

    public void setTblGnrl3(RichTable tblGnrl3) {
        this.tblGnrl3 = tblGnrl3;
    }

    public RichTable getTblGnrl3() {
        return tblGnrl3;
    }

    public void setTblGnrl4(RichTable tblGnrl4) {
        this.tblGnrl4 = tblGnrl4;
    }

    public RichTable getTblGnrl4() {
        return tblGnrl4;
    }

    public void setTblGnrl5(RichTable tblGnrl5) {
        this.tblGnrl5 = tblGnrl5;
    }

    public RichTable getTblGnrl5() {
        return tblGnrl5;
    }

    public void setTblGnrl6(RichTable tblGnrl6) {
        this.tblGnrl6 = tblGnrl6;
    }

    public RichTable getTblGnrl6() {
        return tblGnrl6;
    }

    public void setTblGnrl7(RichTable tblGnrl7) {
        this.tblGnrl7 = tblGnrl7;
    }

    public RichTable getTblGnrl7() {
        return tblGnrl7;
    }

    public void setTblGnrl8(RichTable tblGnrl8) {
        this.tblGnrl8 = tblGnrl8;
    }

    public RichTable getTblGnrl8() {
        return tblGnrl8;
    }

    public void setBtnAgregar(RichCommandButton btnAgregar) {
        this.btnAgregar = btnAgregar;
    }

    public RichCommandButton getBtnAgregar() {
        return btnAgregar;
    }

    public void setBtnAgregar2(RichCommandButton btnAgregar2) {
        this.btnAgregar2 = btnAgregar2;
    }

    public RichCommandButton getBtnAgregar2() {
        return btnAgregar2;
    }

    public void setBtnAgregar3(RichCommandButton btnAgregar3) {
        this.btnAgregar3 = btnAgregar3;
    }

    public RichCommandButton getBtnAgregar3() {
        return btnAgregar3;
    }

    public void setBtnAgregar4(RichCommandButton btnAgregar4) {
        this.btnAgregar4 = btnAgregar4;
    }

    public RichCommandButton getBtnAgregar4() {
        return btnAgregar4;
    }

    public void setBtnAgregar5(RichCommandButton btnAgregar5) {
        this.btnAgregar5 = btnAgregar5;
    }

    public RichCommandButton getBtnAgregar5() {
        return btnAgregar5;
    }

    public void setBtnAgregar6(RichCommandButton btnAgregar6) {
        this.btnAgregar6 = btnAgregar6;
    }

    public RichCommandButton getBtnAgregar6() {
        return btnAgregar6;
    }


    public void setTxtIdOpty(RichInputText txtIdOpty) {
        this.txtIdOpty = txtIdOpty;
    }

    public RichInputText getTxtIdOpty() {
        return txtIdOpty;
    }


    public void setTxtMonto(RichInputText txtMonto) {
        this.txtMonto = txtMonto;
    }

    public RichInputText getTxtMonto() {
        return txtMonto;
    }
    
    public void setPopupFeedback(RichPopup popupFeedback) {
        this.popupFeedback = popupFeedback;
    }

    public RichPopup getPopupFeedback() {
        return popupFeedback;
    }
    
    public void setTxtIdEjVenta(RichInputText txtIdEjVenta) {
        this.txtIdEjVenta = txtIdEjVenta;
    }

    public RichInputText getTxtIdEjVenta() {
        return txtIdEjVenta;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

  /*  public void buscarDatos(ActionEvent actionEvent) {


        //TAB1
        int contadorCont = 0;
        Row rw = null;


        ServiceBiPublisher pantOpty = new ServiceBiPublisher();

        List<CpgOppContAccProdFb> lista2 =
            new ArrayList<CpgOppContAccProdFb>();
        lista2 = pantOpty.obtenerOptyProdContAccFbCpg();

        CollectionModel _tablaGnrl = (CollectionModel)tblGnrl.getValue();
        JUCtrlHierBinding _adfTableGnrldBinding =
            (JUCtrlHierBinding)_tablaGnrl.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl =
            _adfTableGnrldBinding.getDCIteratorBinding();

        int b = 0;
        b = itGnrl.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itGnrl.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab1");

        for (CpgOppContAccProdFb listGnrl : lista2) {

            Util.Log(lista2.size());
            contadorCont = contadorCont + 1;


            rw = itGnrl.getNavigatableRowIterator().createRow();
            itGnrl.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("CodTipDoc", listGnrl.getCodigoTipodocumento());
            rw.setAttribute("TipoDocumento", listGnrl.getTipoDocumento());
            rw.setAttribute("NumeroDni", listGnrl.getNumeroDni());
            rw.setAttribute("Nombre", listGnrl.getNombre());
            rw.setAttribute("SegundoNombre", listGnrl.getSegundoNombre());
            rw.setAttribute("ApellidoPaterno", listGnrl.getApellidoPaterno());
            rw.setAttribute("ApellidoMaterno", listGnrl.getApellidoMaterno());
            rw.setAttribute("Edad", listGnrl.getEdad());
            rw.setAttribute("Genero", listGnrl.getGenero());
            rw.setAttribute("EstadoCivil", listGnrl.getEstadoCivil());
            rw.setAttribute("idGnrl", contadorCont);

            Util.Log(listGnrl.getCodigoTipodocumento() + "1");
            Util.Log(listGnrl.getTipoDocumento() + "2");
            Util.Log(listGnrl.getNumeroDni() + "3");
            Util.Log(listGnrl.getNombre() + "4");
            Util.Log(listGnrl.getSegundoNombre() + "5");
            Util.Log(listGnrl.getApellidoMaterno() + "6");

        }

        //TAB2
        Row rw2 = null;

        CollectionModel _tablaGnrl2 = (CollectionModel)tblGnrl2.getValue();
        JUCtrlHierBinding _adfTableGnrl2dBinding =
            (JUCtrlHierBinding)_tablaGnrl2.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl2 =
            _adfTableGnrl2dBinding.getDCIteratorBinding();

        int a = 0;
        a = itGnrl2.getRowSetIterator().getRowCount();

        for (int i = 0; i < a; i++) {
            itGnrl2.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab2");

        for (CpgOppContAccProdFb listGnrl2 : lista2) {


            rw2 = itGnrl2.getNavigatableRowIterator().createRow();
            itGnrl2.getNavigatableRowIterator().insertRow(rw2);
            rw2.setNewRowState(Row.STATUS_INITIALIZED);


            rw2.setAttribute("Telefono01", listGnrl2.getTelefono01());
            rw2.setAttribute("Telefono02", listGnrl2.getTelefono02());
            rw2.setAttribute("Telefono03", listGnrl2.getTelefono03());
            rw2.setAttribute("Celular01", listGnrl2.getCelular01());
            rw2.setAttribute("Celular02", listGnrl2.getCelular02());
            rw2.setAttribute("Celular03", listGnrl2.getCelular03());
            rw2.setAttribute("PaisContacto", listGnrl2.getPaisContacto());
            rw2.setAttribute("DeptCont", listGnrl2.getDeptCont());
            rw2.setAttribute("DistContacto", listGnrl2.getDistContacto());
            rw2.setAttribute("ProvContacto", listGnrl2.getProvContacto());
            rw2.setAttribute("Direccion", listGnrl2.getDireccion());

        }


        //TAB3

        Row rw3 = null;

        CollectionModel _tablaGnrl3 = (CollectionModel)tblGnrl3.getValue();
        JUCtrlHierBinding _adfTableGnrl3Binding =
            (JUCtrlHierBinding)_tablaGnrl3.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl3 =
            _adfTableGnrl3Binding.getDCIteratorBinding();

        int c = 0;
        c = itGnrl3.getRowSetIterator().getRowCount();

        for (int i = 0; i < c; i++) {
            itGnrl3.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab3");

        for (CpgOppContAccProdFb listGnrl3 : lista2) {


            rw3 = itGnrl3.getNavigatableRowIterator().createRow();
            itGnrl3.getNavigatableRowIterator().insertRow(rw3);
            rw3.setNewRowState(Row.STATUS_INITIALIZED);


            rw3.setAttribute("Sl01nom", listGnrl3.getSl01Nom());
            rw3.setAttribute("Cliente", listGnrl3.getCliente());
            rw3.setAttribute("Email", listGnrl3.getEmail());
            rw3.setAttribute("FecNacimiento", listGnrl3.getFechaNacimiento());
            rw3.setAttribute("FlagClubSueldo", listGnrl3.getFlagClubSueldo());
            rw3.setAttribute("FlagAutorizacion",
                             listGnrl3.getFlagautorizacion());
            rw3.setAttribute("TipoZonaCourier",
                             listGnrl3.getTipoZonaCourier());
            rw3.setAttribute("SbsBaficaedpRccpp",
                             listGnrl3.getSbsBaFiCaEDPEDPRccPP());


        }

        //TAB4

        Row rw4 = null;

        CollectionModel _tablaGnrl4 = (CollectionModel)tblGnrl4.getValue();
        JUCtrlHierBinding _adfTableGnrl4Binding =
            (JUCtrlHierBinding)_tablaGnrl4.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl4 =
            _adfTableGnrl4Binding.getDCIteratorBinding();

        int d = 0;
        d = itGnrl4.getRowSetIterator().getRowCount();

        for (int i = 0; i < d; i++) {
            itGnrl4.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab4");

        for (CpgOppContAccProdFb listGnrl4 : lista2) {

            rw4 = itGnrl4.getNavigatableRowIterator().createRow();
            itGnrl4.getNavigatableRowIterator().insertRow(rw4);
            rw4.setNewRowState(Row.STATUS_INITIALIZED);

            rw4.setAttribute("Banco1DeuRcctc", listGnrl4.getBanco1DEURccTC());
            rw4.setAttribute("Banco1LinRcctc", listGnrl4.getBanco1LINRccTC());
            rw4.setAttribute("Banco1NombreRcctc", listGnrl4.getBanco1NombreRccTC());
            rw4.setAttribute("Banco2LinRcctc", listGnrl4.getBanco2LINRccTc());
            rw4.setAttribute("Banco2DeuRcctc", listGnrl4.getBanco2DEURccTC());
            rw4.setAttribute("Banco2NombreRcctc",
                             listGnrl4.getBanco2NombreRccTC());
            rw4.setAttribute("BaficaedpdeuRcctc",
                             listGnrl4.getBaFiCaEDPDEURccTC());
            rw4.setAttribute("BaficaedpLinRcctc",
                             listGnrl4.getBaFiCaEDPEDPLINRccTC());
            rw4.setAttribute("Banco1NombreRccpp",
                             listGnrl4.getBanco1NombreRccPP());
            rw4.setAttribute("Banco2NombreRccpp",
                             listGnrl4.getBanco2NombreRccPP());
            rw4.setAttribute("Banco2DeudaRccpp",
                             listGnrl4.getBanco2DeudaRccPP());
            rw4.setAttribute("Banco1DeudaRccpp",
                             listGnrl4.getBanco1DeudaRccPP());
        }

        //TAB5
        Row rw5 = null;

        CollectionModel _tablaGnrl5 = (CollectionModel)tblGnrl5.getValue();
        JUCtrlHierBinding _adfTableGnrl5Binding =
            (JUCtrlHierBinding)_tablaGnrl5.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl5 =
            _adfTableGnrl5Binding.getDCIteratorBinding();

        int e = 0;
        e = itGnrl5.getRowSetIterator().getRowCount();

        for (int i = 0; i < e; i++) {
            itGnrl5.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab5");

        for (CpgOppContAccProdFb listGnrl5 : lista2) {

            rw5 = itGnrl5.getNavigatableRowIterator().createRow();
            itGnrl5.getNavigatableRowIterator().insertRow(rw5);
            rw5.setNewRowState(Row.STATUS_INITIALIZED);

            rw5.setAttribute("IdOpp", listGnrl5.getIdOpp());
            rw5.setAttribute("NomOpp", listGnrl5.getNomOpp());
            rw5.setAttribute("MonOpty", listGnrl5.getMonOpty());
            rw5.setAttribute("NomProd", listGnrl5.getNomProd());
            rw5.setAttribute("Tceamax", listGnrl5.getTceaMax());
            rw5.setAttribute("Tceamin", listGnrl5.getTceaMin());
            rw5.setAttribute("Teamax", listGnrl5.getTeaMax());
            rw5.setAttribute("Teamin", listGnrl5.getTeaMin());
            rw5.setAttribute("MontoMaximo", listGnrl5.getMontoMaximo());
            rw5.setAttribute("MontoMinimo", listGnrl5.getMontoMinimo());
            rw5.setAttribute("PlazoMaximo", listGnrl5.getPlazoMaximo());
            rw5.setAttribute("PlazoMinimo", listGnrl5.getPlazoMinimo());
            rw5.setAttribute("Cuota", listGnrl5.getCuota());
            rw5.setAttribute("OrganizationName", listGnrl5.getOrganizationName());
            rw5.setAttribute("Ruc", listGnrl5.getRuc());
            rw5.setAttribute("PaisEmp", listGnrl5.getPaisEmp());
            rw5.setAttribute("DeptEmp", listGnrl5.getDeptEmp());
            rw5.setAttribute("ProvEmp", listGnrl5.getProvEmp());
            rw5.setAttribute("DirEmp", listGnrl5.getDirEmp());
            rw5.setAttribute("DistEmp", listGnrl5.getDistEmp());

        }


        //TAB6

        Row rw6 = null;

        CollectionModel _tablaGnrl6 = (CollectionModel)tblGnrl6.getValue();
        JUCtrlHierBinding _adfTableGnrl6Binding =
            (JUCtrlHierBinding)_tablaGnrl6.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl6 =
            _adfTableGnrl6Binding.getDCIteratorBinding();

        int f = 0;
        f = itGnrl6.getRowSetIterator().getRowCount();

        for (int i = 0; i < f; i++) {
            itGnrl6.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab6");

        for (CpgOppContAccProdFb listGnrl6 : lista2) {

            rw6 = itGnrl6.getNavigatableRowIterator().createRow();
            itGnrl6.getNavigatableRowIterator().insertRow(rw6);
            rw6.setNewRowState(Row.STATUS_INITIALIZED);


            rw6.setAttribute("TcNombrebin1", listGnrl6.getTcNombreBIN1());
            rw6.setAttribute("TcNombrebin2", listGnrl6.getTcNombreBIN2());
            rw6.setAttribute("TcNombrebin3", listGnrl6.getTcNombreBIN3());
            rw6.setAttribute("TcNombrebin4", listGnrl6.getTcNombreBIN4());
            rw6.setAttribute("TcTea1", listGnrl6.getTcTea1());
            rw6.setAttribute("TcTea2", listGnrl6.getTcTea2());
            rw6.setAttribute("TcTea3", listGnrl6.getTcTea3());
            rw6.setAttribute("TcTea4", listGnrl6.getTcTea4());
            rw6.setAttribute("TcPct1", listGnrl6.getTcPct());
            rw6.setAttribute("TcPct2", listGnrl6.getTcPct2());
            rw6.setAttribute("TcPct3", listGnrl6.getTcPct3());
            rw6.setAttribute("TcPct4", listGnrl6.getTcPct4());
        }

        //TAB7

        Row rw7 = null;

        CollectionModel _tablaGnrl7 = (CollectionModel)tblGnrl7.getValue();
        JUCtrlHierBinding _adfTableGnrl7Binding =
            (JUCtrlHierBinding)_tablaGnrl7.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl7 =
            _adfTableGnrl7Binding.getDCIteratorBinding();

        int g = 0;
        g = itGnrl7.getRowSetIterator().getRowCount();

        for (int i = 0; i < g; i++) {
            itGnrl7.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(lista2.size() + "size tab7");

        for (CpgOppContAccProdFb listGnrl7 : lista2) {

            rw7 = itGnrl7.getNavigatableRowIterator().createRow();
            itGnrl7.getNavigatableRowIterator().insertRow(rw7);
            rw7.setNewRowState(Row.STATUS_INITIALIZED);

            rw7.setAttribute("Flujo", listGnrl7.getFlujo());
            rw7.setAttribute("CodigoProducto", listGnrl7.getCodigoProducto());
            rw7.setAttribute("Propension", listGnrl7.getPropension());
            rw7.setAttribute("Decil", listGnrl7.getDecil());
            rw7.setAttribute("codCpg", listGnrl7.getIdCpg());
            rw7.setAttribute("cantOfertasCpg", listGnrl7.getCantOfertas());
            rw7.setAttribute("periodCpg", listGnrl7.getPeriodoCpg());
            rw7.setAttribute("segCpg", listGnrl7.getSegmCpg());
            rw7.setAttribute("activoCpg", listGnrl7.getActivoCpg());
            rw7.setAttribute("codProdCpg", listGnrl7.getCodProd());
            rw7.setAttribute("porcAsigCpg", listGnrl7.getPorcAsig());
            rw7.setAttribute("fecIniCpg", listGnrl7.getFecIni());
            rw7.setAttribute("fecFinCpg", listGnrl7.getFecFin());
            rw7.setAttribute("nomCpg", listGnrl7.getNomCpg());
        }


        //FEEDBACK
        List<Feedback> listFeedback = new ArrayList<Feedback>();
        listFeedback =
                pantOpty.obtenerFeedback(lista2.get(0).getIdOpp().toString());

        Util.Log(lista2.get(0).getIdOpp().toString() + "idOpty");


        Row rw8 = null;

        CollectionModel _tablaGnrl8 = (CollectionModel)tblGnrl8.getValue();
        JUCtrlHierBinding _adfTableGnrl8Binding =
            (JUCtrlHierBinding)_tablaGnrl8.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl8 =
            _adfTableGnrl8Binding.getDCIteratorBinding();

        int h = 0;
        h = itGnrl8.getRowSetIterator().getRowCount();

        for (int i = 0; i < h; i++) {
            itGnrl8.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(listFeedback.size() + "size feedback");


        for (Feedback listGnrl8 : listFeedback) {

            rw8 = itGnrl8.getNavigatableRowIterator().createRow();
            itGnrl8.getNavigatableRowIterator().insertRow(rw8);
            rw8.setNewRowState(Row.STATUS_INITIALIZED);

            rw8.setAttribute("NomFb", listGnrl8.getNomFb());
            rw8.setAttribute("IdFb", listGnrl8.getIdFb());
            rw8.setAttribute("FecGestion", listGnrl8.getFecGestion());
            rw8.setAttribute("tipoGestion", listGnrl8.getNomTipGest());
            rw8.setAttribute("grupoGestion", listGnrl8.getNomGrupGest());
            rw8.setAttribute("detGestion", listGnrl8.getNomDetGest());
            rw8.setAttribute("DescAdGestion", listGnrl8.getDescAdGestion());
            rw8.setAttribute("NomCpg", listGnrl8.getNomCpg());
            rw8.setAttribute("CodCpg", listGnrl8.getCodCpg());
            rw8.setAttribute("MontoDesem", listGnrl8.getMontoDesem());
            rw8.setAttribute("MontoDesem2of", listGnrl8.getMontoDesem2Of());
            rw8.setAttribute("telefono", listGnrl8.getTelefono());
            rw8.setAttribute("IdOpp", listGnrl8.getIdOpty());


            rw8.setAttribute("CanalFb", listGnrl8.getCanal());
            rw8.setAttribute("CodCanalFb", listGnrl8.getIdCanal());
            rw8.setAttribute("Sup", listGnrl8.getSup());
            rw8.setAttribute("JefCanal", listGnrl8.getJefCanal());
            rw8.setAttribute("EjVenta", listGnrl8.getEjVenta());
            rw8.setAttribute("idEjVenta", listGnrl8.getIdEjVenta());
            rw8.setAttribute("idJefCanal", listGnrl8.getIdjefCanal());
            rw8.setAttribute("idSup", listGnrl8.getIdSup());
            rw8.setAttribute("monto", listGnrl8.getMonto());

        }

        txtIdOpty.setValue(lista2.get(0).getIdOpp().toString());
        txtMonto.setValue(listFeedback.get(0).getMonto().toString());
        txtIdEjVenta.setValue(listFeedback.get(0).getIdEjVenta());


    }

    public void agregarTelf01(ActionEvent actionEvent) {
        
     
        String numTelf = "Telefono01";
         
        numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
            btnAgregar.setDisabled(true);

    }

    public void agregarTelefono2(ActionEvent actionEvent) {

        String numTelf = "Telefono02";
         
        numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
            

            btnAgregar2.setDisabled(true);
      
    }

    public void agregarTelefono3(ActionEvent actionEvent) {


        String numTelf = "Telefono03";
         
        numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
        

            btnAgregar3.setDisabled(true);



    }

    public void agregarCelular1(ActionEvent actionEvent) {

       
        String numTelf = "Celular01";
         
     numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
       
            btnAgregar4.setDisabled(true);



    }

    public void agregarCelular2(ActionEvent actionEvent) {

        String numTelf = "Celular02";
         
        numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
        
            btnAgregar5.setDisabled(true);

      

    }

    public void agregarCelular3(ActionEvent actionEvent) {

        String numTelf = "Celular03";
         
        numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);

            btnAgregar6.setDisabled(true);
    }


    public void crearFeedBack() {

        OportunidadLib optyLib = new OportunidadLib();

        String nombreFb = "";
        String idEjVenta = "";
        String ejVenta = "";
        String telefono = "";
        String tipoGestion = "";
        String grupoGestion = "";
        String detalleGestion = "";
        BigDecimal auxFeedbackContactado = new BigDecimal(0);
        BigDecimal auxFeedbackContactadoEfec = new BigDecimal(0);
        BigDecimal auxFeedbackVenta = new BigDecimal(0);
        BigDecimal auxFeedbackRevenueVenta = new BigDecimal(0);
        String nomDetGest = "";
        String nomGrupGest = "";
        String nomTipGest = "";


        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");

        ViewObject vo = dcItteratorBindings2.getViewObject();

        // Row rowSelected2 = voTableData2.getCurrentRow();
        Row[] selectedRows = vo.getFilteredRows("idFb", null);


        for (Row rowSelected2 : selectedRows) {

            nombreFb = nvl(rowSelected2, "nomFb", "");
            idEjVenta = nvl(rowSelected2, "idEjVenta", "");
            ejVenta = nvl(rowSelected2, "ejVenta", "");
            telefono = nvl(rowSelected2, "telf", "");
            tipoGestion = nvl(rowSelected2, "codTipGest", "");
            grupoGestion = nvl(rowSelected2, "codGrupGest", "");
            detalleGestion = nvl(rowSelected2, "codDetGest", "");
            nomDetGest = nvl(rowSelected2, "tipoGest", "");
            nomGrupGest = nvl(rowSelected2, "grupGest", "");
            nomTipGest = nvl(rowSelected2, "detGest", "");


            Util.Log(idEjVenta);
            Util.Log(nombreFb);
            Util.Log(ejVenta);
            Util.Log(telefono);
            Util.Log(tipoGestion + "tipoGest");
            Util.Log(grupoGestion + "grupGest");


            if (tipoGestion.equalsIgnoreCase("1")) {
                Util.Log("tip gest entra");
                auxFeedbackContactado = new BigDecimal(1);

            }

            if (grupoGestion.equalsIgnoreCase("1") ||
                grupoGestion.equalsIgnoreCase("2") ||
                grupoGestion.equalsIgnoreCase("3") ||
                grupoGestion.equalsIgnoreCase("4") ||
                grupoGestion.equalsIgnoreCase("5") ||
                grupoGestion.equalsIgnoreCase("6") ||
                grupoGestion.equalsIgnoreCase("8") ||
                grupoGestion.equalsIgnoreCase("9")) {
                Util.Log("tip gesteee entra");
                auxFeedbackContactadoEfec = new BigDecimal(1);

            }

            if (grupoGestion.equalsIgnoreCase("1")) {
                auxFeedbackVenta = new BigDecimal(1);
                Util.Log("tip gest asdasdentra");
            }

            if (grupoGestion.equalsIgnoreCase("1")) {

                String monto = txtMonto.getValue().toString();


                auxFeedbackRevenueVenta = new BigDecimal(monto);
            }

            if(validarCreacionFb() == true){
                
            
            try {
               /* optyLib.agregarFeedBack(Long.parseLong(txtIdOpty.getValue().toString()),
                                        nombreFb, idEjVenta, ejVenta, telefono,
                                        tipoGestion, grupoGestion,
                                        detalleGestion, auxFeedbackContactado,
                                        auxFeedbackContactadoEfec,
                                        auxFeedbackVenta,
                                        auxFeedbackRevenueVenta, nomDetGest,
                                        nomGrupGest, nomTipGest);

            } catch (WebServiceException e) {
                e.printStackTrace();
            }

            Util.Log(txtIdOpty.getValue().toString() + "idOPP");

            Util.Log(idEjVenta);
            Util.Log(nombreFb);
            Util.Log(ejVenta);
            Util.Log(telefono);
            Util.Log(auxFeedbackContactado + "feedbackContac");
            Util.Log(auxFeedbackContactadoEfec +
                               "feedbackContacEfec");

            Util.Log("ok");
            
            limpiarForm();
            buscarFeedback();
            getPopupFeedback().hide();

            } else{
                
                FacesContext fctx = FacesContext.getCurrentInstance();
                fctx.addMessage("",
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos incompletos - crearfb",
                                                 ""));
                
            }
        }

    }

*/
    public void crearFb(ActionEvent actionEvent) {
    //    crearFeedBack();
    }


    public boolean validarCreacionFb() {

        Boolean crearFb = false;

        DCBindingContainer binding = this.getDCBindingsContainer();
        DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("PopupFeedbackVO1Iterator");

        RowSetIterator rsi = dcItteratorBindings.getRowSetIterator();

        
        if (rsi.getRowCount() > 0) {
            rsi.reset();
            Row currRow;

            while (rsi.hasNext()) {
                currRow = rsi.getCurrentRow();

                String nombreFb = "";
                String idEjVenta = "";
                String ejVenta = "";
                String telefono = "";
                String tipoGestion = "";
                String grupoGestion = "";
                String detalleGestion = "";
                String nomDetGest = "";
                String nomGrupGest = "";
                String nomTipGest = "";
                
                nombreFb = nvl(currRow, "nomFb", "");
                idEjVenta = nvl(currRow, "idEjVenta", "");
                ejVenta = nvl(currRow, "ejVenta", "");
                telefono = nvl(currRow, "telf", "");
                tipoGestion = nvl(currRow, "codTipGest", "");
                grupoGestion = nvl(currRow, "codGrupGest", "");
                detalleGestion = nvl(currRow, "codDetGest", "");
                nomDetGest = nvl(currRow, "tipoGest", "");
                nomGrupGest = nvl(currRow, "grupGest", "");
                nomTipGest = nvl(currRow, "detGest", "");

                rsi.next();
                
                if (!nombreFb.equalsIgnoreCase("") &&
                    !idEjVenta.equalsIgnoreCase("")  &&
                    !ejVenta.equalsIgnoreCase("")  &&
                    !telefono.equalsIgnoreCase("") &&
                    !tipoGestion.equalsIgnoreCase("")  &&
                    !grupoGestion.equalsIgnoreCase("")  &&
                    !detalleGestion.equalsIgnoreCase("") &&
                    !nomDetGest.equalsIgnoreCase("") &&
                    !nomGrupGest.equalsIgnoreCase("") &&
                    !nomTipGest.equalsIgnoreCase(""))  {

            
                    crearFb = true;
                    Util.Log(crearFb + "creafbtrue");


                    } else {

                    Util.Log(crearFb + "creafbfalse");
                   return  crearFb = false;
                    }
            }

            currRow = rsi.last();

            String nombreFb = "";
            String idEjVenta = "";
            String ejVenta = "";
            String telefono = "";
            String tipoGestion = "";
            String grupoGestion = "";
            String detalleGestion = "";
            String nomDetGest = "";
            String nomGrupGest = "";
            String nomTipGest = "";
            
            nombreFb = nvl(currRow, "nomFb", "");
            idEjVenta = nvl(currRow, "idEjVenta", "");
            ejVenta = nvl(currRow, "ejVenta", "");
            telefono = nvl(currRow, "telf", "");
            tipoGestion = nvl(currRow, "codTipGest", "");
            grupoGestion = nvl(currRow, "codGrupGest", "");
            detalleGestion = nvl(currRow, "codDetGest", "");
            nomDetGest = nvl(currRow, "tipoGest", "");
            nomGrupGest = nvl(currRow, "grupGest", "");
            nomTipGest = nvl(currRow, "detGest", "");

            rsi.next();
            
            if (!nombreFb.equalsIgnoreCase("") &&
                !idEjVenta.equalsIgnoreCase("")  &&
                !ejVenta.equalsIgnoreCase("")  &&
                !telefono.equalsIgnoreCase("") &&
                !tipoGestion.equalsIgnoreCase("")  &&
                !grupoGestion.equalsIgnoreCase("")  &&
                !detalleGestion.equalsIgnoreCase("") &&
                !nomDetGest.equalsIgnoreCase("") &&
                !nomGrupGest.equalsIgnoreCase("") &&
                !nomTipGest.equalsIgnoreCase(""))  {
                
                    crearFb = true;
                    Util.Log(crearFb + "creafbtrue");


                } else {

                Util.Log(crearFb + "creafbfalse");
               return  crearFb = false;
            }

        }

        return crearFb;
    }
    
    //crear lote popup
    public void popupCrearFeedback(ActionEvent actionEvent, String numTelf) {
        
        RichPopup.PopupHints hints = new RichPopup.PopupHints();
        

        
        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        Row rw8 = null;


            rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
            dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
            rw8.setNewRowState(Row.STATUS_INITIALIZED);
        
        rw8.setAttribute("telf", numTelf);
        rw8.setAttribute("idEjVenta", txtIdEjVenta.getValue().toString());

        getPopupFeedback().show(hints);
        
        
    }
  
    //NVL

    public String nvl(Row currRow, String a, String b) {
        try {
            if (currRow.getAttribute(a) != null)
                return currRow.getAttribute(a).toString();
            else
                return b;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + a + " NullPointerException");
            return b;
        }

    }
    
    public void buscarFeedback(){
        ServiceBiPublisher pantOpty = new ServiceBiPublisher();
        List<Feedback> listFeedback = new ArrayList<Feedback>();
        listFeedback =
                pantOpty.obtenerFeedback(txtIdOpty.getValue().toString());


        Row rw8 = null;

        CollectionModel _tablaGnrl8 = (CollectionModel)tblGnrl8.getValue();
        JUCtrlHierBinding _adfTableGnrl8Binding =
            (JUCtrlHierBinding)_tablaGnrl8.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itGnrl8 =
            _adfTableGnrl8Binding.getDCIteratorBinding();

        int h = 0;
        h = itGnrl8.getRowSetIterator().getRowCount();

        for (int i = 0; i < h; i++) {
            itGnrl8.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        Util.Log(listFeedback.size() + "size feedback");


        for (Feedback listGnrl8 : listFeedback) {

            rw8 = itGnrl8.getNavigatableRowIterator().createRow();
            itGnrl8.getNavigatableRowIterator().insertRow(rw8);
            rw8.setNewRowState(Row.STATUS_INITIALIZED);

            rw8.setAttribute("NomFb", listGnrl8.getNomFb());
            rw8.setAttribute("IdFb", listGnrl8.getIdFb());
            rw8.setAttribute("FecGestion", listGnrl8.getFecGestion());
            rw8.setAttribute("tipoGestion", listGnrl8.getNomTipGest());
            rw8.setAttribute("grupoGestion", listGnrl8.getNomGrupGest());
            rw8.setAttribute("detGestion", listGnrl8.getNomDetGest());
            rw8.setAttribute("DescAdGestion", listGnrl8.getDescAdGestion());
            rw8.setAttribute("NomCpg", listGnrl8.getNomCpg());
            rw8.setAttribute("CodCpg", listGnrl8.getCodCpg());
            rw8.setAttribute("MontoDesem", listGnrl8.getMontoDesem());
            rw8.setAttribute("MontoDesem2of", listGnrl8.getMontoDesem2Of());
            rw8.setAttribute("telefono", listGnrl8.getTelefono());
            rw8.setAttribute("IdOpp", listGnrl8.getIdLead());
            rw8.setAttribute("CanalFb", listGnrl8.getCanal());
            rw8.setAttribute("CodCanalFb", listGnrl8.getIdCanal());
            rw8.setAttribute("Sup", listGnrl8.getSup());
            rw8.setAttribute("JefCanal", listGnrl8.getJefCanal());
            rw8.setAttribute("EjVenta", listGnrl8.getEjVenta());
            rw8.setAttribute("idEjVenta", listGnrl8.getIdEjVenta());
            rw8.setAttribute("idJefCanal", listGnrl8.getIdjefCanal());
            rw8.setAttribute("idSup", listGnrl8.getIdSup());
            rw8.setAttribute("monto", listGnrl8.getMonto());

        }
    }

    public String limpiarForm() {
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding = bindings.getOperationBinding("CreateInsert");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    
    public String obtenerNumTelf(String numTelf){
    
    ServiceBiPublisher pantOpty = new ServiceBiPublisher();


    //FEEDBACK
    List<Feedback> listFeedback = new ArrayList<Feedback>();
    listFeedback =
            pantOpty.obtenerFeedback(txtIdOpty.getValue().toString());

    Util.Log(txtIdOpty.getValue().toString() + "idopty");

    String telf;
    DCBindingContainer bindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding dcItteratorBindings =
        bindings.findIteratorBinding("CpgOptyProdContAccFb2Iterator");

    // Get an object representing the table and what may be selected within it
    ViewObject voTableData = dcItteratorBindings.getViewObject();

    // Get selected row
    Row rowSelected = voTableData.getCurrentRow();

    // Display attribute of row in console output - would generally be bound to a UI component like a Label and or used to call another proces
    telf = rowSelected.getAttribute(numTelf).toString();
    Util.Log(telf + "gelf");

    //Iterador de la cabecera de busqueda de documentos
    String idFb = "";
    DCBindingContainer bindings2 =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    DCIteratorBinding dcItteratorBindings2 =
        bindings2.findIteratorBinding("FeedBackVo1Iterator");

    Row rw8 = null;

    Util.Log(listFeedback.size() + "size feedback");

    for (Feedback listGnrl8 : listFeedback) {

        rw8 =
    dcItteratorBindings2.getNavigatableRowIterator().createRow();
        dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
        rw8.setNewRowState(Row.STATUS_INITIALIZED);

        rw8.setAttribute("IdOpp", listGnrl8.getIdLead());
        rw8.setAttribute("telefono", telf);
        rw8.setAttribute("CanalFb", listGnrl8.getCanal());
        rw8.setAttribute("CodCanalFb", listGnrl8.getIdCanal());
        rw8.setAttribute("Sup", listGnrl8.getSup());
        rw8.setAttribute("JefCanal", listGnrl8.getJefCanal());
        /*  rw8.setAttribute("EjVenta", listGnrl8.getEjVenta());
    rw8.setAttribute("idEjVenta", listGnrl8.getIdEjVenta());*/
        rw8.setAttribute("idJefCanal", listGnrl8.getIdjefCanal());
        rw8.setAttribute("idSup", listGnrl8.getIdSup());
        rw8.setAttribute("NomCpg", listGnrl8.getNomCpg());
        rw8.setAttribute("CodCpg", listGnrl8.getCodCpg());
        break;
    }
    return telf;
    }
    
    public String obtenerValorTelf(String valorTelf){
        
        String telf;
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("CpgOptyProdContAccFb2Iterator");

        // Get an object representing the table and what may be selected within it
        ViewObject voTableData = dcItteratorBindings.getViewObject();

        // Get selected row
        Row rowSelected = voTableData.getCurrentRow();
        

        // Display attribute of row in console output - would generally be bound to a UI component like a Label and or used to call another proces
        telf = rowSelected.getAttribute(valorTelf).toString();
        rowSelected.getAttribute(1);
        return telf;
        
    }
}
