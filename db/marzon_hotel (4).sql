-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 14, 2014 at 08:51 PM
-- Server version: 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `marzon_hotel`
--

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
-- Table structure for table `discounts`
--

CREATE TABLE IF NOT EXISTS `discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `discounts`
--

INSERT INTO `discounts` (`id`, `name`, `amount`, `active`) VALUES
(1, 'Senior Citizen Discount', 20, 1);

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
(1, 'Superior', 'hssa40d5', 'Completed', '3000', 'PHP', '14G24020NN154222R', 'argiep_1323161216_per@gmail.com'),
(2, 'Standard Double', 'p2mzycvy', 'Completed', '33', 'PHP', '66J76616N2842804Y', 'argiep_1323161216_per@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `products_and_services`
--

CREATE TABLE IF NOT EXISTS `products_and_services` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `food_category` varchar(200) DEFAULT NULL,
  `food_type` varchar(200) DEFAULT NULL,
  `details` varchar(255) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `products_and_services`
--

INSERT INTO `products_and_services` (`id`, `name`, `category`, `food_category`, `food_type`, `details`, `price`) VALUES
(2, 'Whole Body Massage', 'Spa and Pool', '', '', 'a unique experience of \nSwedish Massage', 250),
(3, 'Sprite 1LTT', 'SoftDrinks', '', '', 'Refreshingly clear', 30),
(4, 'Chocolate Cake', 'Cafe', '', '', 'a sweet and elegant cake', 90),
(5, 'RRRR', 'Hotel Service', '', '', 'rrr', 231),
(6, 'Swimming Pool', 'Hotel Service', '', '', 'Crystal Clear Swimming Pool', 200),
(7, 'Apple Juice', 'Restaurant Menu', 'Drinks', 'Cold', 'Sweet apple taste juice', 20),
(8, 'Chocolate Cake ', 'Restaurant Menu', 'Dessert', 'Cakes', 'Chocolate cake with cherry toppings', 35),
(9, 'Mocha Cake', 'Restaurant Menu', 'Dessert', 'Cakes', 'Mocha Cake with chocolate toppings', 60),
(10, 'ooo', 'Hotel Service', '', '', 'ooo', 240),
(11, 'Test Add', 'Hotel Service', '', '', 'ad', 12),
(12, 'Massage Head to Toe', 'Hotel Service', '', '', 'gentle massage', 400),
(13, 'Sweet Horse Back Ridding', 'Hotel Service', '', '', '', 250);

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
  `room_name_number` varchar(55) DEFAULT NULL,
  `down_payment` double NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=156 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `firstname`, `lastname`, `city`, `zip`, `province`, `country`, `email`, `contact`, `username`, `password`, `arrival`, `departure`, `adults`, `child`, `result`, `room_id`, `no_room`, `payable`, `status`, `confirmation`, `room_name_number`, `down_payment`) VALUES
(105, 'Anne', 'Curtis', 'Iloilo City', 5000, 'Iloilo Iloilo City', 'Philippines', 'anne@email.com', 1223456, '', '', '30/05/2014', '31/05/2014', 1, 0, 1, 12, 0, 0, 'CHECKED IN', '8e2ew0', NULL, 0),
(104, 'Angel', 'Locsin', 'Manila', 1000, 'Manila Philippines', 'Philippines', 'angel@gmail.com', 0, '', '', '30/04/2014', '03/05/2014', 1, 0, 4, 11, 0, 0, 'CHECKED IN', '3h000x', NULL, 0),
(106, 'tttt', 'tttt', 'ttt', 444, 'ttttt', 'ttttt', '444', 4444, '', '', '30/07/2014', '31/07/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'x0wa54', NULL, 0),
(107, 'UUUU', 'UUUU', 'UUUU', 5555, 'UUUU', 'UUUU', '5555@MAIL.COM', 0, '', '', '01/08/2014', '02/08/2014', 1, 0, 1, 9, 0, 0, 'CONFIRMED', 'phetaf', 'Room 1102', 0),
(108, 'QQQQ', 'QQQQ', 'QQQQ', 3333, 'QQQQ', 'QQQQ', 'QQQQ@MAIL.COM', 67678445, '', '', '19/08/2014', '20/08/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'c04bzn', NULL, 0),
(109, 'YYYY', 'YYYY', 'YYY', 6666, 'YYYYY', 'YYYY', '6666@MAIL.COM', 77777, '', '', '15/08/2014', '16/08/2014', 1, 0, 1, 8, 0, 0, 'CONFIRMED', 'yb4edu', 'Room 1103', 21),
(155, 'Bulog', 'NI', 'Mandaluyong', 333, 'aa', 'Philippines', 'test@mail.com', 0, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'npjnez', 'Room 1103', 400),
(154, 'Armold', 'Swatzenger', 'Texas', 9909, 'Texas', 'USA', 'texas@mail.com', 454545454, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'ufytbm', 'Room 1103', 20),
(134, '', '', '', 0, '', '', '', 0, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'o30sx5', 'Room 1103', 600);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `type`, `rate`, `description`, `image`, `qty`, `max_adult`, `max_child`) VALUES
(8, 'Standard Single', 950, 'Fully air conditioned', 'photos/Single.jpg', 3, 1, 1),
(9, 'Standard Double', 1100, 'Fully air conditioned', 'photos/images.jpg', 3, 1, 1),
(10, 'The Room One', 2000, '1', 'photos/images.jpg', 0, 0, 0),
(11, 'Twin Room', 2000, '1', 'photos/StandardTwinSharing_600x450P1_3348.jpg', 3, 0, 0),
(12, 'Dark Room', 500, 'Dark and Hot', 'photos/SuiteRoom_600x450P1_3336.jpg', 10, 0, 0),
(13, 'The Room Six One', 1200, '1', 'photos/images.jpg', 0, 0, 0);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=160 ;

--
-- Dumping data for table `roominventory`
--

INSERT INTO `roominventory` (`roominventory_id`, `arrival`, `departure`, `qty_reserve`, `room_id`, `confirmation`, `status`) VALUES
(110, '30/07/2014', '31/07/2014', 0, 8, 'x0wa54', 'Active'),
(111, '01/08/2014', '02/08/2014', 0, 9, 'phetaf', 'Active'),
(112, '19/08/2014', '20/08/2014', 0, 8, 'c04bzn', 'Active'),
(113, '15/08/2014', '16/08/2014', 0, 8, 'yb4edu', 'Active'),
(114, '09/09/2014', '10/09/2014', 0, 8, 'g85yqx', 'Active'),
(115, '16/09/2014', '17/09/2014', 0, 8, '0u5fd4', 'Active'),
(116, '09/09/2014', '11/09/2014', 0, 8, 'w6zwe7', 'Active'),
(117, '09/09/2014', '11/09/2014', 0, 8, '2tcxee', 'Active'),
(118, '09/09/2014', '11/09/2014', 0, 8, 's28msi', 'Active'),
(119, '09/09/2014', '11/09/2014', 0, 8, 'tgwy3w', 'Active'),
(120, '16/09/2014', '17/09/2014', 0, 8, 'bg8aey', 'Active'),
(121, '23/09/2014', '24/09/2014', 0, 8, '8pct4x', 'Active'),
(122, '25/09/2014', '26/09/2014', 0, 8, '6sf3f6', 'Active'),
(123, '26/09/2014', '27/09/2014', 0, 8, 'xvxmis', 'Active'),
(124, '26/09/2014', '27/09/2014', 0, 8, 'kosdhu', 'Active'),
(125, '26/09/2014', '27/09/2014', 0, 8, 'nxat5j', 'Active'),
(126, '11/09/2014', '12/09/2014', 0, 8, 'xjv3h0', 'Active'),
(127, '11/09/2014', '12/09/2014', 0, 8, '4k5zu2', 'Active'),
(128, '11/09/2014', '12/09/2014', 0, 8, '5wbo3q', 'Active'),
(129, '11/09/2014', '12/09/2014', 0, 8, 'siuqqb', 'Active'),
(130, '11/09/2014', '12/09/2014', 0, 8, 'mxvg57', 'Active'),
(131, '10/09/2014', '11/09/2014', 0, 8, '7tz2vj72', 'Active'),
(132, '10/09/2014', '11/09/2014', 0, 8, '8tek2khs', 'Active'),
(133, '10/09/2014', '11/09/2014', 0, 8, 'ze0zcf', 'Active'),
(134, '10/09/2014', '11/09/2014', 0, 8, 'iyy8s2', 'Active'),
(135, '10/09/2014', '11/09/2014', 0, 8, 'ipoyeg', 'Active'),
(136, '10/09/2014', '11/09/2014', 0, 8, '8847e6', 'Active'),
(137, '10/09/2014', '11/09/2014', 0, 8, 'td5gyp', 'Active'),
(138, '10/09/2014', '11/09/2014', 0, 8, 'o30sx5', 'Active'),
(139, '10/09/2014', '11/09/2014', 0, 8, 'ibqz4w', 'Active'),
(140, '10/09/2014', '11/09/2014', 0, 8, 'a036av', 'Active'),
(141, '10/09/2014', '11/09/2014', 0, 8, 'h04er5', 'Active'),
(142, '10/09/2014', '11/09/2014', 0, 8, 'jk5ndv', 'Active'),
(143, '10/09/2014', '11/09/2014', 0, 8, 'mxv0gg', 'Active'),
(144, '10/09/2014', '11/09/2014', 0, 8, '7tfy8h', 'Active'),
(145, '10/09/2014', '11/09/2014', 0, 8, 'x63u0o', 'Active'),
(146, '10/09/2014', '11/09/2014', 0, 8, 'gmce4c', 'Active'),
(147, '10/09/2014', '11/09/2014', 0, 8, '3p0s42', 'Active'),
(148, '10/09/2014', '11/09/2014', 0, 8, 'jf3vf8', 'Active'),
(149, '10/09/2014', '11/09/2014', 0, 8, '8svqt4', 'Active'),
(150, '10/09/2014', '11/09/2014', 0, 8, 'mch2fm', 'Active'),
(151, '10/09/2014', '11/09/2014', 0, 8, 'bz0hjv', 'Active'),
(152, '10/09/2014', '11/09/2014', 0, 8, 'xfps5k', 'Active'),
(153, '10/09/2014', '11/09/2014', 0, 8, 'zxi2mi', 'Active'),
(154, '10/09/2014', '11/09/2014', 0, 8, 't3fs2m', 'Active'),
(155, '10/09/2014', '11/09/2014', 0, 8, 'hhj685', 'Active'),
(156, '10/09/2014', '11/09/2014', 0, 8, '536e4n', 'Active'),
(157, '10/09/2014', '11/09/2014', 0, 8, '5vkiwc', 'Active'),
(158, '10/09/2014', '11/09/2014', 0, 8, 'ufytbm', 'Active'),
(159, '10/09/2014', '11/09/2014', 0, 8, 'npjnez', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `room_type_id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `room_type_id`, `description`) VALUES
(1, 'Room 1102', 9, 'No desc'),
(2, 'Room 1103', 8, 'Near Elevator'),
(3, 'Rooms 601', 13, 'near the fire exit');

