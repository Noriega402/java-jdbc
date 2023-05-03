# Aprendiendo a usar Java con JDBC

## Dependencias

### DB
Para conexion a la base de datos se esta usando la dependencia de postgresql

### Pool
Para el pool de conexiones se esta usando c3p0

### Detalles de app
Se usa la dependencia de commons-java (es para ver logs de la app desde la consola)

## Numero de conexiones de la app

Para poder hacer las pruebas de esto puedes ir al package **com.alura.test** encontraras un archivo llamado **PruebaPoolConexiones.java** ese archivo ayudara a saber como es que funciona el limite de conexiones a una DB

### Ver los procesos del pool de conexiones

Para poder ver las conexiones que se estan usando desde la DB podemos hacer uso del siguiente comando:

- **Para postgres:** 

```
SELECT pid, usename, datname, client_addr, application_name, backend_start, state
FROM pg_stat_activity;
```

- **Para MySQL:**

```
SHOW PROCESSLIST;
```