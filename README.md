# TechnoCenter Teknikal Test

Proyek ini adalah tugas teknikal test untuk mengikuti bootcamp techno center berijalan.

## Fitur

- CRUD Mobil
- User Register
- JWT

## Struktur Proyek

- `Mobil`: Entitas yang merepresentasikan mobil.
- `MobilEnum`: Entitas yang merepresentasikan tipe mobil.
- `MobilRepository`: Repository untuk mengelola entitas `Mobil`.
- `MobilService`: Layanan untuk logika bisnis terkait `Mobil`.
- `MobilController`: Controller untuk menangani permintaan HTTP terkait `Mobil`.

## Teknologi yang Digunakan

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Prasyarat

- Java 21
- Maven
- PostgreSQL

## Konfigurasi Database

1. Tambahkan konfigurasi database PostgreSQL di `application.properties`.
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/mobil
spring.datasource.username=namapengguna
spring.datasource.password=katasandi

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database=postgresql
