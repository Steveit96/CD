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

## **Domain**

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

## **MFA Availability Check**

| **Method** | **URL**                                                                                                               | **Headers**                          |
|------------|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------|
| **GET**    | `{baseUrl}/api/v2/1/authInfo/otp?email=<username>&domain=<domain>`                                                    | `Content-Type: application/json`     |

| **Response Field** | **Type** | **Description**                                                                                             |
|--------------------|----------|-------------------------------------------------------------------------------------------------------------|
| `data`             | Boolean  | Indicates whether MFA is enabled or not. `TRUE` means MFA is enabled, while `FALSE` means it is disabled.  |

------------

## **Login**

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

## **Session**

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

## **Company Info**

###### **Samsung Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/samsung/company`                                                                           | `Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                       |

###### **Global Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/samsung/authInfo`                                                                          | `Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                       |

| **Response Field** | **Type** | **Description**                  |
|--------------------|----------|----------------------------------|
| `data`             | Array    | List of company information      |
| `cmpnNo`           | Integer  | Company number                   |
| `companyId`        | String   | Unique identifier for the company |
| `companyName`      | String   | Name of the company              |

## **Upload Device Info**

###### **Samsung Domain**

| **Method** | **URL**                                                                                                | **Headers**                                                                                                                                               | **Request Body**                                                                                                            | **Expected Response**     |
|------------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/samsung/authInfo`                                                                           | `mobile-domain: opsnow.com`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                                  | ```json { "deviceId": <device_id>, "userNo": <user_no>, "uuid": <uuid> } ```                                               | `200 OK` with no response body |

###### **Global Domain**

| **Method** | **URL**                                                                                                | **Headers**                                                                                                                                               | **Request Body**                                                                                                            | **Expected Response**     |
|------------|--------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/api/v1/<company_portal_id>/authInfo`                                                        | `mobile-domain: opsnow.com`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>`                                                  | ```json { "deviceId": <device_id>, "userNo": <user_no>, "uuid": <uuid> } ```                                               | `200 OK` with no response body |

## **Register Device Token**

###### **Samsung Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             | **Request Body**                                                                                                            | **Expected Response**     |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/samsung/device`                                                         | `mobile-authorization: <mobile_token>`<br>`mobile-domain: <domain_name>`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>` | ```json { "deviceId": <device_id>, "pushServiceType": "FCM", "device_token": <device_token>, "brandName": <device_brand_name>, "pushUseYn": "Y", "modelName": <device_model> } ``` | `200 OK` with no response body |

###### **Global Domain**

| **Method** | **URL**                                                                                               | **Headers**                                                                                                                                             | **Request Body**                                                                                                            | **Expected Response**     |
|------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|---------------------------|
| **POST**   | `{baseUrl}/api/v1/<company_portal_id>/device`                                                         | `mobile-authorization: <mobile_token>`<br>`mobile-domain: <domain_name>`<br>`Content-Type: application/json`<br>`Authorization: Bearer <access_token>` | ```json { "deviceId": <device_id>, "pushServiceType": "FCM", "device_token": <device_token>, "brandName": <device_brand_name>, "pushUseYn": "Y", "modelName": <device_model> } ``` | `200 OK` with no response body |

## **Profile Details**

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

## **Set Push Notification Rule**

| **Method** | **URL**                                                                                     | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|---------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **POST**   | `{baseUrl}/api/v2/<company_portal_id>>/pushRule` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization:<mobile_token>`<br>`Authorization: Bearer <access_token>` |

| **Request Body Field** | **Type** | **Description**                                                              |
|------------------------|----------|------------------------------------------------------------------------------|
| `deviceId`             | String   | The unique identifier of the device.                                         |
| `device_token`         | String   | The FCM token for push notifications.                                        |
| `pushUseYn`            | String   | Indicates if push notifications are enabled (`Y` for Yes, `N` for No).       |
| `pushType`             | String   | The type of push notification (e.g., "PUSH").                                |
| `pushServiceType`      | String   | The service type used for push notifications (e.g., "FCM").                  |
| `voip_token`           | String   | The VoIP token, if applicable (can be an empty string if not used).          |

| **Response**           | **Type** | **Description**                                                              |
|------------------------|----------|------------------------------------------------------------------------------|
| 200 OK                 | None     | The request was successful, with no response body.                           |


## **Get Push Notification Rule**

| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/1/user/<user_id>/pushRule/<device_id>`                    | `Content-Type: application/json`<br>`mobile-domain: opsnow.com`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>`|

| **Response Field**     | **Type** | **Description**                                                      |
|------------------------|----------|----------------------------------------------------------------------|
| `userCntcMethNo`       | Integer  | The user's contact method number.                                    |
| `device_token`         | String   | The FCM token for push notifications.                                |
| `voip_token`           | String   | The VoIP token, if applicable (can be an empty string if not used).  |
| `pushUseYn`            | String   | Indicates if push notifications are enabled (`Y` for Yes, `N` for No).|
| `pushServiceType`      | String   | The service type used for push notifications (e.g., "FCM").          |
| `pushType`             | String   | The type of push notification (e.g., "PUSH", "WAKE").                        |
| `pushRule`             | Array    | An array of push notification rules.                                 |

