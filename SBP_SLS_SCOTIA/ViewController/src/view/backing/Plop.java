package view.backing;


import javax.faces.component.html.HtmlInputText;

import javax.faces.event.ActionEvent;

import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;

import view.Util;

public class Plop {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelBox pb1;
    private RichInputText it1;
    private HtmlInputText it2;
    private RichInputText txtUser;

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


    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(HtmlInputText it2) {
        this.it2 = it2;
    }

    public HtmlInputText getIt2() {
        return it2;
    }

    public void prueba(ActionEvent actionEvent) {
    String res =  ADFContext.getCurrent().getSecurityContext().getUserName();
     
       txtUser.setValue(res);
       
       Util.Log(res);
       
    }

    public void setTxtUser(RichInputText txtUser) {
        this.txtUser = txtUser;
    }

    public RichInputText getTxtUser() {
        return txtUser;
    }
}
