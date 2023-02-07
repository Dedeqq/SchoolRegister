-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 07 Lut 2023, 21:21
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `androiddb`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `classes`
--

CREATE TABLE `classes` (
  `id` int(20) NOT NULL,
  `class` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `classes`
--

INSERT INTO `classes` (`id`, `class`) VALUES
(1, '1A'),
(2, '1B');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grades`
--

CREATE TABLE `grades` (
  `id` int(20) NOT NULL,
  `grade` varchar(70) NOT NULL,
  `grade_value` float NOT NULL,
  `weight` float NOT NULL,
  `student_id` int(20) NOT NULL,
  `teacher_id` int(20) NOT NULL,
  `subject_id` int(20) DEFAULT NULL,
  `description` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `grades`
--

INSERT INTO `grades` (`id`, `grade`, `grade_value`, `weight`, `student_id`, `teacher_id`, `subject_id`, `description`) VALUES
(1, '5', 5, 3, 1, 1, 1, 'sprawdzian'),
(2, '4', 4, 2, 1, 1, 1, 'kartkówka'),
(3, '5-', 4.75, 1, 2, 1, 3, 'aktywność'),
(4, '5', 5, 3, 1, 1, 1, 'sprawdzian'),
(5, '4', 4, 2, 1, 1, 1, 'kartkówka'),
(6, '5-', 4.75, 1, 2, 1, 3, 'aktywność'),
(7, '5', 5, 3, 1, 1, 1, 'sprawdzian'),
(8, '4', 4, 2, 1, 1, 1, 'kartkówka'),
(9, '5-', 4.75, 1, 2, 1, 3, 'aktywność'),
(10, '5', 5, 3, 1, 1, 2, 'sprawdzian'),
(11, '4', 4, 2, 1, 1, 2, 'kartkówka'),
(12, '5-', 4.75, 1, 2, 2, 3, 'aktywność'),
(13, '5', 5, 3, 1, 1, 2, 'sprawdzian'),
(14, '4', 4, 2, 1, 1, 2, 'kartkówka'),
(15, '5-', 4.75, 1, 2, 2, 3, 'aktywność'),
(16, '5', 5, 3, 1, 1, 2, 'sprawdzian'),
(17, '4', 4, 2, 1, 1, 3, 'kartkówka'),
(18, '5-', 4.75, 1, 2, 2, 3, 'aktywność'),
(19, '5', 5, 3, 1, 1, 1, 'sprawdzian'),
(20, '4', 4, 2, 1, 1, 1, 'kartkówka'),
(21, '5-', 4.75, 1, 2, 2, 3, 'aktywność'),
(22, '2', 2, 3, 2, 2, 2, 'sprawdzian'),
(23, '4+', 4.5, 2, 2, 2, 2, 'odpowiedź');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `others`
--

CREATE TABLE `others` (
  `id` int(20) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `user_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `others`
--

INSERT INTO `others` (`id`, `firstname`, `lastname`, `user_id`) VALUES
(1, 'Łukasz', 'Groch', 7);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parents`
--

CREATE TABLE `parents` (
  `id` int(20) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `user_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `parents`
--

INSERT INTO `parents` (`id`, `firstname`, `lastname`, `user_id`) VALUES
(1, 'Maria', 'Kapusta', 3),
(2, 'Joanna', 'Kogut', 4);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
  `id` int(20) NOT NULL,
  `role` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `roles`
--

INSERT INTO `roles` (`id`, `role`) VALUES
(1, 'UCZEŃ'),
(2, 'NAUCZYCIEL'),
(3, 'RODZIC'),
(4, 'INNA');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `schedule`
--

CREATE TABLE `schedule` (
  `id` int(20) NOT NULL,
  `teacher_id` int(20) NOT NULL,
  `class_id` int(20) NOT NULL,
  `subject_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `schedule`
--

INSERT INTO `schedule` (`id`, `teacher_id`, `class_id`, `subject_id`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 2),
(3, 2, 1, 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `students`
--

CREATE TABLE `students` (
  `id` int(20) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `user_id` int(20) NOT NULL,
  `class_id` int(20) NOT NULL,
  `parent_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `students`
--

INSERT INTO `students` (`id`, `firstname`, `lastname`, `user_id`, `class_id`, `parent_id`) VALUES
(1, 'Mateusz', 'Kapusta', 1, 1, 1),
(2, 'Jan', 'Kogut', 2, 1, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subjects`
--

CREATE TABLE `subjects` (
  `id` int(20) NOT NULL,
  `subject` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `subjects`
--

INSERT INTO `subjects` (`id`, `subject`) VALUES
(1, 'MATEMATYKA'),
(2, 'JĘZYK POLSKI'),
(3, 'JĘZYK ANGIELSKI');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `teachers`
--

CREATE TABLE `teachers` (
  `id` int(20) NOT NULL,
  `firstname` varchar(70) NOT NULL,
  `lastname` varchar(70) NOT NULL,
  `user_id` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `teachers`
--

INSERT INTO `teachers` (`id`, `firstname`, `lastname`, `user_id`) VALUES
(1, 'Tomasz', 'Dorosz', 5),
(2, 'Adam', 'Dorecki', 6);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `tests`
--

CREATE TABLE `tests` (
  `id` int(20) NOT NULL,
  `date` varchar(70) NOT NULL,
  `class` varchar(70) NOT NULL,
  `subject` varchar(70) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `username` varchar(70) NOT NULL,
  `password` varchar(40) NOT NULL,
  `email` varchar(50) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `role_id` int(20) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `created_at`, `updated_at`, `role_id`) VALUES
(1, 'uczen1', '4fde3c10235b94a62ce9d541c4c5b47e', 'uczen1@uczen1.pl', '2023-02-07 20:24:27', '2023-02-07 20:24:27', 1),
(2, 'uczen2', '138cd10f3897df6ddf3ba23cc8a672dd', 'uczen2@uczen2.pl', '2023-02-07 20:24:27', '2023-02-07 20:24:27', 1),
(3, 'rodzic1', 'efd225d42edd5a074d31f42f09635ee6', 'rodzic1@rodzic1.pl', '2023-02-07 20:24:27', '2023-02-07 20:24:27', 3),
(4, 'rodzic2', 'c6eba4944a23a89c089c737375a9fd57', 'rodzic2@rodzic2.pl', '2023-02-07 20:24:27', '2023-02-07 20:24:27', 3),
(5, 'nauczyciel1', '88334ae7924306f90d7bbafc6bf3ec13', 'nauczyciel1@nauczyciel1.pl', '2023-02-07 20:24:27', '2023-02-07 20:24:27', 2),
(6, 'nauczyciel2', '0be1f465da23c9358fb25c498dbd851e', 'nauczyciel2@nauczyciel2.pl', '2023-02-07 20:24:28', '2023-02-07 20:24:28', 2),
(7, 'inna1', '8b726c7262828dc364d9d740383fae6b', 'inna1@inna1.pl', '2023-02-07 20:24:28', '2023-02-07 20:24:28', 4);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `grades`
--
ALTER TABLE `grades`
  ADD PRIMARY KEY (`id`),
  ADD KEY `student_id` (`student_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `subject_id` (`subject_id`);

--
-- Indeksy dla tabeli `others`
--
ALTER TABLE `others`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `parents`
--
ALTER TABLE `parents`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `class_id` (`class_id`);

--
-- Indeksy dla tabeli `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `class_id` (`class_id`),
  ADD KEY `parent_id` (`parent_id`);

--
-- Indeksy dla tabeli `subjects`
--
ALTER TABLE `subjects`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indeksy dla tabeli `tests`
--
ALTER TABLE `tests`
  ADD PRIMARY KEY (`id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `classes`
--
ALTER TABLE `classes`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `grades`
--
ALTER TABLE `grades`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT dla tabeli `others`
--
ALTER TABLE `others`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT dla tabeli `parents`
--
ALTER TABLE `parents`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `students`
--
ALTER TABLE `students`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `subjects`
--
ALTER TABLE `subjects`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `tests`
--
ALTER TABLE `tests`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `grades`
--
ALTER TABLE `grades`
  ADD CONSTRAINT `grades_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  ADD CONSTRAINT `grades_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`),
  ADD CONSTRAINT `grades_ibfk_3` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`);

--
-- Ograniczenia dla tabeli `others`
--
ALTER TABLE `others`
  ADD CONSTRAINT `others_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ograniczenia dla tabeli `parents`
--
ALTER TABLE `parents`
  ADD CONSTRAINT `parents_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ograniczenia dla tabeli `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  ADD CONSTRAINT `schedule_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`),
  ADD CONSTRAINT `schedule_ibfk_3` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`);

--
-- Ograniczenia dla tabeli `students`
--
ALTER TABLE `students`
  ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `students_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  ADD CONSTRAINT `students_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `parents` (`id`);

--
-- Ograniczenia dla tabeli `teachers`
--
ALTER TABLE `teachers`
  ADD CONSTRAINT `teachers_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Ograniczenia dla tabeli `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
