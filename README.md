<h1 style="text-align:center">MevMax API Design Doc </h1>

<h6 style="text-align:right">Author: Xiaotong Zhu </h1>
<h6 style="text-align:right">Edited: AUG 3, 2023</h1>
<h6 style="text-align:right">Version: 2.1</h1>



## I. Overview

This guide outlines the process of building a backend for the MexMax project using Java's Spring Boot. We encapsulate the CRUD (Create, Read, Update, Delete) operations with the RDS Postgres DB as API interfaces through a RESTful architecture. This approach allows users to obtain corresponding data in json format by invoking specific APIs.

Our backend design includes a Postgres database that serves to store and manage data related to DeFi (Decentralized Finance) liquidity pools, liquidity tokens, trading pairs, protocols, and routes. Furthermore, our backend design includes multiple API endpoints. These endpoints allow the frontend to obtain or update data via simple HTTP requests. These APIs include data update APIs, data calculation APIs, and path query APIs, among others.



## II. Protocol

The MevMax backend utilizes the HTTP/HTTPS protocol to facilitate requests and responses in a secure and stateless manner. This enables clients to make requests using standard HTTP verbs such as GET, POST, PUT, DELETE, and PATCH.



## III. Domain

The domain name can be modified according to the actual situation, the current preset base domain for the API is `https://mevmax.com/api`. All endpoints for the MevMax backend services can be accessed by appending the relevant path to this base domain.



## IV. Version

Version control of APIs facilitates improved compatibility and maintainability.

The current version of the API is `v1.0`. The version number is indicated in the URL of the API endpoint, for example: `https://mevmax.com/api/v1.0/endpoint`.



## V. Query Params

| Parameter Name | Value                         | Description                |
| -------------- | ----------------------------- | -------------------------- |
| `baseUrl`      | `https://mevmax.com/api/v1.0` | `MexMax API first version` |



## VI. Endpoints

### A. Data Calculation API

1. GET `{{baseUrl}}/total-tokens`: Get total count of tokens.

2. GET `{{baseUrl}}/total-pools`: Get total count of pools.
3. ......

### B. Data Query API

1. GET `{{baseUrl}}/tokens`: Retrieve data about tokens.

2. GET `{{baseUrl}}/pools`: Retrieve data about pools.
3. ......

### C. Data Updating API

The Data Updating API is responsible for updating the data in the database tables. It supports two main operations:

1. PUT `{{baseUrl}}/tokens`: This endpoint updates the Token table in the Postgres DB with new data from the `token_data.json` file. It accepts a JSON object containing the token data and returns a success message if the operation is successful.

2. PUT `{{baseUrl}}/pools`: This endpoint updates the Pool table in the Postgres DB with new data from the `pool_data.json` file. It accepts a JSON object containing the pool data and returns a success message if the operation is successful.

3. PUT `{{baseUrl}}/pool-flags`: Update the "flag" field in the "Pool" table for the specified pool. The request should include the judgment parameters for updating the flag field in the pool table. The backend will use the provided values to update the database.

4. PUT `{{baseUrl}}/dbs`: Update the databases with the provided details. The backend will use the provided database_name and flag value to update the entire database accordingly.



## VII. Filtering

The API supports filtering to allow clients to retrieve specific subsets of data. Query parameters can be appended to the endpoint URL to filter results. For example, to get tokens with a specific symbol, you could use: `GET {{baseUrl}}/tokens?symbol=ETH`.



## VIII. Status Codes

The API uses standard HTTP status codes to indicate the result of a request:

- 200 OK: The request was successful.
- 201 Created: The request was successful, and a resource was created.
- 400 Bad Request: The request could not be understood or was missing required parameters.
- 401 Unauthorized: Authentication failed or user doesn't have permissions for the requested operation.
- 403 Forbidden: Authentication succeeded, but the authenticated user doesn't have access to the requested resource.
- 404 Not Found: The requested resource could not be found.
- 500 Internal Server Error: An error occurred on the server.



## IX. Error Handling

In case of an error, the API will return an HTTP status code, along with a JSON response body containing an error message. For example:

