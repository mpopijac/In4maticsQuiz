
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
-- Tablična struktura za tablicu `odgovor`
--

CREATE TABLE IF NOT EXISTS `odgovor` (
  `IDodgovor` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `tocan` tinyint(4) NOT NULL,
  `IDpitanja` int(11) NOT NULL,
  `timestamp` bigint(20) NOT NULL DEFAULT '0',
  `obrisano` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDodgovor`),
  KEY `fk_odgovor_pitanja1_idx` (`IDpitanja`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=135 ;

--
-- Izbacivanje podataka za tablicu `odgovor`
--

INSERT INTO `odgovor` (`IDodgovor`, `naziv`, `tocan`, `IDpitanja`, `timestamp`, `obrisano`) VALUES
(1, 'Tipke Delete i Backspace', 1, 1, 0, 0),
(2, 'Naredbe Undo i Repeat', 0, 1, 0, 0),
(3, 'Naredbe Cut i Copy', 0, 1, 0, 0),
(4, 'Tipke Home i End', 0, 1, 0, 0),
(5, 'Točno', 0, 2, 0, 0),
(6, 'Netočno', 1, 2, 0, 0),
(7, '4', 1, 3, 0, 0),
(8, '3', 0, 3, 0, 0),
(9, '2', 0, 3, 0, 0),
(10, '5', 0, 3, 0, 0),
(11, 'Točno', 1, 4, 0, 0),
(12, 'Netočno', 0, 4, 0, 0),
(13, 'Okomito', 0, 5, 0, 0),
(14, 'Portret', 0, 5, 0, 0),
(15, 'Vodoravno', 0, 5, 0, 0),
(16, 'Dijagonalno', 0, 5, 0, 0),
(17, 'Pejzaž', 1, 5, 0, 0),
(18, 'Točno', 1, 6, 0, 0),
(19, 'Netočno', 0, 6, 0, 0),
(20, 'Traka', 0, 7, 0, 0),
(21, 'Vrpca', 1, 7, 0, 0),
(22, 'Prečac', 0, 7, 0, 0),
(23, 'Odjeljak', 0, 7, 0, 0),
(24, 'Baza podataka', 0, 8, 0, 0),
(25, 'Prezentacija', 0, 8, 0, 0),
(26, 'Dokument', 1, 8, 0, 0),
(27, 'Web stranica', 0, 8, 0, 0),
(28, 'Crtež', 0, 9, 0, 0),
(29, 'Tekstualni okriv', 0, 9, 0, 0),
(30, 'Ukrasni tekst', 1, 9, 0, 0),
(31, 'Formula', 0, 9, 0, 0),
(32, 'Prored', 1, 10, 0, 0),
(33, 'Oblikovanje odlomka', 0, 11, 0, 0),
(34, 'Ispis teksta', 1, 11, 0, 0),
(35, 'Oblikovanje stranice', 0, 11, 0, 0),
(36, 'Oblikovanje teksta', 0, 11, 0, 0),
(37, 'Točno', 1, 12, 0, 0),
(38, 'Netočno', 0, 12, 0, 0),
(39, 'izradu prezentacija', 0, 13, 0, 0),
(40, ' izradu proračunskih tablica', 1, 13, 0, 0),
(41, 'slanje i primanje elektroničke pošte', 0, 13, 0, 0),
(42, 'izradu baza podataka', 0, 13, 0, 0),
(43, '=', 1, 14, 0, 0),
(44, '?', 0, 14, 0, 0),
(45, '+', 0, 14, 0, 0),
(46, '!', 0, 14, 0, 0),
(47, 'uz lijevi rub čelije', 0, 15, 0, 0),
(48, 'po sredini', 0, 15, 0, 0),
(49, 'uz desni rub čelije', 1, 15, 0, 0),
(50, 'Držimo pritisnutu tipku Enter, a lijevom tipkom miša označavamo željene stupce', 0, 16, 0, 0),
(51, 'Držimo pritisnutu tipku Alt, a lijevom tipkom miša označavamo željene stupce', 0, 16, 0, 0),
(52, 'Držimo pritisnutu tipku Ctrl, a lijevom tipkom miša označavamo željene stupce', 1, 16, 0, 0),
(53, '$', 1, 17, 0, 0),
(54, 'Točno', 0, 18, 0, 0),
(55, 'Netočno', 1, 18, 0, 0),
(56, '5', 0, 19, 0, 0),
(57, '255', 0, 19, 0, 0),
(58, '3', 1, 19, 0, 0),
(59, '7', 0, 19, 0, 0),
(60, 'ROUND', 1, 20, 0, 0),
(61, 'IF', 0, 20, 0, 0),
(62, 'PRODUCT', 0, 20, 0, 0),
(63, 'SUM', 0, 20, 0, 0),
(64, 'Točno', 0, 21, 0, 0),
(65, 'Netočno', 1, 21, 0, 0),
(66, 'Da', 1, 22, 0, 0),
(67, 'Ne', 0, 22, 0, 0),
(68, 'Najveću vrijednost u ćelijama C1 i D15', 0, 23, 0, 0),
(69, 'Prosjek vrijednosti u rasponu ćelija od C1 do D15', 0, 23, 0, 0),
(70, 'Prosjek vrijednosti u ćelijama C1 i D15', 1, 23, 0, 0),
(71, 'Prosjek vrijednosti u ćelijama različitim od ćelija C1 i D15', 0, 23, 0, 0),
(72, 'Da', 1, 24, 0, 0),
(73, 'Ne', 0, 24, 0, 0),
(74, 'LIKE', 0, 25, 0, 0),
(75, 'OR', 0, 25, 0, 0),
(76, 'BETWEEN', 0, 25, 0, 0),
(77, 'FIND', 1, 25, 0, 0),
(78, 'Izvještaji', 1, 26, 0, 0),
(79, 'spremljena kopija (zaliha) baze podataka', 0, 27, 0, 0),
(80, 'pojava kad je ista činjenica zapisana više puta kako bi se ubrzalo pretraživanje', 0, 27, 0, 0),
(81, 'pojava kad je ista činjenica nepotrebno zapisana više puta', 1, 27, 0, 0),
(82, 'atribut', 0, 28, 0, 0),
(83, 'slog', 0, 28, 0, 0),
(84, 'zalihost', 0, 28, 0, 0),
(85, 'primarni ključ', 1, 28, 0, 0),
(86, 'ne postoje dva jednaka retka', 0, 29, 0, 0),
(87, 'redoslijed redaka je bitan', 1, 29, 0, 0),
(88, 'ne postoje dva jednaka stupca', 0, 29, 0, 0),
(89, 'redoslijed stupaca nije bitan', 0, 29, 0, 0),
(90, 'Da', 1, 30, 0, 0),
(91, 'Ne', 0, 30, 0, 0),
(92, 'Da', 1, 31, 0, 0),
(93, 'Ne', 0, 31, 0, 0),
(94, 'plošne i relacijske', 0, 32, 0, 0),
(95, 'baze formatiranih i neformatiranih podataka', 1, 32, 0, 0),
(96, 'hijerarhijske i mrežne', 0, 32, 0, 0),
(97, 'ništa od navedenog', 0, 32, 0, 0),
(98, 'GO', 1, 33, 0, 0),
(99, 'AND', 0, 33, 0, 0),
(100, 'BETWEEN', 0, 33, 0, 0),
(101, 'IN', 0, 33, 0, 0),
(102, 'LIKE', 0, 33, 0, 0),
(103, 'Da', 1, 34, 0, 0),
(104, 'Ne', 0, 34, 0, 0),
(105, 'akcijski upiti', 1, 35, 0, 0),
(106, 'Točno', 1, 36, 0, 0),
(107, 'Netočno', 0, 36, 0, 0),
(108, 'Slajdove', 1, 37, 1450643333, 0),
(109, 'Odlomke', 0, 37, 1450643333, 0),
(110, 'Tablice', 0, 37, 1450643333, 0),
(111, 'Naslove', 0, 37, 1450643333, 0),
(112, 'Alt Gr', 0, 38, 1450643333, 0),
(113, 'Alt', 0, 38, 1450643333, 0),
(114, 'Ctrl', 1, 38, 1450643333, 0),
(115, 'Shift', 0, 38, 1450643333, 0),
(116, 'Umetanje', 1, 39, 1450643333, 0),
(117, 'F5', 1, 40, 1450643333, 0),
(118, 'F3', 0, 40, 1450643333, 0),
(119, 'F4', 0, 40, 1450643333, 0),
(120, 'F6', 0, 40, 1450643333, 0),
(121, 'Točno', 1, 41, 1450643333, 0),
(122, 'Netočno', 0, 41, 1450643333, 0),
(123, 'Tab', 1, 42, 1450643333, 0),
(124, '3', 1, 43, 1450643333, 0),
(125, 'Dijaprojekcija', 0, 44, 1450643333, 0),
(126, 'Stranica bilježaka', 0, 44, 1450643333, 0),
(127, 'Web izgled', 1, 44, 1450643333, 0),
(128, 'Razvrstavač slajdova', 0, 44, 1450643333, 0),
(129, 'Alat za sortiranje slajdova', 1, 45, 1450643333, 0),
(130, 'Normal', 0, 45, 1450643333, 0),
(131, 'Dijaprojekcija', 0, 45, 1450643333, 0),
(132, 'Stranica bilježaka', 0, 45, 1450643333, 0),
(133, 'Točno', 0, 46, 1450643333, 0),
(134, 'Netočno', 1, 46, 1450643333, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
