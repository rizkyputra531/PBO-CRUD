/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rizkyapk;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import rizkyapk.controller.PramukaJpaController;
import rizkyapk.models.Pramuka;

/**
 *
 * @RIZKY SYAHPUTRA
 */
public class RizkyApk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntityManagerFactory emf=Persistence.createEntityManagerFactory("RizkyApkPU");
        Pramuka agt = new Pramuka();
        PramukaJpaController agtctrl = new PramukaJpaController(emf);
        /**
        melakukan inputan untuk database
        */
        
        agt.setNta("123");
        agt.setNama("Alfia Sholawati");
        agt.setGudep("16.04");
        agt.setGolongan("Pandega");
        try {
            agtctrl.create(agt);
        } catch (Exception ex) {
            Logger.getLogger(RizkyApk.class.getName()).log(Level.SERVE, null, ex);
        }
    }
    
}
