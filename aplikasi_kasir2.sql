-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 27, 2024 at 08:09 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_kasir2`
--

-- --------------------------------------------------------

--
-- Table structure for table `riwayat_transaksi`
--

CREATE TABLE `riwayat_transaksi` (
  `id_riwayat` int(10) NOT NULL,
  `kode_transaksi` varchar(100) NOT NULL,
  `total_bayar` decimal(10,2) NOT NULL,
  `kembalian` decimal(10,2) NOT NULL,
  `tanggal_bayar` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `riwayat_transaksi`
--

INSERT INTO `riwayat_transaksi` (`id_riwayat`, `kode_transaksi`, `total_bayar`, `kembalian`, `tanggal_bayar`) VALUES
(6, 'TRX20240223213349', 70000.00, 10000.00, '2024-02-23'),
(7, 'TRX20240223222828', 346000.00, 154000.00, '2024-02-23'),
(8, 'TRX20240223223818', 144000.00, 56000.00, '2024-02-23'),
(9, 'TRX20240225155422', 6000.00, 4000.00, '2024-02-25'),
(10, 'TRX20240226140823', 105000.00, 95000.00, '2024-02-26');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jual`
--

CREATE TABLE `tbl_jual` (
  `id_jual` int(11) NOT NULL,
  `grand_total` int(15) NOT NULL,
  `tgl_jual` date NOT NULL,
  `jam` time NOT NULL,
  `dibayar` decimal(11,0) NOT NULL,
  `kembalikan` decimal(11,0) NOT NULL,
  `id_kasir` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_menu`
--

CREATE TABLE `tbl_menu` (
  `id_menu` varchar(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga_menu` decimal(10,2) NOT NULL,
  `satuan_menu` int(100) NOT NULL,
  `kategori` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_menu`
--

INSERT INTO `tbl_menu` (`id_menu`, `nama_menu`, `harga_menu`, `satuan_menu`, `kategori`) VALUES
('A10', 'Spicy salmon bowl', 65000.00, 9, 'makanan'),
('A11', 'beef bowl kobo', 35000.00, 10, 'makanan'),
('A12', 'nasi goreng kobo', 15000.00, 15, 'makanan'),
('A13', 'Sate titan', 20000.00, 20, 'makanan'),
('A14', 'chicken katsu', 20000.00, 15, 'makanan'),
('A15', 'ramen torikara', 25000.00, 10, 'makanan'),
('B10', 'Tempura set', 20000.00, 10, 'snack'),
('B11', 'Chicken karage ghoul', 15000.00, 10, 'snack'),
('B12', 'cireng suii', 10000.00, 10, 'snack'),
('B13', 'French fries sausage', 32000.00, 10, 'snack'),
('B14', 'Banana chocolate', 15000.00, 10, 'snack'),
('B15', 'Gyoza', 20000.00, 15, 'snack'),
('C10', 'Matcha ', 12000.00, 10, 'minuman'),
('C11', 'Ayumi milk tea', 15000.00, 10, 'minuman'),
('C12', 'peach tea', 20000.00, 10, 'minuman'),
('C13', 'lemon tea', 15000.00, 9, 'minuman'),
('C14', 'lemon sparkling', 15000.00, 10, 'minuman'),
('C15', 'strawberry sparkling', 15000.00, 10, 'minuman'),
('D10', 'Ice cream green tea', 15000.00, 10, 'dessert'),
('D11', 'Milky puding', 15000.00, 10, 'dessert'),
('D12', 'Regal cream cheese', 20000.00, 10, 'dessert'),
('D13', 'Waffle ala conan', 20000.00, 10, 'dessert'),
('D14', 'Klepon hulk', 10000.00, 10, 'dessert'),
('D15', 'Dalgona dessert box', 25000.00, 9, 'dessert');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_transaksi`
--

CREATE TABLE `tbl_transaksi` (
  `kd_transaksi` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `jumlah` int(30) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(2) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `email` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `role` enum('admin','kasir') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `email`, `password`, `role`) VALUES
(1, 'nadia', 'nadia@gmail.com', '123', 'admin'),
(2, 'fioni', 'fioni@gmail.com', '1234', 'kasir');

-- --------------------------------------------------------

--
-- Table structure for table `tmp_transaksi`
--

CREATE TABLE `tmp_transaksi` (
  `no` varchar(100) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga` decimal(10,2) NOT NULL,
  `jumlah` int(100) NOT NULL,
  `total` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `riwayat_transaksi`
--
ALTER TABLE `riwayat_transaksi`
  ADD PRIMARY KEY (`id_riwayat`);

--
-- Indexes for table `tbl_jual`
--
ALTER TABLE `tbl_jual`
  ADD PRIMARY KEY (`id_jual`),
  ADD KEY `id_kasir` (`id_kasir`);

--
-- Indexes for table `tbl_menu`
--
ALTER TABLE `tbl_menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- Indexes for table `tbl_transaksi`
--
ALTER TABLE `tbl_transaksi`
  ADD PRIMARY KEY (`kd_transaksi`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `riwayat_transaksi`
--
ALTER TABLE `riwayat_transaksi`
  MODIFY `id_riwayat` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tbl_jual`
--
ALTER TABLE `tbl_jual`
  MODIFY `id_jual` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(2) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
