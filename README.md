# Antifraud service for TechBiz demo

Listens on port 8081,

POST /antifraud

Body:
{
    "paymentType": "${paymentType}",
    "amountInCents": ${amount},
    "userId":"${userId}"
}

will return HTTP 403 for "crypto", 200 for anything else

