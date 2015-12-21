
-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Računalo: localhost
-- Vrijeme generiranja: Pro 21, 2015 u 09:56 PM
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
  `timestamp` bigint(20) NOT NULL DEFAULT '0',
  `obrisano` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDpitanja`),
  KEY `fk_pitanja_poglavlje1_idx` (`IDpoglavlje`),
  KEY `fk_pitanja_razred1_idx` (`IDrazred`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=47 ;

--
-- Izbacivanje podataka za tablicu `pitanja`
--

INSERT INTO `pitanja` (`IDpitanja`, `pitanje`, `IDpoglavlje`, `IDrazred`, `timestamp`, `obrisano`) VALUES
(1, 'Što koristimo za brisanje teksta?', 1, 5, 0, 0),
(2, 'Dokument u Wordu 2007 i noviji prema unaprijed zadanom ima nastavak .doc ?', 1, 5, 0, 0),
(3, 'Koliko vrsta poravnanja teksta postoji u MS Wordu?', 1, 5, 0, 0),
(4, 'Zadani pomak tabulatora u Wordu je 1,25 cm.', 1, 5, 0, 0),
(5, 'Kakvo može biti usmjerenje stranice u Wordu?', 1, 5, 0, 0),
(6, 'U Wordu ne postoji mogućnost izrade grafikona.', 1, 5, 0, 0),
(7, 'Na vrhu prozora dokumenta nalazi se dio koji se sastoji od kartica, a nazivamo ga:', 1, 5, 0, 0),
(8, 'Datoteka nastala u Wordu naziva se:', 1, 5, 0, 0),
(9, 'Što je WordArt?', 1, 5, 0, 0),
(10, 'Razmak između redaka u dokumentu zovemo: ', 1, 5, 0, 0),
(11, 'Razina oblikovanja dokumenta nije:', 1, 5, 0, 0),
(12, 'Broj stranice u Wordu može biti određen slovom.', 1, 5, 0, 0),
(13, 'Excel je program za:', 2, 7, 0, 0),
(14, 'Kojim znakom započinje pisanje formule u Excelu?', 2, 7, 0, 0),
(15, 'Prema zadanim postavkama Excel datum poravnava:', 2, 7, 0, 0),
(16, 'Označavanje zasebnih stupaca (na preskok) radimo tako da:', 2, 7, 0, 0),
(17, 'Koji znak ispred oznake retka i oznake stupca sadrži apsolutna adresa?', 2, 7, 0, 0),
(18, 'Zaglavlje je prazan prostor između ruba papira i ispisanih podataka  na dnu stranice.', 2, 7, 0, 0),
(19, 'Prema unaprijed zadanim postavkama koliko radnih listova ima radna knjiga:', 2, 7, 0, 0),
(20, 'Funkcija za zaokruživanje brojeva je:', 2, 7, 0, 0),
(21, 'U formuli za zbrajanje možemo koristiti samo dva argumenta:', 2, 7, 0, 0),
(22, 'Grafikon koristimo za grafički prikaz i usporedbu podataka obrađenih u proračunskim tablicama.', 2, 7, 0, 0),
(23, 'Formula =AVERAGE(C1;D15) izračunava:', 2, 7, 0, 0),
(24, 'Tablica (Excel) je niz redaka i stupaca koji sadrže međusobno povezane podatke. ', 2, 7, 0, 0),
(25, 'Koji od navedenih NIJE kriterij za odabir polja u upitu u Accessu 2010?', 3, 8, 0, 0),
(26, 'Kako se u Accessu 2010 nazivaju objekti za formatiranje, izračun i ispis (prikaz) izabranih podataka? (1 riječ)', 3, 8, 0, 0),
(27, 'Redundancija (zalihost) je:', 3, 8, 0, 0),
(28, 'Polje ili više polja kojima se može jednoznačno definirati redak tablice naziva se:', 3, 8, 0, 0),
(29, 'Što nije jedna od osnovnih karakteristika tablice (relacije):', 3, 8, 0, 0),
(30, 'Baza podataka je organizirana i uređena cjelina međusobno povezanih podataka spremljenih bez nepotrebne redundancije.', 3, 8, 0, 0),
(31, 'Normaliziranje znači da se tablica u kojoj se nepotrebno ponavljaju podatci organizira u veći broj tablica.', 3, 8, 0, 0),
(32, 'Zavisno o vrsti i namjeni podataka te načinu korištenja, baze podataka mogu biti:', 3, 8, 0, 0),
(33, 'Izbaci uljeza među kriterijima za odabir polja kod izrade upita', 3, 8, 0, 0),
(34, 'Odnose (relacije) među tablicama postavljamo tako da polje s primarnim ključem odvučemo na polje s vanjskim ključem povezane tablice?', 3, 8, 0, 0),
(35, 'Kako se zovu upiti koji mijenjaju podatke u tablicama baze? (2 riječi)', 3, 8, 0, 0),
(36, 'Uključivanjem opcije Referencijalnog integriteta osiguravamo da se ne može mijenjati vrijednost primarnog ključa ako postoji povezani slog u drugoj tablici.', 3, 8, 0, 0),
(37, 'U PowerPointu možemo ispisati:', 4, 6, 1450642707, 0),
(38, 'Prilikom označavanja više nepovezanih slajdova koristit ćemo tipku:', 4, 6, 1450642707, 0),
(39, 'Pomoću kojeg izbornika (hrvatski naziv) umećemo sliku na slajd?', 4, 6, 1450642707, 0),
(40, 'Za pokretanje prezentacije koristimo tipku:', 4, 6, 1450642707, 0),
(41, 'Unutar prezentacije možemo prikazati video ili film. ', 4, 6, 1450642707, 0),
(42, 'Koju tipku koristimo za izradu uvlaka i pomicanje sadržaja na sljedeću razinu (udesno)?', 4, 6, 1450642707, 0),
(43, 'Koliko vrsta projekcije ili načina prikazivanja prezentacije postoji (napiši broj)?', 4, 6, 1450642707, 0),
(44, 'Što nije način prikaza u PowerPointu:', 4, 6, 1450642707, 0),
(45, 'Prikaz koji prikazuje slajdove u obliku minijatura naziva se:', 4, 6, 1450642707, 0),
(46, 'Jednom objektu na slajdu možemo dodijeliti samo jednu animaciju.', 4, 6, 1450642707, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
