# TechnoCenter Teknikal Test

Proyek ini adalah tugas teknikal test untuk mengikuti bootcamp techno center berijalan.

## Fitur

- CRUD Mobil
- User Register
- JWT

## Endpoint 

- /api/mobil
```agsl
Method: GET
Deskripsi: Mengambil semua mobil yang ada dalam database.
```
- /api/mobil/search
```agsl
Method: GET
Query Parameter: name (String)
Deskripsi: Mencari mobil berdasarkan nama yang mengandung teks tertentu.
```
- /api/mobil/type/{tipeMobil}
```agsl
Method: GET
Path Variable: tipeMobil (String)
Deskripsi: Mencari mobil berdasarkan tipe mobil.
```

- /api/mobil/type
```agsl
Method: GET
Deskripsi: Mengambil semua nama tipe mobil yang ada dalam database.
```

- /api/mobil
```agsl
Method: POST
Body : {
    "nama":"Mobil Jelek",
    "harga":1000000,
    "tipe":"Sedan"
}
Deskripsi: Menambahkan mobil baru ke dalam database.
```

- /api/mobil/{id}
```agsl
Method: PUT
Path Variable: id (Integer)
Body : {
    "nama":"Mobil Biasa",
    "harga":1000000,
    "tipe":"Crossover"
}
Deskripsi: Memperbarui data mobil berdasarkan ID.
```

- /api/mobil/{id}
```agsl
Method: DELETE
Path Variable: id (Integer)
Deskripsi: Menghapus mobil berdasarkan ID.
```

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
