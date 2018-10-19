package utils;

import oracle.jbo.AttributeDef;
import oracle.jbo.AttributeList;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;

public class CustomEntityImpl extends EntityImpl {
    protected void create(AttributeList attributeList) {
        super.create(attributeList);
        for (AttributeDef def : getEntityDef().getAttributeDefs()) {
            String sequenceName = (String)def.getProperty("SequenceName");
            if (sequenceName != null) {
                SequenceImpl s =
                    new SequenceImpl(sequenceName, getDBTransaction());
                setAttribute(def.getIndex(), s.getSequenceNumber());
            }
        }
    }
    
    
}
