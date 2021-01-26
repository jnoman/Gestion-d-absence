package Dao;

import java.util.ArrayList;

import Models.Apprenant;
import Models.Departement;
import Models.Presence;
import Models.Promo;
import Models.User;

public interface InterfaceDb {
   User authentification(String email, String password);
   ArrayList<Departement> getDepartement();
   ArrayList<Promo> getPromotionsByDepartement(int idDepartement);
   Promo getPromotionsByForrmateur(int idFormateur);
   int inscription(User user);
   int cratePromo(Promo promo);
   ArrayList<Apprenant> getApprenant(int idPromo);
   int addAbsence(Presence presence);
   ArrayList<Presence> getListAbsence(int idApprenant);
   int justifierAbsence(int idPresence, boolean justifier);
   ArrayList<Presence> getFichePresence(int idApprenant, int month);
}