```
{
  "status": 400,
  "error": "Bad Request",
  "message": "Missing required parameter: 'symbol'"
}
```



## X. Response

All successful responses from the API are returned in a standardized JSON format. A successful response will include the HTTP status code, a success message, and the requested data. For example:

```
{
  "status": 200,
  "message": "OK",
  "data": {
    "total_tokens": 100
  }
}
```



## XI. All API Endpoints Details

### A. For Data Calculation (KPI / Quality Control)

1. Get Total Count of Tokens

   - Method: GET

   - Endpoint: `{{baseUrl}}/tokens-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of tokens in the "Token" table.

   - Example Request:

     ```
     GET {{baseUrl}}/tokens-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "total_tokens": 100
     }
     ```

2. Get Total Count of Pools

   - Method: GET

   - Endpoint: `{{baseUrl}}/pools-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of pools in the "Pool" table.

   - Example Request:

     ```
     GET {{baseUrl}}/pools-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "total_pools": 50
     }
     ```

3. Get Total Count of Pairs

   - Method: GET

   - Endpoint: `{{baseUrl}}/pairs-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of pairs in the "Pair" table.

   - Example Request:

     ```
     GET {{baseUrl}}/pairs-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "total_pairs": 200
     }
     ```

4. Get Total TVL Recorded in Each Blockchain

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains/tvl`

   - Request: NULL

   - Response: JSON

   - Description: Get the total TVL (Total Value Locked) recorded in each blockchain from the "Pool" table.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains/tvl
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchains": [
         {
           "blockchain_name": "Polygon",
           "total_tvl": 1000000.00
         },
         {
           "blockchain_name": "BNB",
           "total_tvl": 500000.00
         },
         {
           "blockchain_name": "ETH",
           "total_tvl": 2000000.00
         }
       ]
     }
     ```

5. Get Total Token Count in Each Blockchain

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains/token-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of tokens in each blockchain from the "Pool_Pair", "Pair", and "Token" tables.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains/token-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchains": [
         {
           "blockchain_name": "Polygon",
           "token_count": 50
         },
         {
           "blockchain_name": "BNB",
           "token_count": 30
         },
         {
           "blockchain_name": "ETH",
           "token_count": 70
         }
       ]
     }
     ```

6. Get Total Pool Count in Each Blockchain

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains/pool-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of pools in each blockchain from the "Pool" and "Blockchain" tables.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains/pool-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchains": [
         {
           "blockchain_name": "Polygon",
           "pool_count": 25
         },
         {
           "blockchain_name": "BNB",
           "pool_count": 15
         },
         {
           "blockchain_name": "ETH",
           "pool_count": 40
         }
       ]
     }
     ```

7. Get Total Pair Count in Each Blockchain

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains/pair-count`

   - Request: NULL

   - Response: JSON

   - Description: Get the total count of pairs in each blockchain from the "Pool_Pair", "Pair", and "Pool" tables.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains/pair-count
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchains": [
         {
           "blockchain_name": "Polygon",
           "pair_count": 100
         },
         {
           "blockchain_name": "BNB",
           "pair_count": 50
         },
         {
           "blockchain_name": "ETH",
           "pair_count": 150
         }
       ]
     }
     ```

### B. For Data Query

1. Get All Pools

   - Method: GET

   - Endpoint: `{{baseUrl}}/pools`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about all pools, including their routes_url.

   - Example Request:

     ```
     GET {{baseUrl}}/pools
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "pools": [
         {
           "pool_address": "0xac4494e30a85369e332bdb5230d6d694d4259dbc",
           "protocol_name": pancakeswap,
           "blockchain_name": BSC,
           "tvl": 1000000.00,
           "fee": 0.0025,
           "flag": true,
           "token0_address": 0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c,
           "token1_address": 0xee89bd9af5e72b19b48cac3e51acde3a09a3ade3
         },
         {
           "pool_address": "0x55caabb0d2b704fd0ef8192a7e35d8837e678207",
           "protocol_name": thena_fusion,
           "blockchain_name": Polygon,
           "tvl": 500000.00,
           "fee": 0.0018,
           "flag": false,
           "token0_address": 0x55d398326f99059ff775485246999027b3197955,
           "token1_address": 0xa0ed3c520dc0632657ad2eaaf19e26c4fd431a84
         },
         ...
       ]
     }
     ```

2. Get Pool by Address

   - Method: GET

   - Endpoint: `{{baseUrl}}/pools/{pool_address}`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about a specific pool by its pool_address, including routes_url.

   - Example Request:

     ```
     GET {{baseUrl}}/pools/{pool_address}
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "pool_address": "0xac4494e30a85369e332bdb5230d6d694d4259dbc",
       "protocol_name": pancakeswap,
       "blockchain_name": BSC,
       "tvl": 1000000.00,
       "fee": 0.0025,
       "flag": true,
       "token0_address": 0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c,
       "token1_address": 0xee89bd9af5e72b19b48cac3e51acde3a09a3ade3
     }
     ```

3. Get All Protocols

   - Method: GET

   - Endpoint: `{{baseUrl}}/protocols`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about all protocols.

   - Example Request:

     ```
     GET {{baseUrl}}/protocols
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "protocols": [
         {
           "protocol_name": "quickswap"
         },
         {
           "protocol_name": "uniswap"
         },
         ...
       ]
     }
     ```

4. Get Protocol by Name

   - Method: GET

   - Endpoint: `{{baseUrl}}/protocols/{protocol_name}`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about a specific protocol by its protocol_name.

   - Example Request:

     ```
     GET {{baseUrl}}/protocols/{protocol_name}
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "protocol_name": "quickswap"
     }
     ```

5. Get All BlockChains

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about all blockchains.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchains": [
         {
           "blockchain_name": "Polygon"
         },
         {
           "blockchain_name": "BNB"
         },
         ...
       ]
     }
     ```

