
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
-- Tablična struktura za tablicu `odgovor`
--

CREATE TABLE IF NOT EXISTS `odgovor` (
  `IDodgovor` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `tocan` tinyint(4) NOT NULL,
  `IDpitanja` int(11) NOT NULL,
  PRIMARY KEY (`IDodgovor`),
  KEY `fk_odgovor_pitanja1_idx` (`IDpitanja`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=108 ;

--
-- Izbacivanje podataka za tablicu `odgovor`
--

INSERT INTO `odgovor` (`IDodgovor`, `naziv`, `tocan`, `IDpitanja`) VALUES
(1, 'Tipke Delete i Backspace', 1, 1),
(2, 'Naredbe Undo i Repeat', 0, 1),
(3, 'Naredbe Cut i Copy', 0, 1),
(4, 'Tipke Home i End', 0, 1),
(5, 'Točno', 0, 2),
(6, 'Netočno', 1, 2),
(7, '4', 1, 3),
(8, '3', 0, 3),
(9, '2', 0, 3),
(10, '5', 0, 3),
(11, 'Točno', 1, 4),
(12, 'Netočno', 0, 4),
(13, 'Okomito', 0, 5),
(14, 'Portret', 0, 5),
(15, 'Vodoravno', 0, 5),
(16, 'Dijagonalno', 0, 5),
(17, 'Pejzaž', 1, 5),
(18, 'Točno', 1, 6),
(19, 'Netočno', 0, 6),
(20, 'Traka', 0, 7),
(21, 'Vrpca', 1, 7),
(22, 'Prečac', 0, 7),
(23, 'Odjeljak', 0, 7),
(24, 'Baza podataka', 0, 8),
(25, 'Prezentacija', 0, 8),
(26, 'Dokument', 1, 8),
(27, 'Web stranica', 0, 8),
(28, 'Crtež', 0, 9),
(29, 'Tekstualni okriv', 0, 9),
(30, 'Ukrasni tekst', 1, 9),
(31, 'Formula', 0, 9),
(32, 'Prored', 1, 10),
(33, 'Oblikovanje odlomka', 0, 11),
(34, 'Ispis teksta', 1, 11),
(35, 'Oblikovanje stranice', 0, 11),
(36, 'Oblikovanje teksta', 0, 11),
(37, 'Točno', 1, 12),
(38, 'Netočno', 0, 12),
(39, 'izradu prezentacija', 0, 13),
(40, ' izradu proračunskih tablica', 1, 13),
(41, 'slanje i primanje elektroničke pošte', 0, 13),
(42, 'izradu baza podataka', 0, 13),
(43, '=', 1, 14),
(44, '?', 0, 14),
(45, '+', 0, 14),
(46, '!', 0, 14),
(47, 'uz lijevi rub čelije', 0, 15),
(48, 'po sredini', 0, 15),
(49, 'uz desni rub čelije', 1, 15),
(50, 'Držimo pritisnutu tipku Enter, a lijevom tipkom miša označavamo željene stupce', 0, 16),
(51, 'Držimo pritisnutu tipku Alt, a lijevom tipkom miša označavamo željene stupce', 0, 16),
(52, 'Držimo pritisnutu tipku Ctrl, a lijevom tipkom miša označavamo željene stupce', 1, 16),
(53, '$', 1, 17),
(54, 'Točno', 0, 18),
(55, 'Netočno', 1, 18),
(56, '5', 0, 19),
(57, '255', 0, 19),
(58, '3', 1, 19),
(59, '7', 0, 19),
(60, 'ROUND', 1, 20),
(61, 'IF', 0, 20),
(62, 'PRODUCT', 0, 20),
(63, 'SUM', 0, 20),
(64, 'Točno', 0, 21),
(65, 'Netočno', 1, 21),
(66, 'Da', 1, 22),
(67, 'Ne', 0, 22),
(68, 'Najveću vrijednost u ćelijama C1 i D15', 0, 23),
(69, 'Prosjek vrijednosti u rasponu ćelija od C1 do D15', 0, 23),
(70, 'Prosjek vrijednosti u ćelijama C1 i D15', 1, 23),
(71, 'Prosjek vrijednosti u ćelijama različitim od ćelija C1 i D15', 0, 23),
(72, 'Da', 1, 24),
(73, 'Ne', 0, 24),
(74, 'LIKE', 0, 25),
(75, 'OR', 0, 25),
(76, 'BETWEEN', 0, 25),
(77, 'FIND', 1, 25),
(78, 'Izvještaji', 1, 26),
(79, 'spremljena kopija (zaliha) baze podataka', 0, 27),
(80, 'pojava kad je ista činjenica zapisana više puta kako bi se ubrzalo pretraživanje', 0, 27),
(81, 'pojava kad je ista činjenica nepotrebno zapisana više puta', 1, 27),
(82, 'atribut', 0, 28),
(83, 'slog', 0, 28),
(84, 'zalihost', 0, 28),
(85, 'primarni ključ', 1, 28),
(86, 'ne postoje dva jednaka retka', 0, 29),
(87, 'redoslijed redaka je bitan', 1, 29),
(88, 'ne postoje dva jednaka stupca', 0, 29),
(89, 'redoslijed stupaca nije bitan', 0, 29),
(90, 'Da', 1, 30),
(91, 'Ne', 0, 30),
(92, 'Da', 1, 31),
(93, 'Ne', 0, 31),
(94, 'plošne i relacijske', 0, 32),
(95, 'baze formatiranih i neformatiranih podataka', 1, 32),
(96, 'hijerarhijske i mrežne', 0, 32),
(97, 'ništa od navedenog', 0, 32),
(98, 'GO', 1, 33),
(99, 'AND', 0, 33),
(100, 'BETWEEN', 0, 33),
(101, 'IN', 0, 33),
(102, 'LIKE', 0, 33),
(103, 'Da', 1, 34),
(104, 'Ne', 0, 34),
(105, 'akcijski upiti', 1, 35),
(106, 'Točno', 1, 36),
(107, 'Netočno', 0, 36);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