-- --------------------------------------------------------

--
-- Table structure for table `room_status`
--

CREATE TABLE IF NOT EXISTS `room_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `start_date` datetime NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_id` (`room_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=62 ;

--
-- Dumping data for table `room_status`
--

INSERT INTO `room_status` (`id`, `room_id`, `start_date`, `status`) VALUES
(3, 1, '2014-06-21 00:00:00', 1),
(4, 1, '2014-06-22 00:00:00', 2),
(5, 1, '2014-07-05 13:56:28', 0),
(6, 2, '2014-07-12 14:00:20', 1),
(7, 2, '2014-07-13 14:00:20', 1),
(8, 2, '2014-07-05 14:02:53', 2),
(9, 2, '2014-07-06 14:02:53', 2),
(10, 2, '2014-07-07 14:02:53', 2),
(11, 1, '2014-05-30 00:00:00', 2),
(12, 1, '2014-05-30 00:00:00', 2),
(13, 1, '2014-05-30 00:00:00', 2),
(14, 2, '2014-07-30 00:00:00', 1),
(15, 2, '2014-07-30 00:00:00', 1),
(16, 2, '2014-08-01 00:00:00', 1),
(17, 2, '2014-08-19 00:00:00', 1),
(18, 2, '2014-08-19 00:00:00', 1),
(19, 2, '2014-08-01 00:00:00', 2),
(20, 2, '2014-08-01 00:00:00', 2),
(21, 2, '2014-08-19 00:00:00', 2),
(22, 2, '2014-08-19 00:00:00', 2),
(31, 2, '2014-08-22 00:00:00', 2),
(32, 1, '2014-08-01 00:00:00', 1),
(33, 1, '2014-08-02 00:00:00', 1),
(34, 2, '2014-09-10 00:00:00', 1),
(35, 2, '2014-09-11 00:00:00', 1),
(36, 2, '2014-10-14 00:00:00', 2),
(37, 2, '2014-10-15 00:00:00', 2),
(38, 2, '2014-10-17 00:00:00', 2),
(39, 2, '2014-10-18 00:00:00', 2),
(40, 2, '2014-10-19 00:00:00', 2),
(41, 2, '2014-10-20 00:00:00', 2),
(42, 2, '2014-10-21 00:00:00', 2),
(43, 2, '2014-10-22 00:00:00', 2),
(44, 2, '2014-10-23 00:00:00', 2),
(45, 2, '2014-10-24 00:00:00', 2),
(46, 2, '2014-10-25 00:00:00', 2),
(47, 2, '2014-10-17 00:00:00', 2),
(48, 2, '2014-10-18 00:00:00', 2),
(49, 2, '2014-10-19 00:00:00', 2),
(50, 2, '2014-10-20 00:00:00', 2),
(51, 2, '2014-10-21 00:00:00', 2),
(52, 2, '2014-10-22 00:00:00', 2),
(53, 2, '2014-10-23 00:00:00', 2),
(54, 2, '2014-10-24 00:00:00', 2),
(55, 2, '2014-10-25 00:00:00', 2),
(56, 2, '2014-09-10 00:00:00', 1),
(57, 2, '2014-09-11 00:00:00', 1),
(58, 2, '2014-09-10 00:00:00', 2),
(59, 2, '2014-09-11 00:00:00', 2),
(60, 2, '2014-08-15 00:00:00', 1),
(61, 2, '2014-08-16 00:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_email` varchar(255) DEFAULT NULL,
  `custosmer_address` varchar(255) DEFAULT NULL,
  `customer_tel_no` varchar(255) DEFAULT NULL,
  `total_price` double NOT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `customer_last_name` varchar(255) DEFAULT NULL,
  `customer_first_name` varchar(255) DEFAULT NULL,
  `discount_name` varchar(50) NOT NULL,
  `discount_amount` double NOT NULL,
  `checked_out` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `customer_email`, `custosmer_address`, `customer_tel_no`, `total_price`, `zip`, `customer_last_name`, `customer_first_name`, `discount_name`, `discount_amount`, `checked_out`) VALUES