6. Get Blockchain by Name

   - Method: GET

   - Endpoint: `{{baseUrl}}/blockchains/{blockchain_name}`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about a specific blockchain by its blockchain_name.

   - Example Request:

     ```
     GET {{baseUrl}}/blockchains/{blockchain_name}
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "blockchain_name": "Polygon"
     }
     ```

7. Get All Pairs

   - Method: GET

   - Endpoint: `{{baseUrl}}/pairs`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about all pairs, including their routes_url.

   - Example Request:

     ```
     GET {{baseUrl}}/pairs
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "pairs": [
         {
           "token0_address": 0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c,
           "token1_address": 0xee89bd9af5e72b19b48cac3e51acde3a09a3ade3,
           "routes_url": "..."
         },
         {
           "token0_address": 0xe9e7cea3dedca5984780bafc599bd69add087d56,
           "token1_address": 0xf4bcc7b718e027e1b110ea965e5a0d41c2bc5963,
           "routes_url": "..."
         },
         ...
       ]
     }
     ```

8. Get Pair by Token Address

   - Method: GET

   - Endpoint: `{{baseUrl}}/pairs/{token_address}`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about a specific pair by providing a token_address.

   - Example Request:

     ```
     GET {{baseUrl}}/pairs/{token_address}
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "token0_address": 1,
       "token1_address": 2,
       "routes_url": "..."
     }
     ```

9. Get All Tokens

   - Method: GET

   - Endpoint: `{{baseUrl}}/tokens`

   - Request: NULL

   - Response: JSON

   - Description: Retrieve information about all tokens.

   - Example Request:

     ```
     GET {{baseUrl}}/tokens
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Success",
       "tokens": [
         {
           "token_id": 1,
           "token_symbol": "WBTC",
           "token_address": "0x1bfd67037b42cf73acf2047067bd4f2c47d9bfd6",
           "decimal": 18,
         },
         {
           "token_id": 2,
           "token_symbol": "USDC",
           "token_address": "0x2791bca1f2de4661ed88a30c99a7a9449aa84174",
           "decimal": 18,
         },
         ...
       ]
     }
     ```

