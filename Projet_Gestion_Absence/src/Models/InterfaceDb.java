package Models;

import java.util.ArrayList;

public interface InterfaceDb {
   User authentification(String email, String password);
   ArrayList<Departement> getDepartement();
   ArrayList<Promo> getPromotion(int idDepartement);
   int inscription(User user);
   int cratePromo(Promo promo);
   ArrayList<Apprenant> getApprenant(int idPromo);
   int addAbsence(Presence presence);
   ArrayList<Presence> getListAbsence(int idApprenant);
   int justifierAbsence(int idPresence);
   ArrayList<Presence> getFichePresence(int idApprenant);
}