package com.kavarera.berijalantest.mobil;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "MOBIL_DATA")
public class Mobil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(nullable = false)
    private String nama;
    @Column(nullable = false)
    private BigDecimal harga;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MobilEnum tipe;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;
}
