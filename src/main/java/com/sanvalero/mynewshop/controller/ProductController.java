package com.sanvalero.mynewshop.controller;

import com.sanvalero.mynewshop.domain.Product;
import com.sanvalero.mynewshop.exception.ProductNotFoundException;
import com.sanvalero.mynewshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.sanvalero.mynewshop.controller.Response.NOT_FOUND;

@RestController
@Tag(name = "Products", description = "Catalogo")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Operation(summary = "Obtener lista de productos")
    @ApiResponses(value =  {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class))))
    })
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<Set<Product>> getProducts(@RequestParam(value = "category", defaultValue = "")String category) {
        logger.info("inicio getProducts");
        Set<Product> products = null;
        if (category.equals(""))
            products = productService.findAll();
        else
            products = productService.findByCategory(category);
        logger.info("fin getProducts");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

        @Operation(summary = "Obtiene un producto determinado")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Product.class))),
                @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
        })
        @GetMapping(value = "/products/{id}", produces = "application/json")
        public ResponseEntity<Product> getProduct(@PathVariable long id){
            Product product = productService.findById(id)
                    .orElseThrow(() -> new ProductNotFoundException(id));
            return new ResponseEntity<>(product,HttpStatus.OK);
        }



        @Operation(summary = "Registrar un nuevo producto")
        @ApiResponses(value ={
                @ApiResponse(responseCode = "200", description = "Se registra un nuevo producto", content = @Content(schema = @Schema(implementation = Product.class)))
        })
        @PostMapping(value = "/products", produces = "application/json", consumes = "application/json")
        public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct,HttpStatus.OK);

        }


    @Operation(summary = "Modifica un producto del catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el prodcuto", content = @Content(schema = @Schema(implementation = Product.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
        @PutMapping(value = "/products{id}", produces = "application/json", consumes = "application/json")
        public ResponseEntity<Product> modifyProduct(@PathVariable long id, @RequestBody Product newProduct){
        Product product = productService.modifyProduct(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
        }


    @Operation(summary = "Elimina un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El producto no se puede encontrar", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/prodcuts{id}", produces = "application/json")
    public ResponseEntity<Response> deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handlerException(ProductNotFoundException pnfe){
    Response response = Response.errorResponse(NOT_FOUND, pnfe.getMessage());
    logger.error(pnfe.getMessage(), pnfe);
    return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }



}







