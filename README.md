# Cliente Service
- [Uso](#uso)
- [Post](#postear-cliente)

## Uso
Primero debemos de autenticarnos con el microservicio Auth, cuando tengamos nuestro token, tendremos acceso a los [Endpoints](#endpoints) usando el BearerToken

## Endpoints
http://localhost:8010/clientes para get general y post, en caso de querer obtener un cliente por id, put o delete nuestro endpoint es http://localhost:8010/clientes/{idCliente}.
Consultar Swagger/OpenApi http://localhost:8010/swagger-ui.html.

## Postear Cliente
La url es http://localhost:8010/compras en método post

{
"nombre": "Mateo",

"email": "mateo@gmail.com"

}
