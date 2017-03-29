-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost
-- Thời gian đã tạo: Th3 29, 2017 lúc 08:30 SA
-- Phiên bản máy phục vụ: 5.7.17-log
-- Phiên bản PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `btcars`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `car`
--

CREATE TABLE `car` (
  `id` bigint(20) NOT NULL,
  `make` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `trim` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `price` int(11) NOT NULL,
  `ed` int(11) DEFAULT NULL,
  `ev` int(11) DEFAULT NULL,
  `emp` int(11) DEFAULT NULL,
  `w` int(11) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `car`
--

INSERT INTO `car` (`id`, `make`, `model`, `year`, `trim`, `country`, `price`, `ed`, `ev`, `emp`, `w`, `status`, `quantity`, `sold`) VALUES
(1, 'Rolls-Royce', 'Ghost Series II', 2015, 'EWB 4dr Sedan (6.6L 12cyl Turbo 8A)', 'UK', 319400, 7, 48, 555, 5490, 1, 20, 5),
(2, 'Lamborghini', 'Aventador', 2015, 'LP 700-4 2dr Coupe AWD (6.5L 12cyl 7AM)', 'Italy', 397500, 7, 48, 700, 3472, 1, 10, 2),
(3, 'Lamborghini', 'Aventador', 2015, 'LP 720-4 50 Anniversario 2dr Coupe AWD (6.5L 12cyl 7AM)', 'Italy', 497650, 7, 48, 720, 3472, 1, 10, 6),
(4, 'Lamborghini', 'Murcielago', 2010, 'LP 670-4 SuperVeloce 2dr Coupe AWD (6.5L 12cyl 6AM)', 'Italy', 450000, 7, 48, 670, 3450, 1, 20, 5),
(5, 'Porsche', 'Boxster', 2015, 'GTS 2dr Convertible (3.4L 6cyl 6M)', 'Germany', 73500, 3, 24, 330, 2965, 1, 17, 2),
(6, 'Porsche', 'Panamera', 2015, 'Turbo S Executive 4dr Sedan AWD (4.8L 8cyl Turbo 7AM)', 'Germany', 200500, 5, 32, 570, 4586, 1, 12, 3),
(7, 'Lamborghini', 'Gallardo', 2014, 'LP 570-4 Superleggera Edizione Tecnica 2dr Coupe AWD (5.2L 10cyl 6M)', 'Italy', 241200, 5, 40, 570, 2954, 1, 16, 3),
(8, 'Rolls-Royce', 'Wraith', 2014, '2dr Coupe (6.6L 12cyl Turbo 8A)', 'UK', 284900, 7, 48, 615, 2360, 1, 18, 4),
(9, 'Lamborghini', 'Huracan', 2015, 'LP 610-4 2dr Coupe AWD (5.2L 10cyl 7AM)', 'Italy', 237250, 5, 40, 610, 3135, 1, 19, 4),
(10, 'Porsche', '918 Spyder', 2015, 'Weissach Package 2dr Convertible AWD (4.6L 8cyl gas/electric hybrid 7AM)', 'Germany', 929000, 5, 32, 887, 3602, 1, 12, 9),
(11, 'Porsche', 'Macan', 2015, 'Turbo 4dr SUV AWD (3.6L 6cyl Turbo 7AM)', 'Germany', 72300, 4, 24, 400, 4244, 1, 15, 4),
(12, 'Rolls-Royce', 'Phantom Drophead Coupe', 2014, '2dr Convertible (6.7L 12cyl 8A)', 'UK', 474600, 7, 48, 453, 5995, 1, 11, 2),
(13, 'Ferrari', '458 Italia', 2014, 'Speciale 2dr Coupe (4.5L 8cyl 7AM)', 'Italy', 288000, 5, 32, 597, NULL, 1, 12, 2),
(14, 'Ferrari', 'Enzo', 2003, '2dr Coupe (6.0L 12cyl 6AM)', 'Italy', 643330, 6, 48, 660, 3009, 0, 0, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `user_id`) VALUES
(1, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer_car`
--

CREATE TABLE `customer_car` (
  `cars_id` bigint(20) NOT NULL,
  `customers_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `customer_car`
--

INSERT INTO `customer_car` (`cars_id`, `customers_id`) VALUES
(3, 1),
(4, 1),
(8, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `databasechangelog`
--

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `databasechangelog`
--

INSERT INTO `databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
('00000000000001', 'jhipster', 'classpath:config/liquibase/changelog/00000000000000_initial_schema.xml', '2017-03-28 17:14:38', 1, 'EXECUTED', '7:5b9592ea11019ccbdb02a6170bc13cd8', 'createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328101105-1', 'jhipster', 'classpath:config/liquibase/changelog/20170328101105_added_entity_Car.xml', '2017-03-28 17:14:39', 2, 'EXECUTED', '7:0e4104030ecdb50e7bafda556aa5f54e', 'createTable tableName=car', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328101105-1', 'jhipster', 'classpath:config/liquibase/changelog/20170328101105_added_entity_Customer.xml', '2017-03-28 17:14:39', 3, 'EXECUTED', '7:3ca0d103e6e08b64976f8391655ae2cf', 'createTable tableName=customer; createTable tableName=customer_car; addPrimaryKey tableName=customer_car', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328101105-1', 'jhipster', 'classpath:config/liquibase/changelog/20170328101105_added_entity_Orderlist.xml', '2017-03-28 17:14:41', 4, 'EXECUTED', '7:4a98f5cb754469f49244ec03d0ab5acd', 'createTable tableName=orderlist; createTable tableName=orderlist_car; addPrimaryKey tableName=orderlist_car', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328101105-2', 'jhipster', 'classpath:config/liquibase/changelog/20170328101105_added_entity_constraints_Customer.xml', '2017-03-28 17:14:44', 5, 'EXECUTED', '7:722374237ed199d2c83f0ff16d4231b1', 'addForeignKeyConstraint baseTableName=customer, constraintName=fk_customer_user_id, referencedTableName=jhi_user; addForeignKeyConstraint baseTableName=customer_car, constraintName=fk_customer_car_customers_id, referencedTableName=customer; addFor...', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328101105-2', 'jhipster', 'classpath:config/liquibase/changelog/20170328101105_added_entity_constraints_Orderlist.xml', '2017-03-28 17:14:47', 6, 'EXECUTED', '7:1ba94ddf3898111242744b0ca1421629', 'addForeignKeyConstraint baseTableName=orderlist_car, constraintName=fk_orderlist_car_orderlists_id, referencedTableName=orderlist; addForeignKeyConstraint baseTableName=orderlist_car, constraintName=fk_orderlist_car_cars_id, referencedTableName=car', '', NULL, '3.5.3', NULL, NULL, '0696066811'),
('20170328115444-1', 'jhipster', 'classpath:config/liquibase/changelog/20170328115444_added_entity_Recommend.xml', '2017-03-28 18:59:05', 7, 'EXECUTED', '7:ebfa0132d74d9bb4f11ef82fd776c05d', 'createTable tableName=recommend', '', NULL, '3.5.3', NULL, NULL, '0702343694'),
('20170328115444-2', 'jhipster', 'classpath:config/liquibase/changelog/20170328115444_added_entity_constraints_Recommend.xml', '2017-03-28 18:59:06', 8, 'EXECUTED', '7:2f49513c4b123538d0e544c7442d0807', 'addForeignKeyConstraint baseTableName=recommend, constraintName=fk_recommend_car_id, referencedTableName=car', '', NULL, '3.5.3', NULL, NULL, '0702343694');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `databasechangeloglock`
--

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `databasechangeloglock`
--

INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_authority`
--

CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_authority`
--

INSERT INTO `jhi_authority` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_persistent_audit_event`
--

CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL,
  `principal` varchar(100) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_persistent_audit_event`
--

INSERT INTO `jhi_persistent_audit_event` (`event_id`, `principal`, `event_date`, `event_type`) VALUES
(1, 'admin', '2017-03-28 10:17:19', 'AUTHENTICATION_SUCCESS'),
(2, 'admin', '2017-03-28 10:37:18', 'AUTHENTICATION_SUCCESS'),
(3, 'admin', '2017-03-29 07:42:41', 'AUTHENTICATION_SUCCESS');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_persistent_audit_evt_data`
--

CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_persistent_audit_evt_data`
--

INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`, `name`, `value`) VALUES
(1, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(2, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(3, 'remoteAddress', '0:0:0:0:0:0:0:1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_persistent_token`
--

CREATE TABLE `jhi_persistent_token` (
  `series` varchar(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(20) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_persistent_token`
--

INSERT INTO `jhi_persistent_token` (`series`, `user_id`, `token_value`, `token_date`, `ip_address`, `user_agent`) VALUES
('JWk9r7L8FGD4BRkMVsQJ', 3, 'U3DGkeiTph17KrRGgNuL', '2017-03-28', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'),
('OL658giEd67Nkxw0nlGt', 3, '7IFpsDfPKUtOZfsppdmP', '2017-03-29', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'),
('QvJk3dA8C7JDDXrO4RqT', 3, 'Sfwg4yz0ZxlZkImc1HKj', '2017-03-29', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_social_user_connection`
--

CREATE TABLE `jhi_social_user_connection` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `provider_user_id` varchar(255) NOT NULL,
  `rank` bigint(20) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `profile_url` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `expire_time` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_user`
--

CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(5) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_user`
--

INSERT INTO `jhi_user` (`id`, `login`, `password_hash`, `first_name`, `last_name`, `email`, `image_url`, `activated`, `lang_key`, `activation_key`, `reset_key`, `created_by`, `created_date`, `reset_date`, `last_modified_by`, `last_modified_date`) VALUES
(1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', b'1', 'en', NULL, NULL, 'system', '2017-03-28 10:14:35', NULL, 'system', NULL),
(2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', b'1', 'en', NULL, NULL, 'system', '2017-03-28 10:14:35', NULL, 'system', NULL),
(3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', b'1', 'en', NULL, NULL, 'system', '2017-03-28 10:14:35', NULL, 'system', NULL),
(4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', b'1', 'en', NULL, NULL, 'system', '2017-03-28 10:14:35', NULL, 'system', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jhi_user_authority`
--

CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `jhi_user_authority`
--

INSERT INTO `jhi_user_authority` (`user_id`, `authority_name`) VALUES
(1, 'ROLE_ADMIN'),
(3, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(3, 'ROLE_USER'),
(4, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderlist`
--

CREATE TABLE `orderlist` (
  `id` bigint(20) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phonenumber` int(11) NOT NULL,
  `total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `orderlist`
--

INSERT INTO `orderlist` (`id`, `firstname`, `lastname`, `address`, `phonenumber`, `total`) VALUES
(1, 'Bích', 'Lê Sĩ', '123 Đường Giải Phóng', 903489453, 123123),
(2, 'Bích', 'Lê Sĩ', '123 Đường Giải Phóng', 903489453, 123123),
(3, 'Administrator', 'Administrator', '123 Đường Giải Phóng', 904491696, 397500),
(4, 'Administrator', 'Administrator', '123 Đường Giải Phóng', 123123123, 358400),
(5, 'Admin', 'admin', '123 Đường Giải Phóng', 11111, 111111),
(6, 'Administrator', 'Administrator', '123 Đường Giải Phóng', 123312, 397500),
(7, 'Administrator', 'Bách', '123123123', 123123123, 497650);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderlist_car`
--

CREATE TABLE `orderlist_car` (
  `cars_id` bigint(20) NOT NULL,
  `orderlists_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `orderlist_car`
--

INSERT INTO `orderlist_car` (`cars_id`, `orderlists_id`) VALUES
(2, 1),
(2, 3),
(2, 6),
(3, 7),
(4, 5),
(5, 4),
(8, 4),
(9, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `recommend`
--

CREATE TABLE `recommend` (
  `id` bigint(20) NOT NULL,
  `car_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `recommend`
--

INSERT INTO `recommend` (`id`, `car_id`) VALUES
(1, 2),
(4, 3),
(3, 8);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `customer_car`
--
ALTER TABLE `customer_car`
  ADD PRIMARY KEY (`customers_id`,`cars_id`),
  ADD KEY `fk_customer_car_cars_id` (`cars_id`);

--
-- Chỉ mục cho bảng `databasechangeloglock`
--
ALTER TABLE `databasechangeloglock`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `jhi_authority`
--
ALTER TABLE `jhi_authority`
  ADD PRIMARY KEY (`name`);

--
-- Chỉ mục cho bảng `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `idx_persistent_audit_event` (`principal`,`event_date`);

--
-- Chỉ mục cho bảng `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD PRIMARY KEY (`event_id`,`name`),
  ADD KEY `idx_persistent_audit_evt_data` (`event_id`);

--
-- Chỉ mục cho bảng `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD PRIMARY KEY (`series`),
  ADD KEY `fk_user_persistent_token` (`user_id`);

--
-- Chỉ mục cho bảng `jhi_social_user_connection`
--
ALTER TABLE `jhi_social_user_connection`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `user_id` (`user_id`,`provider_id`,`provider_user_id`),
  ADD UNIQUE KEY `user_id_2` (`user_id`,`provider_id`,`rank`);

--
-- Chỉ mục cho bảng `jhi_user`
--
ALTER TABLE `jhi_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`),
  ADD UNIQUE KEY `idx_user_login` (`login`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `idx_user_email` (`email`);

--
-- Chỉ mục cho bảng `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD PRIMARY KEY (`user_id`,`authority_name`),
  ADD KEY `fk_authority_name` (`authority_name`);

--
-- Chỉ mục cho bảng `orderlist`
--
ALTER TABLE `orderlist`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `orderlist_car`
--
ALTER TABLE `orderlist_car`
  ADD PRIMARY KEY (`orderlists_id`,`cars_id`),
  ADD KEY `fk_orderlist_car_cars_id` (`cars_id`);

--
-- Chỉ mục cho bảng `recommend`
--
ALTER TABLE `recommend`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `car_id` (`car_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `car`
--
ALTER TABLE `car`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT cho bảng `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT cho bảng `jhi_social_user_connection`
--
ALTER TABLE `jhi_social_user_connection`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT cho bảng `jhi_user`
--
ALTER TABLE `jhi_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT cho bảng `orderlist`
--
ALTER TABLE `orderlist`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT cho bảng `recommend`
--
ALTER TABLE `recommend`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `fk_customer_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Các ràng buộc cho bảng `customer_car`
--
ALTER TABLE `customer_car`
  ADD CONSTRAINT `fk_customer_car_cars_id` FOREIGN KEY (`cars_id`) REFERENCES `car` (`id`),
  ADD CONSTRAINT `fk_customer_car_customers_id` FOREIGN KEY (`customers_id`) REFERENCES `customer` (`id`);

--
-- Các ràng buộc cho bảng `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`);

--
-- Các ràng buộc cho bảng `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Các ràng buộc cho bảng `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`),
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Các ràng buộc cho bảng `orderlist_car`
--
ALTER TABLE `orderlist_car`
  ADD CONSTRAINT `fk_orderlist_car_cars_id` FOREIGN KEY (`cars_id`) REFERENCES `car` (`id`),
  ADD CONSTRAINT `fk_orderlist_car_orderlists_id` FOREIGN KEY (`orderlists_id`) REFERENCES `orderlist` (`id`);

--
-- Các ràng buộc cho bảng `recommend`
--
ALTER TABLE `recommend`
  ADD CONSTRAINT `fk_recommend_car_id` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
