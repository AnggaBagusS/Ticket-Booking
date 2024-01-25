-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 25 Jan 2024 pada 15.44
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ticket_booking`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `acara`
--

CREATE TABLE `acara` (
  `eventId` bigint(20) NOT NULL,
  `eventName` varchar(100) DEFAULT NULL,
  `eventCategory` varchar(100) DEFAULT NULL,
  `eventDate` date DEFAULT NULL,
  `harga` bigint(20) DEFAULT NULL,
  `harga_spesial` bigint(20) DEFAULT NULL,
  `imageUrl` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `acara`
--

INSERT INTO `acara` (`eventId`, `eventName`, `eventCategory`, `eventDate`, `harga`, `harga_spesial`, `imageUrl`) VALUES
(1, 'Motion Ime', 'Festival', '2023-12-05', 30000, 70000, '\\image\\motionIme.jpg'),
(2, 'Coldplay', 'Konser Musik', '2023-12-10', 1000000, 2500000, NULL);

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `adminId` bigint(20) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`adminId`, `username`, `email`, `password`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengguna`
--

CREATE TABLE `pengguna` (
  `userId` bigint(20) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pengguna`
--

INSERT INTO `pengguna` (`userId`, `username`, `email`, `password`, `role`) VALUES
(5, 'user', 'user@gmail.com', 'user123', 'customer'),
(6, 'admin', 'admin@gmail.com', 'admin123', 'admin'),
(7, 'user2', 'user2@gmail.com', 'user2123', 'customer'),
(8, '', '', '', 'customer'),
(9, '', '', '', 'customer');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `transaksiId` bigint(20) NOT NULL,
  `totalHarga` bigint(20) DEFAULT NULL,
  `eventId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `statusTransaksi` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`transaksiId`, `totalHarga`, `eventId`, `userId`, `statusTransaksi`) VALUES
(35, 7500000, 2, 5, 'Verified'),
(36, 14000000, 2, 5, 'Not Verified'),
(37, 330000, 1, 5, 'Not Verified'),
(39, 330000, 1, 5, 'Verified'),
(40, 9500000, 2, 5, 'Not Verified'),
(41, 330000, 1, 5, 'Verified');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `acara`
--
ALTER TABLE `acara`
  ADD PRIMARY KEY (`eventId`);

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminId`);

--
-- Indeks untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  ADD PRIMARY KEY (`userId`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`transaksiId`),
  ADD KEY `eventId` (`eventId`),
  ADD KEY `userId` (`userId`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `acara`
--
ALTER TABLE `acara`
  MODIFY `eventId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `adminId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `pengguna`
--
ALTER TABLE `pengguna`
  MODIFY `userId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `transaksiId` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`eventId`) REFERENCES `acara` (`eventId`),
  ADD CONSTRAINT `transaksi_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `pengguna` (`userId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
