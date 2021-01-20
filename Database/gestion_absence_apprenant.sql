-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 20, 2021 at 09:48 PM
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
(19, 'SAFI'),
(20, 'YOUSOUFIA');

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
  `Date_absence` date NOT NULL,
  `Duree` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Formateur` (`id_Formateur`),
  KEY `id_apprenant` (`id_apprenant`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

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
  `id_Dep` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_promo`),
  KEY `Constraint_id_dep` (`id_Dep`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`id_promo`, `nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`) VALUES
(19, 'Ada Lovelace', '2021-01-01', '2021-05-01', 19);

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
  `id_promo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Email` (`Email`),
  KEY `user_ibfk_2` (`id_promo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `Email`, `Role`, `password`, `id_promo`) VALUES
(19, 'Elbettal Meryam', 'Elbettal@gmail.com', 'Apprenant', 'azerty', 19),
(20, 'Zakaria kamili', 'kamili@gmail.com', 'Apprenant', 'azerty', 19),
(21, 'Noaman Jamal', 'noaman@gmail.com', 'Apprenant', 'azerty', 19);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `Constraint_id_dep` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_dep`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`id_promo`) REFERENCES `promo` (`id_promo`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
