package eo;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.TransactionEvent;

import utils.CustomEntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Oct 25 11:30:34 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SbpSlsLovCpgFunnelImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        IdCpg {
            public Object get(SbpSlsLovCpgFunnelImpl obj) {
                return obj.getIdCpg();
            }

            public void put(SbpSlsLovCpgFunnelImpl obj, Object value) {
                obj.setIdCpg((String)value);
            }
        }
        ,
        NomCpg {
            public Object get(SbpSlsLovCpgFunnelImpl obj) {
                return obj.getNomCpg();
            }

            public void put(SbpSlsLovCpgFunnelImpl obj, Object value) {
                obj.setNomCpg((String)value);
            }
        }
        ,
        IdTbl {
            public Object get(SbpSlsLovCpgFunnelImpl obj) {
                return obj.getIdTbl();
            }

            public void put(SbpSlsLovCpgFunnelImpl obj, Object value) {
                obj.setIdTbl((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(SbpSlsLovCpgFunnelImpl object);

        public abstract void put(SbpSlsLovCpgFunnelImpl object, Object value);

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
    public static final int IDCPG = AttributesEnum.IdCpg.index();
    public static final int NOMCPG = AttributesEnum.NomCpg.index();
    public static final int IDTBL = AttributesEnum.IdTbl.index();

    /**
     * This is the default constructor (do not remove).
     */
    public SbpSlsLovCpgFunnelImpl() {
    }

    /**
     * Gets the attribute value for IdCpg, using the alias name IdCpg.
     * @return the IdCpg
     */
    public String getIdCpg() {
        return (String)getAttributeInternal(IDCPG);
    }

    /**
     * Sets <code>value</code> as the attribute value for IdCpg.
     * @param value value to set the IdCpg
     */
    public void setIdCpg(String value) {
        setAttributeInternal(IDCPG, value);
    }

    /**
     * Gets the attribute value for NomCpg, using the alias name NomCpg.
     * @return the NomCpg
     */
    public String getNomCpg() {
        return (String)getAttributeInternal(NOMCPG);
    }

    /**
     * Sets <code>value</code> as the attribute value for NomCpg.
     * @param value value to set the NomCpg
     */
    public void setNomCpg(String value) {
        setAttributeInternal(NOMCPG, value);
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
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("eo.SbpSlsLovCpgFunnel");
        }
        return mDefinitionObject;
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