package view;

import Dao.Conexion;

import WsEjRep.ServiceBiPublisher;

import dao.LoginUser;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputNumberSpinbox;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;
import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

import oracle.jbo.Row;

import oracle.jdbc.OracleTypes;

import org.apache.commons.dbutils.DbUtils;

import view.BE.BEConstante;

public class Util {

    public Util() {
        super();
    }

    public static String nvl(Row currRow, String a, String b) {
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

    public static String nvlNoRow(RichInputText txtCodDetGest, String string) {
        try {
            if (txtCodDetGest.getValue() != null)
                return txtCodDetGest.getValue().toString();
            else
                return string;
        } catch (NullPointerException e) {
            Util.Log("nvl String" + txtCodDetGest + " NullPointerException");
            return string;
        }
    }

    public static void Log(Double dblMensaje) {

        ADFContext ctx = ADFContext.getCurrent();

        String userLog = ctx.getSessionScope().get("userLog") == null ? "" : ctx.getSessionScope().get("userLog").toString();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(formatoFecha.format(new Date()) + " ::: " + userLog + " :: " + "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + dblMensaje);
    }

    public static void Log(Integer intMensaje) {

        ADFContext ctx = ADFContext.getCurrent();

        String userLog = ctx.getSessionScope().get("userLog") == null ? "" : ctx.getSessionScope().get("userLog").toString();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(formatoFecha.format(new Date()) + " ::: " + userLog + " :: " + "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + intMensaje);
    }

    public static void Log(Boolean blnMensaje) {

        ADFContext ctx = ADFContext.getCurrent();

        String userLog = ctx.getSessionScope().get("userLog") == null ? "" : ctx.getSessionScope().get("userLog").toString();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(formatoFecha.format(new Date()) + " ::: " + userLog + " :: " + "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + blnMensaje);
    }

    public static void Log(String strMensaje) {

        ADFContext ctx = ADFContext.getCurrent();

        String userLog = ctx.getSessionScope().get("userLog") == null ? "" : ctx.getSessionScope().get("userLog").toString();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(formatoFecha.format(new Date()) + " ::: " + userLog + " :: " + "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + strMensaje);
    }

    public static void LogTime(String strMensaje) {

        ADFContext ctx = ADFContext.getCurrent();

        String userLog = ctx.getSessionScope().get("userLog") == null ? "" : ctx.getSessionScope().get("userLog").toString();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(formatoFecha.format(new Date()) + " ::: " + userLog + " :: " + "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + strMensaje + " *****************************");
    }
    // Obtener un valor cantidad de acuerdo al query

    public static String ExecuteQuery(String Query) {
        String sResultado = "0";

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            //Util.Log("[ExecuteQuery] " + Query);

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(Query);
            rs = st.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.next();
                sResultado = rs.getString("RESULTADO");
            } else {
                sResultado = "0";
            }

        } catch (SQLException e) {
            sResultado = "0";
            Util.Log("[ExecuteQuery] " + e);
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }

        Util.Log("[ExecuteQuery] sResultado : " + sResultado);

        return sResultado;
    }

    // Obtener un valor cantidad de acuerdo al query

    public static Boolean Execute(String Query) {
        boolean bolReturn = false;
        Connection conn = null;
        PreparedStatement st = null;

        try {

            //Util.Log("[Execute] " + Query);

            sbp.utils.Connection connection = new sbp.utils.Connection();
            conn = connection.getConnection();
            st = conn.prepareStatement(Query);
            st.execute();
            st.close();
            conn.close();

            bolReturn = true;
        } catch (SQLException e) {
            bolReturn = false;
            Util.Log("[Execute] " + e + "error");
        } catch (Exception ea) {
            bolReturn = false;
            Util.Log("[Execute] " + ea + "error");
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        return bolReturn;
    }

    public static String NumeroFormateado(String sValue) {
        String r = "";
        String a = "";
        String aux = "";
        boolean b = false;

        try {

            // solo numeros
            for (int i = 0; i < sValue.length(); i++) {
                if (sValue.substring(i, i + 1).equalsIgnoreCase("0") || sValue.substring(i, i + 1).equalsIgnoreCase("1") || sValue.substring(i, i + 1).equalsIgnoreCase("2") || sValue.substring(i, i + 1).equalsIgnoreCase("3") || sValue.substring(i, i + 1).equalsIgnoreCase("4") || sValue.substring(i, i + 1).equalsIgnoreCase("5") ||
                    sValue.substring(i, i + 1).equalsIgnoreCase("6") || sValue.substring(i, i + 1).equalsIgnoreCase("7") || sValue.substring(i, i + 1).equalsIgnoreCase("8") || sValue.substring(i, i + 1).equalsIgnoreCase("9")) {
                    a = a + sValue.substring(i, i + 1);
                }
            }


            int largo = a.length();
            double d = Math.ceil(largo / 3);
            int j = (largo - (int)(d * 3));

            // Caso que contenga (01, 05)
            if ((a.substring(0, 1).equalsIgnoreCase("1") && largo == 8) || (a.substring(0, 1).equalsIgnoreCase("1") && largo == 10)) {
                a = a.substring(1, largo);
                aux = "1-";
                b = true;
                largo = a.length();
                d = Math.ceil(largo / 3) + 1;
                j = (largo - (int)(d * 3));
            } else if ((a.substring(0, 2).equalsIgnoreCase("01") && largo == 9) || (a.substring(0, 2).equalsIgnoreCase("01") && largo == 11)) {
                a = a.substring(2, largo);
                aux = "01-";
                b = true;
                largo = a.length();
                d = Math.ceil(largo / 3) + 1;
                j = (largo - (int)(d * 3));
            } else if ((a.substring(0, 2).equalsIgnoreCase("51") && largo == 9) || (a.substring(0, 2).equalsIgnoreCase("51") && largo == 11)) {
                a = a.substring(2, largo);
                aux = "51-";
                b = true;
                largo = a.length();
                d = Math.ceil(largo / 3) + 1;
                j = (largo - (int)(d * 3));
            }

            for (int i = 0; i <= d; i++) {
                int ini = largo > ((i + 1) * 3) ? ((i + 1) * 3) : largo;
                int fin = largo > (i * 3) ? i * 3 : largo;
                r = "-" + a.substring(largo - ini, largo - fin) + r;
            }


            // Eliminar giones iniciales
            for (int i = 0; i < r.length(); i++) {
                if (r.substring(0, 1).equalsIgnoreCase("-")) {
                    r = r.substring(1, r.length());
                } else {
                    break;
                }
            }

            r = (b ? aux : "") + r;

        } catch (Exception e) {
            Util.Log("[NumeroFormateado] Error : " + e.getMessage());
            r = "";
        }

        return r;
    }


    public static String formatearMonto(String monto) {

        String r = "0";
        try {
            double amount = Double.parseDouble(monto);
            DecimalFormat formatter = new DecimalFormat("#,###");
            r = formatter.format(amount);
        } catch (Exception e) {

        }

        //Util.Log(r + "MONTO FORMATEADO");
        return r;
    }

    public static String nvl(RichInputNumberSpinbox rit, String s) {
        try {
            if (rit.getValue() != null)
                return rit.getValue().toString();
            else
                return s;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s;
        }
    }

    public static String nvl(RichInputText rit, String s) {
        try {
            if (rit.getValue() != null)
                return rit.getValue().toString();
            else
                return s;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s;
        }
    }

    public static String nvl(RichInputDate rit, String s) {
        try {
            if (rit.getValue() != null)
                return rit.getValue().toString();
            else
                return s;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s;
        }
    }

    public static String nvl(RichSelectOneChoice rsoc, String s) {
        try {
            if (rsoc.getValue() != null)
                return rsoc.getValue().toString();
            else
                return s;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s;
        }
    }

    public static String nvl(RichSelectBooleanCheckbox rsbc, String s) {
        try {
            if (rsbc.getValue() != null)
                return rsbc.getValue().toString();
            else
                return s;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s;
        }
    }

    public static String nvl(String s1, String s2) {
        try {
            if (s1 != null || !s1.isEmpty())
                return s1;
            else
                return s2;
        } catch (NullPointerException ex) {
            Util.Log(ex.getMessage());
            return s2;
        }
    }

    public static String getSelectedItem(RichSelectOneChoice pRsoc) {
        String sReturn = "";
        try {
            RichSelectOneChoice rsoc = pRsoc; //(RichSelectOneChoice)valueChangeEvent.getSource();
            List childList = rsoc.getChildren();
            String newVal = pRsoc.getValue().toString(); // (String)valueChangeEvent.getNewValue();

            for (int i = 0; i < childList.size(); i++) {
                if (childList.get(i) instanceof RichSelectItem) {
                    RichSelectItem csi = (RichSelectItem)childList.get(i);
                    if (((String)csi.getValue()).equals(newVal)) { //TODO store the label or print it
                        //Util.Log(csi.getLabel() + " :: " + this.soc1.getValue());
                        sReturn = csi.getLabel();
                    }
                }
            }
        } catch (Exception ex) {
            Util.Log(ex.getMessage());
            sReturn = "";
        }
        return sReturn;
    }

    public static void CopiarPortapapeles(String s) {
        StringSelection ss = new StringSelection(s);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }

    public static Conexion DatosConexionSBP() {
        return DatosConexionSBP(BEConstante.cod_cloudOracle);
    }

    public static Conexion DatosConexionSBP(String psQuery) {
        // Conexion a base de datos
        ServiceBiPublisher sbp = new ServiceBiPublisher();
        String sQuery = "begin SBP_SLS_CONFIG_PKG.pr_obtenerConfiguracion(?,?); end;";
        ResultSet rs = null;
        Connection cnx = null;
        CallableStatement st = null;
        Conexion cnxSBP = new Conexion();
        try {

            sbp.utils.Connection connection;
            connection = new sbp.utils.Connection();
            cnx = connection.getConnection();
            st = cnx.prepareCall(sQuery);
            st.setString(1, psQuery);
            st.registerOutParameter(2, OracleTypes.CURSOR);
            st.execute();
            rs = (ResultSet)st.getObject(2);
            if (rs != null) {

                while (rs.next()) {
                    cnxSBP.setHostName(rs.getString("SBP_HOST_NAME"));
                    cnxSBP.setPort(rs.getString("SBP_PORT"));
                    cnxSBP.setUserName(rs.getString("SBP_USER_NAME"));
                    cnxSBP.setPassword(rs.getString("SBP_USER_PASS"));
                    cnxSBP.setKeyStoreLocation(rs.getString("SBP_KEY_LOCA"));
                    cnxSBP.setKeyStorePassword(rs.getString("SBP_KEY_PASS"));
                    break;
                }

                rs.close();
            }


            //
        } catch (Exception ex) {
            Util.Log(ex.getMessage());

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(cnx);
        }
        return cnxSBP;
    }

    public static void MostrarMensajeOK(String sMensaje) {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, sMensaje, ""));
    }

    public static void MostrarMensajeKO(String sMensaje) {
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "[" + new Exception().getStackTrace()[1].getMethodName() + "] " + sMensaje, ""));
    }

    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }

    public static void redirec(String psUrl) {
        ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletResponse response = (HttpServletResponse)ectx.getResponse();
        String url = ectx.getRequestContextPath() + "/faces/" + psUrl;
        HttpSession session = (HttpSession)ectx.getSession(false);
        session.invalidate();
        try {
            response.sendRedirect(url);
            FacesContext.getCurrentInstance().responseComplete();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return;
    }
    

    public static LoginUser datosUser(String psUserName) {
        ADFContext ctx = ADFContext.getCurrent();
        LoginUser lu = null;
        
        if (ctx.getSessionScope().get(psUserName) != null){
            lu = (LoginUser)ctx.getSessionScope().get(psUserName);
        } else {
            lu = new ServiceBiPublisher().obtenerLoginUser(psUserName).get(0);
            ctx.getSessionScope().put(psUserName, lu);
        }

        return lu;
    }

}
