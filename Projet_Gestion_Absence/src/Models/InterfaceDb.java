package Models;

import java.sql.Connection;
import java.util.ArrayList;


public interface InterfaceDb {
   int authentification();
   ArrayList<Departement> getDepartement();
   ArrayList<Promo> getPromotion();
   User inscription();
   ArrayList<Apprenant> getApprenant();
   int addAbsence();
   ArrayList<Presence> getListAbsence();
   int justifierAbsence();
   ArrayList<Presence> getFichePresence();
}