package eo;

import WsEjRep.ServiceBiPublisher;

import java.sql.SQLException;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;

import utils.CustomEntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sun Jul 23 15:27:07 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SbpSlsListEvXSupImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdSup {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getIdSup();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setIdSup((Number)value);
            }
        }
        ,
        NomSup {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getNomSup();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setNomSup((String)value);
            }
        }
        ,
        IdEjVenta {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getIdEjVenta();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setIdEjVenta((Number)value);
            }
        }
        ,
        NomEjVenta {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getNomEjVenta();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setNomEjVenta((String)value);
            }
        }
        ,
        Cantidad {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getCantidad();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setCantidad((Number)value);
            }
        }
        ,
        PorcQuitar {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getPorcQuitar();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setPorcQuitar((Number)value);
            }
        }
        ,
        IdTbl {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getIdTbl();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setIdTbl((Number)value);
            }
        }
        ,
        CantLeadGest {
            public Object get(SbpSlsListEvXSupImpl obj) {
                return obj.getCantLeadGest();
            }

            public void put(SbpSlsListEvXSupImpl obj, Object value) {
                obj.setCantLeadGest((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(SbpSlsListEvXSupImpl object);

        public abstract void put(SbpSlsListEvXSupImpl object, Object value);

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

    public static final int IDSUP = AttributesEnum.IdSup.index();
    public static final int NOMSUP = AttributesEnum.NomSup.index();
    public static final int IDEJVENTA = AttributesEnum.IdEjVenta.index();
    public static final int NOMEJVENTA = AttributesEnum.NomEjVenta.index();
    public static final int CANTIDAD = AttributesEnum.Cantidad.index();
    public static final int PORCQUITAR = AttributesEnum.PorcQuitar.index();
    public static final int IDTBL = AttributesEnum.IdTbl.index();
    public static final int CANTLEADGEST = AttributesEnum.CantLeadGest.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SbpSlsListEvXSupImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("eo.SbpSlsListEvXSup");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for IdSup, using the alias name IdSup.
     * @return the IdSup
     */
    public Number getIdSup() {
        return (Number)getAttributeInternal(IDSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdSup.
     * @param value value to set the IdSup
     */
    public void setIdSup(Number value) {
        setAttributeInternal(IDSUP, value);
    }

    /**
     * Gets the attribute value for NomSup, using the alias name NomSup.
     * @return the NomSup
     */
    public String getNomSup() {
        return (String)getAttributeInternal(NOMSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for NomSup.
     * @param value value to set the NomSup
     */
    public void setNomSup(String value) {
        setAttributeInternal(NOMSUP, value);
    }

    /**
     * Gets the attribute value for IdEjVenta, using the alias name IdEjVenta.
     * @return the IdEjVenta
     */
    public Number getIdEjVenta() {
        return (Number)getAttributeInternal(IDEJVENTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdEjVenta.
     * @param value value to set the IdEjVenta
     */
    public void setIdEjVenta(Number value) {
        setAttributeInternal(IDEJVENTA, value);
    }

    /**
     * Gets the attribute value for NomEjVenta, using the alias name NomEjVenta.
     * @return the NomEjVenta
     */
    public String getNomEjVenta() {
        return (String)getAttributeInternal(NOMEJVENTA);
    }

    /**
     * Sets <code>value</code> as the attribute value for NomEjVenta.
     * @param value value to set the NomEjVenta
     */
    public void setNomEjVenta(String value) {
        setAttributeInternal(NOMEJVENTA, value);
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
        
     /*   Number por = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        Double total = 0.0; 
        
        ServiceBiPublisher client = new ServiceBiPublisher();

           total = Double.parseDouble(client.obtenerCantOppDelete(getIdEjVenta().toString()));
        System.out.println("total" + total);
        cantidad = Double.parseDouble(value.toString());
        porcentaje = Math.round(((cantidad*100)/total)* 100.0) / 100.0;
        System.out.println("porcentaje" + porcentaje);

        try {
             por = new Number(porcentaje);
            
        } catch (SQLException e) {
        }
                
        setAttributeInternal(PORCQUITAR, por);*/
        setAttributeInternal(CANTIDAD, value);
    }

    /**
     * Gets the attribute value for PorcQuitar, using the alias name PorcQuitar.
     * @return the PorcQuitar
     */
    public Number getPorcQuitar() {
        return (Number)getAttributeInternal(PORCQUITAR);
    }

    /**
     * Sets <code>value</code> as the attribute value for PorcQuitar.
     * @param value value to set the PorcQuitar
     */
    public void setPorcQuitar(Number value) {
        
     /*   Number cant = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        Double total = 0.0; 
        
        ServiceBiPublisher client = new ServiceBiPublisher();

          total = Double.parseDouble(client.obtenerCantOppDelete(getIdEjVenta().toString()));
        
        porcentaje = Double.parseDouble(value.toString());
        cantidad = Math.round(((total*porcentaje)/100))*10.0/10.0;
        
        
        
        try {
             cant = new Number(cantidad);
            
        } catch (SQLException e) {
        }
        
        setAttributeInternal(CANTIDAD, cant);*/
        setAttributeInternal(PORCQUITAR, value);
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
     * Gets the attribute value for CantLeadGest, using the alias name CantLeadGest.
     * @return the CantLeadGest
     */
    public Number getCantLeadGest() {
        return (Number)getAttributeInternal(CANTLEADGEST);
    }

    /**
     * Sets <code>value</code> as the attribute value for CantLeadGest.
     * @param value value to set the CantLeadGest
     */
    public void setCantLeadGest(Number value) {
        setAttributeInternal(CANTLEADGEST, value);
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
