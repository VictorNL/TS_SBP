package view.backing;

import WsEjRep.ServiceBiPublisher;

import client.Campania;



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
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import oracle.apps.sales.custextn.views.common.CampanaSBPC;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_LIST_CAMP {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichOutputLabel ol1;
    private RichCommandButton cb1;
    private RichTable tblCampana;
    private RichInputText txtLoginUser;

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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }
    
    
    public void setTblCampana(RichTable tblCampana) {
        this.tblCampana = tblCampana;
    }

    public RichTable getTblCampana() {
        return tblCampana;
    }
    
    public void setTxtLoginUser(RichInputText txtLoginUser) {
        this.txtLoginUser = txtLoginUser;
    }

    public RichInputText getTxtLoginUser() {
        return txtLoginUser;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public void buscarCampania(ActionEvent actionEvent) {
        
        
        Row rw = null;

        Campania campana = new Campania();

        List<CampanaSBPC> listCampana = new ArrayList<CampanaSBPC>();

        listCampana = campana.buscarEntidad();


        CollectionModel _tablaCampania = (CollectionModel)tblCampana.getValue();
        JUCtrlHierBinding _adfTableCampaniaBinding = (JUCtrlHierBinding)_tablaCampania.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCampania = _adfTableCampaniaBinding.getDCIteratorBinding();


        int b = 0;
        b = itCampania.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itCampania.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (CampanaSBPC listCam : listCampana) {

            rw = itCampania.getNavigatableRowIterator().createRow();
            itCampania.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);

            rw.setAttribute("CodCampania", listCam.getId());
            rw.setAttribute("NomCampania", listCam.getRecordName());
            rw.setAttribute("FecIni", listCam.getFechaInicioC().getValue());
            rw.setAttribute("FecFin", listCam.getFechaFinC().getValue());
            rw.setAttribute("Asignado", listCam.getPorcentajeAsignadoC().getValue());
            rw.setAttribute("CodProd", listCam.getCodProdC().getValue());
            rw.setAttribute("ProductoBase", listCam.getProductoBaseC().getValue());
            rw.setAttribute("CantOfertas", listCam.getCantidadDeOfertasC().getValue()); 
            if(listCam.isActivoC() != null){
                if(listCam.isActivoC()){
                    rw.setAttribute("Estado", "Y");
                } else{
                    rw.setAttribute("Estado", "N");
                }
               
            } else{
                rw.setAttribute("Estado", "N");
           
            }
            
            Util.Log(listCam.getRecordName());
        }
        
     }

    public void obtenerDatos(ActionEvent actionEvent) {
        
        ADFContext ctx = ADFContext.getCurrent();
        
        String idCpg = "";
        
                
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcItteratorBindings =
            bindings.findIteratorBinding("EvoLpSctView1Iterator");
        
        ViewObject voTableData = dcItteratorBindings.getViewObject();
        
        Row rowSelected = voTableData.getCurrentRow();
        
        idCpg = rowSelected.getAttribute("CodCampania").toString();
        
        ctx.getSessionScope().put("idCpg", idCpg);
        
        Util.Log(idCpg + "IDCPG CAPTURADO");
    }
    
    public void obtenerCanal(String idUsuario){
        
        ADFContext ctx = ADFContext.getCurrent();
        
        ServiceBiPublisher canal = new ServiceBiPublisher();
        
        String canalId = "";
        String idSup = "";
        String partyNumber = "";
        String idEjVenta = "";
        
        
        
        List<Canal> lista2 = new ArrayList<Canal>();
        
        Util.Log(idUsuario + "ID Usuario");
        lista2 = canal.obtenerCanal(idUsuario);
        
        for (Canal canalList : lista2) {
            
            canalId = canalList.getCanalId();
            idSup = canalList.getIdSup();
            idEjVenta = canalList.getIdEjVenta();
            partyNumber = canalList.getPartyNumber();
         
        }
        
        ctx.getSessionScope().put("IdCan", canalId);
        ctx.getSessionScope().put("iDsUpEr", idSup);
        ctx.getSessionScope().put("partyNumber", partyNumber);
        ctx.getSessionScope().put("ejecutivoVenta", idEjVenta);
        
        Util.Log(canalId + "canalID");
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
        ctx.getSessionScope().put("PaRtYiD", partyId);
        
        return partyId;
    }
    
    
//-----------------METODO PARA EJECUTAR METODOS ANTES O DESPUES DE CARGAR LA PANTALLA(INI)--------//
    public void onBeforePhase(PhaseEvent phaseEvent) {
           if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
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
       
       public void onLoad(){
           
           ADFContext ctx = ADFContext.getCurrent();
           
           obtenerUser(txtLoginUser.getValue().toString());
           
           String partyId =
               ctx.getSessionScope().get("PaRtYiD") == null ? "" : ctx.getSessionScope().get("PaRtYiD").toString();

           
           obtenerCanal(partyId);
           
           
       }
    //-----------------METODO PARA EJECUTAR METODOS ANTES O DESPUES DE CARGAR LA PANTALLA(FIN)--------//

}

