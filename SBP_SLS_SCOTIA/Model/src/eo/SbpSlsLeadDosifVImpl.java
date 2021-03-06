package eo;

import oracle.adf.share.ADFContext;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jun 06 13:31:43 COT 2018
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SbpSlsLeadDosifVImpl extends EntityImpl {


    /**
     * This is the default constructor (do not remove).
     */
    public SbpSlsLeadDosifVImpl() {
    }


    /**
     * @param idEjecutivo key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number idEjecutivo) {
        return new Key(new Object[]{idEjecutivo});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("eo.SbpSlsLeadDosifV");
    }


}
