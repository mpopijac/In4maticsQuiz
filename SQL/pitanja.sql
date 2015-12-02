
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Računalo: localhost
-- Vrijeme generiranja: Pro 02, 2015 u 07:58 PM
-- Verzija poslužitelja: 10.0.20-MariaDB
-- PHP verzija: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza podataka: `u904180370_inf`
--

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `pitanja`
--

CREATE TABLE IF NOT EXISTS `pitanja` (
  `IDpitanja` int(11) NOT NULL AUTO_INCREMENT,
  `pitanje` text COLLATE utf8_unicode_ci NOT NULL,
  `IDpoglavlje` int(11) NOT NULL,
  `IDrazred` int(11) NOT NULL,
  PRIMARY KEY (`IDpitanja`),
  KEY `fk_pitanja_poglavlje1_idx` (`IDpoglavlje`),
  KEY `fk_pitanja_razred1_idx` (`IDrazred`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=37 ;

--
-- Izbacivanje podataka za tablicu `pitanja`
--

INSERT INTO `pitanja` (`IDpitanja`, `pitanje`, `IDpoglavlje`, `IDrazred`) VALUES
(1, 'Što koristimo za brisanje teksta?', 1, 1),
(2, 'Dokument u Wordu 2007 i noviji prema unaprijed zadanom ima nastavak .doc ?', 1, 1),
(3, 'Koliko vrsta poravnanja teksta postoji u MS Wordu?', 1, 1),
(4, 'Zadani pomak tabulatora u Wordu je 1,25 cm.', 1, 1),
(5, 'Kakvo može biti usmjerenje stranice u Wordu?', 1, 1),
(6, 'U Wordu ne postoji mogućnost izrade grafikona.', 1, 1),
(7, 'Na vrhu prozora dokumenta nalazi se dio koji se sastoji od kartica, a nazivamo ga:', 1, 1),
(8, 'Datoteka nastala u Wordu naziva se:', 1, 1),
(9, 'Što je WordArt?', 1, 1),
(10, 'Razmak između redaka u dokumentu zovemo: ', 1, 1),
(11, 'Razina oblikovanja dokumenta nije:', 1, 1),
(12, 'Broj stranice u Wordu može biti određen slovom.', 1, 1),
(13, 'Excel je program za:', 2, 3),
(14, 'Kojim znakom započinje pisanje formule u Excelu?', 2, 3),
(15, 'Prema zadanim postavkama Excel datum poravnava:', 2, 3),
(16, 'Označavanje zasebnih stupaca (na preskok) radimo tako da:', 2, 3),
(17, 'Koji znak ispred oznake retka i oznake stupca sadrži apsolutna adresa?', 2, 3),
(18, 'Zaglavlje je prazan prostor između ruba papira i ispisanih podataka  na dnu stranice.', 2, 3),
(19, 'Prema unaprijed zadanim postavkama koliko radnih listova ima radna knjiga:', 2, 3),
(20, 'Funkcija za zaokruživanje brojeva je:', 2, 3),
(21, 'U formuli za zbrajanje možemo koristiti samo dva argumenta:', 2, 3),
(22, 'Grafikon koristimo za grafički prikaz i usporedbu podataka obrađenih u proračunskim tablicama.', 2, 3),
(23, 'Formula =AVERAGE(C1;D15) izračunava:', 2, 3),
(24, 'Tablica (Excel) je niz redaka i stupaca koji sadrže međusobno povezane podatke. ', 2, 3),
(25, 'Koji od navedenih NIJE kriterij za odabir polja u upitu u Accessu 2010?', 3, 4),
(26, 'Kako se u Accessu 2010 nazivaju objekti za formatiranje, izračun i ispis (prikaz) izabranih podataka? (1 riječ)', 3, 4),
(27, 'Redundancija (zalihost) je:', 3, 4),
(28, 'Polje ili više polja kojima se može jednoznačno definirati redak tablice naziva se:', 3, 4),
(29, 'Što nije jedna od osnovnih karakteristika tablice (relacije):', 3, 4),
(30, 'Baza podataka je organizirana i uređena cjelina međusobno povezanih podataka spremljenih bez nepotrebne redundancije.', 3, 4),
(31, 'Normaliziranje znači da se tablica u kojoj se nepotrebno ponavljaju podatci organizira u veći broj tablica.', 3, 4),
(32, 'Zavisno o vrsti i namjeni podataka te načinu korištenja, baze podataka mogu biti:', 3, 4),
(33, 'Izbaci uljeza među kriterijima za odabir polja kod izrade upita', 3, 4),
(34, 'Odnose (relacije) među tablicama postavljamo tako da polje s primarnim ključem odvučemo na polje s vanjskim ključem povezane tablice?', 3, 4),
(35, 'Kako se zovu upiti koji mijenjaju podatke u tablicama baze? (2 riječi)', 3, 4),
(36, 'Uključivanjem opcije Referencijalnog integriteta osiguravamo da se ne može mijenjati vrijednost primarnog ključa ako postoji povezani slog u drugoj tablici.', 3, 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
