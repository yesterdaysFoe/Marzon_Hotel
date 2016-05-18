-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 17, 2014 at 05:51 PM
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
  `show_other_info` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `discounts`
--

INSERT INTO `discounts` (`id`, `name`, `amount`, `active`, `show_other_info`) VALUES
(1, 'Senior Citizen Discount', 20, 1, 0);

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
  `status` varchar(20) NOT NULL,
  `confirmation` varchar(20) NOT NULL,
  `room_name_number` varchar(55) DEFAULT NULL,
  `down_payment` double NOT NULL,
  PRIMARY KEY (`reservation_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `firstname`, `lastname`, `city`, `zip`, `province`, `country`, `email`, `contact`, `username`, `password`, `arrival`, `departure`, `adults`, `child`, `result`, `room_id`, `no_room`, `payable`, `status`, `confirmation`, `room_name_number`, `down_payment`) VALUES
(3, 'iiii', 'iy78', '67867', 678, 'iii', '67767', 'MAIL@MAIL.COM', 776767, '', '', '20/11/2014', '22/11/2014', 1, 0, 2, 8, 0, 0, 'CHECKED OUT', 'g3vmdb', 'Room 1103', 475);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=171 ;

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
(160, '22/10/2014', '23/10/2014', 0, 9, 'oteka6', 'Active'),
(161, '28/10/2014', '29/10/2014', 0, 9, 'teyyxg', 'Active'),
(162, '30/10/2014', '31/10/2014', 0, 13, '2yk26k', 'Active'),
(163, '', '', 0, 9, 'g47o0e', 'Active'),
(164, '', '', 0, 9, 'h32ewm', 'Active'),
(165, '', '', 0, 9, 'vavm8g', 'Active'),
(166, '31/10/2014', '31/10/2014', 0, 9, '28qfu6', 'Active'),
(167, '30/10/2014', '31/10/2014', 0, 8, 'tesuny', 'Active'),
(168, '20/11/2014', '21/11/2014', 0, 8, '4nhjop', 'Active'),
(169, '20/11/2014', '21/11/2014', 0, 8, '44wia0', 'Active'),
(170, '20/11/2014', '22/11/2014', 0, 8, 'g3vmdb', 'Active');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `room_status`
--

INSERT INTO `room_status` (`id`, `room_id`, `start_date`, `status`) VALUES
(19, 2, '2014-11-17 01:26:52', 2),
(20, 2, '2014-11-18 01:26:52', 2),
(21, 2, '2014-11-17 00:00:00', 2),
(22, 2, '2014-11-18 00:00:00', 2);

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
  `total_hotel_bill` double DEFAULT NULL,
  `total_restaurant_bill` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  `reservation_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=52 ;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`id`, `customer_email`, `custosmer_address`, `customer_tel_no`, `total_price`, `zip`, `customer_last_name`, `customer_first_name`, `discount_name`, `discount_amount`, `checked_out`, `total_hotel_bill`, `total_restaurant_bill`, `date`, `reservation_id`) VALUES
(51, 'MAIL@MAIL.COM', 'iii 678', '776767', 760, '678', 'iy78', 'iiii', '', 0, 1, 950, 0, '2014-11-17', '3');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `transaction_discounts`
--

INSERT INTO `transaction_discounts` (`id`, `amount`, `name`, `type`, `comments`, `transaction_id`) VALUES
(1, 20, 'Senior Citizen Discount', 'HOTEL_DISCOUNT', '78876655', 51);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `transaction_payments`
--

INSERT INTO `transaction_payments` (`id`, `transaction_id`, `amount`, `date_of_payment`, `name`) VALUES
(1, 51, 475, '2014-11-17 01:27:03', 'Down Payment'),
(2, NULL, 95, '2014-11-17 01:27:43', '');

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `transaction_rooms`
--

INSERT INTO `transaction_rooms` (`id`, `room_id`, `transaction_id`, `arrival_date`, `departure_date`, `price`, `qty`, `room`) VALUES
(1, 8, 51, '2014-11-17', '2014-11-17', 950, 1, 2);

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
