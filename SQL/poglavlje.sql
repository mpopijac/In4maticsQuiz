
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
-- Tablična struktura za tablicu `poglavlje`
--

CREATE TABLE IF NOT EXISTS `poglavlje` (
  `IDpoglavlje` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ukljuceno` tinyint(4) NOT NULL,
  `timestamp` bigint(20) NOT NULL DEFAULT '0',
  `obrisano` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IDpoglavlje`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=54 ;

--
-- Izbacivanje podataka za tablicu `poglavlje`
--

INSERT INTO `poglavlje` (`IDpoglavlje`, `naziv`, `ukljuceno`, `timestamp`, `obrisano`) VALUES
(1, 'Obrada teksta', 1, 0, 0),
(3, 'Access', 1, 0, 0),
(2, 'Excel', 1, 0, 0),
(4, 'PowerPoint', 1, 1450643333, 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
