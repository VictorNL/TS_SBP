package vo.client;

import oracle.jbo.client.remote.ViewUsageImpl;

import vo.common.EvoLpSctView;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 14 17:24:31 COT 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class EvoLpSctViewClient extends ViewUsageImpl implements EvoLpSctView {
    /**
     * This is the default constructor (do not remove).
     */
    public EvoLpSctViewClient() {
    }

    public void url(String cpgId) {
        Object _ret =
            getApplicationModuleProxy().riInvokeExportedMethod(this,"url",new String [] {"java.lang.String"},new Object[] {cpgId});
        return;
    }
}
