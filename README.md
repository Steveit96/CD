# **Alertnow Mobile API Documentation**

------------

[![](https://www.opsnow.com/wp-content/uploads/2024/02/BI-horizontal-blue.png)](https://www.opsnow.com/wp-content/uploads/2024/02/BI-horizontal-blue.png)

------------

## **Environments:**

| **Environment** | **BaseUrl**                                   |
|-----------------|-----------------------------------------------|
| **Production**  | **https://alertnowmblapi.opsnow.com**         |
| **Development** | **https://alertnowmblapidev.opsnow.com**      |
| **Samsung**     | **https://alertnowmblapi.sec-alertnow.com**   |

------------

## **Endpoints**

### **Domain**

| **Method** | **URL**                                   | **Headers**                                                                                                                                               |
|------------|-------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v1/1/common/domain`        | `Content-Type: application/json`<br>`mobile-domain: <domain>`                                                                                             |

| **Response Field**  | **Type** | **Description**                                                                                                                                         |
|---------------------|----------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| `data`              | Object   | Contains details about the domain.                                                                                                                       |
| `data.portalCmpnId` | String   | The ID of the portal company.                                                                                                                            |
| `data.mainDmnNm`    | String   | The main domain name.                                                                                                                                    |
| `data.svcUrl`       | String   | The service URL for the API.                                                                                                                             |
| `data.keycloakJson` | String   | JSON object as a string containing Keycloak configuration settings.                                                                                      |

### **MFA Availability Check**

| **Method** | **URL**                                                                                                               | **Headers**                          |
|------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| **GET**    | `{baseUrl}/api/v2/1/authInfo/otp?email=<username>&domain=<domain>`                                                    | `Content-Type: application/json`     |

| **Response Field** | **Type** | **Description**                                                                                             |
|--------------------|----------|-------------------------------------------------------------------------------------------------------------|
| `data`             | Boolean  | Indicates whether MFA is enabled or not. `TRUE` means MFA is enabled, while `FALSE` means it is disabled.  |

------------

### **Login**

| **Method** | **URL**                                                                                                   | **Headers**                                        | **Body Parameters**                                                                                                             |
|------------|-----------------------------------------------------------------------------------------------------------|---------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `https://ssodev.opsnow.com/auth/realms/BESPIN/protocol/openid-connect/token`                               | `Content-Type: application/x-www-form-urlencoded` | `client_id=platform_api`<br>`client_secret=<secret_id_received_in_domain_response>`<br>`grant_type=password`<br>`username=<email>`<br>`password=<password>`<br>`totp=<otp_if_mfa_available>` |

| **Response Field**        | **Type**   | **Description**                                                                                                                     |
|---------------------------|------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `access_token`            | String     | The token used for authorization in the Bearer scheme.                                                                              |
| `expires_in`              | Integer    | The duration in seconds for which the access token is valid.                                                                        |
| `refresh_expires_in`      | Integer    | The duration in seconds for which the refresh token is valid.                                                                       |
| `refresh_token`           | String     | The token used to obtain a new access token when the current one expires.                                                           |
| `token_type`              | String     | The type of token, typically "Bearer".                                                                                              |
| `not-before-policy`       | Integer    | A policy timestamp to determine if tokens issued before this time should be considered invalid.                                     |
| `session_state`           | String     | The session identifier for the current authentication session.                                                                      |
| `scope`                   | String     | The scope of the token, indicating the permissions associated with it.                                                              |

### **Session**

| **Method** | **URL**                                                                                                | **Headers**                                                                                                                                 |
|------------|--------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v1/<company_portal_id>/common/session`                                                  | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`Authorization: Bearer <access_token>`                              |

| **Response Field** | **Type**  | **Description**                                  |
|--------------------|-----------|--------------------------------------------------|
| `roleCd`           | String    | The role code assigned to the user.              |
| `userNo`           | Integer   | The user's unique identifier.                    |
| `roleName`         | String    | The name of the role assigned to the user (e.g., "Admin"). |
| `portalUserId`     | String    | The user's portal identifier.                    |
| `userName`         | String    | The name of the user.                            |
| `email`            | String    | The email address of the user.                   |

### **Company Info**

#### Samsung Domain

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/samsung/company`                                                                           | `Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                       |

#### Global Domain

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/samsung/authInfo`                                                                          | `Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                       |

| **Response Field** | **Type** | **Description**                  |
|--------------------|----------|----------------------------------|
| `data`             | Array    | List of company information      |
| `cmpnNo`           | Integer  | Company number                   |
| `companyId`        | String   | Unique identifier for the company |
| `companyName`      | String   | Name of the company              |

### **Upload Device Info**

#### Samsung Domain

| **Method** | **URL**                                                                                                | **Headers**                                                                                                                                               | **Request Body**                                                                                                            | **Expected Response**     |
|------------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/samsung/authInfo`                                                                           | `mobile-domain: opsnow.com`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                                  | ```json { "deviceId": <device_id>, "userNo": <user_no>, "uuid": <uuid> } ```                                               | `200 OK` with no response body |

#### Global Domain

| **Method** | **URL**                                                                                                | **Headers**                                                                                                                                               | **Request Body**                                                                                                            | **Expected Response**     |
|------------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/api/v1/<company_portal_id>/authInfo`                                                        | `mobile-domain: opsnow.com`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                                  | ```json { "deviceId": <device_id>, "userNo": <user_no>, "uuid": <uuid> } ```                                               | `200 OK` with no response body |

### **Register Device Token**

###### **Samsung Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             | **Request Body**                                                                                                            | **Expected Response**     |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/samsung/device`                                                         | `mobile-authorization: <mobile_token>`<br>`mobile-domain: <domain_name>`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>` | ```json { "deviceId": <device_id>, "pushServiceType": "FCM", "device_token": <device_token>, "brandName": <device_brand_name>, "pushUseYn": "Y", "modelName": <device_model> } ``` | `200 OK` with no response body |

###### **Global Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             | **Request Body**                                                                                                            | **Expected Response**     |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/api/v1/<company_portal_id>/device`                                                         | `mobile-authorization: <mobile_token>`<br>`mobile-domain: <domain_name>`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>` | ```json { "deviceId": <device_id>, "pushServiceType": "FCM", "device_token": <device_token>, "brandName": <device_brand_name>, "pushUseYn": "Y", "modelName": <device_model> } ``` | `200 OK` with no response body |

#### **Profile Details**

| **Method** | **URL**                                                                                           | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|---------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/user/<userid>` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>` |

| **Response Field** | **Type** | **Description**                                                      |
|--------------------|----------|----------------------------------------------------------------------|
| `userNo`           | Integer  | The user's unique identifier.                                        |
| `userName`         | String   | The name of the user.                                                |
| `email`            | String   | The email address of the user.                                       |
| `timezoneType`     | String   | The type of timezone (e.g., "MANUAL").                                |
| `languageCode`     | String   | The code for the user's language (e.g., "en" for English).            |
| `timezoneCode`     | String   | The code for the user's timezone (e.g., "Etc/GMT-9").                 |
| `timezoneName`     | String   | The name of the user's timezone (e.g., "+09:00 GMT-9").               |
| `languageName`     | String   | The name of the user's language (e.g., "English").                    |



