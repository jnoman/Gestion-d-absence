-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 25, 2021 at 10:19 AM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gestion_absence`
--

-- --------------------------------------------------------

--
-- Table structure for table `departement`
--

DROP TABLE IF EXISTS `departement`;
CREATE TABLE IF NOT EXISTS `departement` (
  `id_dep` int(10) NOT NULL AUTO_INCREMENT,
  `nom_dep` varchar(20) NOT NULL,
  PRIMARY KEY (`id_dep`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `departement`
--

INSERT INTO `departement` (`id_dep`, `nom_dep`) VALUES
(19, 'Safi'),
(20, 'Casablanca');

-- --------------------------------------------------------

--
-- Table structure for table `presence`
--

DROP TABLE IF EXISTS `presence`;
CREATE TABLE IF NOT EXISTS `presence` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_apprenant` int(11) NOT NULL,
  `id_Formateur` int(11) NOT NULL,
  `absence` tinyint(1) DEFAULT NULL,
  `Date_absence` varchar(30) NOT NULL,
  `Duree` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Formateur` (`id_Formateur`),
  KEY `id_apprenant` (`id_apprenant`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `presence`
--

INSERT INTO `presence` (`id`, `id_apprenant`, `id_Formateur`, `absence`, `Date_absence`, `Duree`) VALUES
(23, 20, 21, 1, '2021-01-01', 0),
(24, 20, 21, 1, '2021-01-02', 0),
(25, 20, 21, 1, '2021-01-22', 0),
(26, 20, 21, 1, '2021-01-29', 0),
(27, 20, 21, 1, '2021-01-29', 7),
(28, 20, 21, 1, '25/01/2021', 420),
(29, 20, 21, 1, '25/01/2021', 420),
(30, 20, 21, 1, '25/01/2021', 420),
(31, 20, 21, 1, '2020-12-29', 0),
(32, 20, 21, 1, '25/01/2021', 420),
(33, 20, 21, 1, '2021-01-01', 420),
(34, 20, 21, 1, '2020-12-31', 420),
(35, 20, 21, 1, '2020-12-31', 420),
(36, 20, 21, 1, '2020-12-31', 420),
(37, 20, 21, 1, '2021-01-01', 420),
(38, 20, 21, 1, '25/01/2021', 420);

-- --------------------------------------------------------

--
-- Table structure for table `promo`
--

DROP TABLE IF EXISTS `promo`;
CREATE TABLE IF NOT EXISTS `promo` (
  `id_promo` int(10) NOT NULL AUTO_INCREMENT,
  `nom_promo` varchar(20) NOT NULL,
  `Date_debut_scolaire` date DEFAULT NULL,
  `Date_Fin_scolaire` date DEFAULT NULL,
  `id_Dep` int(10) DEFAULT NULL,
  `id_user_formateur` int(10) DEFAULT NULL,
  PRIMARY KEY (`id_promo`),
  KEY `Promo_ibfk_1` (`id_user_formateur`),
  KEY `Promo_ibfk_2` (`id_Dep`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`id_promo`, `nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`, `id_user_formateur`) VALUES
(19, 'Adaa Lovles', '2021-01-01', '2021-06-01', 19, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nom_complet` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Role` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  `id_promo` int(10) DEFAULT NULL,
  `id_Dep` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Email` (`Email`),
  KEY `user_ibfk_1` (`id_promo`),
  KEY `user_ibfk_2` (`id_Dep`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `Email`, `Role`, `password`, `id_promo`, `id_Dep`) VALUES
(20, 'Zakaria kamili', 'zakariakamili97@gmail.com', 'Apprenant', 'azerty', 19, 19),
(21, 'Youness Echadli', 'Echadli@gmail.com', 'Formateur', 'azerty', 19, 19);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`id_Formateur`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `presence_ibfk_2` FOREIGN KEY (`id_apprenant`) REFERENCES `user` (`id`);

--
-- Constraints for table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `Promo_ibfk_1` FOREIGN KEY (`id_user_formateur`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `Promo_ibfk_2` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_dep`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_promo`) REFERENCES `promo` (`id_promo`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_dep`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
