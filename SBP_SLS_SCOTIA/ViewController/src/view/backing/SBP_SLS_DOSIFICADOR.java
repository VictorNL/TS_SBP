package view.backing;

import Client.MergeLeadInvoke;

import Dao.SimplifiedImportActivity;

import client.ImportLeadResource;

import dao.Lead;

import dao.LeadDelete;

import java.io.ByteArrayOutputStream;

import java.net.SocketException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import org.apache.commons.dbutils.DbUtils;

import view.DosificacionEjecutivoBO;
import view.Util;

public class SBP_SLS_DOSIFICADOR {
    private RichForm f1;
    private RichDocument d1;
    private RichCommandButton cb1;

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

    public void setCb1(RichCommandButton cb1) {
        this.cb1 = cb1;
    }

    public RichCommandButton getCb1() {
        return cb1;
    }

    public void dosificador(ActionEvent actionEvent) throws SocketException {

        List<Lead> leadListadoTabla = new ArrayList<Lead>();


        try {
            // Dofificar todos
            String sLoteEjecucion = DosificacionEjecutivoBO.dosificar();

            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage("",
                            new FacesMessage(FacesMessage.SEVERITY_INFO, DosificacionEjecutivoBO.obtenerLog(sLoteEjecucion),
                                             ""));

            leadListadoTabla = DosificacionEjecutivoBO.listLead(null, null);

            DosificacionEjecutivoBO.asignarLeadTxt(leadListadoTabla);

        } catch (Exception ex) {
            Util.Log("[dosificador] Exception : " + ex.getMessage());
            DosificacionEjecutivoBO.ordenar();
        }
    }

   
}
