package eo;

import java.sql.SQLException;

import oracle.adf.share.ADFContext;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;

import utils.CustomEntityImpl;
import utils.FormatNumber;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 26 21:06:46 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SbpSlsCanalImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdCanal {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getIdCanal();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setIdCanal((Number)value);
            }
        }
        ,
        NomCanal {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getNomCanal();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setNomCanal((String)value);
            }
        }
        ,
        Cantidad {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getCantidad();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setCantidad((Number)value);
            }
        }
        ,
        IdTbl {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getIdTbl();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setIdTbl((Number)value);
            }
        }
        ,
        PorcAsig {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getPorcAsig();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setPorcAsig((Number)value);
            }
        }
        ,
        PartyNumber {
            public Object get(SbpSlsCanalImpl obj) {
                return obj.getPartyNumber();
            }

            public void put(SbpSlsCanalImpl obj, Object value) {
                obj.setPartyNumber((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(SbpSlsCanalImpl object);

        public abstract void put(SbpSlsCanalImpl object, Object value);

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

    public static final int IDCANAL = AttributesEnum.IdCanal.index();
    public static final int NOMCANAL = AttributesEnum.NomCanal.index();
    public static final int CANTIDAD = AttributesEnum.Cantidad.index();
    public static final int IDTBL = AttributesEnum.IdTbl.index();
    public static final int PORCASIG = AttributesEnum.PorcAsig.index();
    public static final int PARTYNUMBER = AttributesEnum.PartyNumber.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SbpSlsCanalImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("eo.SbpSlsCanal");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for IdCanal, using the alias name IdCanal.
     * @return the IdCanal
     */
    public Number getIdCanal() {
        return (Number)getAttributeInternal(IDCANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdCanal.
     * @param value value to set the IdCanal
     */
    public void setIdCanal(Number value) {
        setAttributeInternal(IDCANAL, value);
    }

    /**
     * Gets the attribute value for NomCanal, using the alias name NomCanal.
     * @return the NomCanal
     */
    public String getNomCanal() {
        return (String)getAttributeInternal(NOMCANAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for NomCanal.
     * @param value value to set the NomCanal
     */
    public void setNomCanal(String value) {
        setAttributeInternal(NOMCANAL, value);
    }

    /**
     * Gets the attribute value for Cantidad, using the alias name Cantidad.
     * @return the Cantidad
     */
    public Number getCantidad() {
        return (Number)getAttributeInternal(CANTIDAD);
    }
    

    /**
     * Sets <code>value</code> as the attribute value for Cantidad.
     * @param value value to set the Cantidad
     */
    public void setCantidad(Number value) {
        ADFContext ctx = ADFContext.getCurrent();
        
        Number por = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        Double total = 0.0; 

        String totalAsignado =
            ctx.getSessionScope().get("CaNtiDaD") == null ? "" : ctx.getSessionScope().get("CaNtiDaD").toString();
        

        total = Double.parseDouble(totalAsignado);
        
        if(total != 0){        
            System.out.println(value + "value");
        cantidad = Double.parseDouble(value.toString());
        porcentaje = Math.floor(((cantidad*100)/total)* 100.0) / 100.0;        

        try {
             por = new Number(porcentaje);
            
        } catch (SQLException e) {
        }
        }
                
        setAttributeInternal(CANTIDAD, value);
        setAttributeInternal(PORCASIG, por);
    }

    /**
     * Gets the attribute value for IdTbl, using the alias name IdTbl.
     * @return the IdTbl
     */
    public Number getIdTbl() {
        return (Number)getAttributeInternal(IDTBL);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdTbl.
     * @param value value to set the IdTbl
     */
    public void setIdTbl(Number value) {
        setAttributeInternal(IDTBL, value);
    }

    /**
     * Gets the attribute value for PorcAsig, using the alias name PorcAsig.
     * @return the PorcAsig
     */
    public Number getPorcAsig() {
        return (Number)getAttributeInternal(PORCASIG);
    }

    /**
     * Sets <code>value</code> as the attribute value for PorcAsig.
     * @param value value to set the PorcAsig
     */
    public void setPorcAsig(Number value) {
        ADFContext ctx = ADFContext.getCurrent();
          
        Number cant = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        Double total = 0.0; 
        
        String totalAsignado =
            ctx.getSessionScope().get("CaNtiDaD") == null ? "" : ctx.getSessionScope().get("CaNtiDaD").toString();
        
        total = Double.parseDouble(totalAsignado);
        
        porcentaje = Double.parseDouble(value.toString());
        cantidad = Math.floor(((total*porcentaje)/100))*10.0/10.0;
        
        try {
             cant = new Number(cantidad);
            
        } catch (SQLException e) {
        }
                
        setAttributeInternal(CANTIDAD, cant);
        setAttributeInternal(PORCASIG, value);
    }

    /**
     * Gets the attribute value for PartyNumber, using the alias name PartyNumber.
     * @return the PartyNumber
     */
    public String getPartyNumber() {
        return (String)getAttributeInternal(PARTYNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for PartyNumber.
     * @param value value to set the PartyNumber
     */
    public void setPartyNumber(String value) {
        setAttributeInternal(PARTYNUMBER, value);
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


    /**
     * @param idTbl key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number idTbl) {
        return new Key(new Object[]{idTbl});
    }

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        super.doDML(operation, e);
    }
}
