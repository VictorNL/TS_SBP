package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Canal;
import dao.EjVenta;

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
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_LIST_EV_MANT {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblEVenta;
    private RichSelectOneChoice cboCliPar;

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

    public void setTblEVenta(RichTable tblEVenta) {
        this.tblEVenta = tblEVenta;
    }

    public RichTable getTblEVenta() {
        return tblEVenta;
    }
    
    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
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
        
        String userName = (String) resolveExpression("#{viewScope.userId2}");
            
        buscarEjVenta(userName);
        

    }

    
    public void buscarEjVenta(String userName){
        
        List<Canal> listCan = new ArrayList<Canal>();
        
        List<EjVenta> listFilt = new ArrayList<EjVenta>();
        
        try {
            ServiceBiPublisher serv = new ServiceBiPublisher();
           
            listCan = serv.obtenerEvMant(userName);
            
            Util.Log("listCan --> " + listCan.size());

            CollectionModel _tablaFeedback =
                (CollectionModel)tblEVenta.getValue();
            JUCtrlHierBinding _adfTableFeedbackBinding =
                (JUCtrlHierBinding)_tablaFeedback.getWrappedData();
            DCIteratorBinding itFeedback =
                _adfTableFeedbackBinding.getDCIteratorBinding();

            int h = 0;
            h = itFeedback.getRowSetIterator().getRowCount();

            for (int i = 0; i < h; i++) {
                itFeedback.getRowSetIterator().getRowAtRangeIndex(0).remove();
            }

            Row rw8 = null;

            for (Canal canList : listCan) {

                rw8 = itFeedback.getNavigatableRowIterator().createRow();
                itFeedback.getNavigatableRowIterator().insertRow(rw8);
                rw8.setNewRowState(Row.STATUS_INITIALIZED);
                Util.Log("canList.getIdEjVenta() --> " + canList.getIdEjVenta());
                rw8.setAttribute("idEjVenta", canList.getIdEjVenta());
                rw8.setAttribute("ejVenta", canList.getEjVenta());
                
                listFilt = listInfoEjVenta(canList.getIdEjVenta());
                
                if(listFilt.size() > 0){
                    
                    rw8.setAttribute("tipoUsuario", listFilt.get(0).getTipUsuario());
                    rw8.setAttribute("anexo", listFilt.get(0).getAnexo());
                    rw8.setAttribute("tipoCon", listFilt.get(0).getTipoCon());
                    rw8.setAttribute("Encriptar", listFilt.get(0).getEncriptar());
                    
                } else {
                    
                    rw8.setAttribute("tipoUsuario", "");
                    rw8.setAttribute("anexo", "");
                    rw8.setAttribute("tipoCon", "");
                    rw8.setAttribute("Encriptar", ""); 
                }
                


            }
        } catch (Exception ex) {
            Util.Log("error " + ex.getMessage());
        }
        
    }

    
    public void insertarInfoMant(String idEjVenta, String ejVenta, String anexo, String tipoUsuario, String tipoCon, String encriptar){          


          Connection conn = null;
          PreparedStatement st = null;
          ResultSet rs = null;
          
          try {

              String query = "merge into sbp_sls_list_ev_mant rdm\n" +
                  "    using (select '" + idEjVenta + "' idEjVenta, '" + ejVenta + "' ejVenta, '" +
                  anexo + "' anexo, '" + tipoUsuario + "' tipoUsuario, '" + tipoCon + "' tipo_con, '" + encriptar + "'encriptar from dual) mer\n" +
                  "    on (rdm.id_ej_venta = mer.idEjVenta)\n" +
                  "    when matched then\n" +
                  "      update set rdm.ej_venta = mer.ejVenta,\n" +
                  "                 rdm.anexo = mer.anexo,\n" +
                  "                 rdm.tipo_usuario = mer.tipoUsuario,\n" +
                  "                 rdm.tipo_con = mer.tipo_con,\n" +
                  "                 rdm.encriptar = mer.encriptar\n" +
                  "    when not matched then\n" +
                  "      insert\n" +
                  "        (id_ej_venta, ej_venta, anexo, tipo_usuario, tipo_con, encriptar)\n" +
                  "      values\n" +
                  "        ('" + idEjVenta + "', '" + ejVenta + "', '" + anexo + "', '" + tipoUsuario + "', '" + tipoCon + "', '" +  encriptar +  "')";

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
          
      }
    
    public List<EjVenta> listInfoEjVenta(String idEjVenta) {

        List<EjVenta> listInfo = new ArrayList<EjVenta>();

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String anexo = "";
        String tipUsuario = "";
        String tipoCon = "";
        String encriptar = "";


        try {
            String query =
                "select anexo ANEXO, tipo_usuario TIPO_USUARIO, tipo_con TIPO_CON, encriptar ENCRIPTAR from sbp_sls_list_ev_mant where id_ej_venta = '" +
                idEjVenta + "'";

            Util.Log(query + "query");

            sbp.utils.Connection connection = new sbp.utils.Connection();

            conn = connection.getConnection();

            st = conn.prepareStatement(query);

            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                EjVenta ejVenta = new EjVenta();

                while (rs.next()) {

                    anexo = rs.getString("ANEXO");
                    tipUsuario = rs.getString("TIPO_USUARIO");
                    tipoCon = rs.getString("TIPO_CON");
                    encriptar = rs.getString("ENCRIPTAR");

                    ejVenta.setAnexo(anexo);
                    ejVenta.setTipUsuario(tipUsuario);
                    ejVenta.setTipoCon(tipoCon);
                    ejVenta.setEncriptar(encriptar);
                    listInfo.add(ejVenta);
                }

            }
        } catch (SQLException e) {
            Util.Log(e + "error");
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        return listInfo;
    }
    
    public void procesarInfo(){
            
            String idEjVenta = "";
            String ejVenta = "";
            String tipoUsuario = "";
            String anexo = "";
            String tipoCon = "";
            String encriptar = "";
            
                      
            DCBindingContainer binding = this.getDCBindingsContainer();
            DCIteratorBinding dcItteratorBindings = binding.findIteratorBinding("SbpSlsMantEVVO1Iterator");

            ViewObject vo = dcItteratorBindings.getViewObject();
            RowSetIterator rsi = vo.createRowSetIterator(null);

            if (rsi.getRowCount() > 0) {
              
                Row currRow;

                while (rsi.hasNext()) {
                    
                    currRow = rsi.next();
                    
                    if(currRow.getAttribute("idEjVenta") == null || 
                       currRow.getAttribute("ejVenta") == null ||
                       currRow.getAttribute("tipoUsuario") == null ||
                       currRow.getAttribute("anexo") == null ||
                       currRow.getAttribute("tipoCon") == null ||
                       currRow.getAttribute("Encriptar") == null){
                    
                        FacesContext fctx = FacesContext.getCurrentInstance();
                        fctx.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "No pueden haber campos en blanco",
                                                         ""));
                           
                    } else {
                        
                        idEjVenta = currRow.getAttribute("idEjVenta").toString();     
                        ejVenta = currRow.getAttribute("ejVenta").toString(); 
                        tipoUsuario = currRow.getAttribute("tipoUsuario").toString(); 
                        anexo = currRow.getAttribute("anexo").toString(); 
                        tipoCon = currRow.getAttribute("tipoCon").toString(); 
                        encriptar = currRow.getAttribute("Encriptar").toString(); 
                        
                        Util.Log("idEjVenta --> " + idEjVenta);
                        Util.Log("ejVenta --> " + ejVenta);
                        Util.Log("tipoUsuario --> " + tipoUsuario);
                        Util.Log("anexo --> " + anexo);
                            
                        insertarInfoMant(idEjVenta, ejVenta, anexo, tipoUsuario, tipoCon, encriptar);
                        
                    }
    
                }

            }    
        
        }
    
    public void Procesar(ActionEvent actionEvent) {
        
        procesarInfo();
        
    }

    public void setCboCliPar(RichSelectOneChoice cboCliPar) {
        this.cboCliPar = cboCliPar;
    }

    public RichSelectOneChoice getCboCliPar() {
        return cboCliPar;
    }
}