10. Get Token by Address

    - Method: GET

    - Endpoint: `{{baseUrl}}/tokens/{token_address}`

    - Request: NULL

    - Response: JSON

    - Description: Retrieve information about a specific token by address.

    - Example Request:

      ```
      GET {{baseUrl}}/tokens/{token_address}
      ```

    - Example Response:

      ```
      {
        "status": 200,
        "message": "Success",
        "token_id": 1,
        "token_symbol": "ABC",
        "token_address": "0x2791bca1f2de4661ed88a30c99a7a9449aa84174",
        "decimal": 18,
      }
      ```

11. Get Token by Name

    - Method: GET

    - Endpoint: `{{baseUrl}}/tokens/name/{token_name}`

    - Request: NULL

    - Response: JSON

    - Description: Retrieve information about a specific token by name.

    - Example Request:

      ```
      GET {{baseUrl}}/tokens/name/{token_name}
      ```

    - Example Response:

      ```
      {
        "status": 200,
        "message": "Success",
        "tokens": [
            {
              "token_symbol": "ABC",
              "token_address": "0x2791bca1f2de4661ed88a30c99a7a9449aa84174",
              "decimal": 18,
            },
            {
              "token_symbol": "ABC",
              "token_address": "0xbb4cdb9cbd36b01bd1cbaebf2de08d9173bc095c",
              "decimal": 8,
            }
          ]
      }
      ```

12. Get All Routes URL by Token Addresses

    - Method: GET

    - Endpoint: `{{baseUrl}}/routes?{token_address1}&{token_address2}`

    - Request: NULL

    - Response: JSON

    - Description: Get all `routes_url` from the DB.

    - Example Request:

      ```
      GET {{baseUrl}}/routes
      ```

    - Example Response:

      ```
      {
        "status": 200,
        "message": "Success",
        "routes_url": "[https://url1, https://url2, https://url3...]"
      }
      ```

13. Get Routes URL by Token Addresses

    - Method: GET

    - Endpoint: `{{baseUrl}}/routes?{token_address1}&{token_address2}`

    - Parameters:

      - `token_address1`: The `token_address` of the first token.
      - `token_address2`: The `token_address` of the second token.

    - Request: NULL 

    - Response: JSON

    - Description: Get the `routes_url` associated with the pair of tokens having the specified `token_address1` and `token_address2`.

    - Example Request:

      ```
      GET {{baseUrl}}/routes?token_address1=0x2791bca1f2de4661ed88a30c99a7a9449aa84174&token_address2=0x1bfd67037b42cf73acf2047067bd4f2c47d9bfd6
      ```

    - Example Response:

      ```
      {
        "status": 200,
        "message": "Success",
        "routes_url": "https://..."
      }
      ```



### C. For Data Updating

1. Update Tokens

   - Method: PUT

   - Endpoint: `{{baseUrl}}/tokens`

   - Request: JSON object containing the token data.

   - Response: JSON

   - Description: Update the "Token" table with new data from the `token_data.json` file.

   - Example Request:

     ```
     PUT {{baseUrl}}/tokens
     Content-Type: application/json
     
     {
       "tokens": [
         {
           "token_symbol": "ETH",
           "token_address": "0x1bfd67037b42cf73acf2047067bd4f2c47d9bfd6",
           "decimal": 18,
         },
         {
           "token_symbol": "USDC",
           "token_address": "0x2791bca1f2de4661ed88a30c99a7a9449aa84174",
           "decimal": 18,
         }
       ]
     }
     ```

   - Example Response:

     ```
     jsonCopy code
     {
       "status": 200,
       "message": "Tokens updated successfully."
     }
     ```

