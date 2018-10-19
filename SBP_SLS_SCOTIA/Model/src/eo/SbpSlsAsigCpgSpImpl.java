package eo;

import WsEjRep.ServiceBiPublisher;

import java.sql.SQLException;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.TransactionEvent;

import utils.CustomEntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 25 10:53:20 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SbpSlsAsigCpgSpImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdUsuarioPrm {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getIdUsuarioPrm();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setIdUsuarioPrm((Number)value);
            }
        }
        ,
        NomUsuarioPrm {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getNomUsuarioPrm();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setNomUsuarioPrm((String)value);
            }
        }
        ,
        PorcAsignado {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getPorcAsignado();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setPorcAsignado((Number)value);
            }
        }
        ,
        Cantidad {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getCantidad();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setCantidad((Number)value);
            }
        }
        ,
        Total {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getTotal();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setTotal((Number)value);
            }
        }
        ,
        Asigid {
            public Object get(SbpSlsAsigCpgSpImpl obj) {
                return obj.getAsigid();
            }

            public void put(SbpSlsAsigCpgSpImpl obj, Object value) {
                obj.setAsigid((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(SbpSlsAsigCpgSpImpl object);

        public abstract void put(SbpSlsAsigCpgSpImpl object, Object value);

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


    public static final int IDUSUARIOPRM = AttributesEnum.IdUsuarioPrm.index();
    public static final int NOMUSUARIOPRM = AttributesEnum.NomUsuarioPrm.index();
    public static final int PORCASIGNADO = AttributesEnum.PorcAsignado.index();
    public static final int CANTIDAD = AttributesEnum.Cantidad.index();
    public static final int TOTAL = AttributesEnum.Total.index();
    public static final int ASIGID = AttributesEnum.Asigid.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SbpSlsAsigCpgSpImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("eo.SbpSlsAsigCpgSp");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for IdUsuarioPrm, using the alias name IdUsuarioPrm.
     * @return the IdUsuarioPrm
     */
    public Number getIdUsuarioPrm() {
        return (Number)getAttributeInternal(IDUSUARIOPRM);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdUsuarioPrm.
     * @param value value to set the IdUsuarioPrm
     */
    public void setIdUsuarioPrm(Number value) {
        setAttributeInternal(IDUSUARIOPRM, value);
    }

    /**
     * Gets the attribute value for NomUsuarioPrm, using the alias name NomUsuarioPrm.
     * @return the NomUsuarioPrm
     */
    public String getNomUsuarioPrm() {
        return (String)getAttributeInternal(NOMUSUARIOPRM);
    }

    /**
     * Sets <code>value</code> as the attribute value for NomUsuarioPrm.
     * @param value value to set the NomUsuarioPrm
     */
    public void setNomUsuarioPrm(String value) {
        setAttributeInternal(NOMUSUARIOPRM, value);
    }

    /**
     * Gets the attribute value for PorcAsignado, using the alias name PorcAsignado.
     * @return the PorcAsignado
     */
    public Number getPorcAsignado() {
        return (Number)getAttributeInternal(PORCASIGNADO);
    }

    /**
     * Sets <code>value</code> as the attribute value for PorcAsignado.
     * @param value value to set the PorcAsignado
     */
    public void setPorcAsignado(Number value) {
        
        Number cant = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        Double total = 0.0; 
        
        ServiceBiPublisher client = new ServiceBiPublisher();

     //   total = Double.parseDouble(client.obtenerCantOpp());
        //Double total =Double.parseDouble(getTotal().toString()); //valor estatico
        
        porcentaje = Double.parseDouble(value.toString());
        cantidad = Math.round(((total*porcentaje)/100))*10.0/10.0;
        
        
        
        try {
             cant = new Number(cantidad);
            
        } catch (SQLException e) {
        }
                
        setAttributeInternal(CANTIDAD, cant);
        setAttributeInternal(PORCASIGNADO, value);
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
       
        Number por = new Number(0);
        Double porcentaje =0.0;
        Double cantidad =0.0;
        //Double total =Double.parseDouble(getTotal().toString()); //valor estatico
        Double total = 0.0; 
        
        ServiceBiPublisher client = new ServiceBiPublisher();

     //   total = Double.parseDouble(client.obtenerCantOpp());
     
        cantidad = Double.parseDouble(value.toString());
        porcentaje = Math.round(((cantidad*100)/total)* 100.0) / 100.0;

        try {
             por = new Number(porcentaje);
            
        } catch (SQLException e) {
        }
                
        setAttributeInternal(CANTIDAD, value);
        setAttributeInternal(PORCASIGNADO, por);
        

    }


    /**
     * Gets the attribute value for Total, using the alias name Total.
     * @return the Total
     */
    public Number getTotal() {
        return (Number)getAttributeInternal(TOTAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for Total.
     * @param value value to set the Total
     */
    public void setTotal(Number value) {
        setAttributeInternal(TOTAL, value);
    }

    /**
     * Gets the attribute value for Asigid, using the alias name Asigid.
     * @return the Asigid
     */
    public Number getAsigid() {
        return (Number)getAttributeInternal(ASIGID);
    }

    /**
     * Sets <code>value</code> as the attribute value for Asigid.
     * @param value value to set the Asigid
     */
    public void setAsigid(Number value) {
        setAttributeInternal(ASIGID, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index, AttributeDefImpl attrDef) throws Exception {
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
    protected void setAttrInvokeAccessor(int index, Object value, AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }


    /**
     * @param asigid key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number asigid) {
        return new Key(new Object[]{asigid});
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