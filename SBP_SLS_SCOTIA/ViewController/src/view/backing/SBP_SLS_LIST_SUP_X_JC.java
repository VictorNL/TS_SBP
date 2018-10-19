package view.backing;

import WsEjRep.ServiceBiPublisher;


import dao.Campania;
import dao.Canal;
import dao.LoginUser;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_LIST_SUP_X_JC {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichPanelSplitter ps1;
    private RichPanelGroupLayout pgl2;
    private RichOutputLabel ol1;
    private RichPanelGroupLayout pgl3;
    private RichInputText it1;
    private RichInputText txtIdJefCanal;
    private RichTable tblSup;
    private RichInputText txtCpgId;

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

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }
    
    public void setTxtIdJefCanal(RichInputText txtIdJefCanal) {
        this.txtIdJefCanal = txtIdJefCanal;
    }

    public RichInputText getTxtIdJefCanal() {
        return txtIdJefCanal;
    }
    
    public void setTblSup(RichTable tblSup) {
        this.tblSup = tblSup;
    }

    public RichTable getTblSup() {
        return tblSup;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    public String obtenerUser(String userName){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        ServiceBiPublisher user = new ServiceBiPublisher();
        
        String partyId = "";
        List<LoginUser> listNomUsu = new ArrayList<LoginUser>();
        listNomUsu = user.obtenerLoginUser(userName);
           
           for(LoginUser listaNomUsu : listNomUsu){
              partyId = listaNomUsu.getPartyId();
           }
           
           Util.Log(partyId + "PARTY ID");
        ctx.getSessionScope().put("uSeRiD", partyId);
        
        return partyId;
    }

    public void buscarSupervisores(String idJefCanal) {
        
        Util.Log("Inicio");
        
        try {
        
            ADFContext ctx = ADFContext.getCurrent();
            
            String cpgId = ctx.getSessionScope().get("CampanaId") == null ? "" : ctx.getSessionScope().get("CampanaId").toString();
            String cpgNom = ctx.getSessionScope().get("cpanaNom") == null ? "" : ctx.getSessionScope().get("cpanaNom").toString();
            
            Row rw = null;
    
            ServiceBiPublisher sup = new ServiceBiPublisher();

            List<Canal> listSup = new ArrayList<Canal>();
            
            List<Canal> listSupCanal = new ArrayList<Canal>();

            listSup = sup.obtenerSupXJefCanLead(idJefCanal, cpgId);
    
            CollectionModel _tablaSup = (CollectionModel)tblSup.getValue();
            JUCtrlHierBinding _adfTableSupBinding = (JUCtrlHierBinding)_tablaSup.getWrappedData(); //devuelve el table binding
            DCIteratorBinding itSup = _adfTableSupBinding.getDCIteratorBinding();
    
            for (int i = 0; i < itSup.getRowSetIterator().getRowCount(); i++) {
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
                
            }
            
            for (Canal listSuper : listSup) {
                
                Canal canKardex = new Canal();
                
                canKardex.setIdCpg(txtCpgId.getValue().toString());
                canKardex.setCanalId(listSuper.getCanalId());
                canKardex.setCanal(listSuper.getCanal());
                    
                canKardex.setJefCanal(listSuper.getJefCanal());
                canKardex.setIdJefCanal(listSuper.getIdJefCanal());
                Util.Log(listSuper.getSup() + "listSuper.getSup()");
                canKardex.setSup(listSuper.getSup());
                canKardex.setIdSup(listSuper.getIdSup());
                canKardex.setNomCpg(cpgNom);
                    
                canKardex.setIdEjVenta(listSuper.getIdEjVenta());
                canKardex.setEjVenta(listSuper.getEjVenta());
                canKardex.setCant(listSuper.getCant());
                listSupCanal.add(canKardex);
                
            }
            
            Util.Log("Fin");
            
            // Guardar en session la lista de supervisores
            ctx.getSessionScope().put("ListSupCan", listSup);
            ctx.getSessionScope().put("ListSupCanKardex", listSupCanal);
            ctx.getSessionScope().put("userLog", idJefCanal);
        }
        catch (Exception e) {
            Util.Log(e.getMessage());
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

        ctx.getSessionScope().put("CaNaLiD", canalId);
        ctx.getSessionScope().put("uSeRiD", idJefeCanal);
    }
    
    public String nomCpg(String idCpg) {

        String nomCpg = "";
        ServiceBiPublisher cpg = new ServiceBiPublisher();

        List<Campania> Nomcpg = new ArrayList<Campania>();

        Nomcpg = cpg.obtenerNomCpg(idCpg);

        for (Campania cpgList : Nomcpg) {
            nomCpg = cpgList.getCpgNom();
        }

        Util.Log(nomCpg + "NOM CPG");
        return nomCpg;
    }
    
    public void obtenerDatos(ActionEvent actionEvent) {
        
        Util.Log("Inicio");
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idJefCanal = "";
        String nomSup = "";
        String idSup = "";
        String cantidad = "";

                
        DCBindingContainer bindings = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings = bindings.findIteratorBinding("SbpSlsListSupXJcView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idJefCanal = rowSelected.getAttribute("IdJefCanal").toString();
        nomSup = rowSelected.getAttribute("NomSup").toString();
        idSup = rowSelected.getAttribute("IdSup").toString();
        cantidad = rowSelected.getAttribute("Cantidad").toString();
        
        ctx.getSessionScope().put("JefCanId", idJefCanal);
        ctx.getSessionScope().put("nomSUP", nomSup);
        ctx.getSessionScope().put("SUPerId", idSup);
        ctx.getSessionScope().put("CantSupLead", cantidad);
        
        Util.Log(idSup + "IDSUPER CAPTURADO");
        Util.Log(idJefCanal + "IDJEFCAN CAPTURADO");
        Util.Log(nomSup + "NOM SUPER CAPTURADO");
        
        Util.Log("Fin");
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
        ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
        return valueExp.getValue(elContext);
    }

    public void onLoad() {
        
        Util.Log("Inicio");
        
        try {
            ADFContext ctx = ADFContext.getCurrent();       
            
            //obtener parámetro
            txtIdJefCanal.setValue((String) resolveExpression("#{viewScope.userId2}"));      
            txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));
            
            String nomCpg = nomCpg(txtCpgId.getValue().toString());
            
            ctx.getSessionScope().put("cpanaNom", nomCpg);
            
            // Almacenar en session id campaña
            ctx.getSessionScope().put("CampanaId", this.txtCpgId.getValue().toString());
            
            //obtenerCanal(nomUser);
            buscarSupervisores(this.txtIdJefCanal.getValue().toString());
        } catch (Exception e) {
            Util.Log(e.getMessage());
        }
        
        Util.Log("Fin");
    }


    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }
}