| **Push Rule Field**    | **Type** | **Description**                                                      |
|------------------------|----------|----------------------------------------------------------------------|
| `eventTypeCode`        | String   | The code representing the event type.                                |
| `eventTypeName`        | String   | The name of the event type (e.g., "When incident is assigned to me").|
| `useYN`                | String   | Indicates if the rule is enabled (`Y` for Yes, `N` for No).           |
| `eventStatusCd`        | String   | The status code of the event (Only available for the "CLOSE" and "ACKNOWLEDGE").         |

## **Incident List**

| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/incident?rows=1&page=1&oidx=updatedDt:desc` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>` |

#### Response Fields

| **Field Name**      | **Type** | **Description**                                    |
|---------------------|----------|----------------------------------------------------|
| `incidentNo`        | Integer  | The unique identifier for the incident.            |
| `cIncidentNo`       | Integer  | The customer-specific incident number.             |
| `incidentName`      | String   | The name or title of the incident.                 |
| `statusCd`          | String   | The code representing the current status.          |
| `statusName`        | String   | The name of the current status.                    |
| `urgencyCd`         | String   | The code representing the urgency level.           |
| `urgencyName`       | String   | The name of the urgency level.                     |
| `createdDt`         | String   | The date and time when the incident was created.   |
| `updatedDt`         | String   | The date and time when the incident was last updated. |
| `userNo`            | Integer  | The unique identifier of the user (nullable).      |
| `userName`          | String   | The name of the user (nullable).                   |
| `serviceNo`         | Integer  | The unique identifier for the service.             |
| `serviceName`       | String   | The name of the service.                           |

#### Additional Information

| **Query Params** | **Type** | **Description**                          |
|----------------|----------|------------------------------------------|
| `total`        | Integer  | The total number of incidents.           |
| `page`         | Integer  | The current page number.                 |
| `rows`         | Integer  | The number of incidents per page.        |
| `status`         | String  | To fetch "OPEN" and "ACKNOWLEDGE " incidents. It should not be sent if it's required to fetch all incidents       |
| `oidx` | String | The value of this should be "updatedDt:desc" when sort by time and "Urgency:desc" when sort by urgency. |
| `assignYn` | String | If filter by assignee then it should be Y otherwise the filed should not present in the query params|
| `assignUserNo ` |  String | If filter by My incident then should pass the user id here, otherwise the fileld should not present in the query params |

## **Incident Detail**

| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/incident/<incident_no>`    | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>`|

### Response Fields

| **Field Name**      | **Type** | **Description**                                    |
|---------------------|----------|----------------------------------------------------|
| `incidentNo`        | Integer  | The unique identifier for the incident.            |
| `cIncidentNo`       | Integer  | The customer-specific incident number.             |
| `incidentName`      | String   | The name or title of the incident.                 |
| `statusCd`          | String   | The code representing the current status.          |
| `statusName`        | String   | The name of the current status.                    |
| `urgencyCd`         | String   | The code representing the urgency level.           |
| `urgencyName`       | String   | The name of the urgency level.                     |
| `createdDt`         | String   | The date and time when the incident was created.   |
| `updatedDt`         | String   | The date and time when the incident was last updated. |
| `currentTime`       | String   | The current date and time.                         |
| `userNo`            | Integer  | The unique identifier of the user.                 |
| `userName`          | String   | The name of the user.                              |
| `userUseYn`         | String   | Indicates if the user is active (`Y` for Yes, `N` for No). |
| `serviceNo`         | Integer  | The unique identifier for the service.             |
| `serviceName`       | String   | The name of the service.                           |
| `escalationNo`      | Integer  | The unique identifier for the escalation.          |
| `escalationName`    | String   | The name of the escalation.                        |


## **Incident Comment**


| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/incident/<incident_no>/history?type=cmmt` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>` |

#### Response Fields

| **Field Name** | **Type** | **Description**                      |
|----------------|----------|--------------------------------------|
| `historyNo`    | Integer  | The unique identifier for the history entry. |
| `eventTypeCd`  | String   | The code representing the type of incident.       |
| `textSbst`     | String   | The comment of the incident.            |
| `eventTime`    | String   | The date and time when the event occurred.    |
| `register`     | String   | The name of the person who registered the comment. |

## **Related Incident**

## API Request

| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/incident/<incident_no>/relatedIncident` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>` |

#### Response Fields

| **Field Name** | **Type** | **Description**                     |
|----------------|----------|-------------------------------------|
| `incidentNo`   | Integer  | The unique identifier for the incident. |
| `cIncidentNo`  | Integer  | The related incident number.        |
| `incidentName` | String   | The name of the related incident.   |
| `userNo`       | Integer  | The user number (if applicable).    |
| `userName`     | String   | The user name (if applicable).      |

## **Parent Incident**

| **Method** | **URL**                                                                                                   | **Headers**                                                                                                                                                                                                                                                                                                     |
|------------|-----------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **GET**    | `{baseUrl}/api/v2/<company_portal_id>/incident/<incident_no>/basedIncident` | `Content-Type: application/json`<br>`mobile-domain: <domain_name>`<br>`mobile-authorization: <mobile_token>`<br>`Authorization: Bearer <access_token>` |

#### Response Fields

| **Field Name** | **Type** | **Description**                     |
|----------------|----------|-------------------------------------|
| `incidentNo`   | Integer  | The unique identifier for the incident. |
| `cIncidentNo`  | Integer  | The related incident number.        |
| `incidentName` | String   | The name of the related incident.   |
| `userNo`       | Integer  | The user number (if applicable).    |
| `userName`     | String   | The user name (if applicable).      |












