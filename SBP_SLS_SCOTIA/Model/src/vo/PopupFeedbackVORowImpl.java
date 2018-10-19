package vo;

import oracle.jbo.RowSet;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 11 13:19:16 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PopupFeedbackVORowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        tipoGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.gettipoGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.settipoGest((String)value);
            }
        }
        ,
        codTipGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getcodTipGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setcodTipGest((Number)value);
            }
        }
        ,
        grupGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getgrupGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setgrupGest((String)value);
            }
        }
        ,
        codGrupGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getcodGrupGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setcodGrupGest((Number)value);
            }
        }
        ,
        detGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getdetGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setdetGest((String)value);
            }
        }
        ,
        codDetGest {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getcodDetGest();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setcodDetGest((Number)value);
            }
        }
        ,
        nomFb {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getnomFb();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setnomFb((String)value);
            }
        }
        ,
        idFb {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getidFb();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setidFb((Number)value);
            }
        }
        ,
        telf {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.gettelf();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.settelf((String)value);
            }
        }
        ,
        idEjVenta {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getidEjVenta();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setidEjVenta((Number)value);
            }
        }
        ,
        ejVenta {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getejVenta();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setejVenta((String)value);
            }
        }
        ,
        fecGestion {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getfecGestion();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setfecGestion((String)value);
            }
        }
        ,
        observaciones {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getobservaciones();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setobservaciones((String)value);
            }
        }
        ,
        direccionAd {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getdireccionAd();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setdireccionAd((String)value);
            }
        }
        ,
        telfAd {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.gettelfAd();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.settelfAd((String)value);
            }
        }
        ,
        emailAd {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getemailAd();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setemailAd((String)value);
            }
        }
        ,
        idCanal {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getidCanal();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setidCanal((String)value);
            }
        }
        ,
        canal {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getcanal();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setcanal((String)value);
            }
        }
        ,
        idSup {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getidSup();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setidSup((String)value);
            }
        }
        ,
        sup {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getsup();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setsup((String)value);
            }
        }
        ,
        idJefCanal {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getidJefCanal();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setidJefCanal((String)value);
            }
        }
        ,
        jefCanal {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getjefCanal();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setjefCanal((String)value);
            }
        }
        ,
        montoDesem {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getmontoDesem();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setmontoDesem((String)value);
            }
        }
        ,
        fec2daLlam {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getfec2daLlam();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setfec2daLlam((Date)value);
            }
        }
        ,
        FeebackTipGestLOV1 {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getFeebackTipGestLOV1();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        FeedbackGrupGestLov1 {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getFeedbackGrupGestLov1();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ,
        FeedbackDetGestLOV1 {
            public Object get(PopupFeedbackVORowImpl obj) {
                return obj.getFeedbackDetGestLOV1();
            }

            public void put(PopupFeedbackVORowImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(PopupFeedbackVORowImpl object);

        public abstract void put(PopupFeedbackVORowImpl object, Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int TIPOGEST = AttributesEnum.tipoGest.index();
    public static final int CODTIPGEST = AttributesEnum.codTipGest.index();
    public static final int GRUPGEST = AttributesEnum.grupGest.index();
    public static final int CODGRUPGEST = AttributesEnum.codGrupGest.index();
    public static final int DETGEST = AttributesEnum.detGest.index();
    public static final int CODDETGEST = AttributesEnum.codDetGest.index();
    public static final int NOMFB = AttributesEnum.nomFb.index();
    public static final int IDFB = AttributesEnum.idFb.index();
    public static final int TELF = AttributesEnum.telf.index();
    public static final int IDEJVENTA = AttributesEnum.idEjVenta.index();
    public static final int EJVENTA = AttributesEnum.ejVenta.index();
    public static final int FECGESTION = AttributesEnum.fecGestion.index();
    public static final int OBSERVACIONES = AttributesEnum.observaciones.index();
    public static final int DIRECCIONAD = AttributesEnum.direccionAd.index();
    public static final int TELFAD = AttributesEnum.telfAd.index();
    public static final int EMAILAD = AttributesEnum.emailAd.index();
    public static final int IDCANAL = AttributesEnum.idCanal.index();
    public static final int CANAL = AttributesEnum.canal.index();
    public static final int IDSUP = AttributesEnum.idSup.index();
    public static final int SUP = AttributesEnum.sup.index();
    public static final int IDJEFCANAL = AttributesEnum.idJefCanal.index();
    public static final int JEFCANAL = AttributesEnum.jefCanal.index();
    public static final int MONTODESEM = AttributesEnum.montoDesem.index();
    public static final int FEC2DALLAM = AttributesEnum.fec2daLlam.index();
    public static final int FEEBACKTIPGESTLOV1 = AttributesEnum.FeebackTipGestLOV1.index();
    public static final int FEEDBACKGRUPGESTLOV1 = AttributesEnum.FeedbackGrupGestLov1.index();
    public static final int FEEDBACKDETGESTLOV1 = AttributesEnum.FeedbackDetGestLOV1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PopupFeedbackVORowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute tipoGest.
     * @return the tipoGest
     */
    public String gettipoGest() {
        return (String) getAttributeInternal(TIPOGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute tipoGest.
     * @param value value to set the  tipoGest
     */
    public void settipoGest(String value) {
        setAttributeInternal(TIPOGEST, value);
        setAttributeInternal(GRUPGEST, null);
        setAttributeInternal(DETGEST, null);        
    }

    /**
     * Gets the attribute value for the calculated attribute codTipGest.
     * @return the codTipGest
     */
    public Number getcodTipGest() {
        return (Number) getAttributeInternal(CODTIPGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute codTipGest.
     * @param value value to set the  codTipGest
     */
    public void setcodTipGest(Number value) {
        setAttributeInternal(CODTIPGEST, value);
        setAttributeInternal(CODGRUPGEST, null);
        setAttributeInternal(CODDETGEST, null);  
    }

    /**
     * Gets the attribute value for the calculated attribute grupGest.
     * @return the grupGest
     */
    public String getgrupGest() {
        return (String) getAttributeInternal(GRUPGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute grupGest.
     * @param value value to set the  grupGest
     */
    public void setgrupGest(String value) {
        setAttributeInternal(GRUPGEST, value);
        setAttributeInternal(DETGEST, null);        
    }

    /**
     * Gets the attribute value for the calculated attribute codGrupGest.
     * @return the codGrupGest
     */
    public Number getcodGrupGest() {
        return (Number) getAttributeInternal(CODGRUPGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute codGrupGest.
     * @param value value to set the  codGrupGest
     */
    public void setcodGrupGest(Number value) {
        setAttributeInternal(CODGRUPGEST, value);
        setAttributeInternal(CODDETGEST, null);  
    }

    /**
     * Gets the attribute value for the calculated attribute detGest.
     * @return the detGest
     */
    public String getdetGest() {
        return (String) getAttributeInternal(DETGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute detGest.
     * @param value value to set the  detGest
     */
    public void setdetGest(String value) {
        setAttributeInternal(DETGEST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute codDetGest.
     * @return the codDetGest
     */
    public Number getcodDetGest() {
        return (Number) getAttributeInternal(CODDETGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute codDetGest.
     * @param value value to set the  codDetGest
     */
    public void setcodDetGest(Number value) {
        setAttributeInternal(CODDETGEST, value);
    }

    /**
     * Gets the attribute value for the calculated attribute nomFb.
     * @return the nomFb
     */
    public String getnomFb() {
        return (String) getAttributeInternal(NOMFB);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute nomFb.
     * @param value value to set the  nomFb
     */
    public void setnomFb(String value) {
        setAttributeInternal(NOMFB, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idFb.
     * @return the idFb
     */
    public Number getidFb() {
        return (Number) getAttributeInternal(IDFB);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idFb.
     * @param value value to set the  idFb
     */
    public void setidFb(Number value) {
        setAttributeInternal(IDFB, value);
    }

    /**
     * Gets the attribute value for the calculated attribute telf.
     * @return the telf
     */
    public String gettelf() {
        return (String) getAttributeInternal(TELF);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute telf.
     * @param value value to set the  telf
     */
    public void settelf(String value) {
        setAttributeInternal(TELF, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idEjVenta.
     * @return the idEjVenta
     */
    public Number getidEjVenta() {
        return (Number) getAttributeInternal(IDEJVENTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idEjVenta.
     * @param value value to set the  idEjVenta
     */
    public void setidEjVenta(Number value) {
        setAttributeInternal(IDEJVENTA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ejVenta.
     * @return the ejVenta
     */
    public String getejVenta() {
        return (String) getAttributeInternal(EJVENTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ejVenta.
     * @param value value to set the  ejVenta
     */
    public void setejVenta(String value) {
        setAttributeInternal(EJVENTA, value);
    }

    /**
     * Gets the attribute value for the calculated attribute fecGestion.
     * @return the fecGestion
     */
    public String getfecGestion() {
        return (String) getAttributeInternal(FECGESTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute fecGestion.
     * @param value value to set the  fecGestion
     */
    public void setfecGestion(String value) {
        setAttributeInternal(FECGESTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute observaciones.
     * @return the observaciones
     */
    public String getobservaciones() {
        return (String) getAttributeInternal(OBSERVACIONES);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute observaciones.
     * @param value value to set the  observaciones
     */
    public void setobservaciones(String value) {
        setAttributeInternal(OBSERVACIONES, value);
    }

    /**
     * Gets the attribute value for the calculated attribute direccionAd.
     * @return the direccionAd
     */
    public String getdireccionAd() {
        return (String) getAttributeInternal(DIRECCIONAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute direccionAd.
     * @param value value to set the  direccionAd
     */
    public void setdireccionAd(String value) {
        setAttributeInternal(DIRECCIONAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute telfAd.
     * @return the telfAd
     */
    public String gettelfAd() {
        return (String) getAttributeInternal(TELFAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute telfAd.
     * @param value value to set the  telfAd
     */
    public void settelfAd(String value) {
        setAttributeInternal(TELFAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute emailAd.
     * @return the emailAd
     */
    public String getemailAd() {
        return (String) getAttributeInternal(EMAILAD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute emailAd.
     * @param value value to set the  emailAd
     */
    public void setemailAd(String value) {
        setAttributeInternal(EMAILAD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idCanal.
     * @return the idCanal
     */
    public String getidCanal() {
        return (String) getAttributeInternal(IDCANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idCanal.
     * @param value value to set the  idCanal
     */
    public void setidCanal(String value) {
        setAttributeInternal(IDCANAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute canal.
     * @return the canal
     */
    public String getcanal() {
        return (String) getAttributeInternal(CANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute canal.
     * @param value value to set the  canal
     */
    public void setcanal(String value) {
        setAttributeInternal(CANAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idSup.
     * @return the idSup
     */
    public String getidSup() {
        return (String) getAttributeInternal(IDSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idSup.
     * @param value value to set the  idSup
     */
    public void setidSup(String value) {
        setAttributeInternal(IDSUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute sup.
     * @return the sup
     */
    public String getsup() {
        return (String) getAttributeInternal(SUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute sup.
     * @param value value to set the  sup
     */
    public void setsup(String value) {
        setAttributeInternal(SUP, value);
    }

    /**
     * Gets the attribute value for the calculated attribute idJefCanal.
     * @return the idJefCanal
     */
    public String getidJefCanal() {
        return (String) getAttributeInternal(IDJEFCANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute idJefCanal.
     * @param value value to set the  idJefCanal
     */
    public void setidJefCanal(String value) {
        setAttributeInternal(IDJEFCANAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute jefCanal.
     * @return the jefCanal
     */
    public String getjefCanal() {
        return (String) getAttributeInternal(JEFCANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute jefCanal.
     * @param value value to set the  jefCanal
     */
    public void setjefCanal(String value) {
        setAttributeInternal(JEFCANAL, value);
    }

    /**
     * Gets the attribute value for the calculated attribute montoDesem.
     * @return the montoDesem
     */
    public String getmontoDesem() {
        return (String) getAttributeInternal(MONTODESEM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute montoDesem.
     * @param value value to set the  montoDesem
     */
    public void setmontoDesem(String value) {
        setAttributeInternal(MONTODESEM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute fec2daLlam.
     * @return the fec2daLlam
     */
    public Date getfec2daLlam() {
        return (Date) getAttributeInternal(FEC2DALLAM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute fec2daLlam.
     * @param value value to set the  fec2daLlam
     */
    public void setfec2daLlam(Date value) {
        setAttributeInternal(FEC2DALLAM, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> FeebackTipGestLOV1.
     */
    public RowSet getFeebackTipGestLOV1() {
        return (RowSet)getAttributeInternal(FEEBACKTIPGESTLOV1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> FeedbackGrupGestLov1.
     */
    public RowSet getFeedbackGrupGestLov1() {
        return (RowSet)getAttributeInternal(FEEDBACKGRUPGESTLOV1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> FeedbackDetGestLOV1.
     */
    public RowSet getFeedbackDetGestLOV1() {
        return (RowSet)getAttributeInternal(FEEDBACKDETGESTLOV1);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}