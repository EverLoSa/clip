# Code Challenge Clip REST-API

Esta Api fue creada utilizando Spring Boot y Java, Para la base de datos (por simplicidad) se utilizo una base de datos en memoria (h2database)

## Como installar y Usar

1. Descargar el repo
2. Una vez dentro del repositorio ir a la carpeta de rest-api y  ejecutar: * mvn clean install*
3. Ejecutar java -jar target/rest-api-1.0-SNAPSHOT.jar

NOTA: Para probar los endpoints se pueden utilizar tools como postman o http desde la linea de comando

## Resources

### Transactions
- **Make Transaction:** *url: http://localhost:8080/clip/transactions*
- **Request Parameters:**
            {
                "userId": 2,
                "amount": 10000.0,
                "cardData": "Card Data information"
            }


- **Get Transaction by user:** *url: http://localhost:8080/clip/transactions?user_id=2*
- **Request Parameters:** UserId
- **Output:**
    {
        "transactionId": 3,
        "userId": 2,
        "amount": 20.0,
        "cardData": "card data test 3fqwqkjqewe",
        "transactionDate": "2021-03-23T06:00:00.000+00:00",
        "disbursement": null
    }

### Disbursement
- **Make Disbursement:** *http://localhost:8080/clip/disbursement*

- **Get Disbursements:** *http://localhost:8080/clip/disbursement*
- **Output**
{
        "disbursementId": 1,
        "userId": 1,
        "disbursementAmount": 25.0,
        "disbursementDate": "2021-03-23",
        "transactions": [
            {
                "transactionId": 1,
                "userId": 1,
                "amount": 10.0,
                "cardData": "card data test",
                "transactionDate": "2021-03-23T06:00:00.000+00:00",
                "disbursement": 1
            },
            {
                "transactionId": 2,
                "userId": 1,
                "amount": 15.0,
                "cardData": "card data test 3fqwqkjqewe",
                "transactionDate": "2021-03-23T06:00:00.000+00:00",
                "disbursement": 1
            }
        ]
    }