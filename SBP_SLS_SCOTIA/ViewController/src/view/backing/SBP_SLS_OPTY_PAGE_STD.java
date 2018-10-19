package view.backing;

import Client.LeadLib;
import Client.MergeLeadInvoke;
import Client.OportunidadLib;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;
import dao.CpgLeadContAccProdFb;
import dao.CpgOppContAccProdFb;

import dao.EjVenta;
import dao.Feedback;

import dao.LoginUser;
import dao.LeadRandom;

import java.io.IOException;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceException;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;


import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import org.xml.sax.SAXException;

import view.DosificacionEjecutivoBE;
import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_OPTY_PAGE_STD {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb3;
    private RichPanelBox pb4;
    private RichMessages m1;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputText it6;
    private RichInputText it7;
    private RichInputText it8;
    private RichInputText it9;
    private RichInputText it10;
    private RichCommandButton cb1;
    private RichCommandButton cb2;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s1;
    private RichInputText txtIdOpty;
    private RichCommandButton btnTelf01;
    private RichInputText txtIdEjVenta;
    private RichInputText txtMonto;
    private RichPopup popupFeedback;
    private RichSelectOneChoice txtTipGest;
    private RichSelectOneChoice txtDetGest;
    private RichInputText txtFeedback;
    private RichSelectOneChoice txtGrupGest;
    private RichInputText txtCodTipGest;
    private RichInputText txtCodGrupGest;
    private RichInputText txtCodDetGest;
    private RichOutputText txtFecGestion;
    private RichPanelBox pnlFeedback;
    private RichInputText txtEmailAd;
    private RichInputText txtDirAd;
    private RichInputText txtTelfAd;
    private RichInputText txtObs;
    private RichCommandButton btnOpty1;
    private RichCommandButton btnOpty2;
    private RichCommandButton btnOpty3;
    private RichInputText txtJefCanal;
    private RichInputText txtIdSup;
    private RichInputText txtSup;
    private RichInputText txtIdJefCanl;
    private RichInputText txtIdJefCanal;
    private RichInputText txtCanal;
    private RichInputText txtIdCanal;
    private RichInputText txtCpgId;
    private RichInputText txtLeadOld;
    private RichInputText txtLeadNew;
    private RichInputText txtIdEj;
    private RichInputText txtCanalId;
    private RichInputDate txt2daLlam;
    private RichInputText txtEjecutivoVenta;
    private RichPopup popupConfirm;
    private RichPopup popupTelfExtra;
    private RichInputText txtFecGest;
    private RichInputText txtFeedbackTlfExt;
    private RichInputText txtFecGestTlfExt;
    private RichInputText txtTelfExtra;
    private RichSelectOneChoice txtTipGestTlfExt;
    private RichSelectOneChoice txtGrupGestTlfExt;
    private RichSelectOneChoice txtDetGestTlfExt;
    private RichInputText txtCodTipGestTlfExt;
    private RichInputText txtCodGrupGestTlfExt;
    private RichInputText txtCodDetGestTlfExt;
    private RichPopup popupMensajeError;
    private RichCommandButton btnTelfExtra;
    private RichOutputLabel txtNombre;
    private RichOutputLabel txtEdad;
    private RichOutputLabel txtSexo;
    private RichOutputLabel txtFecNac;
    private RichOutputLabel txtTipDoc;
    private RichOutputLabel txtNumDoc;
    private RichOutputLabel txtEmail;
    private RichOutputLabel txtProd;
    private RichTable tblFbContacto;
    private RichOutputLabel txtMontoMaximo;
    private RichOutputLabel txtMontoMinimo;
    private RichOutputLabel txtPlazoMinimo;
    private RichOutputLabel txtPlazoMaximo;
    private RichOutputLabel txtCuota;
    private RichCommandLink txtTelf01;
    private RichCommandLink txtCel01;
    private RichCommandLink txtTelf02;
    private RichCommandLink txtCel02;
    private RichCommandLink txtTelf03;
    private RichCommandLink txtCel03;
    private RichOutputLabel txtDeudaPp;
    private RichOutputLabel txtDeudaTc;
    private RichOutputLabel txtLinTc;
    private RichOutputLabel txtBancoPp;
    private RichOutputLabel txtSbs;
    private RichOutputLabel txtBancoTc;
    private RichInputText txtContFb;
    private RichInputText txtMontMin;
    private RichInputText txtMontMax;
    private RichPopup popupMontoMin;
    private RichPopup popupMontMaximo;
    private RichSelectOneChoice txtGrupoGestion;
    private RichInputText txtGrupGestTlfExtra;
    private RichOutputLabel txtMensajeValMonto;
    private RichPopup popupValidarCampos;
    private RichOutputLabel txtProv;
    private RichOutputLabel txtDpto;
    private RichOutputLabel txtDistrito;
    private RichOutputLabel txtDir;
    private RichInputText txtMontoFb;
    private RichInputText txtMontoFbExtra;

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

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPb3(RichPanelBox pb3) {
        this.pb3 = pb3;
    }

    public RichPanelBox getPb3() {
        return pb3;
    }

    public void setPb4(RichPanelBox pb4) {
        this.pb4 = pb4;
    }

    public RichPanelBox getPb4() {
        return pb4;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
    }

    public void setIt6(RichInputText it6) {
        this.it6 = it6;
    }

    public RichInputText getIt6() {
        return it6;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setIt8(RichInputText it8) {
        this.it8 = it8;
    }

    public RichInputText getIt8() {
        return it8;
    }

    public void setIt9(RichInputText it9) {
        this.it9 = it9;
    }

    public RichInputText getIt9() {
        return it9;
    }

    public void setIt10(RichInputText it10) {
        this.it10 = it10;
    }

    public RichInputText getIt10() {
        return it10;
    }


    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void setCb2(RichCommandButton cb2) {
        this.cb2 = cb2;
    }

    public RichCommandButton getCb2() {
        return cb2;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setTxtIdOpty(RichInputText txtIdOpty) {
        this.txtIdOpty = txtIdOpty;
    }

    public RichInputText getTxtIdOpty() {
        return txtIdOpty;
    }

    public void setBtnTelf01(RichCommandButton btnTelf01) {
        this.btnTelf01 = btnTelf01;
    }

    public RichCommandButton getBtnTelf01() {
        return btnTelf01;
    }

    public void setTxtIdEjVenta(RichInputText txtIdEjVenta) {
        this.txtIdEjVenta = txtIdEjVenta;
    }

    public RichInputText getTxtIdEjVenta() {
        return txtIdEjVenta;
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

    public void setTxtTipGest(RichSelectOneChoice txtTipGest) {
        this.txtTipGest = txtTipGest;
    }

    public RichSelectOneChoice getTxtTipGest() {
        return txtTipGest;
    }

    public void setTxtDetGest(RichSelectOneChoice txtDetGest) {
        this.txtDetGest = txtDetGest;
    }

    public RichSelectOneChoice getTxtDetGest() {
        return txtDetGest;
    }

    public void setTxtFeedback(RichInputText txtFeedback) {
        this.txtFeedback = txtFeedback;
    }

    public RichInputText getTxtFeedback() {
        return txtFeedback;
    }

    public void setTxtGrupGest(RichSelectOneChoice txtGrupGest) {
        this.txtGrupGest = txtGrupGest;
    }

    public RichSelectOneChoice getTxtGrupGest() {
        return txtGrupGest;
    }


    public void setTxtCodTipGest(RichInputText txtCodTipGest) {
        this.txtCodTipGest = txtCodTipGest;
    }

    public RichInputText getTxtCodTipGest() {
        return txtCodTipGest;
    }

    public void setTxtCodGrupGest(RichInputText txtCodGrupGest) {
        this.txtCodGrupGest = txtCodGrupGest;
    }

    public RichInputText getTxtCodGrupGest() {
        return txtCodGrupGest;
    }

    public void setTxtCodDetGest(RichInputText txtCodDetGest) {
        this.txtCodDetGest = txtCodDetGest;
    }

    public RichInputText getTxtCodDetGest() {
        return txtCodDetGest;
    }

    public void setTxtFecGestion(RichOutputText txtFecGestion) {
        this.txtFecGestion = txtFecGestion;
    }

    public RichOutputText getTxtFecGestion() {
        return txtFecGestion;
    }

    public void setPnlFeedback(RichPanelBox pnlFeedback) {
        this.pnlFeedback = pnlFeedback;
    }

    public RichPanelBox getPnlFeedback() {
        return pnlFeedback;
    }

    public void setTxtEmailAd(RichInputText txtEmailAd) {
        this.txtEmailAd = txtEmailAd;
    }

    public RichInputText getTxtEmailAd() {
        return txtEmailAd;
    }

    public void setTxtDirAd(RichInputText txtDirAd) {
        this.txtDirAd = txtDirAd;
    }

    public RichInputText getTxtDirAd() {
        return txtDirAd;
    }

    public void setTxtTelfAd(RichInputText txtTelfAd) {
        this.txtTelfAd = txtTelfAd;
    }

    public RichInputText getTxtTelfAd() {
        return txtTelfAd;
    }

    public void setTxtObs(RichInputText txtObs) {
        this.txtObs = txtObs;
    }

    public RichInputText getTxtObs() {
        return txtObs;
    }

    public void setBtnOpty1(RichCommandButton btnOpty1) {
        this.btnOpty1 = btnOpty1;
    }

    public RichCommandButton getBtnOpty1() {
        return btnOpty1;
    }

    public void setBtnOpty2(RichCommandButton btnOpty2) {
        this.btnOpty2 = btnOpty2;
    }

    public RichCommandButton getBtnOpty2() {
        return btnOpty2;
    }

    public void setBtnOpty3(RichCommandButton btnOpty3) {
        this.btnOpty3 = btnOpty3;
    }

    public RichCommandButton getBtnOpty3() {
        return btnOpty3;
    }


    public void setTxtJefCanal(RichInputText txtJefCanal) {
        this.txtJefCanal = txtJefCanal;
    }

    public RichInputText getTxtJefCanal() {
        return txtJefCanal;
    }

    public void setTxtIdSup(RichInputText txtIdSup) {
        this.txtIdSup = txtIdSup;
    }

    public RichInputText getTxtIdSup() {
        return txtIdSup;
    }

    public void setTxtSup(RichInputText txtSup) {
        this.txtSup = txtSup;
    }

    public RichInputText getTxtSup() {
        return txtSup;
    }

    public void setTxtIdJefCanl(RichInputText txtIdJefCanl) {
        this.txtIdJefCanl = txtIdJefCanl;
    }

    public RichInputText getTxtIdJefCanl() {
        return txtIdJefCanl;
    }

    public void setTxtIdJefCanal(RichInputText txtIdJefCanal) {
        this.txtIdJefCanal = txtIdJefCanal;
    }

    public RichInputText getTxtIdJefCanal() {
        return txtIdJefCanal;
    }

    public void setTxtCanal(RichInputText txtCanal) {
        this.txtCanal = txtCanal;
    }

    public RichInputText getTxtCanal() {
        return txtCanal;
    }

    public void setTxtIdCanal(RichInputText txtIdCanal) {
        this.txtIdCanal = txtIdCanal;
    }

    public RichInputText getTxtIdCanal() {
        return txtIdCanal;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setTxtLeadOld(RichInputText txtLeadOld) {
        this.txtLeadOld = txtLeadOld;
    }

    public RichInputText getTxtLeadOld() {
        return txtLeadOld;
    }

    public void setTxtLeadNew(RichInputText txtLeadNew) {
        this.txtLeadNew = txtLeadNew;
    }

    public RichInputText getTxtLeadNew() {
        return txtLeadNew;
    }

    public void setTxtIdEj(RichInputText txtIdEj) {
        this.txtIdEj = txtIdEj;
    }

    public RichInputText getTxtIdEj() {
        return txtIdEj;
    }

    public void setTxtCanalId(RichInputText txtCanalId) {
        this.txtCanalId = txtCanalId;
    }

    public RichInputText getTxtCanalId() {
        return txtCanalId;
    }

    public void setTxt2daLlam(RichInputDate txt2daLlam) {
        this.txt2daLlam = txt2daLlam;
    }

    public RichInputDate getTxt2daLlam() {
        return txt2daLlam;
    }

    public void setTxtEjecutivoVenta(RichInputText txtEjecutivoVenta) {
        this.txtEjecutivoVenta = txtEjecutivoVenta;
    }

    public RichInputText getTxtEjecutivoVenta() {
        return txtEjecutivoVenta;
    }

    public void setPopupConfirm(RichPopup popupConfirm) {
        this.popupConfirm = popupConfirm;
    }

    public RichPopup getPopupConfirm() {
        return popupConfirm;
    }
    
    public void setPopupTelfExtra(RichPopup popupTelfExtra) {
        this.popupTelfExtra = popupTelfExtra;
    }

    public RichPopup getPopupTelfExtra() {
        return popupTelfExtra;
    }

    public void setTxtFecGest(RichInputText txtFecGest) {
        this.txtFecGest = txtFecGest;
    }

    public RichInputText getTxtFecGest() {
        return txtFecGest;
    }
    
    public void setTxtFeedbackTlfExt(RichInputText txtFeedbackTlfExt) {
        this.txtFeedbackTlfExt = txtFeedbackTlfExt;
    }

    public RichInputText getTxtFeedbackTlfExt() {
        return txtFeedbackTlfExt;
    }

    public void setTxtFecGestTlfExt(RichInputText txtFecGestTlfExt) {
        this.txtFecGestTlfExt = txtFecGestTlfExt;
    }

    public RichInputText getTxtFecGestTlfExt() {
        return txtFecGestTlfExt;
    }

    public void setTxtTelfExtra(RichInputText txtTelfExtra) {
        this.txtTelfExtra = txtTelfExtra;
    }

    public RichInputText getTxtTelfExtra() {
        return txtTelfExtra;
    }
    
    public void setTxtCodTipGestTlfExt(RichInputText txtCodTipGestTlfExt) {
        this.txtCodTipGestTlfExt = txtCodTipGestTlfExt;
    }

    public RichInputText getTxtCodTipGestTlfExt() {
        return txtCodTipGestTlfExt;
    }

    public void setTxtCodGrupGestTlfExt(RichInputText txtCodGrupGestTlfExt) {
        this.txtCodGrupGestTlfExt = txtCodGrupGestTlfExt;
    }

    public RichInputText getTxtCodGrupGestTlfExt() {
        return txtCodGrupGestTlfExt;
    }

    public void setTxtCodDetGestTlfExt(RichInputText txtCodDetGestTlfExt) {
        this.txtCodDetGestTlfExt = txtCodDetGestTlfExt;
    }

    public RichInputText getTxtCodDetGestTlfExt() {
        return txtCodDetGestTlfExt;
    }

    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    

    public void buscarFeedback(String IdLead) {
        try {
            ServiceBiPublisher pantOpty = new ServiceBiPublisher();
            List<Feedback> listFeedback = new ArrayList<Feedback>();
            listFeedback = pantOpty.obtenerFeedback(IdLead);

            Util.Log("buscarFeedback size feedback " + listFeedback.size());


            CollectionModel _tablaFeedback = (CollectionModel)tblFbContacto.getValue();
            JUCtrlHierBinding _adfTableFeedbackBinding = (JUCtrlHierBinding)_tablaFeedback.getWrappedData(); //devuelve el table binding
            DCIteratorBinding itFeedback = _adfTableFeedbackBinding.getDCIteratorBinding();

            int h = 0;
            h = itFeedback.getRowSetIterator().getRowCount();
            for (int i = 0; i < h; i++) {
                itFeedback.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }
            Row rw8 = null;

            String idFb = null;
            
    
            for (Feedback opp8 : listFeedback) {
       
               rw8 = itFeedback.getNavigatableRowIterator().createRow();

               itFeedback.getNavigatableRowIterator().insertRow(rw8);
      
                rw8.setNewRowState(Row.STATUS_INITIALIZED);
  
                rw8.setAttribute("NomFb", opp8.getNomFb());
                rw8.setAttribute("IdFb", opp8.getIdFb());
                Util.Log("buscar fb 9"); 
                rw8.setAttribute("FecGestion", opp8.getFecGestion());
                rw8.setAttribute("TipoGestion", opp8.getNomTipGest());
                rw8.setAttribute("GrupoGestion", opp8.getNomGrupGest());
                rw8.setAttribute("DetGestion", opp8.getNomDetGest());
                rw8.setAttribute("DescAdGestion", opp8.getDescAdGestion());
                rw8.setAttribute("NomCpg", opp8.getNomCpg());
                rw8.setAttribute("CodCpg", opp8.getCodCpg());
                rw8.setAttribute("MontoDesem", Util.formatearMonto(opp8.getMontoDesem()));
                rw8.setAttribute("MontoDesem2of", Util.formatearMonto(opp8.getMontoDesem2Of()));
                rw8.setAttribute("telefono", opp8.getTelefono());
                rw8.setAttribute("IdLead", opp8.getIdLead());
                rw8.setAttribute("CanalFb", opp8.getCanal());
                rw8.setAttribute("CodCanalFb", opp8.getIdCanal());
                rw8.setAttribute("Sup", opp8.getSup());
                rw8.setAttribute("JefCanal", opp8.getJefCanal());
                rw8.setAttribute("EjVenta", opp8.getEjVenta());
                rw8.setAttribute("idEjVenta", opp8.getIdEjVenta());
                rw8.setAttribute("idJefCanal", opp8.getIdjefCanal());
                rw8.setAttribute("idSup", opp8.getIdSup());
                rw8.setAttribute("monto", Util.formatearMonto(opp8.getMonto()));
                rw8.setAttribute("telfAd", opp8.getTelfAd());
                rw8.setAttribute("obs", opp8.getObs());
                rw8.setAttribute("dirAd", opp8.getDirAd());
                rw8.setAttribute("emailAd", opp8.getEmailAd());
                rw8.setAttribute("fec2daLlam", opp8.getFec2daLlam());

               idFb = opp8.getIdFb().toString();
              Util.Log(idFb + "ifFB");
            }
            
        } catch (Exception ex) {
            Util.Log("buscarFeedback fff" + ex.getMessage());
        }
    }

    public String obtenerNumTelf(String numTelf) {

        ServiceBiPublisher pantOpty = new ServiceBiPublisher();

        ADFContext ctx = ADFContext.getCurrent();

        String idLead =
            ctx.getSessionScope().get("IdOpty") == null ? "" : ctx.getSessionScope().get("IdOpty").toString();


        //FEEDBACK
        List<Feedback> listFeedback = new ArrayList<Feedback>();
        listFeedback = pantOpty.obtenerFeedback(idLead);


        String telf = "";
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("CpgOptyProdContAccFb1Iterator");

        // Get an object representing the table and what may be selected within it
        ViewObject voTableData = dcItteratorBindings.getViewObject();

        // Get selected row
        Row rowSelected = voTableData.getCurrentRow();

        if (rowSelected.getAttribute(numTelf) != null || rowSelected.getAttribute(numTelf) == "") {

            // Display attribute of row in console output - would generally be bound to a UI component like a Label and or used to call another proces
            telf = rowSelected.getAttribute(numTelf).toString();
            Util.Log(telf + "gelf");

            //Iterador de la cabecera de busqueda de documentos
            DCBindingContainer bindings2 =
                (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
            DCIteratorBinding dcItteratorBindings2 =
                bindings2.findIteratorBinding("FeedBackVo1Iterator");

            Row rw8 = null;

            Util.Log(listFeedback.size() + "size feedback");

            for (Feedback listGnrl8 : listFeedback) {

                rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
                dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
                rw8.setNewRowState(Row.STATUS_INITIALIZED);

                rw8.setAttribute("IdLead", listGnrl8.getIdLead());
                rw8.setAttribute("telefono", telf);
                rw8.setAttribute("CanalFb", listGnrl8.getCanal());
                rw8.setAttribute("CodCanalFb", listGnrl8.getIdCanal());
                rw8.setAttribute("Sup", listGnrl8.getSup());
                rw8.setAttribute("JefCanal", listGnrl8.getJefCanal());
                rw8.setAttribute("idJefCanal", listGnrl8.getIdjefCanal());
                rw8.setAttribute("idSup", listGnrl8.getIdSup());
                rw8.setAttribute("NomCpg", listGnrl8.getNomCpg());
                rw8.setAttribute("CodCpg", listGnrl8.getCodCpg());
                break;
            }
        } 
    
        return telf;
    }

    public void popupCrearFeedback(ActionEvent actionEvent, String numTelf) {

        ADFContext ctx = ADFContext.getCurrent();

        String canalId =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String supId =
            ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String nomCanal =
            ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefeCanal =
            ctx.getSessionScope().get("JefeCanal") == null ? "" :
            ctx.getSessionScope().get("JefeCanal").toString();
        String idJefeCanal =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String sup =
            ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String ejVenta =
            ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjecVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();
        
        String montoFb =
            ctx.getSessionScope().get("montoLead") == null ? "" :
            ctx.getSessionScope().get("montoLead").toString();
        
        txtMontoFb.setValue(Util.formatearMonto(montoFb));

        String nomFeedback = "";
        String fecGestion = "";

        RichPopup.PopupHints hints = new RichPopup.PopupHints();

        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        Row rw8 = null;


        DateFormat df = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
        java.util.Date fecha = new Date();
       // Date fecActual = Calendar.getInstance().getTime();
        fecGestion = df.format(fecha);

        txtFecGestion.setValue(fecGestion);

        nomFeedback = "Feedback" + "-" + fecGestion;
        txtFeedback.setValue(nomFeedback);

        rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
        dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
        rw8.setNewRowState(Row.STATUS_INITIALIZED);


        rw8.setAttribute("telf", numTelf);
        rw8.setAttribute("nomFb", txtFeedback.getValue().toString());
        rw8.setAttribute("idEjVenta", idEjecVenta);
        rw8.setAttribute("canal", nomCanal);
        rw8.setAttribute("idCanal", canalId);
        rw8.setAttribute("idJefCanal", idJefeCanal);
        rw8.setAttribute("jefCanal", jefeCanal);
        rw8.setAttribute("idSup", supId);
        rw8.setAttribute("sup", sup);
        rw8.setAttribute("ejVenta", ejVenta);
        rw8.setAttribute("fecGestion", txtFecGestion.getValue().toString());

        Util.Log(txtFeedback.getValue().toString() + "fb");

        getPopupFeedback().show(hints);
    }
    
    public void popupCrearFeedbackTelfExt(ActionEvent actionEvent) {

        ADFContext ctx = ADFContext.getCurrent();

        String canalId =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String supId =
            ctx.getSessionScope().get("SupId") == null ? "" : ctx.getSessionScope().get("SupId").toString();
        String nomCanal =
            ctx.getSessionScope().get("Canal") == null ? "" : ctx.getSessionScope().get("Canal").toString();
        String jefeCanal =
            ctx.getSessionScope().get("JefeCanal") == null ? "" :
            ctx.getSessionScope().get("JefeCanal").toString();
        String idJefeCanal =
            ctx.getSessionScope().get("IdCan") == null ? "" : ctx.getSessionScope().get("IdCan").toString();
        String sup =
            ctx.getSessionScope().get("IdJefeCanal") == null ? "" : ctx.getSessionScope().get("IdJefeCanal").toString();
        String ejVenta =
            ctx.getSessionScope().get("EjecVenta") == null ? "" : ctx.getSessionScope().get("EjecVenta").toString();
        String idEjecVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();
        
        String montoFb =
            ctx.getSessionScope().get("montoLead") == null ? "" :
            ctx.getSessionScope().get("montoLead").toString();
        
        txtMontoFbExtra.setValue(Util.formatearMonto(montoFb));

        String nomFeedback = "";
        String fecGestion = "";

        RichPopup.PopupHints hints = new RichPopup.PopupHints();

        DCBindingContainer bindings2 =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings2 =
            bindings2.findIteratorBinding("PopupFeedbackVO1Iterator");


        Row rw8 = null;


        DateFormat df = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
        java.util.Date fecha = new Date();
        // Date fecActual = Calendar.getInstance().getTime();
        fecGestion = df.format(fecha);

        txtFecGestTlfExt.setValue(fecGestion);

        nomFeedback = "Feedback" + "-" + fecGestion;
        txtFeedbackTlfExt.setValue(nomFeedback);

        rw8 = dcItteratorBindings2.getNavigatableRowIterator().createRow();
        dcItteratorBindings2.getNavigatableRowIterator().insertRow(rw8);
        rw8.setNewRowState(Row.STATUS_INITIALIZED);


        rw8.setAttribute("nomFb", txtFeedbackTlfExt.getValue().toString());
        rw8.setAttribute("idEjVenta", idEjecVenta);
        rw8.setAttribute("canal", nomCanal);
        rw8.setAttribute("idCanal", canalId);
        rw8.setAttribute("idJefCanal", idJefeCanal);
        rw8.setAttribute("jefCanal", jefeCanal);
        rw8.setAttribute("idSup", supId);
        rw8.setAttribute("sup", sup);
        rw8.setAttribute("ejVenta", ejVenta);
        rw8.setAttribute("fecGestion", txtFecGestTlfExt.getValue().toString());

        Util.Log(txtFeedback.getValue().toString() + "fb");

        getPopupTelfExtra().show(hints);
    }

    public void agregarTelf1(ActionEvent actionEvent) {

        String numTelf = txtTelf01.getText();

    //       numTelf = obtenerNumTelf(numTelf);
        Util.Log("GHB1 : " + numTelf);
        popupCrearFeedback(actionEvent, numTelf);


    }

    public void agregarTelf02(ActionEvent actionEvent) {

        String numTelf = txtTelf02.getText();

    //       numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarTelf03(ActionEvent actionEvent) {

        String numTelf = txtTelf03.getText();

    //         numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel01(ActionEvent actionEvent) {
        
        String numTelf = txtCel01.getText();
        
      //  numTelf = obtenerNumTelf(numTelf);

        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel02(ActionEvent actionEvent) {

        String numTelf = txtCel02.getText();

    //     numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
    }

    public void agregarCel03(ActionEvent actionEvent) {

        String numTelf = txtCel03.getText();

     //    numTelf = obtenerNumTelf(numTelf);
        popupCrearFeedback(actionEvent, numTelf);
    }
    
    public void agregarTelfExtra(ActionEvent actionEvent) {
        
        popupCrearFeedbackTelfExt(actionEvent);
    }

    public void limpiarPopup(Row rowSelected) {
        rowSelected.remove();
    }
    
    public void actualizarCheckCont(String tipoGestion){
        
       ADFContext ctx = ADFContext.getCurrent();
       
        MergeLeadInvoke leadPayload = new MergeLeadInvoke();
        
       String idLead =
                ctx.getSessionScope().get("LEADID") == null ? "" : ctx.getSessionScope().get("LEADID").toString();

        Util.Log(idLead + "ID LEAD");
        Util.Log(tipoGestion + "TIPO GESTION");
        
        if(tipoGestion.equalsIgnoreCase("1")){


            try {
                leadPayload.mergeLeadCont(Long.parseLong(idLead), true);
            } catch (ParserConfigurationException e) {
            } catch (SAXException e) {
            } catch (IOException e) {
            }
        }
    }
    
    public void actualizarCheckContEfec(String grupoGestion){
        
       ADFContext ctx = ADFContext.getCurrent();
       
        MergeLeadInvoke leadPayload = new MergeLeadInvoke();
        
       String idLead =
                ctx.getSessionScope().get("LEADID") == null ? "" : ctx.getSessionScope().get("LEADID").toString();

        Util.Log(idLead + "ID LEAD");
        Util.Log(grupoGestion + "GRUPO GESTION");
        
            if (grupoGestion.equalsIgnoreCase("1") ||
                grupoGestion.equalsIgnoreCase("2") ||
                grupoGestion.equalsIgnoreCase("3") ||
                grupoGestion.equalsIgnoreCase("4") ||
                grupoGestion.equalsIgnoreCase("5") ||
                grupoGestion.equalsIgnoreCase("6") ||
                grupoGestion.equalsIgnoreCase("8") ||
                grupoGestion.equalsIgnoreCase("9")){


            try {
                leadPayload.mergeLeadContEfec(Long.parseLong(idLead), true);
            } catch (ParserConfigurationException e) {
            } catch (SAXException e) {
            } catch (IOException e) {
            }
        }
    }



    


    public void crearFb(ActionEvent actionEvent) throws ParseException, DatatypeConfigurationException,
                                                        ParserConfigurationException,
                                                        SAXException,
                                                        IOException {
      //  crearFeedBack();
    }
    
    public void crearFbTlfExtra(ActionEvent actionEvent) throws ParseException, DatatypeConfigurationException,
                                                                ParserConfigurationException,
                                                                SAXException,
                                                                IOException {
       // crearFeedBackTlfExtra();
    }

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

    private String nvlNoRow(RichInputText txtCodDetGest, String string) {
        try {
            Util.Log(txtCodDetGest.getValue().toString() + "CODDETGEST");
            if (!txtCodDetGest.getValue().toString().equalsIgnoreCase(""))
                return txtCodDetGest.getValue().toString();
            else
                return string;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + txtCodDetGest +
                               " NullPointerException");
            return string;
        }
    }
    
    private String nvlNoRowString(String txtCodDetGest, String string) {
        try {
            if (txtCodDetGest != null)
                return txtCodDetGest;
            else
                return string;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + txtCodDetGest +
                               " NullPointerException");
            return string;
        }
    }


    public boolean validarCreacionFb(Row currRow) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String tipoGestion = ctx.getSessionScope().get("tipGest") == null ? "" : ctx.getSessionScope().get("tipGest").toString();
        String grupoGestion = ctx.getSessionScope().get("grupGest") == null ? "" : ctx.getSessionScope().get("grupGest").toString();
        String detalleGestion = ctx.getSessionScope().get("detGest") == null ? "" : ctx.getSessionScope().get("detGest").toString();
        String nomDetGest = ctx.getSessionScope().get("nomDetGest") == null ? "" : ctx.getSessionScope().get("nomDetGest").toString();
        String nomGrupGest = ctx.getSessionScope().get("nomGrupGest") == null ? "" : ctx.getSessionScope().get("nomGrupGest").toString();
        String nomTipGest = ctx.getSessionScope().get("nomTipGest") == null ? "" : ctx.getSessionScope().get("nomTipGest").toString();
        
        Boolean crearFb = false;
        String nombreFb = "";
        String idCanal = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";
        String telefono = "";
        String obs = "";
        String telfAd = "";
        String dirAd = "";
        String emailAd = "";
        
        Util.Log(tipoGestion + "tipoGestion");
        Util.Log(grupoGestion + "grupoGestion");
        Util.Log(detalleGestion + "detalleGestion");
        Util.Log(nomDetGest + "nomDetGest");
        Util.Log(nomGrupGest + "nomGrupGest");
        Util.Log(nomTipGest + "nomTipGest");

        nombreFb = nvl(currRow, "nomFb", "");
        idCanal = nvl(currRow, "idCanal", "");
        canal = nvl(currRow, "canal", "");
        idJefCanal = nvl(currRow, "idJefCanal", "");
        jefCanal = nvl(currRow, "jefCanal", "");
        idSup = nvl(currRow, "idSup", "");
        sup = nvl(currRow, "sup", "");
        idEjVenta = nvl(currRow, "idEjVenta", "");
        ejVenta = nvl(currRow, "ejVenta", "");
        telefono = nvl(currRow, "telf", "");
        obs = nvl(currRow, "observaciones", " ");
        telfAd = nvl(currRow, "telfAd", " ");
        dirAd = nvl(currRow, "direccionAd", " ");
        emailAd = nvl(currRow, "emailAd", " ");
        
        Util.Log(nombreFb + "nombreFb");
        Util.Log(idCanal + "idCanal");
        Util.Log(canal + "canal");
        Util.Log(idJefCanal + "idJefCanal");
        Util.Log(jefCanal + "jefCanal");
        Util.Log(idSup + "idSup");
        Util.Log(sup + "sup");
        Util.Log(idEjVenta + "idEjVenta");
        Util.Log(ejVenta + "ejVenta");
        Util.Log(telefono + "telefono");
        Util.Log(obs + "obs");
        Util.Log(telfAd + "telfAd");
        Util.Log(dirAd + "dirAd");
        Util.Log(emailAd + "emailAd");
        

        if (!nombreFb.equalsIgnoreCase("") &&
            !idEjVenta.equalsIgnoreCase("") && !idCanal.equalsIgnoreCase("") &&
            !canal.equalsIgnoreCase("") && !idJefCanal.equalsIgnoreCase("") &&
            !jefCanal.equalsIgnoreCase("") && !idSup.equalsIgnoreCase("") &&
            !sup.equalsIgnoreCase("") && !ejVenta.equalsIgnoreCase("") && 
            !telefono.equalsIgnoreCase("") &&
            !tipoGestion.equalsIgnoreCase("") &&
            !grupoGestion.equalsIgnoreCase("") &&
            !detalleGestion.equalsIgnoreCase("") &&
            !nomDetGest.equalsIgnoreCase("") &&
            !nomGrupGest.equalsIgnoreCase("") &&
            !nomTipGest.equalsIgnoreCase("") && !obs.equalsIgnoreCase("") &&
            !telfAd.equalsIgnoreCase("") && !dirAd.equalsIgnoreCase("") &&
            !emailAd.equalsIgnoreCase("")) {

            crearFb = true;
            Util.Log(crearFb + "GHB 2");

        } else {

            crearFb = false;
            Util.Log(crearFb + "GHB 3");

        }
        return crearFb;
    }
    
    public boolean validarCreacionFbTelfExtra(Row currRow) {

        Boolean crearFb = false;
        String nombreFb = "";
        String idCanal = "";
        String canal = "";
        String idJefCanal = "";
        String jefCanal = "";
        String idSup = "";
        String sup = "";
        String idEjVenta = "";
        String ejVenta = "";
        String tipoGestion = "";
        String grupoGestion = "";
        String detalleGestion = "";
        String nomDetGest = "";
        String nomGrupGest = "";
        String nomTipGest = "";
        String obs = "";
        String telfAd = "";
        String dirAd = "";
        String emailAd = "";


    /*    nombreFb = nvl(currRow, "nomFb", "");
        idCanal = nvl(currRow, "idCanal", "");
        canal = nvl(currRow, "canal", "");
        idJefCanal = nvl(currRow, "idJefCanal", "");
        jefCanal = nvl(currRow, "jefCanal", "");
        idSup = nvl(currRow, "idSup", "");
        sup = nvl(currRow, "sup", "");
        idEjVenta = nvl(currRow, "idEjVenta", "");
        ejVenta = nvl(currRow, "ejVenta", "");
        tipoGestion = nvl(currRow, "codTipGest", "");
        grupoGestion = nvl(currRow, "codGrupGest", "");
        detalleGestion = nvl(currRow, "codDetGest", "");
        nomDetGest = nvl(currRow, "detGest", "");
        nomGrupGest = nvl(currRow, "grupGest", "");
        nomTipGest = nvl(currRow, "tipoGest", "");
        obs = nvl(currRow, "observaciones", " ");
        telfAd = nvl(currRow, "telfAd", " ");
        dirAd = nvl(currRow, "direccionAd", " ");
        emailAd = nvl(currRow, "emailAd", " ");*/
        
        nombreFb = nvl(currRow, "nomFb", "");
        Util.Log(nombreFb + "1a");
        idCanal = nvl(currRow, "idCanal", "");
        Util.Log(idCanal + "1s");
        canal = nvl(currRow, "canal", "");
        Util.Log(canal + "1e");
        idJefCanal = nvl(currRow, "idJefCanal", "");
        Util.Log(idJefCanal + "13");
        jefCanal = nvl(currRow, "jefCanal", "");
        Util.Log(jefCanal + "14");
        idSup = nvl(currRow, "idSup", "");
        Util.Log(idSup + "15");
        sup = nvl(currRow, "sup", "");
        Util.Log(sup + "16");
        idEjVenta = nvl(currRow, "idEjVenta", "");
        Util.Log(idEjVenta + "17");
        ejVenta = nvl(currRow, "ejVenta", "");
        Util.Log(ejVenta + "1u");
        tipoGestion = nvl(currRow, "codTipGest", "");
        Util.Log(tipoGestion + "1j");
        grupoGestion = nvl(currRow, "codGrupGest", "");
        Util.Log(grupoGestion + "1m");
        detalleGestion = nvl(currRow, "codDetGest", "");
        Util.Log(detalleGestion + "1mg");
        nomDetGest = nvl(currRow, "detGest", "");
        Util.Log(nomDetGest + "1b");
        nomGrupGest = nvl(currRow, "grupGest", "");
        Util.Log(nomGrupGest + "1ggg");
        nomTipGest = nvl(currRow, "tipoGest", "");
        Util.Log(nomTipGest + "1gfff");
        obs = nvl(currRow, "observaciones", " ");
        Util.Log(obs + "1fg");
        telfAd = nvl(currRow, "telfAd", " ");
        Util.Log(telfAd + "1fg");
        dirAd = nvl(currRow, "direccionAd", " ");
        Util.Log(dirAd + "fg1");
        emailAd = nvl(currRow, "emailAd", " ");
        Util.Log(emailAd + "1ddd");

        if (!nombreFb.equalsIgnoreCase("") &&
            !idEjVenta.equalsIgnoreCase("") && !idCanal.equalsIgnoreCase("") &&
            !canal.equalsIgnoreCase("") && !idJefCanal.equalsIgnoreCase("") &&
            !jefCanal.equalsIgnoreCase("") && !idSup.equalsIgnoreCase("") &&
            !sup.equalsIgnoreCase("") && !ejVenta.equalsIgnoreCase("") &&
            !tipoGestion.equalsIgnoreCase("") &&
            !grupoGestion.equalsIgnoreCase("") &&
            !detalleGestion.equalsIgnoreCase("") &&
            !nomDetGest.equalsIgnoreCase("") &&
            !nomGrupGest.equalsIgnoreCase("") &&
            !nomTipGest.equalsIgnoreCase("") && !obs.equalsIgnoreCase("") &&
            !telfAd.equalsIgnoreCase("") && !dirAd.equalsIgnoreCase("") &&
            !emailAd.equalsIgnoreCase("")) {

            crearFb = true;
            Util.Log(crearFb + "GHB 2");

        } else {

            crearFb = false;
            Util.Log(crearFb + "GHB 3");

        }
        return crearFb;
    }

    public String nomFeedback() {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String nomFb = "";


        try {
            String query =
                "select SBP_SLS_NOM_FB_PKG.nom_feedback NOM_FB from dual";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                nomFb = rs.getString("NOM_FB");

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(nomFb + "nomFb");
        return nomFb;
    }

    public List<Canal> listXcpg() {
        List<Canal> listCanal = new ArrayList<Canal>();


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idSup = "";
        String idCpg = "";
        String idCanal = "";
        String cant = "";
        String porcRep = "";
        String pausa = "";


        try {
            String query =
                "select id_sup ID_SUP, id_cpg ID_CPG, id_canal ID_CANAL, cantidad CANT, porc_rep PORC_REP, pausado PAUSA from SBP_SLS_DOSIF_X_CPG";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                Canal cnl = new Canal();

                while (rs.next()) {

                    idSup = rs.getString("ID_SUP");
                    idCpg = rs.getString("ID_CPG");
                    idCanal = rs.getString("ID_CANAL");
                    cant = rs.getString("CANT");
                    porcRep = rs.getString("PORC_REP");
                    pausa = rs.getString("PAUSA");

                    cnl.setIdSup(idSup);
                    cnl.setIdCpg(idCpg);
                    cnl.setCanalId(idCanal);
                    cnl.setCant(cant);
                    cnl.setPorcAsig(porcRep);
                    cnl.setPausa(pausa);
                    listCanal.add(cnl);
                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listCanal;
    }

    public void poblarTabla(ActionEvent actionEvent) {

        ServiceBiPublisher pantOpty = new ServiceBiPublisher();

        //------<INI>Obtención de data de tabla de configuración-----
        List<Canal> listIdSup = new ArrayList<Canal>();
        listIdSup = listIdSup();
        Util.Log("-------------------<1>-----------------");

        //------<INI>Obtención de ejecutivos de venta-----
        List<EjVenta> listEjVenta = new ArrayList<EjVenta>(); //operador
        List<EjVenta> listEjVentaAcum = new ArrayList<EjVenta>(); //acumulador
        for (Canal listIdSupe : listIdSup) {
            listEjVenta =
                    pantOpty.obtenerEjVenta(listIdSupe.getIdSup()); //obtenemos lista del SALES CLOUD
            listEjVentaAcum.addAll(listEjVenta);
        } //acumulamos las listas
        Util.Log("-------------------<2>-----------------");

        //------<INI>Obtención de oportunidades-----
        List<LeadRandom> listRdmOp = new ArrayList<LeadRandom>(); //operador
        List<LeadRandom> listRdmAcum =
            new ArrayList<LeadRandom>(); //acumulador

        for (Canal tblConfig : listIdSup) {
            for (EjVenta ejeVenta : listEjVentaAcum) {
                if (tblConfig.getIdSup().equalsIgnoreCase(ejeVenta.getIdSup()) &&
                    tblConfig.getCanalId().equalsIgnoreCase(ejeVenta.getIdCanal())) {

                    Double leadViejo1;
                    Double leadNuevo1;
                    leadViejo1 =
                            Double.parseDouble(tblConfig.getCant()) * (Double.parseDouble(tblConfig.getPorcAsig())) /
                            100;
                    leadNuevo1 =
                            Double.parseDouble(tblConfig.getCant()) - leadViejo1;
                    //--TRACE--
                    Util.Log(ejeVenta.getIdEjVenta() + "|" +
                             leadViejo1.toString() + "|" +
                             leadNuevo1.toString() + "|" +
                             tblConfig.getIdCpg() + "|" +
                             tblConfig.getCanalId());

                    /* listRdmOp = pantOpty.obtenerLeadRandom(ejeVenta.getIdEjVenta(),
                                                             leadViejo1.toString(),
                                                             leadNuevo1.toString(),
                                                             tblConfig.getIdCpg(),
                                                             tblConfig.getCanalId());

                      Util.Log(listRdmOp.size() + "");
                      listRdmAcum.addAll(listRdmOp); */
                }
            }
        } //acumulamos las listas
        Util.Log("-------------------<3>-----------------");

        //------<INI>Inserción de data----------
        for (LeadRandom ListRdmAcumF : listRdmAcum) {
            insertDataDosificador(ListRdmAcumF.getLeadId(),
                                  ListRdmAcumF.getLeadNumber(), "0");
        }
        ;
        Util.Log("-------------------<4>-----------------");


        for (Canal listIdSupe : listIdSup) {

            listIdSupe.getCanalId();
            listIdSupe.getIdSup();
            listIdSupe.getIdCpg();
            listIdSupe.getPorcAsig();
            listIdSupe.getCant();
            //-------------------------------TRACE-------------------------------
            Util.Log("-------------------<INI TABLA CONFIG>-----------------");
            Util.Log("1:" + listIdSupe.getCanalId());
            Util.Log("2:" + listIdSupe.getIdSup());
            Util.Log("3:" + listIdSupe.getIdCpg());
            Util.Log("4:" + listIdSupe.getPorcAsig());
            Util.Log("5:" + listIdSupe.getCant());
            Util.Log("-------------------<FIN TABLA CONFIG>-----------------");

        }

        Util.Log("-------------------<INI EJECUTIVOS>-----------------" +
                 listEjVentaAcum.size());
        for (EjVenta ListEjVentaAcum : listEjVentaAcum) {
            //-------------------------------TRACE-------------------------------
            Util.Log("1:" + ListEjVentaAcum.getEjVenta());
            Util.Log("2:" + ListEjVentaAcum.getIdEjVenta());
        }
        Util.Log("-------------------<FIN EJECUTIVOS>-----------------");

        Util.Log("-------------------<INI OPPT EJE>-----------------" +
                 listRdmAcum.size());
        for (LeadRandom ListRdmAcum : listRdmAcum) {
            //-------------------------------TRACE-------------------------------
            Util.Log("1:" + ListRdmAcum.getLeadId());
            Util.Log("2:" + ListRdmAcum.getLeadNumber());
        }
        Util.Log("-------------------<FIN OPPT EJE>-----------------");

    }

    public String LeadRandom1() {

        ADFContext ctx = ADFContext.getCurrent();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idLead = "";

        String idEjVenta =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        try {
            String query =
                "SELECT id_opty OPTY FROM SBP_SLS_OPP_DOSIF_X_USER where estado = '0' and id_ej_venta = " +
                idEjVenta + " and rownum = 1";

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                idLead = rs.getString("OPTY");
                Util.Log("opt" + idLead);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(idLead + "OPTY");
        return idLead;
    }

    public List<Canal> listIdSup() {

        List<Canal> listIdSup = new ArrayList<Canal>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String idSup = "";
        String idCpg = "";
        String idCanal = "";
        String cant = "";
        String porcRep = "";


        try {
            String query =
                "select id_sup ID_SUP, id_cpg ID_CPG, id_canal ID_CANAL, cantidad CANT, porc_rep PORC_REP, pausado PAUSA from SBP_SLS_DOSIF_X_CPG ";

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            while (rs.next()) {
                Canal sup = new Canal();
                idSup = rs.getString("ID_SUP");
                idCpg = rs.getString("ID_CPG");
                idCanal = rs.getString("ID_CANAL");
                cant = rs.getString("CANT");
                porcRep = rs.getString("PORC_REP");


                sup.setIdSup(idSup);
                sup.setIdCpg(idCpg);
                sup.setCanalId(idCanal);
                sup.setCant(cant);
                sup.setPorcAsig(porcRep);
                listIdSup.add(sup);

            }

            //}
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }


        return listIdSup;
    }


    public String obtenerCantidad() {

        ADFContext ctx = ADFContext.getCurrent();

        String idSup =
            ctx.getSessionScope().get("IdSup") == null ? "" : ctx.getSessionScope().get("IdSup").toString();
        String idCpg =
            ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String cant = "";


        try {
            String query =
                "select cantidad CANT from SBP_SLS_DOSIF_X_CPG"; // where id_sup = " + idSup + " and id_cpg = " +  idCpg;

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                cant = rs.getString("CANT");
                Util.Log("cant" + cant);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(cant + "OPTY");
        return cant;
    }


    public String obtenerPorcRept() {

        ADFContext ctx = ADFContext.getCurrent();

        String idSup =
            ctx.getSessionScope().get("IdSup") == null ? "" : ctx.getSessionScope().get("IdSup").toString();
        String idCpg =
            ctx.getSessionScope().get("idCpg") == null ? "" : ctx.getSessionScope().get("idCpg").toString();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String porcRep = "";


        try {
            String query = "select porc_rep PORC_REP from SBP_SLS_DOSIF_X_CPG";

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                porcRep = rs.getString("PORC_REP");
                Util.Log("cant" + porcRep);

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        Util.Log(porcRep + "OPTY");
        return porcRep;
    }

    public void actualizaEstadoOpty(String idLead) {


        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;


        try {

            Util.Log("entra");

            String query =
                "update SBP_SLS_OPP_DOSIF_X_USER set estado = '1' where id_opty = " +
                idLead;

            Util.Log(query + "query");
            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);
            st.execute();


        } catch (SQLException e) {
            Util.Log(e + "error");
        } catch (Exception ea) {
            Util.Log(ea + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

    }

    public void insertDataDosificador(String idLead, String IdEjVenta,
                                      String Estatus) {
        PreparedStatement prepareStatement = null;
        sbp.utils.Connection connection = new sbp.utils.Connection();
        PreparedStatement st = null;
        Connection conn = null;

        String query =
            "BEGIN SBP_SLS_OPP_DOSIF_X_USER_PKG.pr_insert_opty(?,?,?);END;";


        try {

            conn = connection.getConnection();
            prepareStatement = conn.prepareStatement(query);
            prepareStatement.setString(1, idLead);
            prepareStatement.setString(2, IdEjVenta);
            prepareStatement.setString(3, Estatus);

            prepareStatement.execute();
            prepareStatement.close();
            conn.close();

        } catch (SQLException e) {
            Util.Log(e + "excepcion" + e.getMessage() +
                               "IT DOES NOT WORK");
        } catch (NullPointerException e) {
            System.out.print(e + "excepcion" + e.getMessage() +
                             "NullPointerException caught");
        } catch (Exception e) {
            Util.Log(e + "excepcion" + e.getMessage() + "Exception");
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);

        }

    }


    public void obtenerCanal(String userName) {

        ADFContext ctx = ADFContext.getCurrent();

        ServiceBiPublisher canal = new ServiceBiPublisher();

        String jefeCanal = "";
        String idJefeCanal = "";
        String canalId = "";
        String supId = "";
        String nomCanal = "";
        String sup = "";
        String idEjecVenta = "";
        String ejVenta = "";


        List<Canal> lista2 = new ArrayList<Canal>();
        lista2 = canal.obtenerCanal(userName);

        for (Canal canalList : lista2) {

            jefeCanal = canalList.getJefCanal();
            idJefeCanal = canalList.getIdJefCanal();
            canalId = canalList.getCanalId();
            supId = canalList.getIdSup();
            nomCanal = canalList.getCanal();
            sup = canalList.getSup();
            idEjecVenta = canalList.getIdEjVenta();
            ejVenta = canalList.getEjVenta();

        }

        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("SupId", supId);
        ctx.getSessionScope().put("Canal", nomCanal);
        ctx.getSessionScope().put("JefeCanal", jefeCanal);
        ctx.getSessionScope().put("IdJefeCanal", idJefeCanal);
        ctx.getSessionScope().put("Super", sup);
        ctx.getSessionScope().put("EjecVenta", ejVenta);
        ctx.getSessionScope().put("IdEjecVenta", idEjecVenta);
    }

    public void onBeforePhase(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId().getOrdinal() ==
            Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
            onLoad();
        }
    }

    private boolean isPostback() {
        return Boolean.TRUE.equals(resolveExpression("#{adfFacesContext.postback}"));
    }

    private Object resolveExpression(String expression) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        return valueExp.getValue(elContext);
    }

    public void onLoad() {

        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = ctx.getSessionScope().get("LEADID") == null ? "" : ctx.getSessionScope().get("LEADID").toString();

        mostrarLead(idLead);
    }

    private void mostrarLead(String idLead) {

        ADFContext ctx = ADFContext.getCurrent();


        try {
            List<CpgLeadContAccProdFb> opp =
                new DosificacionEjecutivoBO().obtenerLeadCont(idLead);

            Util.Log("[mostrarLead] Lead : " +
                     opp.get(0).getIdLead());
            
                ctx.getSessionScope().put("iDlEaD", opp.get(0).getIdLead().toString());

                DCBindingContainer binding = this.getDCBindingsContainer();
                DCIteratorBinding dcItteratorBindings =
                    binding.findIteratorBinding("CpgOptyProdContAccFb1Iterator");
                Row rw = null;

                rw = dcItteratorBindings.getNavigatableRowIterator().createRow();
                dcItteratorBindings.getNavigatableRowIterator().insertRow(rw);
                rw.setNewRowState(Row.STATUS_INITIALIZED);

                String idCpg = "";
                String nomCpg = "";
                String genero = opp.get(0).getGenero();
                String sr = "";
                String monedaOpty = opp.get(0).getMonLead();
                String mon = "";
            
            if(genero.equalsIgnoreCase("MASCULINO")){
                sr = "Sr.";
            } else if(genero.equalsIgnoreCase("FEMENINO")) {
                sr = "Sra./Srta.";
            } else {
                sr = "";
            }
            
            if(monedaOpty.equalsIgnoreCase("PEN")){
                mon = "S/ ";
            } else if(monedaOpty.equalsIgnoreCase("US")){
                mon = "$ ";
            } else {
                mon = "";
            }
            
                Util.Log(sr + "SR/SRA");
                Util.Log("[mostrarOportunidad] Registros opp obtenidos : " +
                         opp.size());
                
                
                txtEmail.setValue(opp.get(0).getEmail());
                txtTipDoc.setValue(opp.get(0).getTipoDocumento());
                txtNumDoc.setValue(opp.get(0).getNumeroDni());
                txtNombre.setValue(sr + " " + opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno());
                txtContFb.setValue(opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno());
                txtFecNac.setValue(opp.get(0).getFechaNacimiento());
                rw.setAttribute("SegundoNombre", opp.get(0).getSegundoNombre());
                rw.setAttribute("ApellidoPaterno", opp.get(0).getApellidoPaterno());
                rw.setAttribute("ApellidoMaterno", opp.get(0).getApellidoMaterno());
                txtEdad.setValue(opp.get(0).getEdad() + " años");
                txtDir.setValue(opp.get(0).getDireccion());
                txtProv.setValue(opp.get(0).getProvContacto());
                txtDpto.setValue(opp.get(0).getDeptCont());
                txtDistrito.setValue(opp.get(0).getDistContacto());
                txtTelf01.setText(Util.NumeroFormateado(opp.get(0).getTelefono01()));
                txtTelf02.setText(Util.NumeroFormateado(opp.get(0).getTelefono02()));
                txtTelf03.setText(Util.NumeroFormateado(opp.get(0).getTelefono03()));
                txtCel01.setText(Util.NumeroFormateado(opp.get(0).getCelular01()));
                txtCel02.setText(Util.NumeroFormateado(opp.get(0).getCelular02()));
                txtCel03.setText(Util.NumeroFormateado(opp.get(0).getCelular03()));
                rw.setAttribute("PaisContacto", opp.get(0).getPaisContacto());
                rw.setAttribute("DeptCont", opp.get(0).getDeptCont());
                rw.setAttribute("DistContacto", opp.get(0).getDistContacto());
                rw.setAttribute("ProvContacto", opp.get(0).getProvContacto());
                rw.setAttribute("Direccion", opp.get(0).getDireccion());
                rw.setAttribute("IdLead", opp.get(0).getIdLead());
               // rw.setAttribute("NomLead", opp.get(0).getNomLead());
               // rw.setAttribute("MonOpty", opp.get(0).getMonLead());
                txtProd.setValue(opp.get(0).getNomProd());
                rw.setAttribute("Tceamax", opp.get(0).getTceaMax());
                rw.setAttribute("Tceamin", opp.get(0).getTceaMin());
                rw.setAttribute("Teamax", opp.get(0).getTeaMax());
                rw.setAttribute("Teamin", opp.get(0).getTeaMin());
                txtMontoMaximo.setValue(mon + Util.formatearMonto(opp.get(0).getMontoMaximo()));
                txtMontMax.setValue(opp.get(0).getMontoMaximo());
                txtMontoMinimo.setValue(mon + Util.formatearMonto(opp.get(0).getMontoMinimo()));
                txtMontMin.setValue(opp.get(0).getMontoMinimo());
                txtPlazoMaximo.setValue(opp.get(0).getPlazoMaximo());
                txtPlazoMinimo.setValue(opp.get(0).getPlazoMinimo());
                txtCuota.setValue(opp.get(0).getCuota());
                txtBancoPp.setValue(opp.get(0).getBanco1NombreRccPP());
                txtBancoTc.setValue(opp.get(0).getBanco1NombreRccTC());
                txtDeudaPp.setValue(opp.get(0).getBanco1DeudaRccPP());
                txtDeudaTc.setValue(opp.get(0).getBanco1DEURccTC());
                txtLinTc.setValue(opp.get(0).getBanco1LINRccTC());
                txtSbs.setValue(opp.get(0).getSbsBaFiCaEDPEDPRccPP());
                rw.setAttribute("OrganizationName",
                                opp.get(0).getOrganizationName());
                rw.setAttribute("Ruc", opp.get(0).getRuc());
                rw.setAttribute("PaisEmp", opp.get(0).getPaisEmp());
                rw.setAttribute("DeptEmp", opp.get(0).getDeptEmp());
                rw.setAttribute("ProvEmp", opp.get(0).getProvEmp());
                rw.setAttribute("DirEmp", opp.get(0).getDirEmp());
                rw.setAttribute("DistEmp", opp.get(0).getDistEmp());
                idCpg = opp.get(0).getIdCpg().toString();
                nomCpg = opp.get(0).getNomCpg();
                txtMonto.setValue(opp.get(0).getMonto());
                txtMontoFb.setValue(opp.get(0).getMonto());

                ctx.getSessionScope().put("CodCpg", idCpg);
                ctx.getSessionScope().put("NomCpg", nomCpg);
            
                ctx.getSessionScope().put("montoLead", opp.get(0).getMonto());

                Util.Log("[mostrarLead] idCpg : " + idCpg);

                // Buscar feedback
                this.buscarFeedback(idLead);

        } catch (Exception ex) {
            Util.Log("[mostrarOportunidad] : " + ex.getMessage());
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getPopupConfirm().show(hints);

        }
    }

    public void aceptarLead(ActionEvent actionEvent) {

        ADFContext ctx = ADFContext.getCurrent();

        String IdEjecutivo =
            ctx.getSessionScope().get("IdEjecVenta") == null ? "" :
            ctx.getSessionScope().get("IdEjecVenta").toString();

        DosificacionEjecutivoBO.ordenarAntiguos(IdEjecutivo);
        this.mostrarLead(IdEjecutivo);
    }

    public void rechazarLead(ActionEvent actionEvent) {
        getPopupConfirm().hide();
    }

    public void setTxtTipGestTlfExt(RichSelectOneChoice txtTipGestTlfExt) {
        this.txtTipGestTlfExt = txtTipGestTlfExt;
    }

    public RichSelectOneChoice getTxtTipGestTlfExt() {
        return txtTipGestTlfExt;
    }

    public void setTxtGrupGestTlfExt(RichSelectOneChoice txtGrupGestTlfExt) {
        this.txtGrupGestTlfExt = txtGrupGestTlfExt;
    }

    public RichSelectOneChoice getTxtGrupGestTlfExt() {
        return txtGrupGestTlfExt;
    }

    public void setTxtDetGestTlfExt(RichSelectOneChoice txtDetGestTlfExt) {
        this.txtDetGestTlfExt = txtDetGestTlfExt;
    }

    public RichSelectOneChoice getTxtDetGestTlfExt() {
        return txtDetGestTlfExt;
    }

    public void setPopupMensajeError(RichPopup popupMensajeError) {
        this.popupMensajeError = popupMensajeError;
    }

    public RichPopup getPopupMensajeError() {
        return popupMensajeError;
    }

    public void setBtnTelfExtra(RichCommandButton btnTelfExtra) {
        this.btnTelfExtra = btnTelfExtra;
    }

    public RichCommandButton getBtnTelfExtra() {
        return btnTelfExtra;
    }

    public void setTxtNombre(RichOutputLabel txtNombre) {
        this.txtNombre = txtNombre;
    }

    public RichOutputLabel getTxtNombre() {
        return txtNombre;
    }

    public void setTxtEdad(RichOutputLabel txtEdad) {
        this.txtEdad = txtEdad;
    }

    public RichOutputLabel getTxtEdad() {
        return txtEdad;
    }

    public void setTxtSexo(RichOutputLabel txtSexo) {
        this.txtSexo = txtSexo;
    }

    public RichOutputLabel getTxtSexo() {
        return txtSexo;
    }

    public void setTxtFecNac(RichOutputLabel txtFecNac) {
        this.txtFecNac = txtFecNac;
    }

    public RichOutputLabel getTxtFecNac() {
        return txtFecNac;
    }

    public void setTxtTipDoc(RichOutputLabel txtTipDoc) {
        this.txtTipDoc = txtTipDoc;
    }

    public RichOutputLabel getTxtTipDoc() {
        return txtTipDoc;
    }

    public void setTxtNumDoc(RichOutputLabel txtNumDoc) {
        this.txtNumDoc = txtNumDoc;
    }

    public RichOutputLabel getTxtNumDoc() {
        return txtNumDoc;
    }

    public void setTxtEmail(RichOutputLabel txtEmail) {
        this.txtEmail = txtEmail;
    }

    public RichOutputLabel getTxtEmail() {
        return txtEmail;
    }

    public void setTxtProd(RichOutputLabel txtProd) {
        this.txtProd = txtProd;
    }

    public RichOutputLabel getTxtProd() {
        return txtProd;
    }

    public void setTblFbContacto(RichTable tblFbContacto) {
        this.tblFbContacto = tblFbContacto;
    }

    public RichTable getTblFbContacto() {
        return tblFbContacto;
    }

    public void crrr(ActionEvent actionEvent) {
        ADFContext ctx = ADFContext.getCurrent();
        
        String idLead = ctx.getSessionScope().get("OPPID") == null ? "" : ctx.getSessionScope().get("OPPID").toString();


        mostrarLead(idLead);
    }

    public void setTxtMontoMaximo(RichOutputLabel txtMontoMaximo) {
        this.txtMontoMaximo = txtMontoMaximo;
    }

    public RichOutputLabel getTxtMontoMaximo() {
        return txtMontoMaximo;
    }

    public void setTxtMontoMinimo(RichOutputLabel txtMontoMinimo) {
        this.txtMontoMinimo = txtMontoMinimo;
    }

    public RichOutputLabel getTxtMontoMinimo() {
        return txtMontoMinimo;
    }

    public void setTxtPlazoMinimo(RichOutputLabel txtPlazoMinimo) {
        this.txtPlazoMinimo = txtPlazoMinimo;
    }

    public RichOutputLabel getTxtPlazoMinimo() {
        return txtPlazoMinimo;
    }

    public void setTxtPlazoMaximo(RichOutputLabel txtPlazoMaximo) {
        this.txtPlazoMaximo = txtPlazoMaximo;
    }

    public RichOutputLabel getTxtPlazoMaximo() {
        return txtPlazoMaximo;
    }

    public void setTxtCuota(RichOutputLabel txtCuota) {
        this.txtCuota = txtCuota;
    }

    public RichOutputLabel getTxtCuota() {
        return txtCuota;
    }

    public void setTxtTelf01(RichCommandLink txtTelf01) {
        this.txtTelf01 = txtTelf01;
    }

    public RichCommandLink getTxtTelf01() {
        return txtTelf01;
    }

    public void setTxtCel01(RichCommandLink txtCel01) {
        this.txtCel01 = txtCel01;
    }

    public RichCommandLink getTxtCel01() {
        return txtCel01;
    }

    public void setTxtTelf02(RichCommandLink txtTelf02) {
        this.txtTelf02 = txtTelf02;
    }

    public RichCommandLink getTxtTelf02() {
        return txtTelf02;
    }

    public void setTxtCel02(RichCommandLink txtCel02) {
        this.txtCel02 = txtCel02;
    }

    public RichCommandLink getTxtCel02() {
        return txtCel02;
    }

    public void setTxtTelf03(RichCommandLink txtTelf03) {
        this.txtTelf03 = txtTelf03;
    }

    public RichCommandLink getTxtTelf03() {
        return txtTelf03;
    }

    public void setTxtCel03(RichCommandLink txtCel03) {
        this.txtCel03 = txtCel03;
    }

    public RichCommandLink getTxtCel03() {
        return txtCel03;
    }

    public void setTxtDeudaPp(RichOutputLabel txtDeudaPp) {
        this.txtDeudaPp = txtDeudaPp;
    }

    public RichOutputLabel getTxtDeudaPp() {
        return txtDeudaPp;
    }

    public void setTxtDeudaTc(RichOutputLabel txtDeudaTc) {
        this.txtDeudaTc = txtDeudaTc;
    }

    public RichOutputLabel getTxtDeudaTc() {
        return txtDeudaTc;
    }

    public void setTxtLinTc(RichOutputLabel txtLinTc) {
        this.txtLinTc = txtLinTc;
    }

    public RichOutputLabel getTxtLinTc() {
        return txtLinTc;
    }

    public void setTxtBancoPp(RichOutputLabel txtBancoPp) {
        this.txtBancoPp = txtBancoPp;
    }

    public RichOutputLabel getTxtBancoPp() {
        return txtBancoPp;
    }

    public void setTxtSbs(RichOutputLabel txtSbs) {
        this.txtSbs = txtSbs;
    }

    public RichOutputLabel getTxtSbs() {
        return txtSbs;
    }

    public void setTxtBancoTc(RichOutputLabel txtBancoTc) {
        this.txtBancoTc = txtBancoTc;
    }

    public RichOutputLabel getTxtBancoTc() {
        return txtBancoTc;
    }

    public void setTxtContFb(RichInputText txtContFb) {
        this.txtContFb = txtContFb;
    }

    public RichInputText getTxtContFb() {
        return txtContFb;
    }

    public void setTxtMontMin(RichInputText txtMontMin) {
        this.txtMontMin = txtMontMin;
    }

    public RichInputText getTxtMontMin() {
        return txtMontMin;
    }

    public void setTxtMontMax(RichInputText txtMontMax) {
        this.txtMontMax = txtMontMax;
    }

    public RichInputText getTxtMontMax() {
        return txtMontMax;
    }

    public void setPopupMontoMin(RichPopup popupMontoMin) {
        this.popupMontoMin = popupMontoMin;
    }

    public RichPopup getPopupMontoMin() {
        return popupMontoMin;
    }

    public void setPopupMontMaximo(RichPopup popupMontMaximo) {
        this.popupMontMaximo = popupMontMaximo;
    }

    public RichPopup getPopupMontMaximo() {
        return popupMontMaximo;
    }

    public void setTxtGrupoGestion(RichSelectOneChoice txtGrupoGestion) {
        this.txtGrupoGestion = txtGrupoGestion;
    }

    public RichSelectOneChoice getTxtGrupoGestion() {
        return txtGrupoGestion;
    }

    public void setTxtGrupGestTlfExtra(RichInputText txtGrupGestTlfExtra) {
        this.txtGrupGestTlfExtra = txtGrupGestTlfExtra;
    }

    public RichInputText getTxtGrupGestTlfExtra() {
        return txtGrupGestTlfExtra;
    }

    public void setTxtMensajeValMonto(RichOutputLabel txtMensajeValMonto) {
        this.txtMensajeValMonto = txtMensajeValMonto;
    }

    public RichOutputLabel getTxtMensajeValMonto() {
        return txtMensajeValMonto;
    }

    public void setPopupValidarCampos(RichPopup popupValidarCampos) {
        this.popupValidarCampos = popupValidarCampos;
    }

    public RichPopup getPopupValidarCampos() {
        return popupValidarCampos;
    }

    public void setTxtProv(RichOutputLabel txtProv) {
        this.txtProv = txtProv;
    }

    public RichOutputLabel getTxtProv() {
        return txtProv;
    }

    public void setTxtDpto(RichOutputLabel txtDpto) {
        this.txtDpto = txtDpto;
    }

    public RichOutputLabel getTxtDpto() {
        return txtDpto;
    }

    public void setTxtDistrito(RichOutputLabel txtDistrito) {
        this.txtDistrito = txtDistrito;
    }

    public RichOutputLabel getTxtDistrito() {
        return txtDistrito;
    }

    public void setTxtDir(RichOutputLabel txtDir) {
        this.txtDir = txtDir;
    }

    public RichOutputLabel getTxtDir() {
        return txtDir;
    }

    public void setTxtMontoFb(RichInputText txtMontoFb) {
        this.txtMontoFb = txtMontoFb;
    }

    public RichInputText getTxtMontoFb() {
        return txtMontoFb;
    }

    public void setTxtMontoFbExtra(RichInputText txtMontoFbExtra) {
        this.txtMontoFbExtra = txtMontoFbExtra;
    }

    public RichInputText getTxtMontoFbExtra() {
        return txtMontoFbExtra;
    }
}

