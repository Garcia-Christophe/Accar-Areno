-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 15 fév. 2023 à 13:30
-- Version du serveur : 10.5.12-MariaDB-0+deb11u1
-- Version de PHP : 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `zfm1-zgarciach_1`
--

-- --------------------------------------------------------

-- 
-- Drop tables
-- 

DROP TABLE IF EXISTS Artiste;
DROP TABLE IF EXISTS Billet;
DROP TABLE IF EXISTS Utilisateur;
DROP TABLE IF EXISTS Salle;
DROP TABLE IF EXISTS Soiree;
DROP TABLE IF EXISTS Concert;
DROP TABLE IF EXISTS Groupe;

--
-- Structure de la table `Artiste`
--

CREATE TABLE `Artiste` (
  `idArtiste` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `villeOrigine` varchar(50) DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `idGroupe` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Billet`
--

CREATE TABLE `Billet` (
  `idBillet` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `idSoiree` int(11) DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Concert`
--

CREATE TABLE `Concert` (
  `idConcert` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `heure` time DEFAULT NULL,
  `duree` time DEFAULT NULL,
  `idGroupe` int(11) DEFAULT NULL,
  `idSoiree` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Groupe`
--

CREATE TABLE `Groupe` (
  `idGroupe` int(11) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `nbArtistes` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Salle`
--

CREATE TABLE `Salle` (
  `idSalle` int(11) NOT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `adresse` varchar(50) DEFAULT NULL,
  `capacite` int(11) DEFAULT NULL,
  `nomGest` varchar(50) DEFAULT NULL,
  `prenomGest` varchar(50) DEFAULT NULL,
  `association` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Soiree`
--

CREATE TABLE `Soiree` (
  `idSoiree` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `idSalle` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `Utilisateur`
--

CREATE TABLE `Utilisateur` (
  `idUtilisateur` int(11) NOT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Artiste`
--
ALTER TABLE `Artiste`
  ADD PRIMARY KEY (`idArtiste`),
  ADD KEY `idGroupe` (`idGroupe`);

--
-- Index pour la table `Billet`
--
ALTER TABLE `Billet`
  ADD PRIMARY KEY (`idBillet`),
  ADD KEY `idSoiree` (`idSoiree`),
  ADD KEY `idUtilisateur` (`idUtilisateur`);

--
-- Index pour la table `Concert`
--
ALTER TABLE `Concert`
  ADD PRIMARY KEY (`idConcert`),
  ADD KEY `idGroupe` (`idGroupe`),
  ADD KEY `idSoiree` (`idSoiree`);

--
-- Index pour la table `Groupe`
--
ALTER TABLE `Groupe`
  ADD PRIMARY KEY (`idGroupe`);

--
-- Index pour la table `Salle`
--
ALTER TABLE `Salle`
  ADD PRIMARY KEY (`idSalle`);

--
-- Index pour la table `Soiree`
--
ALTER TABLE `Soiree`
  ADD PRIMARY KEY (`idSoiree`),
  ADD KEY `idSalle` (`idSalle`);

--
-- Index pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  ADD PRIMARY KEY (`idUtilisateur`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Artiste`
--
ALTER TABLE `Artiste`
  MODIFY `idArtiste` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `Billet`
--
ALTER TABLE `Billet`
  MODIFY `idBillet` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Concert`
--
ALTER TABLE `Concert`
  MODIFY `idConcert` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Groupe`
--
ALTER TABLE `Groupe`
  MODIFY `idGroupe` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Salle`
--
ALTER TABLE `Salle`
  MODIFY `idSalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Soiree`
--
ALTER TABLE `Soiree`
  MODIFY `idSoiree` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `Utilisateur`
--
ALTER TABLE `Utilisateur`
  MODIFY `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Billet`
--
ALTER TABLE `Billet`
  ADD CONSTRAINT `Billet_ibfk_1` FOREIGN KEY (`idSoiree`) REFERENCES `Soiree` (`idSoiree`),
  ADD CONSTRAINT `Billet_ibfk_2` FOREIGN KEY (`idUtilisateur`) REFERENCES `Utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `Concert`
--
ALTER TABLE `Concert`
  ADD CONSTRAINT `Concert_ibfk_1` FOREIGN KEY (`idGroupe`) REFERENCES `Groupe` (`idGroupe`),
  ADD CONSTRAINT `Concert_ibfk_2` FOREIGN KEY (`idSoiree`) REFERENCES `Soiree` (`idSoiree`);

--
-- Contraintes pour la table `Groupe`
--
ALTER TABLE `Artiste`
  ADD CONSTRAINT `Artiste_ibfk_1` FOREIGN KEY (`idGroupe`) REFERENCES `Groupe` (`idGroupe`);

--
-- Contraintes pour la table `Soiree`
--
ALTER TABLE `Soiree`
  ADD CONSTRAINT `Soiree_ibfk_1` FOREIGN KEY (`idSalle`) REFERENCES `Salle` (`idSalle`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
