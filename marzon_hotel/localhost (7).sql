-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 22, 2014 at 02:38 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `argie_tamera`
--
CREATE DATABASE `argie_tamera` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `argie_tamera`;

-- --------------------------------------------------------

--
-- Table structure for table `amenities`
--

CREATE TABLE IF NOT EXISTS `amenities` (
  `amenities_id` int(11) NOT NULL AUTO_INCREMENT,
  `pic` varchar(100) NOT NULL,
  `des` text NOT NULL,
  PRIMARY KEY (`amenities_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `amenities`
--

INSERT INTO `amenities` (`amenities_id`, `pic`, `des`) VALUES
(1, 'amenities/IconAC.jpg', 'Every room in the Tamera Plaza Inn has Air Conditioning. Each room has an easy-to-use remote control to set your comfort level without having to leave the bed.'),
(2, 'amenities/IconBkfst.jpg', 'Upon your arrival, you will recieve a complimentary "Welcome Drink" for your travel here. Also, there is Free Breakfast for all accomodations.'),
(3, 'amenities/IconCocktail.jpg', 'Located in the lobby, we offer a full-service Bar & Coffee Shop with a variety of beverages. '),
(4, 'amenities/IconFunction.jpg', 'Located on the 4th floor, we hold many activities for all occasions here in the Function Room. One can reserve this room for conferences, parties, and more.'),
(5, 'amenities/IconGen.jpg', 'We have generators on standby 24 hours a day, 7 days a week in an event of a "Brown Out", providing uninterrupted electricity service to the building.'),
(6, 'amenities/IconLaundry.jpg', 'Whether on business or pleasure, we provide laundry and pressing service here. We deliver your clothes to your room, or in person to accomodate your schedule.'),
(7, 'amenities/IconLongDist.jpg', 'Need to call home or send documents? We provide Fax services and phones equipped for Long Distance calls to home, the office, or anywhere in between.'),
(8, 'amenities/restaurantLG.jpg', 'Not only a great place to eat, but has great street-side view of Bacolod City. Try the famous "Tamera Chicken", great for the whole family!'),
(9, 'amenities/IconShower.jpg', 'Every room is individualy equipped with personal water heaters in the showers. Fully adjustable, you''ll always have a comfortable shower without burning or freezing yourself.'),
(10, 'amenities/IconTaxi.jpg', 'Have a meeting to go to or just want to go out? You can schedule trips anywhere around Bacolod City by utilizing our transportation service offered here.'),
(11, 'amenities/IconTV.jpg', 'No room would be complete without complimentary Cable TV and telephone service in every room. Channels may vary.'),
(12, 'amenities/SmIconWiFi.png', 'All area of Tamera Plaza Inn is wifi ready');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `email` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`comment_id`, `name`, `email`, `content`) VALUES
(1, 'The name', 'email@mail.com', 'sdasd'),
(2, 'The other', 'man@mail.com', 'testing');

-- --------------------------------------------------------

--
-- Table structure for table `payment_notification`
--

CREATE TABLE IF NOT EXISTS `payment_notification` (
  `pay_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `item_number` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL,
  `amount` decimal(65,0) NOT NULL,
  `currency` varchar(20) NOT NULL,
  `txn_id` varchar(30) NOT NULL,
  `payer_email` varchar(100) NOT NULL,
  PRIMARY KEY (`pay_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `payment_notification`
--

INSERT INTO `payment_notification` (`pay_id`, `item_name`, `item_number`, `status`, `amount`, `currency`, `txn_id`, `payer_email`) VALUES
(1, 'Superior', 'hssa40d5', 'Completed', 3000, 'PHP', '14G24020NN154222R', 'argiep_1323161216_per@gmail.com'),
(2, 'Standard Double', 'p2mzycvy', 'Completed', 33, 'PHP', '66J76616N2842804Y', 'argiep_1323161216_per@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `city` varchar(30) NOT NULL,
  `zip` int(11) NOT NULL,
  `province` varchar(30) NOT NULL,
  `country` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contact` int(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `arrival` varchar(30) NOT NULL,
  `departure` varchar(30) NOT NULL,
  `adults` int(11) NOT NULL,
  `child` int(11) NOT NULL,
  `result` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `no_room` int(11) NOT NULL,
  `payable` int(11) NOT NULL,
  `status` varchar(10) NOT NULL,
  `confirmation` varchar(20) NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=103 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `firstname`, `lastname`, `city`, `zip`, `province`, `country`, `email`, `contact`, `username`, `password`, `arrival`, `departure`, `adults`, `child`, `result`, `room_id`, `no_room`, `payable`, `status`, `confirmation`) VALUES
(1, 'a', 'a', 'a', 1, 'a', 'a', 'a@a.com', 2, '', 'a', '27/01/2012', '29/01/2012', 1, 0, 2, 6, 1, 3000, '', 'hssa40d5'),
(2, '', '', '', 0, '', '', '123@yahoo.com', 0, '', '123456', '13/07/2009', '15/07/2009', 1, 0, 2, 0, 1, 0, '', 'avtxyjw8'),
(3, 'mec', 'ol', 'bac', 6110, 'tang', 'phil', 'mecileoligo@gmail.com', 2147483647, '', '12345', '01/02/2012', '07/02/2012', 1, 0, 6, 6, 1, 9000, '', 'b0hz8hy6'),
(4, 'a', 'a', 'a', 1, 'a', 'a', 'A@A.COM', 1, '', 'A', '31/01/2012', '01/02/2012', 1, 0, -1, 6, 1, -1500, '', 'jeggntns'),
(5, 'mecile', 'oligo', 'bacolod', 6115, 'tngub', 'Philippines', 'policarpio.argie@yahoo.com', 2147483647, '', 'mecile', '01/02/2012', '04/02/2012', 1, 1, 3, 9, 1, 33, '', 'p2mzycvy'),
(6, 'manuel', 'cruz', 'samar', 6700, '78sada', 'p', 'M_bogz_08@yahoo.com', 956265384, '', 'bogz', '31/01/2012', '31/01/2012', 1, 0, 0, 6, 1, 0, '', 'prwsb78x'),
(7, 'qq', 'q', 'q', 1234, 'q', 'q', 'q@yahoo.com', 12345, '', '1', '07/02/2012', '09/02/2012', 1, 0, 2, 6, 1, 3000, '', 'wiztqowr'),
(8, 'qq', 'q', 'q', 1234, 'q', 'q', 'q@yahoo.com', 12345, '', 'buang', '07/02/2012', '09/02/2012', 1, 0, 2, 6, 1, 3000, '', '4e36xnyo'),
(9, 'qqdwewq', 'q', 'q', 1234, 'q', 'q', 't@yahoo.com', 12345, '', 'buang', '07/02/2012', '09/02/2012', 1, 0, 2, 6, 1, 3000, '', 'd42zmddo'),
(10, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'qgdjq50w'),
(11, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'qzfrr5nm'),
(12, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '2t0wx8jw'),
(13, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'o2txjg6e'),
(14, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'uuxm5d8v'),
(15, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'or2e4vbe'),
(16, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'd4m4vz56'),
(17, 'asas', 'sds', 'sd', 6116, 'sdsafds', 'wrtrev', 'g@yahoo.com', 912324, '', 'ty', '08/02/2012', '09/02/2012', 1, 0, 1, 8, 1, 950, '', 'yyagsh8h'),
(18, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'yvmzxsqh'),
(19, 'sa', 'as', 'sds', 4567, 'sads', 'sad', 'e@yahoo.com', 2147483647, '', 't', '07/02/2012', '09/02/2012', 1, 0, 2, 8, 2, 3800, '', 'qhrwgyp6'),
(20, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '3wz4k5d4'),
(21, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'og6w32ve'),
(22, '', '', '', 0, '', '', 'policarpio.argie@yahoo.com', 0, '', '', '10/02/2012', '24/02/2012', 1, 0, 14, 0, 1, 0, '', 'kra3anac'),
(23, 'sda', 'sadsa', 'asdsa', 1234, '2234', 'asdsad', 'simplemark14@yahoo.com', 1312312312, '', 'jeremiah', '04/02/2012', '17/02/2012', 3, 1, 13, 8, 1, 12310, '', 'ka4vqr8w'),
(24, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, 'out', 'nv6yirjb'),
(25, 'qww', 'www', 'www', 111, 'www', 'www', 'man@mail.com', 111, '', '111', '12/03/2014', '13/03/2014', 1, 0, 1, 8, 1, 950, '', 'mun30mtu'),
(27, 'yyy', 'yyy', 'yyy', 0, 'yyy', 'yyy', 'man@mail.com', 5555, '', '666', '11/03/2014', '11/03/2014', 1, 0, 0, 8, 2, 0, '', '5xewqdq2'),
(28, 'hhhh', 'hhh', 'hhh', 7777, 'hhh', 'hhh', 'man@yopmail.com', 0, '', 'hhh', '11/03/2014', '13/03/2014', 1, 0, 2, 8, 1, 1900, '', 'db8g3svu'),
(29, 'ggg', 'ggg', 'ggg', 66, 'ggg', 'ggg', 'man@yopmail.com', 777, '', '777', '27/03/2014', '28/03/2014', 1, 0, 1, 8, 3, 2850, '', 'mrpyge4f'),
(30, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'a5gugvym'),
(31, 'vvvv', 'vvv', 'vvvv', 777, 'vvvv', 'vvv888', 'man@mail.com', 777, '', '777', '27/03/2014', '28/03/2014', 1, 0, 1, 8, 3, 2850, '', 'ffus0tfv'),
(32, 'ddd', 'ddd', 'ddd', 777, 'ddd', 'ddd', 'man@yopmail.com', 555, '', '555', '26/03/2014', '27/03/2014', 1, 0, 1, 8, 2, 1900, '', 'bzjtotzo'),
(33, 'ggg', 'ggg', 'ggg', 888, 'ggg', 'ggg', 'man@yopmail.com', 3333, '', '333', '18/03/2014', '19/03/2014', 1, 0, 1, 8, 8, 7600, '', 'fcq6fcbv'),
(34, 'kkk', 'kkk', 'kkk', 0, 'kkk', 'kkk', 'man@yopmail.com', 666, '', '666', '30/03/2014', '31/03/2014', 1, 0, 1, 8, 1, 950, '', 'yxxyygm4'),
(35, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'y3ts63ko'),
(36, 'xxx', 'xxx', 'xx', 111, 'xxx', 'xxx', 'man@yopmail.com', 333, '', '333', '31/03/2014', '31/03/2014', 1, 0, 0, 8, 6, 0, '', 'hcdmem6w'),
(37, 'tttt', 'tt', 'ttt', 777, 'tttt', 'ttt', 'yesterdaysfoe@gmail.com', 444, '', '444', '26/03/2014', '27/03/2014', 1, 0, 1, 8, 1, 950, '', 'ppqzrfcf'),
(38, 'llll', 'llll', 'lll', 0, 'lll', 'lll', 'yesterdaysfoe@gmail.com', 0, '', '000', '25/03/2014', '28/03/2014', 1, 0, 3, 8, 1, 2850, '', 'w8cnc6py'),
(39, 'yyy', 'yyy', 'yyy', 888, 'YYYY', 'yyy', 'yesterdaysfoe@gmail.com', 6666, '', '6666', '18/03/2014', '19/03/2014', 1, 0, 1, 9, 1, 1100, '', '5zuubzg6'),
(40, 'nnn', 'nnn', 'nnn', 666, 'nn', 'nnn', 'yesterdaysfoe@gmail.com', 555, '', '5555', '07/03/2015', '08/03/2015', 1, 0, 1, 9, 2, 2200, '', 'mpawq3e3'),
(41, 'ttttt', 'ttt', 'tttt', 888, 'tttt', 'ttt', 'yesterdaysfoe@gmail.com', 888, '', '8888', '03/03/2018', '04/03/2018', 1, 0, 1, 8, 1, 950, '', 'ce7a5w45'),
(42, 'jjj', 'jjj', 'jjj', 0, 'jjj', 'jjj', 'yesterdaysfoe@gmail.com', 0, '', '000', '30/03/2015', '31/03/2015', 1, 0, 1, 8, 1, 950, '', 'ufhkmg2a'),
(43, '1111', '1111', '111', 1111, '1111', '1111', '1@yopmail.com', 111, '', '1111', '11/03/2014', '12/03/2014', 1, 0, 1, 9, 1, 1100, '', '0wvu2k3h'),
(44, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'q75avs7m'),
(45, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'vgo5kcwk'),
(46, '555', '55', '55', 555, '555', '555', '1@yopmail.com', 5555, '', '5555', '11/03/2014', '12/03/2014', 1, 0, 1, 9, 1, 1100, '', '0jerg0de'),
(47, '8888', '8888', '8888', 8888, '8888', '888', 'man@mail.com', 8888, '', '8888', '12/03/2014', '12/03/2014', 1, 0, 0, 9, 1, 0, '', 'e58ryaqt'),
(48, 'rrr', 'rrr', 'rrrr', 777, 'rr', 'rrr', 'man@mail.com', 7777, '', '7777', '13/03/2015', '17/03/2015', 1, 0, 4, 8, 1, 3800, '', 'xmefvzna'),
(49, 'bbbb', 'bbb', 'bbb', 1, 'bbbb', 'bbb', 'man@mail.com', 666, '', 'bbb', '24/03/2014', '25/03/2014', 1, 0, 1, 8, 1, 950, '', 'ggno87z3'),
(50, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'q32jy5d4'),
(51, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'fmo5adn7'),
(52, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'ex07tqqw'),
(53, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'x7ksftcr'),
(54, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'gz0dpxnb'),
(55, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'n2nniaf6'),
(56, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'mjeve5xm'),
(57, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '7emy4iqr'),
(58, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'no8tacfw'),
(59, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'khb543bu'),
(60, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'dir5m0gg'),
(61, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'ygm4k2vn'),
(62, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'ehivf2kt'),
(63, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'srye62uq'),
(64, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'jd8734xf'),
(65, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'if4bn5zf'),
(66, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'bm4wz4za'),
(67, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '8qcpy8m3'),
(68, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'jf6ujt6d'),
(69, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'heb4ekgo'),
(70, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '0uj44n25'),
(71, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'j7swjpt2'),
(72, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'yfozav36'),
(73, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'fvhkpka6'),
(74, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'wu54z5mg'),
(75, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'sk3p45wf'),
(76, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'fd0zizef'),
(77, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'qb3s6sfb'),
(78, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'at3e7rem'),
(79, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'fhi2id2q'),
(80, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '8j8m2rad'),
(81, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'i20p6nj2'),
(82, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'v5qihgnu'),
(83, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'm2ro2mm0'),
(84, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'ftjdxoij'),
(85, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'aedjapbf'),
(86, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'tq53bnra'),
(87, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'z0db8usj'),
(88, '4444', '444', '444', 444, '4444', '444', 'man@mail.com', 4444, '', '4444', '11/03/2014', '12/03/2014', 1, 0, 1, 9, 1, 1100, '', 'fitp0k4w'),
(89, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'n38fvbkj'),
(90, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'rrwgjm5i'),
(91, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'zc4gbgoo'),
(92, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'q505fuk2'),
(93, 'rr', 'rr', 'r', 666, 'r', 'rr', 'man@mail.com', 666, '', '666', '18/03/2014', '26/03/2014', 1, 0, 8, 8, 1, 7600, '', 'ppb7j625'),
(94, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'fnacc6bv'),
(95, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'rdwrswyo'),
(96, 'mmm', 'mmmm', 'mmm', 8888, 'm', 'mmm', 'man@mail.com', 8888, '', '8888', '26/03/2016', '27/03/2016', 1, 0, 1, 9, 1, 1100, '', 'rx6h53d6'),
(97, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '7ofipgex'),
(98, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', '0qbhhxaq'),
(99, '', '', '', 0, '', '', '', 0, '', '', '', '', 0, 0, 0, 0, 0, 0, '', 'g4bfjjfh'),
(100, 'yyyy', 'yyyy', 'yyy', 0, 'yyyy', 'yyyyy', 'man@mail.com', 0, '', '00000', '31/03/2017', '31/03/2017', 1, 0, 0, 8, 1, 0, '', 'dm3ht7zh'),
(101, 'uuuu', 'uuu', 'uuu', 99, 'uuuu', 'uuu', 'man@mail.com', 7777, '', '7777', '25/03/2016', '26/03/2016', 1, 0, 1, 11, 1, 2000, '', 'on74kdx2'),
(102, '12121', '21212', '121212', 12121, '1212', '12121', 'man@mail.com', 1212, '', '343', '24/03/2014', '25/03/2014', 1, 0, 1, 8, 1, 950, '', 'qwsbr742');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `room_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(30) NOT NULL,
  `rate` int(11) NOT NULL,
  `description` varchar(300) NOT NULL,
  `image` varchar(100) NOT NULL,
  `qty` int(11) NOT NULL,
  `max_adult` int(11) NOT NULL,
  `max_child` int(11) NOT NULL,
  PRIMARY KEY (`room_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `type`, `rate`, `description`, `image`, `qty`, `max_adult`, `max_child`) VALUES
(8, 'Standard Single', 950, 'Fully air conditioned', 'photos/Single.jpg', 3, 1, 1),
(9, 'Standard Double', 1100, 'Fully air conditioned', 'photos/images.jpg', 3, 1, 1),
(10, 'The Room', 1200, '1', 'hotel_pics/box_1.jpg', 2, 0, 0),
(11, 'Twin Room', 2000, '1', 'photos/StandardTwinSharing_600x450P1_3348.jpg', 3, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `roominventory`
--

CREATE TABLE IF NOT EXISTS `roominventory` (
  `roominventory_id` int(11) NOT NULL AUTO_INCREMENT,
  `arrival` varchar(30) NOT NULL,
  `departure` varchar(30) NOT NULL,
  `qty_reserve` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `confirmation` varchar(10) NOT NULL,
  `status` varchar(30) NOT NULL,
  PRIMARY KEY (`roominventory_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=107 ;

--
-- Dumping data for table `roominventory`
--

INSERT INTO `roominventory` (`roominventory_id`, `arrival`, `departure`, `qty_reserve`, `room_id`, `confirmation`, `status`) VALUES
(1, '27/01/2012', '29/01/2012', 1, 6, 'hssa40d5', 'out'),
(2, '13/07/2009', '15/07/2009', 1, 0, 'avtxyjw8', 'Active'),
(3, '01/02/2012', '07/02/2012', 1, 6, 'b0hz8hy6', 'Active'),
(4, '31/01/2012', '01/02/2012', 1, 6, 'jeggntns', 'Active'),
(5, '01/02/2012', '04/02/2012', 1, 9, 'p2mzycvy', 'out'),
(6, '31/01/2012', '31/01/2012', 1, 6, 'prwsb78x', 'Active'),
(7, '07/02/2012', '09/02/2012', 1, 6, 'wiztqowr', 'Active'),
(8, '07/02/2012', '09/02/2012', 1, 6, '4e36xnyo', 'Active'),
(9, '07/02/2012', '09/02/2012', 1, 6, 'd42zmddo', 'Active'),
(10, '', '', 0, 0, 'qgdjq50w', 'Active'),
(11, '', '', 0, 0, 'qzfrr5nm', 'Active'),
(12, '', '', 0, 0, '2t0wx8jw', 'Active'),
(13, '', '', 0, 0, 'o2txjg6e', 'Active'),
(14, '', '', 0, 0, 'uuxm5d8v', 'Active'),
(15, '', '', 0, 0, 'or2e4vbe', 'Active'),
(16, '', '', 0, 0, 'd4m4vz56', 'Active'),
(17, '08/02/2012', '09/02/2012', 1, 8, 'yyagsh8h', 'Active'),
(18, '', '', 0, 0, 'yvmzxsqh', 'Active'),
(19, '07/02/2012', '09/02/2012', 2, 8, 'qhrwgyp6', 'Active'),
(20, '', '', 0, 0, '3wz4k5d4', 'Active'),
(21, '', '', 0, 0, 'og6w32ve', 'Active'),
(22, '10/02/2012', '24/02/2012', 1, 0, 'kra3anac', 'Active'),
(23, '04/02/2012', '17/02/2012', 1, 8, 'ka4vqr8w', 'Active'),
(24, '', '', 0, 0, 'nv6yirjb', 'out'),
(25, '12/03/2014', '13/03/2014', 1, 8, 'mun30mtu', 'Active'),
(26, '11/03/2014', '13/03/2014', 1, 8, 'nw8c00x6', 'out'),
(27, '11/03/2014', '11/03/2014', 2, 8, '5xewqdq2', 'Active'),
(28, '11/03/2014', '13/03/2014', 1, 8, 'db8g3svu', 'Active'),
(29, '27/03/2014', '28/03/2014', 3, 8, 'mrpyge4f', 'Active'),
(30, '', '', 0, 0, 'a5gugvym', 'Active'),
(31, '27/03/2014', '28/03/2014', 3, 8, 'ffus0tfv', 'Active'),
(32, '26/03/2014', '27/03/2014', 2, 8, 'bzjtotzo', 'Active'),
(33, '18/03/2014', '19/03/2014', 8, 8, 'fcq6fcbv', 'Active'),
(34, '30/03/2014', '31/03/2014', 1, 8, 'yxxyygm4', 'Active'),
(35, '', '', 0, 0, 'y3ts63ko', 'Active'),
(36, '31/03/2014', '31/03/2014', 6, 8, 'hcdmem6w', 'Active'),
(37, '26/03/2014', '27/03/2014', 1, 8, 'ppqzrfcf', 'Active'),
(38, '25/03/2014', '28/03/2014', 1, 8, 'w8cnc6py', 'Active'),
(39, '18/03/2014', '19/03/2014', 1, 9, '5zuubzg6', 'Active'),
(40, '07/03/2015', '08/03/2015', 2, 9, 'mpawq3e3', 'Active'),
(41, '03/03/2018', '04/03/2018', 1, 8, 'ce7a5w45', 'Active'),
(42, '30/03/2015', '31/03/2015', 1, 8, 'ufhkmg2a', 'Active'),
(43, '11/03/2014', '12/03/2014', 1, 9, '0wvu2k3h', 'Active'),
(44, '', '', 0, 0, 'q75avs7m', 'Active'),
(45, '', '', 0, 0, 'vgo5kcwk', 'Active'),
(46, '11/03/2014', '12/03/2014', 1, 9, '0jerg0de', 'Active'),
(47, '12/03/2014', '12/03/2014', 1, 9, 'e58ryaqt', 'Active'),
(48, '25/03/2014', '31/03/2014', 1, 8, 'a2ezdj2w', 'Active'),
(49, '13/03/2015', '17/03/2015', 1, 8, 'xmefvzna', 'Active'),
(50, '24/03/2014', '25/03/2014', 1, 8, 'ggno87z3', 'Active'),
(51, '', '', 0, 0, 'q32jy5d4', 'Active'),
(52, '', '', 0, 0, 'fmo5adn7', 'Active'),
(53, '', '', 0, 0, 'ex07tqqw', 'Active'),
(54, '', '', 0, 0, 'x7ksftcr', 'Active'),
(55, '', '', 0, 0, 'gz0dpxnb', 'Active'),
(56, '', '', 0, 0, 'n2nniaf6', 'Active'),
(57, '', '', 0, 0, 'mjeve5xm', 'Active'),
(58, '', '', 0, 0, '7emy4iqr', 'Active'),
(59, '', '', 0, 0, 'no8tacfw', 'Active'),
(60, '', '', 0, 0, 'khb543bu', 'Active'),
(61, '', '', 0, 0, 'dir5m0gg', 'Active'),
(62, '', '', 0, 0, 'ygm4k2vn', 'Active'),
(63, '', '', 0, 0, 'ehivf2kt', 'Active'),
(64, '', '', 0, 0, 'srye62uq', 'Active'),
(65, '', '', 0, 0, 'jd8734xf', 'Active'),
(66, '', '', 0, 0, 'if4bn5zf', 'Active'),
(67, '', '', 0, 0, 'bm4wz4za', 'Active'),
(68, '', '', 0, 0, '8qcpy8m3', 'Active'),
(69, '', '', 0, 0, 'jf6ujt6d', 'Active'),
(70, '', '', 0, 0, 'heb4ekgo', 'Active'),
(71, '', '', 0, 0, '0uj44n25', 'Active'),
(72, '', '', 0, 0, 'j7swjpt2', 'Active'),
(73, '', '', 0, 0, 'yfozav36', 'Active'),
(74, '', '', 0, 0, 'fvhkpka6', 'Active'),
(75, '', '', 0, 0, 'wu54z5mg', 'Active'),
(76, '', '', 0, 0, 'sk3p45wf', 'Active'),
(77, '', '', 0, 0, 'fd0zizef', 'Active'),
(78, '', '', 0, 0, 'qb3s6sfb', 'Active'),
(79, '', '', 0, 0, 'at3e7rem', 'Active'),
(80, '', '', 0, 0, 'fhi2id2q', 'Active'),
(81, '', '', 0, 0, '8j8m2rad', 'Active'),
(82, '', '', 0, 0, 'i20p6nj2', 'Active'),
(83, '', '', 0, 0, 'v5qihgnu', 'Active'),
(84, '', '', 0, 0, 'm2ro2mm0', 'Active'),
(85, '', '', 0, 0, 'ftjdxoij', 'Active'),
(86, '', '', 0, 0, 'aedjapbf', 'Active'),
(87, '', '', 0, 0, 'tq53bnra', 'Active'),
(88, '', '', 0, 0, 'z0db8usj', 'Active'),
(89, '11/03/2014', '12/03/2014', 1, 9, 'fitp0k4w', 'Active'),
(90, '', '', 0, 0, 'n38fvbkj', 'Active'),
(91, '', '', 0, 0, 'rrwgjm5i', 'Active'),
(92, '', '', 0, 0, 'zc4gbgoo', 'Active'),
(93, '', '', 0, 0, 'q505fuk2', 'Active'),
(94, '18/03/2014', '26/03/2014', 1, 8, 'ppb7j625', 'Active'),
(95, '', '', 0, 0, 'fnacc6bv', 'Active'),
(96, '', '', 0, 0, 'rdwrswyo', 'Active'),
(97, '26/03/2016', '27/03/2016', 1, 9, 'rx6h53d6', 'Active'),
(98, '', '', 0, 0, '7ofipgex', 'Active'),
(99, '', '', 0, 0, '0qbhhxaq', 'Active'),
(100, '31/03/2014', '01/03/2015', 1, 10, 'nckz228t', 'Active'),
(101, '', '', 0, 0, '6q3k3oju', 'Active'),
(102, '30/03/2018', '31/03/2018', 1, 8, 'cn0tqurc', 'Active'),
(103, '', '', 0, 0, 'g4bfjjfh', 'Active'),
(104, '31/03/2017', '31/03/2017', 1, 8, 'dm3ht7zh', 'Active'),
(105, '25/03/2016', '26/03/2016', 1, 11, 'on74kdx2', 'Active'),
(106, '24/03/2014', '25/03/2014', 1, 8, 'qwsbr742', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `position` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `position`) VALUES
(1, 'admin', 'admin', 'admin'),
(2, 'argie', 'argie', 'frontdesk');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
