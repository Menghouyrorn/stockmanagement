-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 13, 2022 at 04:08 AM
-- Server version: 8.0.27
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `final`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
CREATE TABLE IF NOT EXISTS `book` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Quality` varchar(50) NOT NULL,
  `Price` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`ID`, `Name`, `Description`, `Quality`, `Price`) VALUES
(2, 'Java Book', 'Best for studenT', '3', '5');

-- --------------------------------------------------------

--
-- Table structure for table `computer`
--

DROP TABLE IF EXISTS `computer`;
CREATE TABLE IF NOT EXISTS `computer` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Description` varchar(100) DEFAULT NULL,
  `Quality` varchar(50) DEFAULT NULL,
  `Price` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `computer`
--

INSERT INTO `computer` (`ID`, `Name`, `Description`, `Quality`, `Price`) VALUES
(5, 'Asus', 'Best for Student', '3', '500'),
(8, 'Lenovo', 'best for customer', '2', '400');

-- --------------------------------------------------------

--
-- Table structure for table `drink`
--

DROP TABLE IF EXISTS `drink`;
CREATE TABLE IF NOT EXISTS `drink` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` varchar(120) NOT NULL,
  `Quality` varchar(50) NOT NULL,
  `Price` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `drink`
--

INSERT INTO `drink` (`ID`, `Name`, `Description`, `Quality`, `Price`) VALUES
(2, 'Water', 'for Drink', '3', '1'),
(5, 'Pespsi', 'For drink', '3', '1');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
CREATE TABLE IF NOT EXISTS `register` (
  `firstName` varchar(100) NOT NULL,
  `lastName` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`firstName`, `lastName`, `email`, `password`, `Gender`, `ID`) VALUES
('admin', 'admin', 'admin@gmail.com', 'admin', 'Male', 1),
('tun', 'sreynouch', 'sreynouch@gmail.com', '123', 'female', 2),
('Menghouy', 'Rorn', 'menghouy@gmail.com', '123', 'Male', 3),
('dom', 'dom', 'dom@gmail.com', '123', 'Male', 11),
('Sreylis', 'Kong', 'sreylis@gmail.com', '123', 'Female', 9),
('menghouy', 'rorn', 'menghouyrorn@gmail.com', '123', 'Male', 20);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
