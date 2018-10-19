package view.backing;

import WsEjRep.ServiceBiPublisher;

import dao.Items;

import dao.NomColumn;

import dao.OptyColumn;

import dao.ProdColumn;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;

import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.JboException;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import org.apache.commons.dbutils.DbUtils;
import org.apache.myfaces.trinidad.model.CollectionModel;

import view.Util;

public class SBP_SLS_CONT_FEED {
    private RichForm f1;
    private RichDocument d1;
    private RichTable tblProdOppCpg;
    private RichTable tblContCuent;

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

    public void setTblProdOppCpg(RichTable tblProdOppCpg) {
        this.tblProdOppCpg = tblProdOppCpg;
    }

    public RichTable getTblProdOppCpg() {
        return tblProdOppCpg;
    }

    public void setTblContCuent(RichTable tblContCuent) {
        this.tblContCuent = tblContCuent;
    }

    public RichTable getTblContCuent() {
        return tblContCuent;
    }

    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public DCBindingContainer getDCBindingsContainer() {
        return (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    }


    public String validarEstadoContact(String codCol) {


        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;


        CallableStatement stmt = null;

        String sql;
        String validacion = "no";

        sql = "Begin select nvl(ESTADO,'no') into ? from SBP_SLS_CONTACT_COL where COD_COL = ? ; Exception when no_data_found then null; end;";

        // variables
        try {
            conn = connection.getConnection();

            stmt = conn.prepareCall(sql);

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, codCol);
            stmt.execute();

            validacion = stmt.getString(1);

        } catch (SQLException e) {
            Util.Log("error " + e);
            throw new JboException(e);

        } finally {
            //DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }

        if (validacion == null) {
            validacion = "no";
        }

        return validacion;

    }


    public String validarEstadoOpty(String codCol) {


        sbp.utils.Connection connection = new sbp.utils.Connection();
        Connection conn = null;


        CallableStatement stmt = null;

        String sql;
        String validacion = "no";

        sql = "Begin select nvl(ESTADO,'no') into ? from SBP_SLS_OPTY_COL where COD_COL = ? ; Exception when no_data_found then null; end;";

        // variables
        try {
            conn = connection.getConnection();

            stmt = conn.prepareCall(sql);

            stmt.registerOutParameter(1, Types.VARCHAR);
            stmt.setString(2, codCol);
            stmt.execute();

            validacion = stmt.getString(1);

        } catch (SQLException e) {
            Util.Log("error " + e);
            throw new JboException(e);

        } finally {
            //DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(stmt);
            DbUtils.closeQuietly(conn);
        }

        if (validacion == null) {
            validacion = "no";
        }

        return validacion;

    }


    public void buscarColContAccount() {
        int contadorCont = 0;
        Row rw = null;

        ServiceBiPublisher nomCol = new ServiceBiPublisher();

        List<NomColumn> lista2 = new ArrayList<NomColumn>();
        lista2 = nomCol.obtenerNomCol();

        CollectionModel _tablaCol = (CollectionModel)tblContCuent.getValue();
        JUCtrlHierBinding _adfTableColBinding =
            (JUCtrlHierBinding)_tablaCol.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itCol = _adfTableColBinding.getDCIteratorBinding();

        int b = 0;
        b = itCol.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itCol.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }


        for (NomColumn listCol : lista2) {
            contadorCont = contadorCont +1;
            
            rw = itCol.getNavigatableRowIterator().createRow();
            itCol.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("NomColumn", listCol.getNomColumn());
            rw.setAttribute("NomOrCol", listCol.getNomOrCol());
            rw.setAttribute("NomTab", listCol.getNomTab());
            rw.setAttribute("CodCol", listCol.getCodCol());
            rw.setAttribute("NroOrden", contadorCont);


            Util.Log(validarEstadoContact(listCol.getCodCol()) +
                               "hooddddo");
            if (validarEstadoContact(listCol.getCodCol()).equalsIgnoreCase("Si")) {
                rw.setAttribute("visible", true);

            }

            Util.Log(validarEstadoContact(listCol.getCodCol()) +
                               "hooo");

        }
    }


    public void buscarOptyCpgProdCol() {

        int contadorOpty = 0;
        Row rw = null;

        ServiceBiPublisher optyCol = new ServiceBiPublisher();

        List<OptyColumn> lista2 = new ArrayList<OptyColumn>();
        lista2 = optyCol.obtenerOptyCol();


        CollectionModel _tablaOpty = (CollectionModel)tblProdOppCpg.getValue();
        JUCtrlHierBinding _adfTableOptyColBinding =
            (JUCtrlHierBinding)_tablaOpty.getWrappedData(); //devuelve el table binding
        DCIteratorBinding itOptyCol =
            _adfTableOptyColBinding.getDCIteratorBinding();

        int b = 0;
        b = itOptyCol.getRowSetIterator().getRowCount();

        for (int i = 0; i < b; i++) {
            itOptyCol.getRowSetIterator().getRowAtRangeIndex(0).remove();
        }

        for (OptyColumn listOpty : lista2) {
            
            contadorOpty = contadorOpty +1;

            rw = itOptyCol.getNavigatableRowIterator().createRow();
            itOptyCol.getNavigatableRowIterator().insertRow(rw);
            rw.setNewRowState(Row.STATUS_INITIALIZED);


            rw.setAttribute("OptyColumn", listOpty.getOptyColumn());
            rw.setAttribute("CodCol", listOpty.getCodCol());
            rw.setAttribute("NomOrCol", listOpty.getOptyOrCol());
            rw.setAttribute("NomTab", listOpty.getNomTab());
            rw.setAttribute("NroOrden", contadorOpty);
            
            Util.Log(validarEstadoOpty(listOpty.getCodCol()) +
                               "hooddddo");
            if (validarEstadoOpty(listOpty.getCodCol()).equalsIgnoreCase("Si")) {
                rw.setAttribute("visibleOpty", true);

            }

            Util.Log(validarEstadoOpty(listOpty.getCodCol()) +
                               "hooo");
    
        }

    }

    public void buscar(ActionEvent actionEvent) {
        buscarColContAccount();
        buscarOptyCpgProdCol();
    }

    public String guardar() {

        cambiarEstadoContact();
        cambiarEstadoOpty();

        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("Commit");
        Object result = operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            return null;
        }
        return null;
    }

    public void cambiarEstadoContact() {
        Util.Log("eaaaa123");
        DCBindingContainer bindingsReg = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingReg =
            bindingsReg.findIteratorBinding("SbpSlsContactColView1Iterator");

        ViewObject voReg = itorBindingReg.getViewObject();
        Row[] selectedRowsReg = voReg.getFilteredRows("visible", true);
        Util.Log("eaaaa");
        for (Row currRowReg : selectedRowsReg) {
            currRowReg.setAttribute("Estado", "Si");
            Util.Log("entra");

        }

        Util.Log(selectedRowsReg.length + "1");
    }


    public void cambiarEstadoOpty() {
        Util.Log("eaaaa12w3");
        DCBindingContainer bindingsReg = this.getDCBindingsContainer();
        DCIteratorBinding itorBindingReg =
            bindingsReg.findIteratorBinding("SbpSlsOptyColView1Iterator");

        ViewObject voReg = itorBindingReg.getViewObject();
        Row[] selectedRowsReg = voReg.getFilteredRows("visibleOpty", true);
        Util.Log("eaaawwa");
        for (Row currRowReg : selectedRowsReg) {
            currRowReg.setAttribute("Estado", "Si");
            Util.Log("entra");

        }

        Util.Log(selectedRowsReg.length + "12");
    }

}
