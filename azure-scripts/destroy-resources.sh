#!/bin/bash

# Variável do grupo de recursos
RESOURCE_GROUP="rg-fiap-iottu"

# Apagar grupo de recursos (e tudo que está dentro)
az group delete --name $RESOURCE_GROUP --yes --no-wait
