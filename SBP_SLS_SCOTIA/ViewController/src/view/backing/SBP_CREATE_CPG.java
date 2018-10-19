package view.backing;

import client.MergeEntityInvoke;

import com.oracle.xmlns.apps.sales.custextn.extnservice.types.MergeEntity;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.xml.parsers.ParserConfigurationException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputComboboxListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import org.xml.sax.SAXException;

import view.Util;

public class SBP_CREATE_CPG {
    private RichForm f1;
    private RichDocument d1;
    private RichInputText txtNomCpg;
    private RichInputText txtPorcAsig;
    private RichInputText txtId;
    private RichInputText txtObjectName;
    private RichInputDate txtFechaIni;
    private RichInputDate txtFechaFin;
    private RichInputText txtCantOf;
    private RichInputComboboxListOfValues cboProdBas;

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

    public void setTxtNomCpg(RichInputText txtNomCpg) {
        this.txtNomCpg = txtNomCpg;
    }

    public RichInputText getTxtNomCpg() {
        return txtNomCpg;
    }

    public void setTxtPorcAsig(RichInputText txtPorcAsig) {
        this.txtPorcAsig = txtPorcAsig;
    }

    public RichInputText getTxtPorcAsig() {
        return txtPorcAsig;
    }
    
    public void setTxtId(RichInputText txtId) {
        this.txtId = txtId;
    }

    public RichInputText getTxtId() {
        return txtId;
    }

    public void setTxtObjectName(RichInputText txtObjectName) {
        this.txtObjectName = txtObjectName;
    }

    public RichInputText getTxtObjectName() {
        return txtObjectName;
    }

    public void setTxtFechaIni(RichInputDate txtFechaIni) {
        this.txtFechaIni = txtFechaIni;
    }

    public RichInputDate getTxtFechaIni() {
        return txtFechaIni;
    }

    public void setTxtFechaFin(RichInputDate txtFechaFin) {
        this.txtFechaFin = txtFechaFin;
    }

    public RichInputDate getTxtFechaFin() {
        return txtFechaFin;
    }

    public void setTxtCantOf(RichInputText txtCantOf) {
        this.txtCantOf = txtCantOf;
    }

    public RichInputText getTxtCantOf() {
        return txtCantOf;
    }
    
    public void setCboProdBas(RichInputComboboxListOfValues cboProdBas) {
        this.cboProdBas = cboProdBas;
    }

    public RichInputComboboxListOfValues getCboProdBas() {
        return cboProdBas;
    }

    public void crearCampania(ActionEvent actionEvent) throws ParseException {
        MergeEntityInvoke mergeEntity = new MergeEntityInvoke();

        String id = "";

        String resultado;

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        Util.Log("a");
       //Util.Log(txtCantOf.getValue().toString());
   /*       try {

            if (txtId.getValue() != null) {

                id = txtId.getValue().toString();

            }

         //String dateIni = format.format(txtFechaIni.getValue());
         //String dateFin = format.format(txtFechaFin.getValue());

           if(txtNomCpg.getValue() == null || txtPorcAsig.getValue() == null || txtFechaIni.getValue() == null ||
            cboProdBas.getValue() == null || txtFechaFin.getValue() == null || txtCantOf.getValue() == null ||
           txtObjectName.getValue() == null){
           FacesContext fctx = FacesContext.getCurrentInstance();
           fctx.addMessage("",
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos incompletos",""));

           }
                
      resultado = mergeEntity.mergeEntity(id,
                                            txtNomCpg.getValue().toString(),
                                            cboProdBas.getValue().toString(),
                                            txtPorcAsig.getValue().toString(),
                                            txtFechaIni.getValue().toString(),
                                            txtFechaFin.getValue().toString(),
                                            txtCantOf.getValue().toString(),
                                             "true",
                                             txtPorcAsig.getValue().toString(),
                                            txtObjectName.getValue().toString());
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }*/
    }


    public void crearCpg(ActionEvent actionEvent) {
        
        MergeEntityInvoke mergeEntity = new MergeEntityInvoke();

        String id = "";

        String resultado;

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        //String dateIni = format.format(txtFechaIni.getValue());
        //String dateFin = format.format(txtFechaFin.getValue());
     /*       try {

            if (txtId.getValue() != null) {

                id = txtId.getValue().toString();

            }

           // String dateIni = format.format(txtFechaIni.getValue());
           // String dateFin = format.format(txtFechaFin.getValue());

           if(txtNomCpg.getValue() == null || txtPorcAsig.getValue() == null || txtFechaIni.getValue() == null ||
            cboProdBas.getValue() == null || txtFechaFin.getValue() == null || txtCantOf.getValue() == null ||
           txtObjectName.getValue() == null){
           FacesContext fctx = FacesContext.getCurrentInstance();
           fctx.addMessage("",
                   new FacesMessage(FacesMessage.SEVERITY_ERROR, "Existen campos incompletos",""));

           }
                
      resultado = mergeEntity.mergeEntity(id,
                                            txtNomCpg.getValue().toString(),
                                            cboProdBas.getValue().toString(),
                                            txtPorcAsig.getValue().toString(),
                                            txtFechaIni.getValue().toString(),
                                            txtFechaFin.getValue().toString(),
                                            txtCantOf.getValue().toString(),
                                            "true",
                                            txtPorcAsig.getValue().toString(),
                                            txtObjectName.getValue().toString());
        } catch (ParserConfigurationException e) {
        } catch (SAXException e) {
        } catch (IOException e) {
        }*/
    }
}
