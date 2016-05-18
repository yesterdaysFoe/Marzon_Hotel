-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 21, 2014 at 03:54 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
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
(1, 'Superior', 'hssa40d5', 'Completed', 3000, 'PHP', '14G24020NN154222R', 'argiep_1323161216_per@gmail.com'),
(2, 'Standard Double', 'p2mzycvy', 'Completed', 33, 'PHP', '66J76616N2842804Y', 'argiep_1323161216_per@gmail.com');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=59 ;

--
-- Dumping data for table `products_and_services`
--

INSERT INTO `products_and_services` (`id`, `name`, `category`, `food_category`, `food_type`, `details`, `price`) VALUES
(2, 'Whole Body Massage', 'Spa and Pool', '', '', 'a unique experience of \nSwedish Massage', 250),
(3, 'Sprite 1LTT', 'SoftDrinks', '', '', 'Refreshingly clear', 30),
(4, 'Chocolate Cake', 'Cafe', '', '', 'a sweet and elegant cake', 90),
(5, 'Hand Towels', 'Hotel Service', '', '', 'clean and soft', 50),
(6, 'Body Towels', 'Hotel Service', '', '', 'clean and new', 50),
(7, 'Chicken Sandwich', 'Restaurant Menu', 'Snacks', '...', '', 110),
(8, 'Tuna Sandwich', 'Restaurant Menu', 'Snacks', '...', '', 110),
(9, 'Philly Cheese Steak', 'Restaurant Menu', 'Snacks', '...', '', 155),
(10, 'Extra Pillow', 'Hotel Service', '', '', 'feather pillow', 60),
(11, 'Extra Matress', 'Hotel Service', '', '', 'damul foam', 690),
(12, 'Extra Blanket', 'Hotel Service', '', '', 'comforter', 100),
(13, 'Laundry and Dry Cleaning', 'Hotel Service', '', '', '20 pesos per piece', 20),
(14, 'Hawaiian Spam', 'Restaurant Menu', 'Snacks', '...', '', 150),
(15, 'Seaside Sandwich', 'Restaurant Menu', 'Snacks', '...', '', 150),
(16, 'BBQ Bacon Cheese & Egg', 'Restaurant Menu', 'Snacks', '...', '', 150),
(17, 'Veggie Mozzarella Sandwich', 'Restaurant Menu', 'Snacks', '...', '', 140),
(18, 'Cheese Waffle', 'Restaurant Menu', 'Snacks', '...', '', 95),
(19, 'Blueberry Waffle', 'Restaurant Menu', 'Snacks', '...', '', 110),
(20, 'Chocolate Waffle', 'Restaurant Menu', 'Snacks', '...', '', 95),
(21, 'Banana Waffle', 'Restaurant Menu', 'Snacks', '...', '', 95),
(22, 'Mango Waffle', 'Restaurant Menu', 'Snacks', '...', '', 95),
(23, 'Bacon & Cheese Waffle', 'Restaurant Menu', 'Snacks', '...', '', 110),
(24, 'Ham & Cheese Waffle', 'Restaurant Menu', 'Snacks', '...', '', 110),
(25, 'Classic Spaghetti', 'Restaurant Menu', 'Snacks', '...', '', 120),
(26, 'Spaghetti with Meatballs', 'Restaurant Menu', 'Snacks', '...', '', 155),
(27, 'Baked Zitti', 'Restaurant Menu', 'Snacks', '...', '', 120),
(28, 'Chicken Carbonara', 'Restaurant Menu', 'Snacks', '...', '', 115),
(29, 'Beef Riblets', 'Restaurant Menu', 'Lunch/Dinner', 'Beefs', '', 324),
(30, 'Sizzling Bulalo Steak', 'Restaurant Menu', 'Lunch/Dinner', 'Beefs', '', 330),
(31, 'Grilled Pork Ribs', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 320),
(32, 'Grilled Pork Liempo', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 210),
(33, 'Streetfood Style G. Pork Ears', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 205),
(34, 'Honey Galzed Pork Belly', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 220),
(35, 'Hungarian Sausage', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 295),
(36, 'Bacon Roll-Ups', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 210),
(37, 'Lechon Kawali', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 195),
(38, 'Bopis', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 180),
(39, 'Pork Face Kare-kare', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 280),
(40, 'Pork BBQ', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 190),
(41, 'Pork Dinakdakan w/ s.egg', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 195),
(42, 'Pork Adobo sa Calamanci', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 210),
(43, 'Binagoongang Pork Liempo', 'Restaurant Menu', 'Lunch/Dinner', 'Porks', '', 220),
(44, 'Red Horse', 'Restaurant Menu', 'Beverage', '...', '', 35),
(45, 'San Mig Light', 'Restaurant Menu', 'Beverage', '...', '', 40),
(46, 'Red Wine', 'Restaurant Menu', 'Beverage', '...', '', 190),
(47, 'Espresso Shot', 'Restaurant Menu', 'Drinks', 'Hot', '', 68),
(48, 'Americano', 'Restaurant Menu', 'Drinks', 'Hot', '', 68),
(49, 'Cappucino', 'Restaurant Menu', 'Drinks', 'Hot', '', 95),
(50, 'Caffe Latte', 'Restaurant Menu', 'Drinks', 'Hot', '', 105),
(51, 'Caffe Mocha', 'Restaurant Menu', 'Drinks', 'Hot', '', 105),
(52, 'Kahlua Coffee', 'Restaurant Menu', 'Drinks', 'Hot', '', 135),
(53, 'Cafe Au Lait', 'Restaurant Menu', 'Drinks', 'Hot', '', 90),
(54, 'Strawberry Milkshake', 'Restaurant Menu', 'Drinks', 'Cold', '', 100),
(55, 'Chocolate Milkshake', 'Restaurant Menu', 'Drinks', 'Cold', '', 100),
(56, 'Cookie n Cream', 'Restaurant Menu', 'Drinks', 'Cold', '', 110),
(57, 'Latte Fudge', 'Restaurant Menu', 'Drinks', 'Cold', '', 110),
(58, 'Swimming Pool ', 'Hotel Service', '', '', 'Clear and clean', 100);

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
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=157 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `firstname`, `lastname`, `city`, `zip`, `province`, `country`, `email`, `contact`, `username`, `password`, `arrival`, `departure`, `adults`, `child`, `result`, `room_id`, `no_room`, `payable`, `status`, `confirmation`, `room_name_number`, `down_payment`) VALUES
(105, 'Anne', 'Curtis', 'Iloilo City', 5000, 'Iloilo Iloilo City', 'Philippines', 'anne@email.com', 1223456, '', '', '30/05/2014', '31/05/2014', 1, 0, 1, 12, 0, 0, 'CHECKED IN', '8e2ew0', NULL, 0),
(104, 'Angel', 'Locsin', 'Manila', 1000, 'Manila Philippines', 'Philippines', 'angel@gmail.com', 0, '', '', '30/04/2014', '03/05/2014', 1, 0, 4, 11, 0, 0, 'CHECKED IN', '3h000x', NULL, 0),
(106, 'tttt', 'tttt', 'ttt', 444, 'ttttt', 'ttttt', '444', 4444, '', '', '30/07/2014', '31/07/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'x0wa54', NULL, 0),
(107, 'UUUU', 'UUUU', 'UUUU', 5555, 'UUUU', 'UUUU', '5555@MAIL.COM', 0, '', '', '01/08/2014', '02/08/2014', 1, 0, 1, 9, 0, 0, 'CHECKED IN', 'phetaf', 'Room 1102', 0),
(108, 'QQQQ', 'QQQQ', 'QQQQ', 3333, 'QQQQ', 'QQQQ', 'QQQQ@MAIL.COM', 67678445, '', '', '19/08/2014', '20/08/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'c04bzn', NULL, 0),
(109, 'YYYY', 'YYYY', 'YYY', 6666, 'YYYYY', 'YYYY', '6666@MAIL.COM', 77777, '', '', '15/12/2014', '16/12/2014', 1, 0, 1, 8, 0, 0, 'CONFIRMED', 'yb4edu', 'Room 1103', 21),
(155, 'Bulog', 'NI', 'Mandaluyong', 333, 'aa', 'Philippines', 'test@mail.com', 0, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'npjnez', 'Room 1103', 400),
(154, 'Armold', 'Swatzenger', 'Texas', 9909, 'Texas', 'USA', 'texas@mail.com', 454545454, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'ufytbm', 'Room 1103', 20),
(134, '', '', '', 0, '', '', '', 0, '', '', '10/09/2014', '11/09/2014', 1, 0, 1, 8, 0, 0, 'CHECKED IN', 'o30sx5', 'Room 1103', 600),
(156, 'hello', 'hello', 'hello', 2100, 'hello', 'hello', 'hello@yahoo.com', 543431, '', '', '22/10/2014', '25/10/2014', 0, 0, 0, 9, 0, 0, 'CHECKED IN', '8ko6oj', 'Room 1102', 0);

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
(8, 'Standard Twin', 1250, 'relaxing', 'photos/twin.jpg', 0, 2, 2),
(9, 'Standard Queen', 1250, 'comfortable', 'photos/queen.jpg', 0, 3, 3),
(10, 'Standard Room', 2100, 'enjoyable', 'photos/standard.jpg', 0, 3, 3),
(11, 'Deluxe Room', 2930, 'amazing', 'photos/deluxe.jpg', 0, 3, 3),
(12, 'Family Room ', 3400, 'Bonding', 'photos/family.jpg', 0, 4, 3),
(13, 'Suite Room ', 3900, 'expensive', 'photos/suite.jpg', 0, 4, 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=161 ;

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
(159, '10/09/2014', '11/09/2014', 0, 8, 'npjnez', 'Active'),
(160, '22/10/2014', '25/10/2014', 0, 9, '8ko6oj', 'Active');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `name`, `room_type_id`, `description`) VALUES
(1, 'Room 101', 12, 'bondy'),
(2, 'Room 102', 8, 'normal'),
(3, 'Room 103', 9, 'royal'),
(4, 'Room 104', 8, 'normal\n'),
(5, 'Room 105', 9, 'royal'),
(6, 'Room 106', 8, 'normal'),
(7, 'Room 107', 8, 'normal'),
(8, 'Room 108', 9, 'royal'),
(9, 'Room 109', 9, 'royal'),
(10, 'Room 110', 10, 'just'),
(11, 'Room 111', 10, 'just'),
(12, 'Room 113', 10, 'just'),
(13, 'Room 114', 11, 'beautiful'),
(14, 'Room 115', 13, 'expensive'),
(15, 'Room 116', 9, 'royal'),
(16, 'Room 118', 11, 'ajkfh'),
(17, 'Room 200', 11, 'ajkfh'),
(18, 'Room 201', 12, 'ajkfh'),
(19, 'Room 205', 12, 'ajkfh'),
(20, 'Room 300', 13, 'ajkfh'),
(21, 'Room 305', 13, 'ajkfh');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=116 ;

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
(61, 2, '2014-08-16 00:00:00', 1),
(62, 2, '2014-10-14 00:00:00', 2),
(63, 2, '2014-10-15 00:00:00', 2),
(64, 2, '2014-10-14 00:00:00', 2),
(65, 2, '2014-10-15 00:00:00', 2),
(66, 2, '2014-10-14 00:00:00', 2),
(67, 2, '2014-10-15 00:00:00', 2),
(68, 2, '2014-10-14 00:00:00', 2),
(69, 2, '2014-10-15 00:00:00', 2),
(70, 2, '2014-10-16 00:00:00', 2),
(71, 2, '2014-10-17 00:00:00', 2),
(72, 2, '2014-10-18 00:00:00', 2),
(73, 2, '2014-10-19 00:00:00', 2),
(74, 2, '2014-10-20 00:00:00', 2),
(75, 2, '2014-10-14 00:00:00', 2),
(76, 2, '2014-10-15 00:00:00', 2),
(77, 2, '2014-10-16 00:00:00', 2),
(78, 2, '2014-10-17 00:00:00', 2),
(79, 2, '2014-10-18 00:00:00', 2),
(80, 2, '2014-10-19 00:00:00', 2),
(81, 2, '2014-10-20 00:00:00', 2),
(82, 2, '2014-10-14 00:00:00', 2),
(83, 2, '2014-10-15 00:00:00', 2),
(84, 2, '2014-10-16 00:00:00', 2),
(85, 2, '2014-10-17 00:00:00', 2),
(86, 2, '2014-10-18 00:00:00', 2),
(87, 2, '2014-10-19 00:00:00', 2),
(88, 2, '2014-10-20 00:00:00', 2),
(89, 2, '2014-10-14 00:00:00', 2),
(90, 2, '2014-10-15 00:00:00', 2),
(91, 2, '2014-10-16 00:00:00', 2),
(92, 2, '2014-10-17 00:00:00', 2),
(93, 2, '2014-10-18 00:00:00', 2),
(94, 2, '2014-10-19 00:00:00', 2),
(95, 2, '2014-10-20 00:00:00', 2),
(96, 2, '2014-10-14 00:00:00', 2),
(97, 2, '2014-10-15 00:00:00', 2),
(98, 2, '2014-10-16 00:00:00', 2),
(99, 2, '2014-10-17 00:00:00', 2),
(100, 2, '2014-10-18 00:00:00', 2),
(101, 2, '2014-10-19 00:00:00', 2),
(102, 2, '2014-10-20 00:00:00', 2),
(103, 1, '2014-11-01 00:00:00', 2),
(104, 1, '2014-11-02 00:00:00', 2),
(105, 1, '2014-11-03 00:00:00', 2),
(106, 1, '2014-11-04 00:00:00', 2),
(107, 1, '2014-11-05 00:00:00', 2),
(108, 1, '2014-10-22 00:00:00', 1),
(109, 1, '2014-10-23 00:00:00', 1),
(110, 1, '2014-10-24 00:00:00', 1),
(111, 1, '2014-10-25 00:00:00', 1),
(112, 1, '2014-10-22 00:00:00', 2),
(113, 1, '2014-10-23 00:00:00', 2),
(114, 1, '2014-10-24 00:00:00', 2),
(115, 1, '2014-10-25 00:00:00', 2);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=38 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `customer_email`, `custosmer_address`, `customer_tel_no`, `total_price`, `zip`, `customer_last_name`, `customer_first_name`, `discount_name`, `discount_amount`, `checked_out`) VALUES
(1, 'angel@gmail.com', 'Manila Philippines', '0', 250, '1000', 'Locsin', 'Angel', '', 0, 0),
(8, '', ' Bankers Subd.  ', '', 670, '', 'Aquino', 'Kris', '', 0, 1),
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
(23, 'test@mail.com', 'aa 333 333 333 333 333 333 333 333', '0', 6916, '333', 'NI', 'Bulog', '', 0, 1),
(24, 'theone@mail.com', 'No One 0', '0232211', 8550, '0', 'Name', 'The ', 'Senior Citizen Discount', 0.2, 1),
(25, 'texas@mail.com', 'Texas', '454545454', 0, '9909', 'Swatzenger', 'Armold', '...', 0, 1),
(31, 'NONE', 'NONE', 'NONE', 168, 'NONE', '56765756', 'WALK-IN:', '', 0, 1),
(32, '', ' Bankers Subd.', '', 192, '', 'Aquino', 'Kris', '', 0, 1),
(33, NULL, NULL, NULL, 0, NULL, NULL, NULL, '', 0, 1),
(34, NULL, NULL, NULL, 0, NULL, NULL, NULL, '', 0, 1),
(35, '5555@MAIL.COM', 'UUUU', '0', 0, '5555', 'UUUU', 'UUUU', '...', 0, 0),
(36, '', '', '', 0, '', '', '', '', 0, 0),
(37, 'hello@yahoo.com', 'hello', '543431', 0, '2100', 'hello', 'hello', '...', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `transaction_discounts`
--

CREATE TABLE IF NOT EXISTS `transaction_discounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `comments` varchar(255) NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transaction_id` (`transaction_id`),
  KEY `transaction_id_2` (`transaction_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `transaction_discounts`
--

INSERT INTO `transaction_discounts` (`id`, `amount`, `name`, `type`, `comments`, `transaction_id`) VALUES
(1, 20, 'Senior Citizen Discount', 'RESTAURANT_DISCOUNT', '3242', 10),
(2, 20, 'Senior Citizen Discount', 'RESTAURANT_DISCOUNT', '678678', 23),
(6, 20, 'Senior Citizen Discount', 'RESTAURANT_DISCOUNT', '56756', 31),
(7, 20, 'Senior Citizen Discount', 'RESTAURANT_DISCOUNT', '5567657', 32),
(8, 20, 'Senior Citizen Discount', 'HOTEL_DISCOUNT', '5656565', NULL),
(9, 20, 'Senior Citizen Discount', 'HOTEL_DISCOUNT', '6767', 23);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

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
(8, 25, 20, '2014-10-14 22:27:12', 'Down Payment'),
(9, NULL, 677, '2014-10-19 17:59:57', ''),
(10, NULL, 300, '2014-10-19 18:04:18', ''),
(11, 35, 0, '2014-10-19 23:02:04', 'Down Payment'),
(12, 37, 0, '2014-10-20 09:02:55', 'Down Payment');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=33 ;

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
(14, 9, 2, 60, 120, 16),
(15, 8, 2, 35, 70, 1),
(16, 8, 2, 35, 70, 1),
(17, 9, 2, 60, 120, 21),
(18, 8, 3, 35, 105, 19),
(19, 9, 3, 60, 180, 21),
(20, 8, 2, 35, 70, 21),
(21, 9, 5, 60, 300, 23),
(22, 8, 4, 35, 140, 23),
(23, 8, 5, 35, 175, 10),
(24, 8, 3, 35, 105, 23),
(25, 9, 10, 60, 600, 23),
(26, 8, 6, 35, 210, NULL),
(27, 9, 4, 60, 240, NULL),
(28, 9, 30, 60, 1800, 23),
(29, 8, 2, 35, 70, 1),
(30, 9, 2, 60, 120, 1),
(31, 9, 1, 60, 60, 8),
(32, 7, 1, 20, 20, 8);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

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
(12, 8, 23, '2014-10-14', '2014-10-19', 950, 1, 2),
(13, 8, 24, '2014-10-17', '2014-10-25', 950, 1, 2),
(14, 8, 25, '2014-09-10', '2014-09-11', 950, 1, 2),
(15, 9, 35, '2014-11-01', '2014-11-05', 1100, 1, 1),
(16, 9, 37, '2014-10-22', '2014-10-25', 1100, 1, 1);

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
-- Constraints for table `transaction_discounts`
--
ALTER TABLE `transaction_discounts`
  ADD CONSTRAINT `transaction_discounts_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`);

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
