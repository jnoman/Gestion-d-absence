-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 26 jan. 2021 à 12:32
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_absence`
--

drop schema if exists gestion_absence;

create schema gestion_absence;

use gestion_absence;

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `id_dep` int(10) NOT NULL,
  `nom_dep` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id_dep`, `nom_dep`) VALUES
(19, 'youcode safi'),
(20, 'youcode youssofiya');

-- --------------------------------------------------------

--
-- Structure de la table `presence`
--

CREATE TABLE `presence` (
  `id` int(10) NOT NULL,
  `id_apprenant` int(11) NOT NULL,
  `id_Formateur` int(11) NOT NULL,
  `absence` tinyint(1) DEFAULT NULL,
  `Date_absence` varchar(30) NOT NULL,
  `Duree` int(11) DEFAULT NULL,
  `justifier` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `presence`
--

INSERT INTO `presence` (`id`, `id_apprenant`, `id_Formateur`, `absence`, `Date_absence`, `Duree`, `justifier`) VALUES
(27, 25, 21, 1, '2021-01-29', 7, NULL),
(28, 25, 21, 1, '25/01/2021', 420, NULL),
(29, 25, 21, 1, '25/01/2021', 420, NULL),
(36, 20, 21, 1, '2020-12-31', 420, NULL),
(37, 20, 21, 1, '2021-01-01', 420, NULL),
(38, 20, 21, 1, '25/01/2021', 420, NULL),
(39, 25, 24, 1, '26/01/2021', 180, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `promo`
--

CREATE TABLE `promo` (
  `id_promo` int(10) NOT NULL,
  `nom_promo` varchar(20) NOT NULL,
  `Date_debut_scolaire` date DEFAULT NULL,
  `Date_Fin_scolaire` date DEFAULT NULL,
  `id_Dep` int(10) DEFAULT NULL,
  `id_user_formateur` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `promo`
--

INSERT INTO `promo` (`id_promo`, `nom_promo`, `Date_debut_scolaire`, `Date_Fin_scolaire`, `id_Dep`, `id_user_formateur`) VALUES
(20, 'ada lovels', '2021-01-10', '2021-01-22', 19, 22),
(21, 'mari', '2020-11-15', '2021-08-31', 19, 24);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `nom_complet` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Role` varchar(40) NOT NULL,
  `password` varchar(30) NOT NULL,
  `id_promo` int(10) DEFAULT NULL,
  `id_Dep` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom_complet`, `Email`, `Role`, `password`, `id_promo`, `id_Dep`) VALUES
(20, 'Youcode Administrate', 'youcode@gmail.com', 'admin', 'aaaaaaaa', NULL, NULL),
(21, 'koutar islis', 'koutar@gmail.com', 'secretaire', 'aaaaaaaa', NULL, NULL),
(22, 'jamal eddine noman', 'jamalnoman6@gmail.com', 'formateur', 'VzKRCwzO#3l$', NULL, NULL),
(23, 'fatima zahra jirari', 'fatima@gmail.com', 'secretaire', '58r2Eq%QBS2p', NULL, 19),
(24, 'hanae elwehabi', 'hanae@gmail.com', 'formateur', 'ThJ1D?8#rpGt', NULL, NULL),
(25, 'jamal eddine noman', 'jnoman@gmail.com', 'apprenant', 'MHYo#3c#N8UJ', 21, NULL),
(26, 'zakaria kamili', 'zkamili@gmail.com', 'apprenant', 'G9@6tc?1HTy6', 20, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id_dep`);

--
-- Index pour la table `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_Formateur` (`id_Formateur`),
  ADD KEY `id_apprenant` (`id_apprenant`);

--
-- Index pour la table `promo`
--
ALTER TABLE `promo`
  ADD PRIMARY KEY (`id_promo`),
  ADD KEY `Promo_ibfk_1` (`id_user_formateur`),
  ADD KEY `Promo_ibfk_2` (`id_Dep`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD KEY `user_ibfk_1` (`id_promo`),
  ADD KEY `user_ibfk_2` (`id_Dep`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `departement`
--
ALTER TABLE `departement`
  MODIFY `id_dep` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT pour la table `presence`
--
ALTER TABLE `presence`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT pour la table `promo`
--
ALTER TABLE `promo`
  MODIFY `id_promo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`id_Formateur`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `presence_ibfk_2` FOREIGN KEY (`id_apprenant`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `promo`
--
ALTER TABLE `promo`
  ADD CONSTRAINT `Promo_ibfk_1` FOREIGN KEY (`id_user_formateur`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `Promo_ibfk_2` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_dep`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`id_promo`) REFERENCES `promo` (`id_promo`),
  ADD CONSTRAINT `user_ibfk_2` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_dep`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
