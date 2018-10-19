package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.CpgLeadContAccProdFb;
import dao.CpgOppContAccProdFb;

import dao.Feedback;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;

import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelLabelAndMessage;
import oracle.adf.view.rich.component.rich.output.RichMessages;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_OPP_PAGE_STD {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichPanelBox pb2;
    private RichPanelBox pb3;
    private RichPanelBox pb4;
    private RichPanelBox pb5;
    private RichMessages m1;
    private RichPanelLabelAndMessage plam1;
    private RichOutputText ot1;
    private RichPanelLabelAndMessage plam2;
    private RichOutputText ot2;
    private RichInputText it1;
    private RichTable t1;

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

    public void setPb5(RichPanelBox pb5) {
        this.pb5 = pb5;
    }

    public RichPanelBox getPb5() {
        return pb5;
    }

    public void setM1(RichMessages m1) {
        this.m1 = m1;
    }

    public RichMessages getM1() {
        return m1;
    }

    public void setPlam1(RichPanelLabelAndMessage plam1) {
        this.plam1 = plam1;
    }

    public RichPanelLabelAndMessage getPlam1() {
        return plam1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setPlam2(RichPanelLabelAndMessage plam2) {
        this.plam2 = plam2;
    }

    public RichPanelLabelAndMessage getPlam2() {
        return plam2;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }
    
    private void mostrarOportunidad(String optyId) {

        ADFContext ctx = ADFContext.getCurrent();

        try {
            List<CpgLeadContAccProdFb> opp =
                new DosificacionEjecutivoBO().obtenerLeadCont(optyId);

            Util.Log("[mostrarOportunidad] Oportunidad : " +
                     opp.get(0).getIdLead());
            
                ctx.getSessionScope().put("iDoPtY", opp.get(0).getIdLead().toString());

                DCBindingContainer binding = this.getDCBindingsContainer();
                //Util.Log("[mostrarOportunidad] DCBindingContainer");
                DCIteratorBinding dcItteratorBindings =
                    binding.findIteratorBinding("CpgOptyProdContAccFb1Iterator");
                //Util.Log("[mostrarOportunidad] DCIteratorBinding");
                Row rw = null;

                rw = dcItteratorBindings.getNavigatableRowIterator().createRow();
                dcItteratorBindings.getNavigatableRowIterator().insertRow(rw);
                rw.setNewRowState(Row.STATUS_INITIALIZED);

        /*        String idCpg = "";
                String nomCpg = "";
                String genero = opp.get(0).getGenero();
                String sr = "";
                String monedaOpty = opp.get(0).getMonOpty();
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
           
                
                rw.setAttribute("Email", opp.get(0).getEmail());
                //txtEmail.setValue(opp.get(0).getEmail());
                rw.setAttribute("TipoDocumento", opp.get(0).getTipoDocumento());
               // txtTipDoc.setValue(opp.get(0).getTipoDocumento());
                rw.setAttribute("NumeroDni", opp.get(0).getNumeroDni());
               // txtNumDoc.setValue(opp.get(0).getNumeroDni());
              // rw.setAttribute("Nombre", sr + " " + opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno());
              //  txtNombre.setValue(sr + " " + opp.get(0).getNombre() + " " + opp.get(0).getApellidoPaterno());
                rw.setAttribute("FecNacimiento", opp.get(0).getFechaNacimiento());
               // txtFecNac.setValue(opp.get(0).getFechaNacimiento());
                rw.setAttribute("SegundoNombre", opp.get(0).getSegundoNombre());
                rw.setAttribute("ApellidoPaterno", opp.get(0).getApellidoPaterno());
                rw.setAttribute("ApellidoMaterno", opp.get(0).getApellidoMaterno());
               rw.setAttribute("Edad", opp.get(0).getEdad() + " años");
                //txtEdad.setValue(opp.get(0).getEdad());
                //rw.setAttribute("Genero", opp.get(0).getGenero());
                rw.setAttribute("Telefono01", Util.NumeroFormateado(opp.get(0).getTelefono01()));
                rw.setAttribute("Telefono02", Util.NumeroFormateado(opp.get(0).getTelefono02()));
                rw.setAttribute("Telefono03", Util.NumeroFormateado(opp.get(0).getTelefono03()));
                rw.setAttribute("Celular01", Util.NumeroFormateado(opp.get(0).getCelular01()));
                rw.setAttribute("Celular02", Util.NumeroFormateado(opp.get(0).getCelular02()));
                rw.setAttribute("Celular03", Util.NumeroFormateado(opp.get(0).getCelular03()));
                rw.setAttribute("PaisContacto", opp.get(0).getPaisContacto());
                rw.setAttribute("DeptCont", opp.get(0).getDeptCont());
                rw.setAttribute("DistContacto", opp.get(0).getDistContacto());
                rw.setAttribute("ProvContacto", opp.get(0).getProvContacto());
                rw.setAttribute("Direccion", opp.get(0).getDireccion());
                rw.setAttribute("IdOpp", opp.get(0).getIdOpp());
                rw.setAttribute("NomOpp", opp.get(0).getNomOpp());
                rw.setAttribute("MonOpty", opp.get(0).getMonOpty());
                //rw.setAttribute("NomProd", opp.get(0).getNomProd());
                //txtProd.setValue(opp.get(0).getNomProd());
                rw.setAttribute("Tceamax", opp.get(0).getTceaMax());
                rw.setAttribute("Tceamin", opp.get(0).getTceaMin());
                rw.setAttribute("Teamax", opp.get(0).getTeaMax());
                rw.setAttribute("Teamin", opp.get(0).getTeaMin());
                rw.setAttribute("MontoMaximo", mon + Util.formatearMonto(opp.get(0).getMontoMaximo()));
                rw.setAttribute("MontoMinimo", mon + Util.formatearMonto(opp.get(0).getMontoMinimo()));
                rw.setAttribute("PlazoMaximo", opp.get(0).getPlazoMaximo());
                rw.setAttribute("PlazoMinimo", opp.get(0).getPlazoMinimo());
                rw.setAttribute("Cuota", opp.get(0).getCuota());
                rw.setAttribute("OrganizationName",
                                opp.get(0).getOrganizationName());
                rw.setAttribute("Ruc", opp.get(0).getRuc());
                rw.setAttribute("PaisEmp", opp.get(0).getPaisEmp());
                rw.setAttribute("DeptEmp", opp.get(0).getDeptEmp());
                rw.setAttribute("ProvEmp", opp.get(0).getProvEmp());
                rw.setAttribute("DirEmp", opp.get(0).getDirEmp());
                rw.setAttribute("DistEmp", opp.get(0).getDistEmp());

                ctx.getSessionScope().put("CodCpg", idCpg);
                ctx.getSessionScope().put("NomCpg", nomCpg);

                Util.Log("[mostrarOportunidad] idCpg : " + idCpg);
        */ 
            
            rw.setAttribute("Email", opp.get(0).getEmail());
            rw.setAttribute("Celular01", Util.NumeroFormateado(opp.get(0).getCelular01()));
            rw.setAttribute("Genero", opp.get(0).getGenero());
            
                // Buscar feedback
                this.buscarFeedback(optyId);

        } catch (Exception ex) {
            Util.Log("[mostrarOportunidad] : " + ex.getMessage());


        }
    }
    
    public void buscarFeedback(String IdOportunidad) {
        try {
            ServiceBiPublisher pantOpty = new ServiceBiPublisher();
            List<Feedback> listFeedback = new ArrayList<Feedback>();
            listFeedback = pantOpty.obtenerFeedback(IdOportunidad);

            Util.Log("buscarFeedback size feedback " + listFeedback.size());


         /*   CollectionModel _tablaFeedback =
                (CollectionModel)tblFbContacto.getValue();
            JUCtrlHierBinding _adfTableFeedbackBinding =
                (JUCtrlHierBinding)_tablaFeedback.getWrappedData(); //devuelve el table binding
            DCIteratorBinding itFeedback =
                _adfTableFeedbackBinding.getDCIteratorBinding();

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
                rw8.setAttribute("FecGestion", opp8.getFecGestion());
                rw8.setAttribute("tipoGestion", opp8.getNomTipGest());
                rw8.setAttribute("grupoGestion", opp8.getNomGrupGest());
                rw8.setAttribute("detGestion", opp8.getNomDetGest());
                rw8.setAttribute("DescAdGestion", opp8.getDescAdGestion());
                rw8.setAttribute("NomCpg", opp8.getNomCpg());
                rw8.setAttribute("CodCpg", opp8.getCodCpg());
                rw8.setAttribute("MontoDesem", Util.formatearMonto(opp8.getMontoDesem()));
                rw8.setAttribute("MontoDesem2of", Util.formatearMonto(opp8.getMontoDesem2Of()));
                rw8.setAttribute("telefono", opp8.getTelefono());
                rw8.setAttribute("IdOpp", opp8.getIdOpty());
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
    */
            
         DCBindingContainer binding = this.getDCBindingsContainer();
         DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("FeedbackContactVO1Iterator");
         Row rw = null;

         rw = dcItteratorBindings.getNavigatableRowIterator().createRow();
         dcItteratorBindings.getNavigatableRowIterator().insertRow(rw);
         rw.setNewRowState(Row.STATUS_INITIALIZED);
            
         for (Feedback opp8 : listFeedback) {
             
            Util.Log(opp8.getNomTipGest() + "DATO");
                rw.setAttribute("idOpp", opp8.getIdLead());
               

            }
            
        } catch (Exception ex) {
            Util.Log("buscarFeedback " + ex.getMessage());
        }
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
        
        String optyId = ctx.getSessionScope().get("OPPID") == null ? "" : ctx.getSessionScope().get("OPPID").toString();

        mostrarOportunidad(optyId);
    }
}
