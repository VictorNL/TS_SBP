package vo;

import oracle.jbo.Row;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Sat May 27 19:44:38 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CpgOptyProdContAccFbImpl extends ViewObjectImpl {
    /**
     * This is the default constructor (do not remove).
     */
    public CpgOptyProdContAccFbImpl() {
    }
    
    protected void executeQueryForCollection(Object object, Object[] object2, int i) {
                //super.executeQueryForCollection(object, object2, i);
                //if(this.first()==null){
                    Row createRow = this.createRow();
                    this.insertRow(createRow);
                    this.setCurrentRow(createRow);
                //}
        }
}