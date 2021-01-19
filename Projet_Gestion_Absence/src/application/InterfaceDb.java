package application;

import java.sql.Connection;

public interface InterfaceDb {
   Connection getConnection();
   int authentification();
   void getDepartement();
   void getPromotion();
   User inscription();
   void getApprenant();
   int addAbsence();
   int getListAbsence();
   int justifierAbsence();
   void getFichePresence();
}