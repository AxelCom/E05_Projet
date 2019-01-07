CREATE SEQUENCE seqProduit;
CREATE TABLE PRODUITS
(
	idProduit int NOT NULL,
	nomProduit varchar(50) UNIQUE,
	prixHT float,
	qte int,
	CONSTRAINT pk_Produits PRIMARY KEY (idProduit)
)


INSERT INTO PRODUITS (idProduit,nomProduit,prixHT,qte) VALUES (seqProduit.nextval,'Mars',1.60,100),
INSERT INTO PRODUITS (idProduit,nomProduit,prixHT,qte) VALUES (seqProduit.nextval,'Maltesers',2,80),
INSERT INTO PRODUITS (idProduit,nomProduit,prixHT,qte) VALUES (seqProduit.nextval,'Bounty',1.70,120),
INSERT INTO PRODUITS (idProduit,nomProduit,prixHT,qte) VALUES (seqProduit.nextval,'Twix',1.20,50),