2. Update Pools

   - Method: PUT

   - Endpoint:` {{baseUrl}}/pools`

   - Request: JSON object containing the pool data.

   - Response: JSON

   - Description: Update the "Pool" table with new data from the `pool_data.json` file.

   - Example Request:

     ```
     PUT {{baseUrl}}/pools
     Content-Type: application/json
     
     {
       "pools": [
           {
         "Name": "apeswap",
         "pool_address": "0x2e707261d086687470b515b320478eb1c88d49bb",
         "tvl": 721285.2754370774,
         "fee": 0.0015,
         "token1": "0x55d398326f99059ff775485246999027b3197955",
         "token1_symbol": "USDT",
         "token1_decimals": 18,
         "token2": "0xe9e7cea3dedca5984780bafc599bd69add087d56",
         "token2_symbol": "BUSD",
         "token2_decimals": 18
       },
       {
         "Name": "apeswap",
         "pool_address": "0x4f263c2f03d8dcd7dea928de0224e34cebd9f03c",
         "tvl": 711853.6943376027,
         "fee": 0.0015,
         "token1": "0x55d398326f99059ff775485246999027b3197955",
         "token1_symbol": "USDT",
         "token1_decimals": 18,
         "token2": "0xd88ca08d8eec1e9e09562213ae83a7853ebb5d28",
         "token2_symbol": "XWIN",
         "token2_decimals": 18
       },
       ]
     }
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Pools updated successfully."
     }
     ```

3. Update Pool Flags

   - Method: PUT

   - Endpoint: `{{baseUrl}}/pool-flags`

   - Request: JSON object containing the judgment parameters for updating the flag field in the pool table.

   - Response: JSON

   - Description: Update the "flag" field in the "Pool" table for the specified pool. The request should include the judgment parameters for updating the flag field in the pool table. The backend will use the provided values to update the database.

   - Example Request:

     ```
     PUT {{baseUrl}}/pool-flags
     Content-Type: application/json
     
     {
       "tvl": 1000,
       "other_judgment_param": "values"
     }
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Pool flags updated successfully."
     }
     ```

4. Update Databases

   - Method: PUT

   - Endpoint: `{{baseUrl}}/dbs`

   - Request: JSON object containing the database and other parameter details.

   - Response: JSON

   - Description: Update the databases with the provided details. The backend will use the provided database_name and flag value to update the entire database accordingly.

   - Example Request:

     ```
     PUT {{baseUrl}}/dbs
     Content-Type: application/json
     
     {
       "database_name": "dbs_name",
       "flag": "True"
     }
     ```

   - Example Response:

     ```
     {
       "status": 200,
       "message": "Databases updated successfully."
     }
     ```



## XII. APIs Summary

