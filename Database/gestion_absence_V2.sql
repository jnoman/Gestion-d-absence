-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Feb 04, 2021 at 03:31 PM
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

drop schema if exists gestion_absence;

create schema gestion_absence;

use gestion_absence;

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
(19, 'youcode safi'),
(20, 'youcode youssofiya');

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
  `justifier` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_Formateur` (`id_Formateur`),
  KEY `id_apprenant` (`id_apprenant`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `presence`
--

INSERT INTO `presence` (`id`, `id_apprenant`, `id_Formateur`, `absence`, `Date_absence`, `Duree`, `justifier`) VALUES
(44, 25, 24, 0, '2021-01-26', 0, NULL),
(45, 25, 24, 1, '2021-01-26', 420, NULL),
(46, 25, 24, 1, '2021-01-26', 420, NULL),
(47, 25, 24, 1, '2021-01-26', 180, 0),
(48, 25, 24, 1, '2021-01-26', 420, NULL),
(49, 25, 24, 1, '2021-01-27', 180, 1),
(50, 25, 24, 1, '2021-01-27', 20, 0),
(51, 25, 24, 1, '2021-01-27', 420, NULL),
(52, 25, 24, 0, '2021-01-30', 0, NULL),
(53, 25, 24, 1, '2021-01-30', 420, NULL),
(54, 25, 24, 1, '2021-01-30', 180, NULL),
(55, 25, 24, 1, '2021-01-30', 420, NULL),
(56, 25, 24, 1, '2021-01-30', 420, NULL),
(57, 25, 24, 1, '2021-01-30', 420, NULL),
(58, 25, 24, 1, '2021-01-30', 420, NULL),
(59, 25, 24, 1, '2021-01-30', 420, NULL),
(60, 25, 24, 1, '2021-01-30', 420, NULL),
(61, 25, 24, 1, '2021-01-31', 420, NULL),
(62, 25, 24, 1, '2021-01-31', 420, NULL),
(63, 25, 24, 1, '2021-01-31', 420, NULL),
(64, 26, 24, 1, '2021-01-31', 180, NULL),
(65, 25, 24, 1, '2021-01-31', 420, NULL),
(66, 26, 24, 1, '2021-01-31', 180, NULL),
(67, 26, 24, 1, '2021-02-01', 420, NULL),
(68, 25, 24, 1, '2021-02-01', 420, NULL),
(69, 26, 24, 1, '2021-02-01', 420, NULL),
(70, 26, 24, 1, '2021-02-01', 420, NULL),
(71, 25, 24, 1, '2021-02-01', 420, NULL),
(72, 25, 24, 1, '2021-02-01', 420, NULL),
(73, 26, 24, 1, '2021-02-01', 420, NULL),
(74, 26, 24, 1, '2021-02-01', 420, NULL),
(75, 25, 24, 1, '2021-02-01', 420, NULL),
(76, 26, 24, 1, '2021-02-01', 420, NULL),
(77, 26, 24, 1, '2021-02-01', 420, NULL),
(78, 25, 24, 1, '2021-02-01', 420, NULL),
(79, 26, 24, 1, '2021-02-01', 420, NULL),
(80, 26, 24, 1, '2021-02-01', 420, NULL),
(81, 25, 24, 1, '2021-02-01', 420, NULL),
(82, 26, 24, 1, '2021-02-01', 420, NULL),
(83, 25, 24, 1, '2021-02-01', 420, NULL),
(90, 26, 24, 1, '2021-02-04', 420, NULL),
(91, 25, 24, 1, '2021-02-04', 420, NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `promo`
--

INSERT INTO `promo` (`id_promo`, `nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`, `id_user_formateur`) VALUES
(20, 'ada lovels', '2021-01-10', '2021-01-22', 19, 22),
(21, 'mari', '2020-11-15', '2021-08-31', 19, 24),
(22, 'Ada Lovles', '2021-01-28', '2021-07-01', 19, 27);

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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `Email`, `Role`, `password`, `id_promo`, `id_Dep`) VALUES
(20, 'Youcode Administrate', 'youcode@gmail.com', 'admin', 'aaaaaaaa', NULL, NULL),
(21, 'koutar islis', 'koutar@gmail.com', 'secretaire', 'aaaaaaaa', NULL, NULL),
(22, 'jamal eddine noman', 'jamalnoman6@gmail.com', 'formateur', 'VzKRCwzO#3l$', NULL, NULL),
(23, 'fatima zahra jirari', 'fatima@gmail.com', 'secretaire', '58r2Eq%QBS2p', NULL, 19),
(24, 'hanae elwehabi', 'hanae@gmail.com', 'formateur', 'ThJ1D?8#rpGt', NULL, NULL),
(25, 'jamal eddine noman', 'jnoman@gmail.com', 'apprenant', 'MHYo#3c#N8UJ', 21, NULL),
(26, 'zakaria kamili', 'zkamili@gmail.com', 'apprenant', 'G9@6tc?1HTy6', 21, NULL),
(27, 'Ahmed Mohammed', 'Mohammed@gmail.com', 'formateur', 'BOAdNLxAIiNM', NULL, NULL),
(28, 'Reda Fettati', 'Fettati@gmail.com', 'apprenant', 'Qz!ota%7IC6?', 22, NULL);

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
