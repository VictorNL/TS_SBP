package project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

import org.apache.commons.dbutils.DbUtils;

@WebService
public class Prueba2 {
    public Prueba2() {
        super();
    }

    @WebMethod
    public String hola(String mensaje) {

        String hello = mensaje + "2";

        return hello;
    }

    @WebMethod
    public String insertTablaWs(String idLead, String nombreLead) {

        PreparedStatement ps = null;
        sbp.utils.Connection cnx = new sbp.utils.Connection();
        Connection conn = null;

        try {
            String query =
                "insert into sbp_sls_prueba_WS(codigo_lead, nombre_lead) values(?,?)";
            System.out.println(query + "query");
            conn = cnx.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, idLead);
            ps.setString(2, nombreLead);
            ps.execute();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }

        return "ok";
    }

    public String insertTablaSpeechWs(String codigospeech, String fecnaccli,
                                      String telfllam, String celular,
                                      String telfcasa, String email,
                                      String telfref, String codparentesco,
                                      String descparentesco,
                                      String codestcivil, String descestcivil,
                                      String codgenero, String descgenero,
                                      String appatcony, String apmatcont,
                                      String nomcony, String nom2cony,
                                      String fecnaccony, String fecingcentlab,
                                      String doccony, String codgencony,
                                      String descgencony, String direccion,
                                      String dept, String prov,
                                      String distrito, String refdir,
                                      String codcondlab, String condlabdesc,
                                      String nomcentlab, String ruccentlab,
                                      String dircentlab, String deptcentlab,
                                      String provcentlab, String distcentlab,
                                      String refcentlab, String telfcentlab,
                                      String anexocentlab,
                                      String centlabcodcar,
                                      String centlabdesccar,
                                      String lugenttarjdep,
                                      String lugenttarjdepcod,
                                      String lugenttarjindepdesc,
                                      String lugenttarjindepcod,
                                      String valormemb,
                                      String valordesgravamen,
                                      String ciclofact, String envioestcta,
                                      String flagaut, String flagdispefect) {

        PreparedStatement ps = null;
        sbp.utils.Connection cnx = new sbp.utils.Connection();
        Connection conn = null;

        try {
            String query = "insert into sbp_sls_prueba_WS\n" +
                "  (codigo_speech,\n" +
                "   fec_nac_cli,\n" +
                "   telf_llam,\n" +
                "   celular,\n" +
                "   telf_casa,\n" +
                "   email,\n" +
                "   telf_ref,\n" +
                "   cod_parentesco,\n" +
                "   desc_parentesco,\n" +
                "   cod_est_civil,\n" +
                "   desc_est_civil,\n" +
                "   cod_genero,\n" +
                "   desc_genero,\n" +
                "   ap_pat_cony,\n" +
                "   ap_mat_cont,\n" +
                "   nom_cony,\n" +
                "   nom2_cony,\n" +
                "   fec_nac_cony,\n" +
                "   fec_ing_cent_lab,\n" +
                "   doc_cony,\n" +
                "   cod_gen_cony,\n" +
                "   desc_gen_cony,\n" +
                "   direccion,\n" +
                "   dept,\n" +
                "   prov,\n" +
                "   distrito,\n" +
                "   ref_dir,\n" +
                "   cod_cond_lab,\n" +
                "   cond_lab_desc,\n" +
                "   nom_cent_lab,\n" +
                "   ruc_cent_lab,\n" +
                "   dir_cent_lab,\n" +
                "   dept_cent_lab,\n" +
                "   prov_cent_lab,\n" +
                "   dist_cent_lab,\n" +
                "   ref_cent_lab,\n" +
                "   telf_cent_lab,\n" +
                "   anexo_cent_lab,\n" +
                "   cent_lab_cod_car,\n" +
                "   cent_lab_desc_car,\n" +
                "   lug_ent_tarj_dep,\n" +
                "   lug_ent_tarj_dep_cod,\n" +
                "   lug_ent_tarj_indep_desc,\n" +
                "   lug_ent_tarj_indep_cod,\n" +
                "   valor_memb,\n" +
                "   valor_desgravamen,\n" +
                "   ciclo_fact,\n" +
                "   envio_est_cta,\n" +
                "   flag_aut,\n" +
                "   flag_disp_efect)\n" +
                "values\n" +
                "  (?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?,   ?)";
            
            conn = cnx.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, codigospeech);
            ps.setString(2, fecnaccli);
            ps.setString(3, telfllam);
            ps.setString(4, celular);
            ps.setString(5, telfcasa);
            ps.setString(6, email);
            ps.setString(7, telfref);
            ps.setString(8, codparentesco);
            ps.setString(9, descparentesco);
            ps.setString(10, codestcivil);
            ps.setString(11, descestcivil);
            ps.setString(12, codgenero);
            ps.setString(13, descgenero);
            ps.setString(14, appatcony);
            ps.setString(15, apmatcont);
            ps.setString(16, nomcony);
            ps.setString(17, nom2cony);
            ps.setString(18, fecnaccony);
            ps.setString(19, fecingcentlab);
            ps.setString(20, doccony);
            ps.setString(21, codgencony);
            ps.setString(22, descgencony);
            ps.setString(23, direccion);
            ps.setString(24, dept);
            ps.setString(25, prov);
            ps.setString(26, distrito);
            ps.setString(27, refdir);
            ps.setString(28, codcondlab);
            ps.setString(29, condlabdesc);
            ps.setString(30, nomcentlab);
            ps.setString(31, ruccentlab);
            ps.setString(32, dircentlab);
            ps.setString(33, deptcentlab);
            ps.setString(34, provcentlab);
            ps.setString(35, distcentlab);
            ps.setString(36, refcentlab);
            ps.setString(37, telfcentlab);
            ps.setString(38, anexocentlab);
            ps.setString(39, centlabcodcar);
            ps.setString(40, centlabdesccar);
            ps.setString(41, lugenttarjdep);
            ps.setString(42, lugenttarjdepcod);
            ps.setString(43, lugenttarjindepdesc);
            ps.setString(44, lugenttarjindepcod);
            ps.setString(45, valormemb);
            ps.setString(46, valordesgravamen);
            ps.setString(47, ciclofact);
            ps.setString(48, envioestcta);
            ps.setString(49, flagaut);
            ps.setString(50, flagdispefect);
            System.out.println(query + "query");
            ps.execute();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            DbUtils.closeQuietly(ps);
            DbUtils.closeQuietly(conn);
        }

        return "ok";
    }
}
