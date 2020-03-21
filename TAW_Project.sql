-----------------
-- DROP TABLES --
-----------------

DROP TABLE USUARIO;
DROP TABLE CATEGORIA;
DROP TABLE PALABRA_CLAVE;
DROP TABLE PRODUCTO;
DROP TABLE COMENTARIO;
DROP TABLE PRODUCTO_PALABRA_CLAVE;

----------------------------
-- CREACION DE LAS TABLAS --
----------------------------

CREATE TABLE USUARIO (
    id_usuario INTEGER , /* PK */
    administrador BOOLEAN,
    username VARCHAR(50),
    password VARCHAR(50),
    nombre VARCHAR(50),
    email VARCHAR(50),
    PRIMARY KEY (id_usuario)
);

CREATE TABLE CATEGORIA (
    id_categoria INTEGER, /* PK */
    nombre VARCHAR(50),
    supercategoria INTEGER, /* FK -> CATEGORIA  */
    PRIMARY KEY (id_categoria),
    CONSTRAINT supercategorias_fk FOREIGN KEY (supercategoria) REFERENCES CATEGORIA(id_categoria)
);


CREATE TABLE PALABRA_CLAVE (
    id_palabra_clave INTEGER, /* PK */
    palabra VARCHAR(50),
    PRIMARY KEY (id_palabra_clave)
);

CREATE TABLE PRODUCTO(
    id_producto INTEGER, /* PK */
    nombre VARCHAR(50),
    descripcion VARCHAR(200),
    precio DECIMAL(10,2),
    fecha DATE,
    imagen VARCHAR(100),
    propietario INTEGER, /* FK -> USUARIO */
    categoria INTEGER, /* FK -> CATEGORIA */
    PRIMARY KEY (id_producto),
    CONSTRAINT usuario_producto_fk FOREIGN KEY (propietario) REFERENCES USUARIO(id_usuario),
    CONSTRAINT categoria_producto_fk FOREIGN KEY (categoria) REFERENCES CATEGORIA(id_categoria)
);

CREATE TABLE COMENTARIO(
    texto VARCHAR(200),
    valoracion INTEGER,
    fecha DATE,
    autor INTEGER, /* FK -> USUARIO */ /* PK */
    producto INTEGER, /* FK -> PRODUCTO */ /* PK */
    PRIMARY KEY (autor, producto),
    CONSTRAINT usuario_comentario_fk FOREIGN KEY (autor) REFERENCES USUARIO(id_usuario),
    CONSTRAINT producto_comentario_fk FOREIGN KEY (producto) REFERENCES PRODUCTO(id_producto)
);

CREATE TABLE PRODUCTO_PALABRA_CLAVE(
    producto INTEGER, /* FK -> PRODUCTO */ /* PK */
    palabra_clave INTEGER, /* FK -> PALABRA_CLAVE */ /* PK */
    PRIMARY KEY (producto, palabra_clave),
    CONSTRAINT producto_palabra_clave_fk FOREIGN KEY (producto) REFERENCES PRODUCTO(id_producto),
    CONSTRAINT palabra_clave_producto_fk FOREIGN KEY (palabra_clave) REFERENCES PALABRA_CLAVE(id_palabra_clave)
);

--------------------------
-- INSERTs de los datos --
--------------------------

-- NOTA: HAY QUE HACER LAS INSERCIONES EN EL ORDEN DE CREACION DE LAS TABLAS
