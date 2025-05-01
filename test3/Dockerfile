# Dockerfile pour lancer le test Python d'API
FROM python:3.11-alpine

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier AuthorizationTest
COPY ContentTest.py .

# Installer les dépendances nécessaires
RUN pip install --no-cache-dir requests

# Exécuter le script Python avec les paramètres fournis
ENTRYPOINT ["python", "ContentTest.py"]
