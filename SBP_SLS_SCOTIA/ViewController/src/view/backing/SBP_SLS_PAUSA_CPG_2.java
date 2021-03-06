package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Campania;
import dao.Canal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import org.apache.commons.dbutils.DbUtils;

import view.Util;

public class SBP_SLS_PAUSA_CPG_2 {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtUserName;
    private RichInputText txtCpgId;
    private RichSelectBooleanCheckbox txtPausa;

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

    public void setTxtUserName(RichInputText txtUserName) {
        this.txtUserName = txtUserName;
    }

    public RichInputText getTxtUserName() {
        return txtUserName;
    }

    public void setTxtCpgId(RichInputText txtCpgId) {
        this.txtCpgId = txtCpgId;
    }

    public RichInputText getTxtCpgId() {
        return txtCpgId;
    }
    
    public void onBeforePhase(PhaseEvent phaseEvent) {
        Util.Log("inicio");
           if (phaseEvent.getPhaseId().getOrdinal() == Lifecycle.PROCESS_COMPONENT_EVENTS_ID && !isPostback()) {
               onLoad();
           }  
           Util.Log("fin");
       }
       private boolean isPostback() {
           return Boolean.TRUE.equals(resolveExpression("#{adfFacesContext.postback}"));
       }
       private Object resolveExpression(String expression) {
           Util.Log("inicio");
           FacesContext facesContext = FacesContext.getCurrentInstance();
           Application app = facesContext.getApplication();
           ExpressionFactory elFactory = app.getExpressionFactory();
           ELContext elContext = facesContext.getELContext();
           ValueExpression valueExp = elFactory.createValueExpression(elContext, expression, Object.class);
           Util.Log("fin");
           return valueExp.getValue(elContext);
           
       }
       
       public void onLoad(){
           
           ADFContext ctx = ADFContext.getCurrent();       
                   
           txtUserName.setValue((String) resolveExpression("#{viewScope.userId2}"));
           obtenerCanal(txtUserName.getValue().toString());
           
           txtCpgId.setValue((String) resolveExpression("#{viewScope.cpgId2}"));
           String cpgId = txtCpgId.getValue().toString();
           
           ctx.getSessionScope().put("CampaignId", cpgId);
           
           datosOfRep();
           Util.Log(txtUserName.getValue().toString() + "USUARIO LOGUEADO");
           Util.Log(txtCpgId.getValue().toString() + "CAMAPA�A");
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
        ctx.getSessionScope().put("uSeRiD", supId);
        ctx.getSessionScope().put("userLog", userName);
    }
    
    public void datosOfRep(){

        ADFContext ctx = ADFContext.getCurrent();

        String idSup =  ctx.getSessionScope().get("uSeRiD") == null ? "" : ctx.getSessionScope().get("uSeRiD").toString();
        String idCpg =  ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();
        String idCanal =   ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
       
        try {
            String query =
                "select nvl(pausa_jefe_canal,0) PAUSA_JEFE_CANAL from SBP_SLS_DOSIF_X_CPG_LEAD where id_canal = " +
                idCanal + " and ID_CPG = " + idCpg;

            Util.Log(query + "QUERY");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();
            if(rs != null){
                
          
            while (rs.next()) {

                this.txtPausa.setValue(rs.getString("PAUSA_JEFE_CANAL"));
            }
            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        
        if(txtPausa.getValue() != null){
            if(txtPausa.getValue().toString().equalsIgnoreCase("1")){
               txtPausa.setValue(true);
            } else{
                txtPausa.setValue(false);
            }
        } else {
                txtPausa.setValue(false);
            }
    }

    public void setTxtPausa(RichSelectBooleanCheckbox txtPausa) {
        this.txtPausa = txtPausa;
    }

    public RichSelectBooleanCheckbox getTxtPausa() {
        return txtPausa;
    }

    public void procesar(ActionEvent actionEvent) {
        Util.Log("ENTRO");
        
        ADFContext ctx = ADFContext.getCurrent();
        
        
        String pausa = "";

        String idSup = ctx.getSessionScope().get("uSeRiD") == null ? "" : ctx.getSessionScope().get("uSeRiD").toString();
        String idCpg = ctx.getSessionScope().get("CampaignId") == null ? "" : ctx.getSessionScope().get("CampaignId").toString();
        String idCanal = ctx.getSessionScope().get("CaNaLiD") == null ? "" : ctx.getSessionScope().get("CaNaLiD").toString();

        String nameCpg = nomCpg(idCpg);

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        
        if (txtPausa.getValue().toString().equalsIgnoreCase("true")) {
            pausa = "1";
        }  else{
            pausa = "0";
        }
        
        try {

            String query = "update SBP_SLS_DOSIF_X_CPG_LEAD set PAUSA_JEFE_CANAL = " + pausa + " where id_cpg = " + idCpg + " and id_canal = " + idCanal; 

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            st.execute();

        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        if (txtPausa.getValue().toString().equalsIgnoreCase("true")) {
        
        FacesContext.getCurrentInstance().addMessage("",
                                                     new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                      "Se paus� la Campa�a: " + nameCpg,
                                                                      ""));
        } else{
            
            FacesContext.getCurrentInstance().addMessage("",
                                                         new FacesMessage(FacesMessage.SEVERITY_INFO,
                                                                          "Se registraron los datos en la campa�a : " +
                                                                          nameCpg,
                                                                          ""));
        }
    }
    
    public String nomCpg(String idCpg){
           
        String nomCpg = "";
        ServiceBiPublisher cpg = new ServiceBiPublisher();
        
        List<Campania> Nomcpg = new ArrayList<Campania>();
        
        Nomcpg = cpg.obtenerNomCpg(idCpg);
        
        for(Campania cpgList : Nomcpg){
            nomCpg = cpgList.getCpgNom();
        }
        
        Util.Log(nomCpg + "NOM CPG");
        return nomCpg;
    }
    
}
