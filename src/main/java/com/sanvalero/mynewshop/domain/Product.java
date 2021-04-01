package com.sanvalero.mynewshop.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Schema(description = "Identificador del producto", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Schema(description = "Nombre del producto", example = "Cereales", required = true)
    @Column
    private String name;
    @Schema(description = "Descripcion del producto", example = "Cereales de chocolate")
    @Column
    private String description;
    @Schema(description = "Categoria del producto", example = "Desayuno")
    @Column
    private String category;
    @Schema(description = "Precio del producto", example = "2.00", defaultValue = "0.00")
    @Column
    private float price;
    @Schema(description = "Fecha de registro", example = "2021-05-11")
    @Column
    private LocalDateTime creationDate;


}