| API Name                                  | Method | Endpoint                                               | Request                                                      | Response                                                     | Description                                                  |
| ----------------------------------------- | ------ | ------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **For Data Calculation**                  |        |                                                        |                                                              |                                                              |                                                              |
| Get Total Count of Tokens                 | GET    | `{{baseUrl}}/tokens-count`                             | NULL                                                         | {"status": 200, "message": "Success", "total_tokens": 100}   | Get the total count of tokens in the "Token" table.          |
| Get Total Count of Pools                  | GET    | `{{baseUrl}}/pools-count`                              | NULL                                                         | {"status": 200, "message": "Success", "total_pools": 50}     | Get the total count of pools in the "Pool" table.            |
| Get Total Count of Pairs                  | GET    | `{{baseUrl}}/pairs-count`                              | NULL                                                         | {"status": 200, "message": "Success", "total_pairs": 200}    | Get the total count of pairs in the "Pair" table.            |
| Get Total TVL Recorded in Each Blockchain | GET    | `{{baseUrl}}/blockchains/tvl`                          | NULL                                                         | {"status": 200, "message": "Success", "blockchains": [...]}  | Get the total TVL recorded in each blockchain from the "Pool" table. |
| Get Total Token Count in Each Blockchain  | GET    | `{{baseUrl}}/blockchains/token-count`                  | NULL                                                         | {"status": 200, "message": "Success", "blockchains": [...]}  | Get the total count of tokens in each blockchain from multiple tables. |
| Get Total Pool Count in Each Blockchain   | GET    | `{{baseUrl}}/blockchains/pool-count`                   | NULL                                                         | {"status": 200, "message": "Success", "blockchains": [...]}  | Get the total count of pools in each blockchain from multiple tables. |
| Get Total Pair Count in Each Blockchain   | GET    | `{{baseUrl}}/blockchains/pair-count`                   | NULL                                                         | {"status": 200, "message": "Success", "blockchains": [...]}  | Get the total count of pairs in each blockchain from multiple tables. |
| **For Data Query**                        |        |                                                        |                                                              |                                                              |                                                              |
| Get All Pools                             | GET    | `{{baseUrl}}/pools`                                    | NULL                                                         | {"status": 200, "message": "Success", "pools": [...]}        | Retrieve information about all pools, including their routes_url. |
| Get Pool by Address                       | GET    | `{{baseUrl}}/pools/{pool_address}`                     | NULL                                                         | {"status": 200, "message": "Success", "pool_id": 1, ...}     | Retrieve information about a specific pool by its pool_address. |
| Get All Protocols                         | GET    | `{{baseUrl}}/protocols`                                | NULL                                                         | {"status": 200, "message": "Success", "protocols": [...]}    | Retrieve information about all protocols.                    |
| Get Protocol by Name                      | GET    | `{{baseUrl}}/protocols/{protocol_name}`                | NULL                                                         | {"status": 200, "message": "Success", "protocol_id": 1, ...} | Retrieve information about a specific protocol by its protocol_name. |
| Get All BlockChains                       | GET    | `{{baseUrl}}/blockchains`                              | NULL                                                         | {"status": 200, "message": "Success", "blockchains": [...]}  | Retrieve information about all blockchains.                  |
| Get Blockchain by Name                    | GET    | `{{baseUrl}}/blockchains/{blockchain_name}`            | NULL                                                         | {"status": 200, "message": "Success", "blockchain_id": 1, ...} | Retrieve information about a specific blockchain by its blockchain_name. |
| Get All Pairs                             | GET    | `{{baseUrl}}/pairs`                                    | NULL                                                         | {"status": 200, "message": "Success", "pairs": [...]}        | Retrieve information about all pairs, including their routes_url. |
| Get Pair by Token Address                 | GET    | `{{baseUrl}}/pairs/{token_address}`                    | NULL                                                         | {"status": 200, "message": "Success", "pair_id": 1, ...}     | Retrieve information about a specific pair by providing a token_address. |
| Get All Tokens                            | GET    | `{{baseUrl}}/tokens`                                   | NULL                                                         | {"status": 200, "message": "Success", "tokens": [...]}       | Retrieve information about all tokens.                       |
| Get Token by Address                      | GET    | `{{baseUrl}}/tokens/{token_address}`                   | NULL                                                         | {"status": 200, "message": "Success", "token_id": 1, ...}    | Retrieve information about a specific token by address.      |
| Get Token by Name                         | GET    | `{{baseUrl}}/tokens/name/{token_name}`                 | NULL                                                         | {"status": 200, "message": "Success", "token_id": 1, ...}    | Retrieve information about a specific token by name.         |
| Get All Routes URL by Token Addresses     | GET    | `{{baseUrl}}/routes`                                   | NULL                                                         | "routes_url": "url1, url2, url3, ...]"}                      | Get all `routes_url` from the DB.                            |
| Get Routes URL by Token Addresses         | GET    | `{{baseUrl}}/routes?{token0_address}&{token1_address}` | NULL                                                         | {"status": 200, "message": "Success", "routes_url": "https://..."} | Get the `routes_url` associated with the pair of tokens having the specified `token_address1` and `token_address2`. |
| **For Data Updating**                     |        |                                                        |                                                              |                                                              |                                                              |
| Update Tokens                             | PUT    | `{{baseUrl}}/tokens`                                   | JSON object containing the token data                        | {"status": 200, "message": "Tokens updated successfully."}   | Update the "Token" table with new data from the `token_data.json` file. |
| Update Pools                              | PUT    | `{{baseUrl}}/pools`                                    | JSON object containing the pool data                         | {"status": 200, "message": "Pools updated successfully."}    | Update the "Pool" table with new data from the `pool_data.json` file. |
| Update Pool Flags                         | PUT    | `{{baseUrl}}/pool-flags`                               | JSON object containing the judgment parameters               | {"status": 200, "message": "Pool flags updated successfully."} | Update the "flag" field in the "Pool" table for the specified pool. |
| Update Databases                          | PUT    | `{{baseUrl}}/dbs`                                      | JSON object containing the database and other parameter details | {"status": 200, "message": "Databases updated successfully."} | Update the databases with the provided details.              |

