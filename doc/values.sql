--
-- Déchargement des données de la table `Groupe`
--

INSERT INTO `Groupe` (`idGroupe`, `nom`, `nbArtistes`) VALUES
(1, 'The Beatles', 2),
(2, 'Groupe 2', 1);

--
-- Déchargement des données de la table `Salle`
--

INSERT INTO `Salle` (`idSalle`, `nom`, `adresse`, `capacite`, `nomGest`, `prenomGest`, `association`) VALUES
(1, 'Carnegie Hall', '881 7th Ave, New York, NY', 5002, 'John', 'Doe', 'Carnegie Hall Corporation');

--
-- Déchargement des données de la table `Soiree`
--

INSERT INTO `Soiree` (`idSoiree`, `nom`, `idSalle`) VALUES
(1, 'Classical Concert', 1);

--
-- Déchargement des données de la table `Concert`
--

INSERT INTO `Concert` (`idConcert`, `date`, `heure`, `duree`, `idGroupe`, `idSoiree`) VALUES
(1, '2023-02-20', '19:00:00', '02:00:00', 1, 1);

--
-- Déchargement des données de la table `Utilisateur`
--

INSERT INTO `Utilisateur` (`idUtilisateur`, `mdp`, `nom`) VALUES
(1, 'password123', 'Alice'),
(4, '123654', 'alexia');

--
-- Déchargement des données de la table `Billet`
--

INSERT INTO `Billet` (`idBillet`, `prix`, `idSoiree`, `idUtilisateur`) VALUES
(1, 50, 1, 1);

--
-- Déchargement des données de la table `Artiste`
--

INSERT INTO `Artiste` (`idArtiste`, `nom`, `villeOrigine`, `dateNaissance`, `idGroupe`) VALUES
(1, 'Bob Dylan', 'Duluth, Minnesota', '1941-05-24', 1),
(2, 'Sorin', 'Cholet, Maine et Loire', '2001-04-02', 1),
(3, 'Garcia', 'Hyères, Var', '2001-11-07', 2);
