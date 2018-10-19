package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Campania;

import dao.Canal;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_PRINC_JEF_CAN {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtCpgId;
    private RichInputText txtUserName;
    private RichInputText txtCodCpg;
    private RichInputText txtNomCpg;
    private RichInputText txtFecIni;
    private RichInputText txtFecFi;
    private RichInputText txtFecFin;
    private RichInputText txtAsig;
    private RichInputText txtProd;
    private RichInputText txtCantOfTot;
    private RichInputText txtEstado;
    private RichInputText txtCantOpp;
    private RichInputText txtPeriodo;
    private RichTable tblJefxSup;
    private RichInputText txtCantLead;

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


    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }

    public void setTxtUserName(RichInputText txtUserName) {
        this.txtUserName = txtUserName;
    }

    public RichInputText getTxtUserName() {
        return txtUserName;
    }
    
    public void setTxtCodCpg(RichInputText txtCodCpg) {
        this.txtCodCpg = txtCodCpg;
    }

    public RichInputText getTxtCodCpg() {
        return txtCodCpg;
    }

    public void setTxtNomCpg(RichInputText txtNomCpg) {
        this.txtNomCpg = txtNomCpg;
    }

    public RichInputText getTxtNomCpg() {
        return txtNomCpg;
    }

    public void setTxtFecIni(RichInputText txtFecIni) {
        this.txtFecIni = txtFecIni;
    }

    public RichInputText getTxtFecIni() {
        return txtFecIni;
    }

    public void setTxtFecFi(RichInputText txtFecFi) {
        this.txtFecFi = txtFecFi;
    }

    public RichInputText getTxtFecFi() {
        return txtFecFi;
    }

    public void setTxtFecFin(RichInputText txtFecFin) {
        this.txtFecFin = txtFecFin;
    }

    public RichInputText getTxtFecFin() {
        return txtFecFin;
    }

    public void setTxtAsig(RichInputText txtAsig) {
        this.txtAsig = txtAsig;
    }

    public RichInputText getTxtAsig() {
        return txtAsig;
    }

    public void setTxtProd(RichInputText txtProd) {
        this.txtProd = txtProd;
    }

    public RichInputText getTxtProd() {
        return txtProd;
    }

    public void setTxtCantOfTot(RichInputText txtCantOfTot) {
        this.txtCantOfTot = txtCantOfTot;
    }

    public RichInputText getTxtCantOfTot() {
        return txtCantOfTot;
    }

    public void setTxtEstado(RichInputText txtEstado) {
        this.txtEstado = txtEstado;
    }

    public RichInputText getTxtEstado() {
        return txtEstado;
    }

    public void setTxtCantOpp(RichInputText txtCantOpp) {
        this.txtCantOpp = txtCantOpp;
    }

    public RichInputText getTxtCantOpp() {
        return txtCantOpp;
    }

    public void setTxtPeriodo(RichInputText txtPeriodo) {
        this.txtPeriodo = txtPeriodo;
    }

    public RichInputText getTxtPeriodo() {
        return txtPeriodo;
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
        
        //obtener parámetro
        txtUserName.setValue((String) resolveExpression("#{viewScope.userId2}"));    
        txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));      
        
        String nomUser = txtUserName.getValue().toString();
        String cpgId = txtCpgId.getValue().toString();
        
        Util.Log(txtUserName.getValue().toString() + "NOM USER");
        
        Util.Log(txtCpgId.getValue().toString() + "CPG");
        
        infCpg(txtCpgId.getValue().toString(), txtUserName.getValue().toString());
        //listarDatosCpg(txtCpgId.getValue().toString(), txtUserName.getValue().toString());
        
        ctx.getSessionScope().put("nomUser", nomUser);
        ctx.getSessionScope().put("userLog", nomUser);
        
        
        ctx.getSessionScope().put("CampanaId", cpgId);
        
    }
    
    
    public void buscarSupervisores(String jefCanal, String cpgId){
        
        ADFContext ctx = ADFContext.getCurrent();
        
     /*   String cpgId = ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();
        
        String jefCanal =
            ctx.getSessionScope().get("nomUser") == null ? "" : ctx.getSessionScope().get("nomUser").toString();
*/
        Row rw = null;

        ServiceBiPublisher sup = new ServiceBiPublisher();

        List<Canal> listSup = new ArrayList<Canal>();

        listSup = sup.obtenerSupXJefCanLead(jefCanal, cpgId);
        
        Util.Log(listSup.size() + "tamaño");


        CollectionModel _tablaSup = (CollectionModel)tblJefxSup.getValue();
        JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();

        int b = 0;
        b = itSup.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itSup.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (Canal listSuper : listSup) {

            rw = itSup.getNavigatableRowIterator().createRow();
            itSup.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("IdJefCanal", listSuper.getIdJefCanal());
            rw.setAttribute("JefCanal", listSuper.getJefCanal());
            rw.setAttribute("IdSup", listSuper.getIdSup());
            rw.setAttribute("NomSup", listSuper.getSup());
            rw.setAttribute("Cantidad", Integer.parseInt(listSuper.getCant()));
            //rw.setAttribute("Cantidad", Util.formatearMonto(listSuper.getCant()));
            Util.Log(listSuper.getCant() + "CANT");
            
        }
    }
    
    
    public void listarDatosCpg(String cpgId, String userName) {

        ADFContext ctx = ADFContext.getCurrent();
     
     //   double porc = 0.0;
     
        String canalId = "";
        String campaignId = "";
        String userId = "";

        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpgJc2(cpgId, userName);

        for (Campania listCamp : listCpg) {
           
            txtCodCpg.setValue(listCamp.getCpgId());
            campaignId = listCamp.getCpgId();
            txtNomCpg.setValue(listCamp.getCpgNom());
            txtProd.setValue(listCamp.getProd());
            txtFecIni.setValue(listCamp.getFecIni());
            txtFecFin.setValue(listCamp.getFecFin());
            txtPeriodo.setValue(listCamp.getPeriodoCpg());
            txtEstado.setValue(listCamp.getActivo());
            
            Util.Log(listCamp.getActivo() + "ESTADO");
                                                                         
            canalId = listCamp.getCanalId();
            userId = listCamp.getUserId();
        }
        
        ctx.getSessionScope().put("CaNaLiD", canalId);
        ctx.getSessionScope().put("CampaignId", campaignId);
        ctx.getSessionScope().put("uSeRiD", userId);
        
    }

    public void procesar(ActionEvent actionEvent) {
        infCpg(txtCpgId.getValue().toString(), txtUserName.getValue().toString());
                

    }
    
    public void infCpg(String cpgId, String userName){


        double porc = 0.0;

        
        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> listCpg = new ArrayList<Campania>();

        listCpg = cpg.obtenerCpgJc2(cpgId, userName);
        
        if(listCpg.size() != 0){

        for (Campania listCamp : listCpg) {

            porc = Math.floor((Double.parseDouble(listCamp.getCantLead())/Double.parseDouble(listCamp.getCantOf())) * 100.0);
            Util.Log(porc + "PORCENTAJE");
            String porcAsignado = String.valueOf(porc);
            
            txtCodCpg.setValue(listCamp.getCpgId());
            txtNomCpg.setValue(listCamp.getCpgNom());
            txtAsig.setValue(porcAsignado + "%");
            txtProd.setValue(listCamp.getProd());
            txtFecIni.setValue(listCamp.getFecIni());
            txtFecFin.setValue(listCamp.getFecFin());
            txtCantOfTot.setValue(Util.formatearMonto(listCamp.getCantOf()));
            txtPeriodo.setValue(listCamp.getPeriodoCpg());
            txtCantLead.setValue(Util.formatearMonto(listCamp.getCantLead()));
            txtEstado.setValue(listCamp.getActivo());
            Util.Log(listCamp.getActivo() + "ESTADO");
        }
        
        buscarSupervisores(userName, cpgId);
                            
        FacesContext fctx = FacesContext.getCurrentInstance();
        fctx.addMessage("",
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos Procesados",""));   
        
        } else{
            
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay leads asignados a esta campaña",
                                             ""));

        }
        
    }

    public void setTblJefxSup(RichTable tblJefxSup) {
        this.tblJefxSup = tblJefxSup;
    }

    public RichTable getTblJefxSup() {
        return tblJefxSup;
    }

    public void setTxtCantLead(RichInputText txtCantLead) {
        this.txtCantLead = txtCantLead;
    }

    public RichInputText getTxtCantLead() {
        return txtCantLead;
    }
}