(1, 'angel@gmail.com', 'Manila Philippines', '0', 250, '1000', 'Locsin', 'Angel', '', 0, 0),
(8, '', ' Bankers Subd.', '', 590, '', 'Aquino', 'Kris', '', 0, 0),
(9, 'anne@email.com', 'Iloilo Iloilo City 5000', '1223456', 500, '5000', 'Curtis', 'Anne', '', 0, 0),
(10, '', ' ', '', 1130, '', 'Salvador', 'Maja', '', 0, 0),
(11, 'f4#mail.com', 'taipei 88888 88888', '099090', 1380, '88888', 'Chii', 'San', '', 0, 0),
(12, 'FFFF@MAIL.COM', 'FFFF 1222', '433442', 950, '1222', 'FFFF', 'FFFF', '', 0, 0),
(13, 'i@mail.com', 'iiii', '5565656', 950, '45', 'iiii', 'iiii', '', 0, 0),
(14, 'anne@email.com', 'Iloilo Iloilo City', '1223456', 0, '5000', 'Curtis', 'Anne', '', 0, 0),
(15, 'anne@email.com', 'Iloilo Iloilo City', '1223456', 0, '5000', 'Curtis', 'Anne', '', 0, 0),
(16, 'anne@email.com', 'Iloilo Iloilo City 5000 5000', '1223456', 655, '5000', 'Curtis', 'Anne', '...', 0.2, 0),
(17, '444', 'ttttt', '4444', 0, '444', 'tttt', 'tttt', '...', 0, 0),
(18, '444', 'ttttt', '4444', 0, '444', 'tttt', 'tttt', '...', 0, 0),
(19, '5555@MAIL.COM', 'UUUU', '0', 0, '5555', 'UUUU', 'UUUU', '...', 0, 0),
(20, 'QQQQ@MAIL.COM', 'QQQQ', '67678445', 0, '3333', 'QQQQ', 'QQQQ', '...', 0, 0),
(21, '6666@MAIL.COM', 'YYYYY 6666', '77777', 950, '6666', 'YYYY', 'YYYY', '20% - Senior Citizen', 0.2, 0),
(22, '6666@MAIL.COM', 'YYYYY', '77777', 0, '6666', 'YYYY', 'YYYY', '...', 0, 0),
(23, 'test@mail.com', 'aa', '0', 0, '333', 'NI', 'Bulog', '...', 0, 0),
(24, 'theone@mail.com', 'No One 0', '0232211', 8550, '0', 'Name', 'The ', 'Senior Citizen Discount', 0.2, 1),
(25, 'texas@mail.com', 'Texas', '454545454', 0, '9909', 'Swatzenger', 'Armold', '...', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_payments`
--

CREATE TABLE IF NOT EXISTS `transaction_payments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_id` int(11) DEFAULT NULL,
  `amount` double NOT NULL,
  `date_of_payment` datetime NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_id` (`transaction_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `transaction_payments`
--

INSERT INTO `transaction_payments` (`id`, `transaction_id`, `amount`, `date_of_payment`, `name`) VALUES
(2, NULL, 10, '2014-05-01 00:00:00', ''),
(3, 8, 20, '2014-05-01 00:00:00', ''),
(4, 10, 500, '2014-05-01 00:00:00', ''),
(5, 11, 460, '2014-05-01 00:00:00', ''),
(6, 11, 200, '2014-05-01 00:00:00', ''),
(7, 24, 600, '2014-10-14 00:44:07', 'Down Payment'),
(8, 25, 20, '2014-10-14 22:27:12', 'Down Payment');

-- --------------------------------------------------------

--
-- Table structure for table `transaction_products`
--

CREATE TABLE IF NOT EXISTS `transaction_products` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_and_services_id` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` double NOT NULL,
  `total_price` double NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_and_services_id` (`product_and_services_id`),
  KEY `transaction_id` (`transaction_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `transaction_products`
--

INSERT INTO `transaction_products` (`id`, `product_and_services_id`, `qty`, `price`, `total_price`, `transaction_id`) VALUES
(7, 3, 3, 30, 90, 8),
(8, 2, 2, 250, 500, 8),
(9, 2, 4, 250, 250, NULL),
(10, 3, 1, 30, 30, 10),
(11, 3, 3, 30, 30, 11),
(12, 2, 1, 250, 250, 11),
(13, 8, 1, 35, 35, 16),
(14, 9, 2, 60, 120, 16);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_rooms`
--

CREATE TABLE IF NOT EXISTS `transaction_rooms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `room_id` int(11) NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `arrival_date` date NOT NULL,
  `departure_date` date NOT NULL,
  `price` double NOT NULL,
  `qty` int(11) NOT NULL,
  `room` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_id` (`transaction_id`),
  KEY `room` (`room`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `transaction_rooms`
--

INSERT INTO `transaction_rooms` (`id`, `room_id`, `transaction_id`, `arrival_date`, `departure_date`, `price`, `qty`, `room`) VALUES
(1, 8, 12, '2014-07-05', '2014-07-05', 950, 1, 2),
(2, 8, 13, '2014-07-05', '2014-07-07', 950, 1, 2),
(3, 9, 14, '2014-05-30', '2014-05-31', 500, 1, 1),
(4, 9, 15, '2014-05-30', '2014-05-31', 500, 1, 1),
(5, 9, 16, '2014-05-30', '2014-05-31', 500, 1, 1),
(6, 8, 17, '2014-07-30', '2014-07-31', 950, 1, 2),
(7, 8, 18, '2014-07-30', '2014-07-31', 950, 1, 2),
(8, 8, 19, '2014-08-01', '2014-08-02', 1100, 1, 2),
(9, 8, 20, '2014-08-19', '2014-08-20', 950, 1, 2),
(10, 8, 21, '2014-08-15', '2014-08-16', 950, 1, 2),
(11, 8, 22, '2014-08-22', '2014-08-22', 950, 1, 2),
(12, 8, 23, '2014-10-14', '2014-10-15', 950, 1, 2),
(13, 8, 24, '2014-10-17', '2014-10-25', 950, 1, 2),
(14, 8, 25, '2014-09-10', '2014-09-11', 950, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `position` varchar(45) NOT NULL,
  `active` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `username`, `password`, `position`, `active`) VALUES
(1, 'admin', 'admin', 'admin', 1),
(3, 'r', 'r', 'Restaurant Cashier', 1),
(4, 'a', 'a', 'Front Desk', 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `room_status`
--
ALTER TABLE `room_status`
  ADD CONSTRAINT `room_status_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`);

--
-- Constraints for table `transaction_payments`
--
ALTER TABLE `transaction_payments`
  ADD CONSTRAINT `transaction_payments_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`);

--
-- Constraints for table `transaction_products`
--
ALTER TABLE `transaction_products`
  ADD CONSTRAINT `transaction_products_ibfk_1` FOREIGN KEY (`product_and_services_id`) REFERENCES `products_and_services` (`id`),
  ADD CONSTRAINT `transaction_products_ibfk_2` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`);

--
-- Constraints for table `transaction_rooms`
--
ALTER TABLE `transaction_rooms`
  ADD CONSTRAINT `transaction_rooms_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`),
  ADD CONSTRAINT `transaction_rooms_ibfk_2` FOREIGN KEY (`room`) REFERENCES `rooms` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
